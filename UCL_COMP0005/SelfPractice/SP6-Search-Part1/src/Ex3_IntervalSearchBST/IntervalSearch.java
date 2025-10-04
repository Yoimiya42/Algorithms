package Ex3_IntervalSearchBST;
import Model.Node;

import java.util.ArrayList;
import java.util.List;

/*
Exercise 3 – Interval Search in a BST
Design and implement an algorithm that, given a Binary Search Tree and two keys x
and y, finds all keys between (and excluding) x and y in the BST. The runtime should
be proportional to lg N + M, where N is the size of the BST and M is the number of
keys returned.
Hint: combine ideas from BST search and in-order traversal.
 */
public class IntervalSearch {


	public static <K extends Comparable<K>, V> List<K> intervalSearch(Node<K, V> root, K min, K max){
		List<K> results = new ArrayList<>();
		intervalSearch(results, root, min, max);
		return results;
	}

	private static <K extends Comparable<K>, V> void intervalSearch(List<K> results, Node<K, V> node, K min, K max){

		if(node == null)
			return;
		if (node.key.compareTo(min) > 0)
			intervalSearch(results, node.left, min, max);

		if (node.key.compareTo(min) > 0 && node.key.compareTo(max) < 0)
			results.add(node.key);

		if (node.key.compareTo(max) < 0)
			intervalSearch(results, node.right, min, max);
	}

	public static void main(String[] args) {

		Node<Integer, String> root = new Node<>(10, "ten");
		root.left = new Node<>(5, "five");
		root.right = new Node<>(15, "fifteen");
		root.left.left = new Node<>(2, "two");
		root.left.right = new Node<>(7, "seven");
		root.right.right = new Node<>(20, "twenty");

		// search interval: (5, 20)
		List<Integer> result = IntervalSearch.intervalSearch(root, 5, 20);
		System.out.println("Keys between 5 and 20 (exclusive): " + result);
		// Expected Output：[7, 10, 15]
	}
}
