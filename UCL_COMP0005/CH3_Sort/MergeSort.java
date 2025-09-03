package Sort;

import java.util.Arrays;

public class MergeSort<T extends Comparable<T>> implements Sorter<T>{

	@Override
	public void sort(T arr[]){
		T[] aux = (T[]) new Comparable[arr.length];
		mergeSort(arr, aux, 0, arr.length - 1);
	}

	public void merge(T[] arr, T[] aux, int lo, int mid, int hi){
		int i = lo;
		int j = mid + 1;

		for(int k = lo; k <= hi; k++)
			aux[k] = arr[k];

		for(int k = lo; k <= hi; k++){
			if (i > mid)
				arr[k] = aux[j++];
			else if (j > hi)
				arr[k] = aux[i++];
			else if (aux[j].compareTo(aux[i]) < 0)
				arr[k] = aux[j++];
			else
				arr[k] = aux[i++];
		}
	}

	public void mergeSort(T[] arr, T[] aux, int lo, int hi){
		if (hi <= lo)
			return;

		int mid = (hi - lo) / 2 + lo;
		mergeSort(arr, aux, lo, mid);
		mergeSort(arr, aux, mid + 1, hi);
		merge(arr, aux, lo, mid, hi);
	}

	public static void main(String[] args) {
		Sorter<Integer> mergeSorter = new MergeSort<>();

		Integer[] arr1 = {5, 2, 9, 1, 5, 6};
		mergeSorter.sort(arr1);
		System.out.println("随机数组: " + Arrays.toString(arr1));

		// 2. 已排序数组
		Integer[] arr2 = {1, 2, 3, 4, 5};
		mergeSorter.sort(arr2);
		System.out.println("已排序数组: " + Arrays.toString(arr2));

		// 3. 逆序数组
		Integer[] arr3 = {9, 7, 5, 3, 1};
		mergeSorter.sort(arr3);
		System.out.println("逆序数组: " + Arrays.toString(arr3));

		// 4. 有重复元素
		Integer[] arr4 = {2, 3, 2, 1, 1, 5, 5, 4};
		mergeSorter.sort(arr4);
		System.out.println("有重复元素: " + Arrays.toString(arr4));

		// 5. 单个元素 / 空数组
		Integer[] arr5 = {42};
		mergeSorter.sort(arr5);
		System.out.println("单个元素: " + Arrays.toString(arr5));

		Integer[] arr6 = {};
		mergeSorter.sort(arr6);
		System.out.println("空数组: " + Arrays.toString(arr6));

		// 6. 字符串排序
		Sorter<String> mergeSortStr = new MergeSort<>();
		String[] arr7 = {"banana", "apple", "pear", "orange", "apple"};
		mergeSortStr.sort(arr7);
		System.out.println("字符串数组: " + Arrays.toString(arr7));
	}
}
