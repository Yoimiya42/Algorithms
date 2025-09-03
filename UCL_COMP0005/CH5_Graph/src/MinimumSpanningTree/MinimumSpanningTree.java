package MinimumSpanningTree;

import WeightedGraph.*;

import java.util.*;

public class MinimumSpanningTree<T> {


	private final UndirectedWeightedGraph<T> uwg;

	public MinimumSpanningTree(UndirectedWeightedGraph<T> udiWeightedGraph) {
		this.uwg = udiWeightedGraph;
	}

	private class UnionFind{

		Map<T, T> parent;
		Map<T, Integer> size;

		public UnionFind(){
			parent = new HashMap<>();
			size = new HashMap<>();

			for(T v : uwg.getAllVertices()){
				parent.put(v, v);
				size.put(v, 1);
			}
		}

		void union(T u, T v){
			T uParent = find(u);
			T vParent = find(v);

			if (uParent.equals(vParent))
				return;

			int uSize = size.get(uParent);
			int vSize = size.get(vParent);

			if (uSize <= vSize){
				parent.put(uParent, vParent);
				size.put(vParent, vSize + uSize);
			}else{
				parent.put(vParent, uParent);
				size.put(uParent, uSize + vSize);
			}
		}

		boolean isConnected(T u , T v){
			return find(u).equals(find(v));
		}

		T find(T u){
			if (! parent.get(u).equals(u))
				parent.put(u, find(parent.get(u)));
			return parent.get(u);
		}
	}


	public List<Edge<T>> kruskal(){
		List<Edge<T>> mst = new ArrayList<>();
		PriorityQueue<Edge<T>> edges_pq = new PriorityQueue<>();
		UnionFind uf = new UnionFind();

		List<Edge<T>> edges = uwg.getAllEdges();
		edges_pq.addAll(edges);

		while(! edges_pq.isEmpty()){
			Edge<T> e = edges_pq.poll();

			T source = e.getSource();
			T target = e.getTarget();
			if (! uf.isConnected(source, target)){
				mst.add(e);
				uf.union(source, target);
			}

			if (mst.size() >= uwg.getAllVertices().size() - 1)
				break;
		}

		return mst;
	}

	public List<Edge<T>> prim_lazy(){
		List<Edge<T>> mst = new ArrayList<Edge<T>>();
		Set<T> visited = new HashSet<T>();
		PriorityQueue<Edge<T>> edges_pq = new PriorityQueue<>();
		T start = getRandomStart();

//		System.out.println("Start: " + start);
		visited.add(start);
		edges_pq.addAll(uwg.getAdjacentEdges(start));

		while(! edges_pq.isEmpty()) {
			Edge<T> e = edges_pq.poll();
			T source = e.getSource();
			T target = e.getTarget();
			if(visited.contains(target))
				continue;

			visited.add(target);
			mst.add(e);

			for(Edge<T> edge : uwg.getAdjacentEdges(target)) {
				if (!visited.contains(edge.getTarget()))
					edges_pq.add(edge);
			}
		}

		return mst;
	}

	 private T getRandomStart(){
		List<T> vertices = uwg.getAllVertices();

		Random rand = new Random();
		return vertices.get(rand.nextInt(vertices.size()));
	 }



	 public static void main(String[] args) {
		 UndirectedWeightedGraph<Integer> graph = new UndirectedWeightedGraph<Integer>();

		 // 添加城市（顶点）
		 int[] cities = {0,1,2,3,4,5,6,7};
		 for (int city : cities) {
			 graph.addVertex(city);
		 }

		 graph.addEdge(0, 2,  0.26); //#
		 graph.addEdge(0, 6,  0.58);
		 graph.addEdge(4, 0,  0.38);
		 graph.addEdge(6, 2,  0.40); //#
		 graph.addEdge(7, 0,  0.16); //#
		 graph.addEdge(7, 2,  0.36);
		 graph.addEdge(3, 2,  0.17); //#
		 graph.addEdge(3, 1,  0.29);
		 graph.addEdge(1, 2,  0.34);
		 graph.addEdge(1, 7,  0.19);//#
		 graph.addEdge(1, 5,  0.42);
		 graph.addEdge(7, 5,  0.28); //#
		 graph.addEdge(4, 5,  0.35);//#
		 graph.addEdge(4, 7,  0.37);
		 graph.addEdge(4, 6,  0.93);


		 MinimumSpanningTree<Integer> mst = new MinimumSpanningTree<Integer>(graph);


		 List<Edge<Integer>> mstPrim = mst.prim_lazy();
		 List<Edge<Integer>> mstKruskal = mst.kruskal();
		 System.out.println("Prim: " + mstPrim);
		 System.out.println("Kruskal: " + mstKruskal);

		 /*
		 Start: 3
		 MST边: [3 -> 2 (0.17), 2 -> 0 (0.26), 0 -> 7 (0.16), 7 -> 1 (0.19), 7 -> 5 (0.28), 5 -> 4 (0.35), 2 -> 6 (0.4)]

		Start: 4
		MST边: [4 -> 5 (0.35), 5 -> 7 (0.28), 7 -> 0 (0.16), 7 -> 1 (0.19), 0 -> 2 (0.26), 2 -> 3 (0.17), 2 -> 6 (0.4)]
		  */
	 }



}
