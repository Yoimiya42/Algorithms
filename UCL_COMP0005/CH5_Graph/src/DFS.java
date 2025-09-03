import Graph.Graph;

import java.util.*;

public class DFS<T> implements Traversal<T>{

	private final Graph<T> graph;

	private final Set<T> visited;
	private final Map<T, T> prev;

	public DFS(Graph<T> graph){
		this.graph = graph;

		visited = new HashSet<T>();
		prev = new HashMap<>();
	}

	@Override
	public void traverse(T start){
		dfs(start);
	}

	public void dfs(T v) {
		visited.add(v);

		for(T adj : graph.getAdjacentVertices(v)){
			if(!visited.contains(adj)){
				dfs(adj);
				prev.put(adj, v);
			}
		}
	}

	public boolean hasPathTo(T v){
		return visited.contains(v);
	}

}
