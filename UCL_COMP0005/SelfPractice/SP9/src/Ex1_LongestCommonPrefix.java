public class Ex1_LongestCommonPrefix {

	private class Node{
		char ch;
		boolean isEnd;
		Node left, middle, right;

		Node(char ch) {
			this.ch = ch;
		}
	}

	public String findLongestCommonPrefix(String[] words) {
		if (words == null || words.length == 0) 
			return "";
		for (String str : words){
			if (str == null || str.length() == 0)
				return "";
		}
		StringBuilder longestCommonPrefix = new StringBuilder();
		Node root = buildTernaryTrie(words);

		Node temp = root;

		while(temp != null && temp.left == null && temp.right == null) {
			longestCommonPrefix.append(temp.ch);
			if (temp.isEnd)
				break;
				
			temp = temp.middle;
		}

		return longestCommonPrefix.toString();
	}

	private Node buildTernaryTrie(String[] strs){
		Node root = null;
		for (String str : strs) {
			root = put(root, str, 0);
		}

		return root;
	}

	private Node put(Node node, String str, int digit){
		char ch = str.charAt(digit);
		if (node == null)
			node = new Node(ch);

		if (ch < node.ch) 
			node.left = put(node.left, str, digit);
		else if (ch > node.ch)
			node.right = put(node.right, str, digit);
		else if (digit < str.length() - 1)
			node.middle = put(node.middle, str, digit + 1);
		else
			node.isEnd = true;

		return node;
	}



	public static void main(String[] args) {
		// String[] words_set1 = {"apple", "appointment", "appendix", "appeal", "apparel"}; // "app"
		// String[] words_set2 = {"prerequisite", "prevent", "pressure", "present", "predict", "premium", "precise", "prepare", "preface", "previous"}; // "pre"
		// String[] words_set3 = {"omit", "prelude", "liability"}; // ""
		// String[] words_set4 = {"automatic", "automobile", "autonomy", "author"}; // "aut"
		String[] words_set5 = {"a", "b"}; // ""


		Ex1_LongestCommonPrefix lcp = new Ex1_LongestCommonPrefix();
		// System.out.println("Longest Common Prefix: " + lcp.findLongestCommonPrefix(words_set1));
		// System.out.println("Longest Common Prefix: " + lcp.findLongestCommonPrefix(words_set2));
		// System.out.println("Longest Common Prefix: " + lcp.findLongestCommonPrefix(words_set3));
		// System.out.println("Longest Common Prefix: " + lcp.findLongestCommonPrefix(words_set4));
		System.out.println("Longest Common Prefix: " + lcp.findLongestCommonPrefix(words_set5));
	}
}
