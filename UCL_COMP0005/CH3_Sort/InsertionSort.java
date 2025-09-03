package Sort;

public class InsertionSort<T extends Comparable<T>> implements Sorter<T>{

	@Override
	public void sort(T[] arr){
		int length = arr.length;
		for(int i = 1; i < length; i++){
			T toInsert = arr[i];
			int j = i - 1;
			while(j >= 0 && toInsert.compareTo(arr[j]) < 0)
				arr[j + 1] = arr[j--];
			arr[j + 1] = toInsert;
		}
	}

}
