package Ex1_SymbolTablePerformance;

import java.util.Random;

public class PerformanceTest {

	private static void testPerformance(int n){

		UnsortedLinkedList<Integer, Integer> unsortedLinkedlist = new UnsortedLinkedList<>();
		SortedArray<Integer, Integer> sortedArray = new SortedArray<>(1024);
		Random random = new Random();

		long start, end;

		// Put
		start = System.nanoTime();
		for(int i = 0; i < n; i++)
			unsortedLinkedlist.put(random.nextInt(), random.nextInt());
		end = System.nanoTime();
		long unsortedLinkedListTime_Put = end - start;

		start = System.nanoTime();
		for(int i = 0; i < n; i++)
			sortedArray.put(random.nextInt(), random.nextInt());
		end = System.nanoTime();
		long sortedArrayTime_Put = end - start;

		// Get
		start = System.nanoTime();
		for(int i = 0; i < n; i++)
			unsortedLinkedlist.get(random.nextInt());
		end = System.nanoTime();
		long unsortedLinkedListTime_Get = end - start;

		start = System.nanoTime();
		for(int i = 0; i < n; i++)
			sortedArray.get(random.nextInt());
		end = System.nanoTime();
		long sortedArrayTime_Get = end - start;

		System.out.printf("---------------------- n= %6d -----------------\n" +
						"put: LinkedList = %12d ns\n" +
						"     SortedArray= %12d ns\n" +
						"get: LinkedList = %12d ns\n" +
						"     SortedArray= %12d ns%n",
				n, unsortedLinkedListTime_Put, sortedArrayTime_Put, unsortedLinkedListTime_Get, sortedArrayTime_Get);
	}


	public static void main(String[] args) {
		int[] testSizes = {10, 100, 1000, 6000, 12000, 20000, 100000};
		for(int n : testSizes)
			testPerformance(n);

	}
}
