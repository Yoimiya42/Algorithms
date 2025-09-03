public class CH7_StringSort {
    private static final int R = 256; // radix (extended ASCII)
    private static String[] aux;

    public void LSD(String[] strings, int stringLength)
    {
        int arrayLength = strings.length;
        int alphabetSize = 256; // Extended ASCII
        String[] aux = new String[arrayLength];

        // Least Significant Digit First (LSD) sorting
        // Sort by each character from right to left
        // Prerequisite: All strings have the same length
        for(int digit = stringLength - 1; digit >= 0; digit--)
        {
            int[] count = new int[alphabetSize + 1];//257

            // 1. Compute frequency of each character
            for(int i = 0; i < arrayLength; i++)
                count[strings[i].charAt(digit) + 1]++; 
            
            // 2. Transform counts to partition(ranks) indices
            for (int r = 0; r < alphabetSize; r++)
                count[r+1] += count[r];

            // 3. Distribute the strings into the auxiliary array
            for (int i = 0; i < arrayLength; i++)
                aux[count[strings[i].charAt(digit)]++] = strings[i];

            // 4. Copy back to the original array
            System.arraycopy(aux, 0, strings, 0, arrayLength);
        }
    }


    private int charAt(String str, int digit){
        if (digit < str.length())
            return str.charAt(digit);
        else
            return -1;
    }

    public void MSD(String[] strings) {
        int N = strings.length;
        aux = new String[N];
        MSD(strings, 0, N-1, 0);
    }
    
    public void MSD(String[] strings, int lo, int hi, int d){
        if(lo >= hi) return; // Base case: if the array is empty or has one element
        int count[] = new int[R + 2]; // Initialize count array for each character

        // 1. Key-indexed counting
        // Compute frequency of each character
        for(int i = lo; i <= hi; i++)
            count[charAt(strings[i], d) + 2]++;

        // Transform counts to indices
        for(int r = 0; r < R + 1; r++)
            count[r + 1] += count[r]; 

        // Distribute the strings into the auxiliary array
        for(int i = lo; i <= hi; i++)
            aux[count[charAt(strings[i], d) + 1]++] = strings[i];

        // Copy back to the original array
        for(int i = lo; i <= hi; i++)
            strings[i] = aux[i - lo]; // Adjust index based on the count array
        
        // 2. Recursively sort for each character value.
        for(int r = 0; r < R; r++)
            MSD(strings, lo + count[r], lo + count[r + 1] - 1, d + 1); 
    }



    public static void main(String[] args) {
        String[] strings = {"ACBDEF", "ABCCDE", "BFCDEA", "DBFADE", "ACACBF"};
        int stringLength = 6; // Length of the longest string

        CH7_StringSort sorter = new CH7_StringSort();
        sorter.LSD(strings, stringLength);

        for (String str : strings) {
            System.out.println(str);
        }
    }
}
