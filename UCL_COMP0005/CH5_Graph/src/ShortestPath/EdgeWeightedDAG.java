package ShortestPath;

import WeightedGraph.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EdgeWeightedDAG<T> {

	private DirectedWeightedGraph<T> graph;
	private TopologicalSort<T> topologicalSort;

	public EdgeWeightedDAG(DirectedWeightedGraph<T> dwg) {
		graph = dwg;
		topologicalSort = new TopologicalSort<T>(graph);
	}

	// Prerequisite: Directed Weighted Graph is Acyclic (DAG)

	public Map<T, Double> edgeWeightedDAG() {
		List<T> topologicalOrder = topologicalSort.getTopologicalOrderByDFS();

		Map<T, T> prev = new HashMap<>();
		Map<T, Double> distToSource = new HashMap<T, Double>();
		for(T v : graph.getAllVertices()){
			distToSource.put(v, Double.POSITIVE_INFINITY);
			prev.put(v, null);
		}
		distToSource.put(topologicalOrder.get(0), 0.0);

		for(T v: topologicalOrder){
			for(Edge<T> e : graph.getAdjacentEdges(v)){
				T target = e.getTarget();
				double weight = e.getWeight();

				double newDist = distToSource.get(v) + weight;
				double oldDist = distToSource.get(target);

				if (newDist < oldDist) {
					distToSource.put(target, newDist);
					prev.put(target, v);
				}
			}
		}

		return distToSource;
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

		EdgeWeightedDAG<String> acyclic_sp = new EdgeWeightedDAG<String>(graph);
		System.out.println(acyclic_sp.edgeWeightedDAG());
	}
}
