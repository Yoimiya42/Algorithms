package Graph;

public class UndirectedGraph<T> extends AbstractGraph<T> {

	public UndirectedGraph(){
		super();
	}

	@Override
	public void addEdge(T u, T v){
		addVertex(u);
		addVertex(v);
		adjacencyList.get(u).add(v);
		adjacencyList.get(v).add(u);
	}


}
