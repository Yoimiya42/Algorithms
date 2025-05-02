

# Sort

## MergeSort
1. Divide the array into two halves.
2. Sort and merge.
 
## QuickSort
Choose a pivot and partition => [x | x < pivot] [pivot] [x | x > pivot]

## HeapSort

#### Priority Queue(implemented with binary heap)
max heap -> ascending order  

for a index `i` (start with 1): 
- parent = `i / 2`
- left child = `i * 2`
- right child = `i * 2 + 1`  
**enqueue**: swim up
**dequeue**: swap with the last -> sink down
---
**HeapSort**
1. Construct a max heap: 
   ```
   for (int i = size / 2; i >= 1;i--)
      sink(i);
   ```
2. Dequeue the max and swap with the last (virtual size-1)
3. Sink the new root down to maintain the heap property.


# Search
- Symbol table: Array-based & linked-list based

- Binary Search Tree (BST):
- insert
- search
- min/max
- floor/ceiling
- delete
  - no children & one child: substitute with the child
  - two children: replace with min in right subtree
- traverse (in-order, pre-order, post-order)
- 

# Graph

# String