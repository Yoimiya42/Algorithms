package ShortestPath;

import WeightedGraph.*;


import java.util.*;

public class BellmanFord<T> {

	private final DirectedWeightedGraph<T> graph;
	private final Map<T, T> prev;
	private final T start;

	public BellmanFord(DirectedWeightedGraph<T> dwg, T start) {
		this.graph = dwg;
		this.prev = new HashMap<>();
		this.start = start;
	}

	// Prerequisite: No negative cycles
	public Map<T, Double> bellmanFord() {
		Map<T, Double> distToSource = new HashMap<T, Double>();
		for(T v : graph.getAllVertices()){
			distToSource.put(v, Double.POSITIVE_INFINITY);
			prev.put(v, null);
		}
		distToSource.put(start, 0.0);

		int verticesNum = graph.getAllVertices().size();
		// V-1 iterations
		for(int i = 0; i < verticesNum; i++){
			if (! relaxEdges(distToSource, prev))
				break;
		}

		// one more iteration to check negative cycle
		if (relaxEdges(distToSource, prev))
			throw new IllegalStateException("The graph has negative cycle.");

		return distToSource;
	}

	private boolean relaxEdges(Map<T, Double> distToSource, Map<T, T> prev) {
		boolean updated = false;

		for(Edge<T> e : graph.getAllEdges()){
			T source = e.getSource();
			T target = e.getTarget();
			double weight = e.getWeight();

			double oldDist = distToSource.get(target);
			double newDist = distToSource.get(source) + weight;
			if (newDist < oldDist) {
				distToSource.put(target, newDist);
				prev.put(target, source);

				updated = true;
			}
		}

		return updated;
	}

	public Map<List<T>, Double> getShortestPaths() {
		Map<List<T>, Double> shortestPaths = new HashMap<>();
		Map<T, Double> distToSource = bellmanFord();

		for(T v : graph.getAllVertices()){
			List<T> path = new ArrayList<T>();
			T temp = v;
			while(temp != null){
				path.add(temp);
				temp = prev.get(temp);
			}
			Collections.reverse(path);
			shortestPaths.put(path, distToSource.get(v));
		}

		return shortestPaths;
	}


	public static void main(String[] args) {
		DirectedWeightedGraph<String> graph = new DirectedWeightedGraph<String>();

		graph.addEdge("S", "A", 5.0);
		graph.addEdge("S", "D", 9.0);
		graph.addEdge("S", "G", 8.0);
		graph.addEdge("A", "C", 15.0);
		graph.addEdge("A", "B", 12.0);
		graph.addEdge("A", "G", 4.0);
		graph.addEdge("B", "C", 3.0);
		graph.addEdge("B", "F", 11.0);
		graph.addEdge("C", "F", 9.0);
		graph.addEdge("D", "E", 4.0);
		graph.addEdge("D", "G", 5.0);
		graph.addEdge("D", "F", 20.0);
		graph.addEdge("E", "B", 1.0);
		graph.addEdge("E", "F", 13.0);
		graph.addEdge("G", "B", 7.0);
		graph.addEdge("G", "E", 6.0);

		BellmanFord<String> bf = new BellmanFord<String>(graph, "S");
		System.out.println(bf.bellmanFord());
		System.out.println(bf.getShortestPaths());
	}
}
