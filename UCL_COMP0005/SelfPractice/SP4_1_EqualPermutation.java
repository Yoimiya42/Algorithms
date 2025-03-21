/*
 * Exercise 1 – Permutations
    Given two integer arrays of size n, design an algorithm to determine whether one is a permutation of the other (i.e., they contain exactly the same entries but, possibly, in a different order). Your algorithm should have guaranteed sub-quadratic performance in the worst-case scenario.
 */
import java.util.Arrays;
import java.util.Stack;

public class SP4_1_EqualPermutation {
    public static void main(String[] args){
        int arr1[] = {14,24,42,11,9,64};
        int arr2[] = {42,64,14,11,24,9};
        Permutation p = new Permutation();
        System.out.println(p.isEqualPermutation(arr1, arr2));
    }
}


// QuickSort
class Permutation{

    public void swap(int arr[], int ia, int ib)
    {
        int temp = arr[ia];
        arr[ia] = arr[ib];
        arr[ib] = temp;
    }

    public int medianOfThree(int arr[], int left, int mid, int right)
    {
        int l = arr[left], m = arr[mid], r = arr[right];

        if((l <= m && m <= r) || (r <= m && m <= l))
            return mid;
        if((m <= l && l <= r) || (r <= l && l <= m))
            return left;
        return right;
    }

    public int partition(int arr[], int left, int right){
        int medianIndex = medianOfThree(arr, left, (left+right)/2, right);
        swap(arr, right, medianIndex);

        int l = 0;
        int r = arr.length - 1;

        while(l < r)
        {
            while(l < r && arr[l] >= arr[right])
                l++;

            while(l < r && arr[r] <= arr[right])
                r--;
            
            swap(arr, l, r);
        }

        swap(arr, l, right);
        return l;
    }

    //Descending
    public void quickSort(int arr[]){
        Stack <Integer> stack = new Stack<>();
        stack.push(0);              // push left first
        stack.push(arr.length - 1);      // push right then

        while(!stack.isEmpty())
        {

            int right = stack.pop();  
            int left = stack.pop();  
            int pivotIndex = partition(arr, left, right);

            if (pivotIndex + 1 < right)
            {
                stack.push(pivotIndex + 1);
                stack.push(right);
            }
            
            if(pivotIndex - 1 > left)
            {
                stack.push(left);
                stack.push(pivotIndex - 1);
            }
        }

    }

    public boolean isEqualPermutation(int arr1[], int arr2[]){
        if(arr1 == null && arr2 == null)
            return true;
        if(arr1 == null || arr2 == null)
            return false;

        if(arr1.length != arr2.length)
            return false;

        quickSort(arr1);
        quickSort(arr2);

        return Arrays.equals(arr1, arr2);
    }
}

