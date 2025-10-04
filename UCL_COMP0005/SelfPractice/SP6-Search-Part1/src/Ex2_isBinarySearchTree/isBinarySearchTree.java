package Ex2_isBinarySearchTree;
import Model.Node;

/*
Exercise 2 – Is a Binary Tree a Binary Search Tree?

Consider the Binary Search Tree data structure seen at lectures to realise the Symbol
Table ADT. Design and implement an algorithm that, given as argument a Node (with
a value and a pair of left/right children nodes), determines if this is the root of a binary
search tree and returns True if so, or False otherwise.
Hint: design a recursive function isBST(node_x, min_key, max_key) that
determines whether node_x is the root of a binary search tree with all keys
between min_key and max_key.
 */

public class isBinarySearchTree<K extends Comparable<K>, V> {


	public boolean isBST(Node<K, V> root) {
		return isBST(root, null, null);
	}

	private boolean isBST(Node<K, V> node, K min_key, K max_key){
		if(node == null)
			return true;

		if(min_key != null && node.key.compareTo(min_key) <= 0)
			return false;
		if(max_key != null && node.key.compareTo(max_key) >= 0)
			return false;

		return isBST(node.left, min_key, node.key) &&
				isBST(node.right, node.key, max_key);
	}

	public static void main(String[] args) {
		// Example 1: A valid BST
		int v = 1;
		Node root1 = new Node(10, v);
		root1.left = new Node(5, v);
		root1.right = new Node(15, v);
		root1.left.left = new Node(3, v);
		root1.left.right = new Node(7, v);
		root1.right.left = new Node(12, v);
		root1.right.right = new Node(18, v);

		isBinarySearchTree tree1 = new isBinarySearchTree();
		System.out.println("Tree 1 is a BST: " + tree1.isBST(root1)); // Expected: true

		// Example 2: An invalid BST
		Node root2 = new Node(10, v);
		root2.left = new Node(5, v);
		root2.right = new Node(15, v);
		root2.left.left = new Node(3, v);
		root2.left.right = new Node(12, v); // Invalid: 12 is in the left subtree of 10.

		isBinarySearchTree tree2 = new isBinarySearchTree();
		System.out.println("Tree 1 is a BST: " + tree2.isBST(root2)); // Expected: true
	}
}

