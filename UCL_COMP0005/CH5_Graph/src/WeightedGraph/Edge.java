package WeightedGraph;

public class Edge<T> implements Comparable<Edge<T>> {

	private T source;
	private T target;
	private double weight;

	public Edge(T source, T target, double weight) {
		this.source = source;
		this.target = target;
		this.weight = weight;
	}


	public T getSource()    {return source;}

	public T getTarget()    {return target;}

	public double getWeight()   {return weight;}

	@Override
	public String toString() {
		return source + " -> " + target + " (" + weight + ")";
	}

	@Override
	public int compareTo(Edge<T> e) {
		return Double.compare(this.weight, e.weight);
	}
}
