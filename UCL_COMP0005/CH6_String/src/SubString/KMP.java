package SubString;

public class KMP {

	private final int R = 256;

	public boolean kmp(String pattern, String text) {
		int[][] dfa = buildDfaForm(pattern);
		int p, t;
		for(t = 0, p = 0; t < text.length() && p < pattern.length(); t++)
			p = dfa[text.charAt(t)][p];

		if (p == pattern.length())
			return true;

		return false;
	}

	public int[][] buildDfaForm(String pattern){

		int[][] dfa = new int[R][pattern.length()];

		dfa[pattern.charAt(0)][0] = 1;
		for(int reset = 0, p = 1; p < pattern.length(); p++){
			for(int c = 0; c < R; c++)
				dfa[c][p] = dfa[c][reset];
			dfa[pattern.charAt(p)][p] = p + 1;
			reset = dfa[pattern.charAt(p)][reset];
		}

		return dfa;
	}

	public static void main(String[] args) {
		String pattern = "ABABAC";
		KMP kmp = new KMP();
		int[][] dfa = kmp.buildDfaForm(pattern);
		System.out.println(dfa);
	}
}
