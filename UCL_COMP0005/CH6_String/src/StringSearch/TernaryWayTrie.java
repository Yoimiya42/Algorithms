package StringSearch;

import java.util.HashSet;
import java.util.Set;

public class TernaryWayTrie<V> implements SymbolTable<String, V> {

	private final int R = 256;
	private Node<V> root;

	private class Node<V>{
		char c;
		V value;
		Node<V> left;
		Node<V> middle;
		Node<V> right;

		Node(char c){
			this.c = c;
		}
	}



	@Override
	public void put(String key, V value) {
		root = put(root, key, value, 0);
	}

	private Node<V> put(Node<V> node, String key, V value, int digit){
		char ch = key.charAt(digit);
		if (node == null)
			node = new Node<>(ch);

		if (digit == key.length() - 1){
			node.value = value;
			return node;
		}

		if (ch < node.c)
			node.left = put(node.left, key, value, digit);
		else if (ch > node.c)
			node.right = put(node.right, key, value, digit);
		else
			node.middle = put(node.middle, key, value, digit + 1);

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

		if (digit == key.length() - 1)
			return node;

		char ch = key.charAt(digit);
		if (ch < node.c)
			return get(node.left, key, digit);
		else if (ch > node.c)
			return get(node.right, key, digit);
		else
			return get(node.middle, key, digit + 1);
	}

	@Override
	public Set<String> keySet() {
		Set<String> set = new HashSet<String>();
		collect(root, set, new StringBuilder());
		return set;
	}

	private void collect(Node<V> node, Set<String> set, StringBuilder prefix){
		if (node == null)
			return;

		collect(node.left, set, prefix);

		StringBuilder newPrefix = new StringBuilder(prefix).append(node.c);
		if (node.value != null)
			set.add(newPrefix.toString());
		collect(node.middle, set, newPrefix);

		collect(node.right, set, prefix);
	}


	@Override
	public Set<String> keysWithPrefix(String prefix) {
		Set<String> set = new HashSet<>();
		Node<V> prefixNode = get(root, prefix, 0);

		if (prefixNode == null)
			return set;

		// prefix itself is a key
		if (prefixNode.value != null && !prefix.isEmpty())
			set.add(prefix);

		collect(prefixNode, set, new StringBuilder(prefix));
		return set;
	}

	@Override
	public String longestPrefixOf(String query) {

		Node<V> node = root;
		int length = 0;
		int i = 0;

		while (node != null && i < query.length()) {
			char ch = query.charAt(i);
			if (ch < node.c)
				node = node.left;
			else if (ch > node.c)
				node = node.right;
			else{
				i++;
				if (node.value != null)
					length = i;
				node = node.middle;
			}
		}

		return query.substring(0, length);
	}
}
