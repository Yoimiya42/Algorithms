package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractGraph<T> implements Graph<T> {

	protected Map<T, List<T>> adjacencyList;

	public AbstractGraph() {
		adjacencyList = new HashMap<>();
	}

	@Override
	public void addVertex(T v) {
		adjacencyList.putIfAbsent(v, new ArrayList<>());
	}

	@Override
	public List<T> getAdjacentVertices(T v) {
		return adjacencyList.getOrDefault(v, new ArrayList<>());
	}

	@Override
	public List<T> getAllVertices() {
		return new ArrayList<>(adjacencyList.keySet());
	}


}
