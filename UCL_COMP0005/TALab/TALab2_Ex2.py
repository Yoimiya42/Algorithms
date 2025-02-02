# Exercise 2 – Min-cost climbing stairs

    # On a staircase, the i-th step has some non-negative cost cost[i] assigned to it. The staircase starts at index i=0. Once you pay the cost, you can either climb one or two steps. Design and implement an algorithm to find the minimum cost to reach the top floor. You can start your climb from either step index 0, or step index 1.

    # For example, given a staircase of 9 floors (from 0 to 8) and input cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1], the min-cost climb starting from 0 is 6.

    # Hint: Think from top to bottom, and bottom to top.
from typing import List

class MinCost:

    def dp(self, stairs:List[int]) -> int:
        """
        Time Complexity: O(n)
        Space Complexity: O(n)
        """

        dp:List[int] = [0] * (len(stairs) + 1)  # len(dp) = 11

        for i in range(2, len(dp)):
            dp[i] = min(stairs[i - 1] + dp[i - 1],  stairs[i - 2] + dp[i - 2])

        return dp[len(stairs)]
    
    def dp_da(self, stairs:List[int]) -> int:
        """
        Time Complexity: O(n)
        Space Complexity: O(1)
        """
        former:int = 0
        latter:int = 0
        dp:int = 0
        for curr in range(2, len(stairs) + 1):
            dp =  min(former + stairs[curr - 2],  latter + stairs[curr - 1])
            former, latter = latter, dp

        return latter

if __name__ == "__main__":

    # stairs:List[int] = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
    stairs:List[int] = [5,10,5,20,15,10,15,5,20]  #40

    
    mc = MinCost()
    res = mc.dp_da(stairs)
    print(res)