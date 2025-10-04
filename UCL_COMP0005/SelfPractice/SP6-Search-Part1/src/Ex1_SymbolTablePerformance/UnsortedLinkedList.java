package Ex1_SymbolTablePerformance;

public class UnsortedLinkedList<K extends Comparable<K>, V> implements SymbolTable<K, V>{
	private Node head;

	private class Node{
		K key;
		V value;
		Node next;

		Node(K key, V Value){
			this.key = key;
			this.value = Value;
		}
	}

	public UnsortedLinkedList(){
		head = null;
	}

	/*
	Time Complexity:O(n)
	 */
	@Override
	public void put(K key, V value) {
		for(Node curr = head; curr != null; curr = curr.next){
			if (curr.key.equals(key)){
				curr.value = value;
				return;
			}
		}

		Node node = new Node(key, value);
		node.next = head;
		head = node;
	}

	/*
	Time Complexity:O(n)
	 */
	@Override
	public V get(K key) {
		for(Node curr = head; curr != null; curr = curr.next){
			if (curr.key.equals(key))
				return curr.value;
		}

		return null;
	}
}
