import Graph.Graph;

import java.util.*;

public class TopologicalSort<T> {

	private final Graph<T> graph;

	public TopologicalSort(Graph<T> graph) {
		this.graph = graph;
	}


	/*
	******************* PostOrder ************************
	 */
	public List<T> getPostOrder(){
		Set<T> visited = new HashSet<>();
		List<T> postOrder = new ArrayList<>();

		for(T v : graph.getAllVertices()){
			if (! visited.contains(v)){
				dfs(visited, postOrder, v);
			}
		}
		return postOrder;
	}

	private void dfs(Set<T> visited, List<T> postOrder, T v){
		visited.add(v);
		for(T adj: graph.getAdjacentVertices(v)){
			if (! visited.contains(adj))
				dfs(visited, postOrder, adj);
		}

		postOrder.add(v);
	}

	/*
	********************** DAG Check ***********************
	 */
	public boolean isDAG() {
		Set<T> visited = new HashSet<>();
		Stack<T> stack = new Stack<>();
		for(T v : graph.getAllVertices()){
			if (! visited.contains(v)){
				if (dfs_hasCycle(visited, stack, v))
					return false;
			}
		}

		return true;
	}

	private boolean dfs_hasCycle(Set<T> visited, Stack<T> stack, T v){

		if (stack.contains(v))
			return true;

		if (visited.contains(v))
			return false;

		visited.add(v);
		stack.push(v);
		for(T adj : graph.getAdjacentVertices(v)){
			if (dfs_hasCycle(visited, stack, adj))
				return true;
		}

		stack.pop();
		return false;
	}


	/* --------------- TopologicalOrder (DFS based) ------------------------
	1. Check if the graph is the directed acyclic graph.
	2. Run DFS and record the post-order
	3. Reverse the post-order to get topological order
	 */
	public List<T> getTopologicalOrderByDFS() throws IllegalStateException{
		if (! isDAG())
			throw new IllegalStateException("The graph is not a directed acyclic graph.");

		List<T> postOrder = getPostOrder();
		Collections.reverse(postOrder);

		return postOrder;
	}


	/* ----------------- TopologicalOrder (In-degree based) --------------------
	1. Generate the in-degree table

	 */


	public List<T> getTopologicalOrderByInDegree() throws IllegalStateException{
		if (! isDAG())
			throw new IllegalStateException("The graph is not a directed acyclic graph.");

		List<T> topologicalOrder = new ArrayList<>();
		Map<T, Integer> inDegree = new HashMap<>();


		for(T v : graph.getAllVertices())
			inDegree.put(v, 0);

		for(T v : graph.getAllVertices()){
			for(T adj : graph.getAdjacentVertices(v))
				inDegree.put(adj, inDegree.get(adj) + 1);
		}


		Queue<T> queue = new LinkedList<>();
		for(T v : graph.getAllVertices()){
			if (inDegree.get(v) == 0)
				queue.offer(v);
		}

		while (! queue.isEmpty()) {
			T v = queue.poll();
			topologicalOrder.add(v);

			for (T adj : graph.getAdjacentVertices(v)) {
				inDegree.put(adj, inDegree.get(adj) - 1);
				if (inDegree.get(adj) == 0)
					queue.offer(adj);
			}
		}

		return topologicalOrder;
	}

}
