# Exercise 3 – Search in a bitonic array

    # An array is bitonic if it is comprised of an increasing sequence of integers followed immediately by a decreasing sequence of integers. Write a program that, given a bitonic array of n distinct integer values, determines whether a given integer is in the array. Use O(lg n) compares in the worst case.
    # For example, the array [2,4,6,8,10,12,11,9,7,5,3] is bitonic, while the following are not: [1,2,3], [1,2,3,2,3],[5].

    # Hint: First, find the maximum integer using lg n compares—this divides the array into the increasing and decreasing pieces.

from typing import List

class Bitonic:
    def __init__(self, array:List[int]):
        self.array:List[int] = array

    def findPeekIndex(self) -> int:
        start:int = 0
        end:int   = len(self.array) - 1
        mid:int   = -1
        while(start <= end):
            mid = start + (end - start) // 2

            left = float('-inf') if mid - 1 < 0 else mid - 1
            right = float('inf') if mid + 1 > len(self.array) else mid + 1

            if self.array[left] < self.array [mid] > self.array[right]: 
                return mid
            
            if self.array[left] < self.array [mid] < self.array[right]: 
                start = mid + 1

            elif self.array[left] > self.array [mid] > self.array[right]: 
                end = mid - 1
        
    def seek_in_half(self, s:int, e:int, target:int) -> bool:
        start:int = s
        end:int   = e
        

        while(start <= end):
            mid = start + (end - start) // 2
            if self.array[mid] == target:
                return True
            if self.array[mid] < target:
                start = mid + 1
            else:
                end = mid - 1
        
        return False

    def findTarget(self, array:List[int], target:int) -> bool:
        i_peek:int = self.findPeekIndex()
        return self.seek_in_half(0, i_peek, target) or self.seek_in_half(i_peek, len(self.array) -1, target)

if __name__ == "__main__":
    array:List[int] = [4,8,20,42,36,28,24,20,16,6]
    bi = Bitonic(array)
    res = bi.findTarget(array, 42)
    print(res)

            
            