package StringCompression;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {
	private static class Node implements Comparable<Node>{
		char ch;
		int freq;
		Node left, right;

		Node(char ch , int freq, Node left, Node right) {
			this.ch = ch;
			this.freq = freq;
			this.left = left;
			this.right = right;
		}

		public boolean isLeaf(){
			return this.left == null && this.right == null;
		}

		@Override
		public int compareTo(Node n){
			return this.freq - n.freq;
		}
	}

	public String compress(String str){
		Node root = constructHuffmanTrie(str);

		Map<Character, String> huffmanCodes = new HashMap<>();
		constructHuffmanCodes(root, huffmanCodes, "");

		StringBuilder compressed = new StringBuilder();
		for(char ch : str.toCharArray())
			compressed.append(huffmanCodes.get(ch));

		return compressed.toString();
	}

	private Node constructHuffmanTrie(String str) {

		// Compute the frequency of each char
		Map<Character, Integer> charFrequencies = new HashMap<>();
		for(char c : str.toCharArray())
			charFrequencies.put(c, charFrequencies.getOrDefault(c, 0) + 1);

		PriorityQueue<Node> pq = new PriorityQueue<>();
		for(char ch : charFrequencies.keySet())
			pq.offer(new Node(ch, charFrequencies.get(ch), null, null));

		while(pq.size() > 1){
			Node x = pq.poll();
			Node y = pq.poll();
			pq.offer(new Node('\0', x.freq + y.freq, x, y));
		}

		return pq.poll();
	}

	private void constructHuffmanCodes(Node node, Map<Character, String> huffmanCodes, String code) {
		if (node == null)
			return;

		if (node.isLeaf())
			huffmanCodes.put(node.ch, code);

		constructHuffmanCodes(node.left, huffmanCodes, code + "0");
		constructHuffmanCodes(node.right, huffmanCodes, code + "1");
	}

	public String encodingHuffmanTrie(String str){
		Node root = constructHuffmanTrie(str);
		StringBuilder encodedTrie = new StringBuilder();
		encodingHuffmanTrie(root, encodedTrie);

		return encodedTrie.toString();
	}

	private void encodingHuffmanTrie(Node node, StringBuilder encodedTrie){
		if (node == null)
			return;

		if (node.isLeaf()){
			encodedTrie.append('1');
			encodedTrie.append(Integer.toBinaryString(node.ch));
			return;
		}

		encodedTrie.append('0');
		encodingHuffmanTrie(node.left, encodedTrie);
		encodingHuffmanTrie(node.right, encodedTrie);
	}



	public static void main(String[] args) {
		System.out.println(Integer.toBinaryString('?'));

		System.out.println((char)Integer.parseInt("1100001", 2));
	}
}
