/*
	Completed by Kevin Luan and executed successfully at 21:28 on Sept 10th 2025, in London.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Ex2_PatternSearch
{
	private final int R = 256;

	private final String text;
	private final int T;
	private final String pattern;
	private final int P;

	private final Map<Character, Integer> skipForm = new HashMap<>();

	public Ex2_PatternSearch(String text, String pattern) {
		this.text = text;
		T = text.length();
		this.pattern = pattern;
		P = pattern.length();
	}

	private void generateSkipForm(){
		for(int i = 0; i < P; i++ )
			skipForm.put(pattern.charAt(i),  i);
	}

	public int boyerMoore(){
		generateSkipForm();

		int t = 0;
		int count = 0;

		while(t <= T - P){
			int p = P - 1;
			int skip = 0;

			while (p >= 0 && pattern.charAt(p) == text.charAt(t + p))
				p--;

			if (p < 0){
				count++;
				t += P;
			}else {
				skip = Math.max(1, skipForm.getOrDefault(text.charAt(p), -1));
				t += skip;
			}
		}

		return count;
	}
	
	public static void main(String[] args) {
		String filePath = "data/Moby Dict.txt";
		String pattern = "the";

		StringBuilder textBuilder = new StringBuilder();
		try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
			String line;
			while((line = br.readLine()) != null){
				textBuilder.append(line).append(" ");
			}
		} catch (IOException e){
			e.printStackTrace();
		}

		String text = textBuilder.toString();
		Ex2_PatternSearch ex = new Ex2_PatternSearch(text, pattern);
		System.out.println(ex.boyerMoore()); // 18516
	}
}





