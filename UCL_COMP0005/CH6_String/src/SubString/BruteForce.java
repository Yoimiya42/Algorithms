package SubString;

public class BruteForce {


	public static int isSubstring(String pattern, String text){

		int textLen = text.length();
		int patternLen = pattern.length();

		for(int t = 0; t <= textLen - patternLen ; t++){
			int p;
			for(p = 0; p < pattern.length(); p++)
				if (pattern.charAt(p) != text.charAt(t + p)) break;
			if (p == patternLen)
				return t;
		}
		return -1;
	}
}
