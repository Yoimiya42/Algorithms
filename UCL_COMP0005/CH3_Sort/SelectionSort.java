package Sort;

public class SelectionSort<T extends Comparable<T>> implements Sorter<T>{

	public void swap(T[] arr, int i, int j){
		T temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	@Override
	public void sort(T[] arr){
		int length = arr.length;
		for(int i = 0; i < length; i++)
		{
			int minIndex = i;
			for(int j = i + 1; j < length; j++)
			{
				if(arr[j].compareTo(arr[minIndex]) < 0)
					minIndex = j;
			}
			swap(arr, i, minIndex);
		}
	}
}
