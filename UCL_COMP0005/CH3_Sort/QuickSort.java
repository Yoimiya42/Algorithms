package Sort;

import java.util.Arrays;

public class QuickSort<T extends Comparable<T>> implements Sorter<T>{

	@Override
	public void sort(T[] arr){
		quickSort(arr, 0, arr.length - 1);
	}

	public void swap(T[] arr, int i, int j){
		T temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public int getMedianIndex(T[] arr, int lo, int mid, int hi){
		if (arr[lo].compareTo(arr[mid]) < 0 && arr[mid].compareTo(arr[hi]) < 0
			|| arr[hi].compareTo(arr[mid]) < 0 && arr[mid].compareTo(arr[lo]) < 0)
			return mid;
		else if (arr[lo].compareTo(arr[hi]) < 0 && arr[hi].compareTo(arr[mid]) < 0
			|| arr[mid].compareTo(arr[hi]) < 0 && arr[hi].compareTo(arr[lo]) < 0)
			return hi;
		else
			return lo;
	}

	public int partition(T[] arr, int lo, int hi){

		int mid = lo + (hi - lo) / 2;
		int medianIndex = getMedianIndex(arr, lo, mid, hi);
		swap(arr, medianIndex, lo);
		T pivot = arr[lo];

		int i = lo + 1, j = hi;
		while (i < j) {
			while (i < j && arr[j].compareTo(pivot) > 0)
				j--;

			while (i < j && arr[i].compareTo(pivot) < 0)
				i++;

			if (i < j) {
				swap(arr, i, j);
			}
		}

		swap(arr, lo, j);
		return j;
	}

	public void quickSort(T[] arr, int lo, int hi){

		while (lo < hi){
			int pivotIndex = partition(arr, lo, hi);
			if(pivotIndex - lo < hi - pivotIndex){
				quickSort(arr, lo, pivotIndex - 1);
				lo = pivotIndex + 1;
			}else{
				quickSort(arr, pivotIndex + 1, hi);
				hi = pivotIndex - 1;
			}
		}
	}

	public static void main(String[] args) {
		Sorter<Integer> quickSort = new QuickSort<>();

		// 1. 一般乱序数组
		Integer[] arr1 = {3, 6, 8, 10, 1, 2, 1};
		quickSort.sort(arr1);
		System.out.println("Test1: " + Arrays.toString(arr1));

		// 2. 已经有序的数组（升序）
		Integer[] arr2 = {1, 2, 3, 4, 5, 6, 7};
		quickSort.sort(arr2);
		System.out.println("Test2: " + Arrays.toString(arr2));

		// 3. 逆序数组（降序）
		Integer[] arr3 = {9, 8, 7, 6, 5, 4, 3};
		quickSort.sort(arr3);
		System.out.println("Test3: " + Arrays.toString(arr3));

		// 4. 全部相同元素
		Integer[] arr4 = {5, 5, 5, 5, 5, 5};
		quickSort.sort(arr4);
		System.out.println("Test4: " + Arrays.toString(arr4));

		// 5. 含负数、零和正数
		Integer[] arr5 = {0, -10, 5, -3, 8, 2, -1};
		quickSort.sort(arr5);
		System.out.println("Test5: " + Arrays.toString(arr5));

		// 6. 字符串排序
		QuickSort<String> quickSortStr = new QuickSort<>();
		String[] arr6 = {"pear", "apple", "orange", "banana", "grape"};
		quickSortStr.sort(arr6);
		System.out.println("Test6: " + Arrays.toString(arr6));
	}
}
