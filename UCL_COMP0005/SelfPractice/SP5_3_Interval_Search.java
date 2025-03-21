/*
Exercise 3 – Interval Search in a BST

    Design and implement an algorithm that, given a Binary Search Tree and two keys x and y, finds all keys between (and excluding) x and y in the BST. The runtime should be proportional to lg N + M, where N is the size of the BST and M is the number of keys returned.

    Hint: combine ideas from BST search and in-order traversal.
 */

import java.util.ArrayList;

public class SP5_3_Interval_Search<T extends Comparable<T>> extends Tree<T>
{
    public static void main(String[] args) 
    {
        Integer[] test = {7,12,4,6,7,8,16,42,84,64,1,3};
        SP5_3_Interval_Search<Integer> bst = new SP5_3_Interval_Search<>(test);
        bst.buildBSTfromArray();

        System.out.println(bst.intervalSearch(64, 4));
    }

    public SP5_3_Interval_Search(T[] values)
    {
        super(values);
    }

    public void IntervalSearch(ArrayList<T> res, Node node, T max, T min)
    {
        if(node == null)    return;

        if(node.value.compareTo(min) > 0)
            IntervalSearch(res, node.left, max, min);

        if(node.value.compareTo(min) > 0 && node.value.compareTo(max) < 0)
            res.add(node.value);
            
        if(node.value.compareTo(max) < 0)
            IntervalSearch(res, node.right, max, min);
        
    }

    public ArrayList<T> intervalSearch(T max, T min)
    {   
        ArrayList<T> res = new ArrayList<>();
        IntervalSearch(res, getRoot(), max, min);

        return res;
    }
}

