package Graph;

import java.util.List;

public interface Graph<T> {
	void addVertex(T v);
	void addEdge(T u, T v);
	List<T> getAdjacentVertices(T v);
	List<T> getAllVertices();
}
