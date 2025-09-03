package WeightedGraph;

public class UndirectedWeightedGraph<T> extends AbstractWeightedGraph<T>{

	public UndirectedWeightedGraph() {
		super();
	}


	@Override
	public void addEdge(T v, T u, double weight) {
		addVertex(v);
		addVertex(u);
		adjacencyMap.get(v).add(new Edge<>(v, u, weight));
		adjacencyMap.get(u).add(new Edge<>(u, v, weight));
	}
}
