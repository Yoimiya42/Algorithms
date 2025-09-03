package StringSort;


/*
Prerequisite: All string have the same length.
*/

public class LeastSignificantDigitFirstSort {

	private final int R = 256; // ASCII

	private int charAt(String str, int digit) {
		if (digit < str.length())
			return str.charAt(digit);
		else
			return -1;
	}

	public void LSD(String[] strings){

		int stringNum = strings.length;
		int strLength = strings[0].length(); // All string has the same length.
		String[] aux = new String[stringNum];

		for(int digit = strLength - 1; digit >= 0; digit--)
		{
			// Compute the frequency of each char
			int[] count = new int[R + 1];
			for(int i = 0; i < stringNum; i++)
				count[charAt(strings[i], digit) + 1]++;//Offset by +1

 			// Transform counts to partition(ranks) indices
			for(int i = 0; i <= R; i++)
				count[i+1] += count[i];

			// Distribute the strings into the aux array
			for(int i = 0; i < stringNum; i++)
				aux[count[charAt(strings[i], digit)]++] = strings[i];

			// Copy back to the original array
			for(int i = 0; i < stringNum; i++)
				strings[i] = aux[i];
		}

	}

}
