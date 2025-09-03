package StringSort;

public class MostSignificantDigitFirstSort {

	private final int R = 256;
	private String[] aux;

	private int charAt(String str, int digit) {
		if (digit < str.length())
			return str.charAt(digit);
		else
			return -1;
	}
	public void MSD(String[] strings){
		int stringNum = strings.length;
		aux = new String[stringNum];
		MSD(strings, 0, stringNum - 1, 0);
	}

	public void MSD(String[] strings, int start, int end, int digit){
		if (start >= end) // If the subarray is empty or has only one element
			return;

		int[] count = new int[R + 2];
		for(int i = start; i < end; i++)
			count[charAt(strings[i], digit) + 2]++;

		for(int i = 0; i < R + 1; i++)
			count[i+1] += count[i];

		for(int i = start; i <= end; i++)
			aux[count[charAt(strings[i], digit) + 1]++] = strings[i];

		for(int i = start; i <= end; i++)
			strings[i] = aux[i - start];

		for(int r = 0; r < R; r++)
			MSD(strings, start + count[r], start + count[r] - 1, digit + 1);
	}
}
