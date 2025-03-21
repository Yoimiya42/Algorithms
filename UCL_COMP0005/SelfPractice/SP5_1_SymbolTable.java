/*
Exercise 1 – Linked List vs Ordered Array Symbol Table

    Consider two alternative implementations of a symbol table: one using an unordered linked list, and one using an ordered array. Experimentally compare the performance of the two implementations on put() and get() operations, as you vary the number of n of key/value pairs you insert and search for. At what value of n does the ST implemented via an ordered array become 10, 100, and 1000 faster than the one implemented via sequential search?
*/

import java.util.Random;
import java.util.function.BiConsumer;

public class SP5_1_SymbolTable{
    public static void measureSymbolTableOp(
        SymbolTable<Integer, Integer> st, 
        String operationName,
        BiConsumer<SymbolTable<Integer, Integer>, Integer> operation,
        Integer[] keys
                                            )
    {
        long start = System.nanoTime();
        for(int key : keys)
            operation.accept(st, key);
        long time = System.nanoTime() - start;
        System.out.printf("%s %s time for n = %d is\t\t %f ms\n", 
                        st.getClass().getSimpleName(),
                        operationName, keys.length, time/1e6);
    }
    public static void main(String[] args){
        int[] testSizes = {500, 1000, 2000, 4000, 8000, 16000, 64000};
        Random random = new Random();

        for(int n : testSizes)
        {
            SymbolTable<Integer, Integer> linkedListST = new LinkedListST<>();
            SymbolTable<Integer, Integer> orderedArrayST = new OrderedArrayST<>(n); 

            Integer[] keys = new Integer[n];
            for(int i = 0; i < n; i++)
                keys[i] = random.nextInt(n);

            measureSymbolTableOp(linkedListST, "put()", (st, key) -> st.put(key, key), keys);
            measureSymbolTableOp(orderedArrayST, "put()", (st, key) -> st.put(key, key), keys);

            measureSymbolTableOp(linkedListST, "get()", (st, key) -> st.get(key), keys);
            measureSymbolTableOp(orderedArrayST, "get()", (st, key) -> st.get(key), keys);

            System.out.println();
        }
    }
}

interface SymbolTable<Key extends Comparable<Key>, Value>{
    void put(Key key, Value value);
    Value get(Key key);
}

class LinkedListST<Key extends Comparable<Key>, Value> 
                    implements SymbolTable<Key, Value>
{
    private Node head;
    private int size;

    private class Node 
    {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next)
        {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    @Override
    public void put(Key key, Value value){
        for (Node cur = head; cur != null; cur = cur.next) {
            if (cur.key.equals(key)) {
                cur.value = value;
                return;
            }
        }
        head = new Node(key, value, head);
        size++;
    }

    @Override
    public Value get(Key key)
    {
        for(Node cur = head; cur != null; cur = cur.next)
        {
            if(cur.key.equals(key))
                return cur.value;
        }

        return null;
    }
}


class OrderedArrayST<Key extends Comparable<Key>, Value> 
                    implements SymbolTable<Key, Value>
{
    
    private Key[] keys;
    private Value[] values;
    private int size;

    @SuppressWarnings("unchecked")
    public OrderedArrayST(int capacity)
    {
        keys = (Key[])new Comparable[capacity];
        values = (Value[])new Object[capacity];
    }

    public int binarySearch(Key key)
    {
        int lo = 0;
        int hi = size - 1;
        while(lo <= hi)
        {
            int mid = lo + (hi - lo) / 2;
            if(key.compareTo(keys[mid]) < 0) hi = mid -1;
            else if(key.compareTo(keys[mid]) > 0) lo = mid + 1;
            else return mid;
        }

        return lo;
    }
    @Override
    public void put(Key key, Value value)
    {
        int pos = binarySearch(key);
        if(pos < size && keys[pos].compareTo(key) == 0)
        {
            values[pos] = value;
        }else{
            for(int i = size; i > pos; i--)
            {
                keys[i] = keys[i -1];
                values[i] = values[i - 1];
            }
            keys[pos] = key;
            values[pos] = value;
            size++;
        }
    }

    @Override 
    public Value get(Key key)
    {
        int pos = binarySearch(key);
        if(pos < size && keys[pos].compareTo(key) == 0) 
            return values[pos];
        
        return null;
    }
} 
