package WeightedGraph;

import Graph.Graph;

import java.util.List;

public interface WeightedGraph<T> extends Graph<T> {

	// void addVertex(T v);
	// void getAllVertices();

	void addEdge(T v, T w, double weight);
	List<Edge<T>> getAdjacentEdges(T v);
	List<Edge<T>> getAllEdges();
}
