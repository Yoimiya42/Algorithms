import Graph.Graph;
import Graph.DirectedGraph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StrongConnectivity<T> {

	private Graph<T> graph;

	public StrongConnectivity(Graph<T> graph) {
		this.graph = graph;
	}

	/* ---------- Public APIs --------------------------
	 */

	/*
	1. Get the reverse graph(G^R) of the original graph.
	2. Run DFS in G^R to get the postOrder;
	3. Run DFS in G in postOrder.
	 */
	public Map<T, Integer> getStronglyConnectedComponents() {
		Graph<T> reversedGraph = getReversedGraph();
		List<T> postOrder = new TopologicalSort<T>(reversedGraph).getPostOrder();
		return assignComponents(postOrder);
	}

	public boolean areStronglyConnected(T u, T v){
		Map<T, Integer> components = getStronglyConnectedComponents();
		return components.get(u).equals(components.get(v));
	}


	/* ----------- Private Methods -------------------------
	 */
	private Graph<T> getReversedGraph() {
		Graph<T> reversedGraph = new DirectedGraph<T>();

		for(T v : graph.getAllVertices()){
			for(T u : graph.getAdjacentVertices(v)){
				reversedGraph.addEdge(u, v);
			}
		}

		return reversedGraph;
	}

	private Map<T, Integer> assignComponents(List<T> postOrder) {

		Map<T, Integer> components = new HashMap<>();
		for(T v : graph.getAllVertices())
			components.put(v, 0);

		int componentsId = 1;
		for(T v: postOrder){
			if (components.get(v) == 0){
				dfs(v, componentsId, components);
				componentsId++;
			}
		}

		return components;
	}

	private void dfs(T v, int componentsId, Map<T, Integer> components) {
		components.put(v, componentsId);

		for(T adj : graph.getAdjacentVertices(v)){
			if (components.get(adj) == 0){
				dfs(adj, componentsId, components);
			}
		}
	}

}
