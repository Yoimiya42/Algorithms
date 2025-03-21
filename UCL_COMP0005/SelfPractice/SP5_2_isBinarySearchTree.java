/*
Exercise 2 – Is a Binary Tree a Binary Search Tree?

    Consider the Binary Search Tree data structure seen at lectures to realise the Symbol Table ADT. Design and implement an algorithm that, given as argument a Node (with a value and a pair of left/right children nodes), determines if this is the root of a binary search tree and returns True if so, or False otherwise.


    Hint: design a recursive function isBST(node_x, min_key, max_key) that determines whether node_x is the root of a binary search tree with all keys between min_key and max_key.
*/
import java.util.ArrayList;

public class SP5_2_isBinarySearchTree{
    public static void main(String[] args) {

        Integer[] values = {7, 2, 4, 8, 9, 10, 1, 4, 8};
        Tree<Integer> bst = new Tree<>(values);
        bst.buildBSTfromArray();
        bst.inorderTraverse();
        System.out.println(bst.isBST());

        bst.buildBTfromArray();
        bst.inorderTraverse();
        System.out.println(bst.isBST());

    }
}

class Tree<T extends Comparable<T>>{
    class Node
    {
        T value;
        Node left;
        Node right;
    
        public Node(T value)
        {
            this.value = value;
        }
    }

    private final T[] values;
    private Node root;

    public Tree(T[] values)
    {
        this.values = values;
        root = null;
    }

    // I. Build Binary Search Tree ------------------------------------
    public Node buildBST(Node node, T val)
    {   
        if(node == null)
            return new Node(val);
            
        if (val.compareTo(node.value) < 0)
            node.left = buildBST(node.left, val);
        else if(val.compareTo(node.value) > 0)
            node.right = buildBST(node.right, val);

        return node;
    }

    public void buildBSTfromArray(){
        for(T value : values)
        {
            root = buildBST(root, value);
        }
    }

    // II. Build Binary Tree -------------------------------------------
    public Node buildBT(T[] values, int index){
        if(index >= values.length)
            return null;

        Node node = new Node(values[index]);
        node.left = buildBT(values, index * 2 + 1);
        node.right = buildBT(values, index * 2 + 2);

        return node;
    }

    public void buildBTfromArray()
    {
        root = buildBT(values, 0);
    }

    // III. Traverse tree in inorder precedence ---------------------------
    public void inorderTraverseAux(ArrayList<T> res, Node node)
    {
        if(node != null)
        {
            inorderTraverseAux(res,node.left);
            res.add(node.value);
            System.out.printf("%s ", node.value);
            inorderTraverseAux(res, node.right);
        }  
    }

    public void inorderTraverse()
    {   
        ArrayList<T> res = new ArrayList<>();
        inorderTraverseAux(res, root);
    }

    // IV. Check if tree is a Binary Search Tree -------------------------

    public boolean isBST(Node node, T min, T max)
    {
        if(node == null)
            return true;
        
        if((min != null && node.value.compareTo(min) < 0) ||
            (max != null && node.value.compareTo(max) > 0))
            return false;

        return isBST(node.left, null, node.value) && isBST(node.right, node.value, null);
    }

    public boolean isBST()
    {
        return isBST(root, null, null);
    }

    public Node getRoot()
    {   return root;    }

}
