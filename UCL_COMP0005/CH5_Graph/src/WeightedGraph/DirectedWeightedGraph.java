package WeightedGraph;

public class DirectedWeightedGraph<T> extends AbstractWeightedGraph<T> {

	public DirectedWeightedGraph() {
		super();
	}


	@Override
	public void addEdge(T v, T u, double weight) {
		addVertex(v);
		addVertex(u);
		adjacencyMap.get(v).add(new Edge<>(v, u, weight));
	}
}
