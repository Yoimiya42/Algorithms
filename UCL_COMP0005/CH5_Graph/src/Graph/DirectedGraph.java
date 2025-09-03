package Graph;

import java.util.ArrayList;

public class DirectedGraph<T> extends AbstractGraph<T> {

	public DirectedGraph(){
		super();
	}

	@Override
	public void addEdge(T u, T v) {
		addVertex(u);
		addVertex(v);
		adjacencyList.get(u).add(v);
	}

}
