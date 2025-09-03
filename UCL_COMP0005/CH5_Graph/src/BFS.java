import Graph.Graph;

import java.util.*;

public class BFS<T> implements Traversal<T>{

	private final Graph<T> graph;

	private final Map<T, Integer> distToSource;
	private final Map<T , T> prev;

	public BFS(Graph<T> graph) {
		this.graph = graph;

		distToSource = new HashMap<>();
		prev = new HashMap<>();
	}

	@Override
	public void traverse(T start){
		bfs(start);
	}

	public void bfs(T v){
		Queue<T> queue = new LinkedList<>();
		queue.add(v);
		distToSource.put(v, 0);
		prev.put(v, null);


		while(! queue.isEmpty()) {
			T u = queue.remove();

			for (T adj : graph.getAdjacentVertices(u))
				if (! distToSource.containsKey(adj)){
					queue.add(adj);
					distToSource.put(adj, distToSource.get(u) + 1);
					prev.put(adj, u);
				}
		}
	}


	public boolean hasPathTo(T v) {
		return distToSource.containsKey(v);
	}

	public List<T> shortestPath(T source, T dest){
		bfs(source);

		List<T> shortestPath = new ArrayList<>();
		if (! distToSource.containsKey(dest))
			return shortestPath;

		for(T temp = dest; temp != null; temp = prev.get(temp))
			shortestPath.add(temp);

		Collections.reverse(shortestPath);
		return shortestPath;
	}

}
