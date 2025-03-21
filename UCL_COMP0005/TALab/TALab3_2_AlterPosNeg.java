
/*
Exercise 2 – Separate positive and negative numbers

    Given a list of both positive and negative numbers in random order, design and implement an efficient algorithm to rearrange the array elements so that positive and negative numbers are placed alternatively, starting from a positive number, and so that positives are sorted and negatives are sorted. If there are more negative or positive numbers, they should be placed at the end of the rearranged list (for this latter part, the order in which numbers appear is not important).

    Example:
    Given [-8, 1, 2, -4, 6, 12, 5, -10, 16, 7, 11], the algorithm should output:
    [1, -4, 6, -8, 12, -10, 5, 2, 16, 7, 11]
 */
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TALab3_2_AlterPosNeg {
    public static void main(String[] args) 
    {
        AlterPosNeg obj = new AlterPosNeg();
        int[] test = {-8,9,-12,10,-42,7,16,64,24,-11,-14,4,32,48};
        obj.alterSorted(test);
        System.out.println(Arrays.toString(test));
    }
}

class AlterPosNeg{
    public void alterSorted(int arr[])
    {   
        mergeSort(arr);

        int negCount = 0;
        int findNeg = 0;

        while(findNeg < arr.length && arr[findNeg++] < 0 ) 
            negCount++;

        int neg = negCount -1;
        int pos = negCount;
        int i = 0;
        int[] res = new int[arr.length];

        while(neg >= 0 && pos < arr.length)
        {
            res[i++] = arr[pos++];
            res[i++] = arr[neg--];
        }

        while(neg >= 0)
            res[i++] = arr[neg--];
        while(pos < arr.length)
            res[i++] = arr[pos++];
            
        System.arraycopy(res, 0, arr, 0, res.length);
    }

    public void mergeSort(int arr[]){

        Queue <int[]> queue = new LinkedList<>();
        for(int elem : arr)
            queue.add(new int[]{elem});

        while(queue.size() > 1)
        {
            int[] arr1 = queue.poll();
            int[] arr2 = queue.poll();
            queue.add(merge(arr1, arr2));
        }

        int sortedArr[] = queue.poll();
        System.arraycopy(sortedArr, 0, arr, 0, sortedArr.length);
    }

    public int[] merge(int arr1[], int[] arr2)
    {   
        int length = arr1.length + arr2.length;
        int[] merged = new int[length];

        int i1 = 0, i2 = 0, i = 0;

        while(i1 < arr1.length && i2 < arr2.length)
        {
            if(arr1[i1] < arr2[i2])
                merged[i++] = arr1[i1++];
            else
                merged[i++] = arr2[i2++];
        }

        while(i1 < arr1.length)
            merged[i++] = arr1[i1++];
        while(i2 < arr2.length)
            merged[i++] = arr2[i2++];

        return merged;
    }
}