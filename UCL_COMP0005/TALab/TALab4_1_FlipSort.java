/*
 Exercise 1 – Pancake Sorting
    Pancake Sort is a colloquial term for the mathematical problem of sorting a disordered stack of pancakes in order of size. A spatula can be inserted at any point i in the stack and used to flip all pancakes above it.

    Given an unsorted array, design and implement the pancake algorithm to sort it using only the “flip” operation, whose effect is to reverse the elements of the array between elements 0 and i (with i being the position where you imagine having inserted the spatula for flipping). Whereas a traditional sorting algorithm attempts to sort with the fewest comparisons possible, the goal here is to sort the sequence in as few “flips” as possible. Your algorithm should return the indices at which flips were performed.

    Example:
    Unsorted Input: [3,2,4,1]
    Sorted Output: [1,2,3,4]
    Algorithm Output: [1, 2, 3] ß indices where flips where performed (3 flips total).

    We have performed 3 flips (bold indicates it has been flipped):

    Start: [3, 2, 4, 1]
    1st flip (k = 1): [2, 3, 4, 1]
    2nd flip (k = 2): [4, 3, 2, 1]
    3rd flip (k = 3): [1, 2, 3, 4]

    Hint:
    Intuitively, this problem can be solved by:
    · Finding the largest out-of-order value
    · Flip that largest unsorted value to the bottom (you may need to flip it to the top first)
    · Repeat until the pancake stack is ordered
 */

import java.util.ArrayList;
import java.util.Arrays;

public class TALab4_1_FlipSort {
    public static void main(String args[]){

        PancakeSort pancake = new PancakeSort();
        int test[] = {9,42,24,7,36,4,8,11};
        ArrayList <Integer> res = pancake.pancakeSort(test);
        System.out.println(res);
    }
}

class PancakeSort{

    public ArrayList<Integer> pancakeSort(int arr[]){

        int i = arr.length - 1;
        ArrayList <Integer> flips = new ArrayList<>();

        while(i > 0){
            System.out.println("Before: " + Arrays.toString(arr));
            int maxIndex = findMaxIndex(arr, 0, i);
            if(maxIndex != i)
            {
                if(maxIndex != 0)
                {
                    reverseArr(arr, 0, maxIndex);
                    flips.add(maxIndex + 1);
                }
                reverseArr(arr, 0, i );
                flips.add(i + 1);
            }

            i--;
            System.out.println("Array: " + Arrays.toString(arr));
            System.out.println("Flips: " +flips);
        }

        return flips;
    }

    public int findMaxIndex(int arr[], int left, int right){
        int max = left;
        for(int i = left; i <= right; i++)
            if(arr[i] > arr[max]) max = i;
        
        return max;

    }

    public void reverseArr(int arr[], int left , int right)
    {
        while(left < right)
            swap(arr, left++, right--);
    }

    public void swap(int arr[], int left, int right)
    {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}

/* Output
Before: [9, 42, 24, 7, 36, 4, 8, 11]
Array: [11, 8, 4, 36, 7, 24, 9, 42]
Flips: [2, 8]

Before: [11, 8, 4, 36, 7, 24, 9, 42]
Array: [9, 24, 7, 11, 8, 4, 36, 42]
Flips: [2, 8, 4, 7]

Before: [9, 24, 7, 11, 8, 4, 36, 42]
Array: [4, 8, 11, 7, 9, 24, 36, 42]
Flips: [2, 8, 4, 7, 2, 6]

Before: [4, 8, 11, 7, 9, 24, 36, 42]
Array: [9, 7, 4, 8, 11, 24, 36, 42]
Flips: [2, 8, 4, 7, 2, 6, 3, 5]

Before: [9, 7, 4, 8, 11, 24, 36, 42]
Array: [8, 4, 7, 9, 11, 24, 36, 42]
Flips: [2, 8, 4, 7, 2, 6, 3, 5, 4]

Before: [8, 4, 7, 9, 11, 24, 36, 42]
Array: [7, 4, 8, 9, 11, 24, 36, 42]
Flips: [2, 8, 4, 7, 2, 6, 3, 5, 4, 3]

Before: [7, 4, 8, 9, 11, 24, 36, 42]
Array: [4, 7, 8, 9, 11, 24, 36, 42]
Flips: [2, 8, 4, 7, 2, 6, 3, 5, 4, 3, 2]

[2, 8, 4, 7, 2, 6, 3, 5, 4, 3, 2]

 */