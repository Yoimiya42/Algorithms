
# Exercise 1 - Unique paths

    # Image you have a grid of r rows and c columns. You are standing at the top left corner of a grid (s) and you want to move to a goal (g). You can move through the grid only by moving right or down. For any two given integers (r,c), how many unique paths exist to get from s to g?

    # For a 2x2 grid the answer is 2. You can go down and right, or right and down.
    # What for a grid r x c?


    
from math import comb, factorial
from typing import List

class UniquePath:

    def combination(self, r:int, c:int) -> int:
        return comb(r + c - 2, r - 1)

    def combination2(self, r:int, c:int) -> int:
        return factorial(r + c - 2) // factorial(r - 1) // factorial(c - 1)
    

    def combination3(self, r:int, c:int) ->int:

        res: int = 1
        for i in range(1, r):
            res =  res * (r + c - 2 - (i - 1)) // i
        return res

    def dp_iter_2d_arr(self, r:int, c:int) -> int:
        dp:List[int][int] = [[1] * c] + [[1] + [0] * (c - 1)] * (r - 1)

        for row in range(1, r):
            for col in range(1, c):
                dp[row][col] = dp[row - 1][col] + dp[row][col - 1]

        return dp[r - 1][c - 1]
    
    def dp_iter_1d_arr(self, r:int, c:int) -> int:
        dp:List[int] = [1] * c
        for _ in range(1, r):
            for j in range(1, c):
                dp[j] += dp[j - 1]

        return dp[c - 1]


if __name__ == "__main__":

    up = UniquePath()
    res = up.dp_iter_1d_arr(5, 6)
    print(res)

    
