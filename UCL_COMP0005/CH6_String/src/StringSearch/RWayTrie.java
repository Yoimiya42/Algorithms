package StringSearch;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RWayTrie<V> implements SymbolTable<String, V>{
	private final static int R = 256;
	private Node<V> root;

	private static class Node<V>{
		V value;
		Node<V>[] next;

		Node(){
			next = new Node[R];
		}
	}


	@Override
	public void put(String key, V value) {
		root = put(root, key, value, 0);
	}

	private Node<V> put(Node<V> node ,String key, V value, int digit){
		if (node == null)
			node = new Node<V>();

		if (digit == key.length()){
			node.value = value;
			return node;
		}

		char ch = key.charAt(digit);
		node.next[ch] = put(node.next[ch], key, value, digit + 1);

		return node;
	}


	@Override
	public V get(String key) {
		Node<V> node = get(root, key, 0);
		return node == null? null : node.value;
	}

	private Node<V> get(Node<V> node, String key, int digit){
		if (node == null)
			return null;

		if (digit == key.length())
			return node;

		char ch = key.charAt(digit);
		return get(node.next[ch], key, digit + 1);
	}

	@Override
	public Set<String> keySet() {
		return null;
	}


	@Override
	public Set<String> keysWithPrefix(String prefix) {
		return Set.of();
	}

	@Override
	public String longestPrefixOf(String key) {
		return "";
	}
}
