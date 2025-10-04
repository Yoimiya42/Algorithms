package SubString;

import java.util.HashMap;
import java.util.Map;

public class BoyerMoore {

	public boolean boyerMoore(String pattern, String text){
		Map<Character, Integer> skipForm = buildSkipForm(pattern);
		int t = 0;
		while(t < text.length() - pattern.length()){
			int p = pattern.length() - 1;
			int skip = 0;
			while(p >= 0){
				if (pattern.charAt(p) != text.charAt(t + p)){
					skip = Math.max(1, p - skipForm.getOrDefault(text.charAt(t + p), -1));
					break;
				}else{
					p--;
				}
			}

			t += skip;
			if (skip == 0)
				return true;
		}

		return false;
	}

	private Map<Character, Integer> buildSkipForm(String pattern){
		Map<Character, Integer> skipForm = new HashMap<>();
		for(int p = 0; p < pattern.length(); p++)
			skipForm.put(pattern.charAt(p), p);

		return skipForm;
	}


	public static void main(String[] args) {
		String pattern = "YOIMIYA";
		String text = "HSKDNADNCGAYOIMIYACBSDSNC";
		BoyerMoore boyerMoore = new BoyerMoore();
		System.out.println(boyerMoore.boyerMoore(pattern, text));
	}
}
