package Model;

public class Node<K extends Comparable<K>, V> {

	public K key;
	public V value;
	public Node<K, V> left, right;

	public Node(K key, V value){
		this.key = key;
		this.value = value;
	}
}
