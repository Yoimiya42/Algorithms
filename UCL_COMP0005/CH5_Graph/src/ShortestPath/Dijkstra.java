package ShortestPath;

import WeightedGraph.*;
import java.util.*;

public class Dijkstra<T> {

	// Prerequisite: non-negative weights
	// The shortest paths from the single source to all vertices

	private final DirectedWeightedGraph<T> dwg;
	private final Map<T, T> prev;
	private final T start;

	public Dijkstra(DirectedWeightedGraph<T> dwg, T start) {
		this.dwg = dwg;
		prev = new HashMap<>();
		this.start = start;
	}

	public Map<T, Double> dijkstra(){

		Map<T, Double> distToSource = new HashMap<>();
		for (T v : dwg.getAllVertices()){
			distToSource.put(v, Double.POSITIVE_INFINITY);
			prev.put(v, null);
		}

		distToSource.put(start, 0.0);
		prev.put(start, null);
		PriorityQueue<Map.Entry<T, Double>> pq = new PriorityQueue<>(
				(a, b) -> Double.compare(a.getValue(), b.getValue())
		);

		pq.offer(new AbstractMap.SimpleEntry<>(start, 0.0));

		while (!pq.isEmpty()){
			Map.Entry<T, Double> entry = pq.poll();
			T source = entry.getKey();
			double weight = entry.getValue();

			if (weight > distToSource.get(source))
				continue;

			for (Edge<T> edge : dwg.getAdjacentEdges(source)){
				T target = edge.getTarget();
				Double newDist = distToSource.get(source) + edge.getWeight();
				Double oldDist = distToSource.get(target);
				if (newDist < oldDist){
					distToSource.put(target, newDist);
					prev.put(target, source);
					pq.add(new AbstractMap.SimpleEntry<>(target, newDist));
				}
			}

		}

		return distToSource;
	}

	public Map<List<T>, Double> getShortestPaths(){
		Map<List<T>, Double> shortestPaths = new HashMap<>();
		Map<T, Double> distToSource = dijkstra();

		for(T v : distToSource.keySet()){
			List<T> path = new ArrayList<>();
			T temp = v;
			while ( temp != null){
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

		Dijkstra<String> dijkstra = new Dijkstra<String>(graph, "S");
		System.out.println(dijkstra.dijkstra());
		System.out.println(dijkstra.getShortestPaths());
	}

}
