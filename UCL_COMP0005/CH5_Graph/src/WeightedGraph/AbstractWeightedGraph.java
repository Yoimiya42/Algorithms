package WeightedGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWeightedGraph<T>  implements WeightedGraph<T> {

	protected Map<T, List<Edge<T>>> adjacencyMap;

	public AbstractWeightedGraph() {
		adjacencyMap = new HashMap<>();
	}

	@Override
	public void addVertex(T v) {
		adjacencyMap.putIfAbsent(v, new ArrayList<>());
	}

	@Override
	public List<T> getAllVertices() {
		return new ArrayList<>(adjacencyMap.keySet());
	}

	@Override
	public List<Edge<T>> getAllEdges() {
		List<Edge<T>> allEdges = new ArrayList<>();
		for(List<Edge<T>> edges : adjacencyMap.values())
			allEdges.addAll(edges);
		return allEdges;
	}

	@Override
	public List<T> getAdjacentVertices(T v) {
		List<T> adjacentVertices = new ArrayList<>();
		List<Edge<T>> edges = getAdjacentEdges(v);
		for(Edge<T> e : edges){
			T source = e.getSource();
			T target = e.getTarget();
			adjacentVertices.add(v.equals(source) ? target : source);
		}

		return adjacentVertices;
	}

	@Override
	public List<Edge<T>> getAdjacentEdges(T v) {
		return new ArrayList<>(adjacencyMap.getOrDefault(v, new ArrayList<>()));
	}


	@Override
	public void addEdge(T u, T v) {}

}
