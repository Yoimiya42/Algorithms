from math import comb, factorial

class UniquePath:

    def combination(self, r:int, c:int) -> int:
        return comb(r + c - 2, c - 1 )
    
    def combination2(self, r:int, c:int) -> int:
        return factorial(r + c -2) // factorial(r - 1) // factorial(c - 1)
    
    def combination3(self, r:int, c:int) -> int:
        res:int  = 1
        for i in range(1, r):
            res = res * (r + c - 2 - (i - 1)) // i
        return res
    
    def dp_rec(self, r:int, c:int) -> int:   

        if (r == 1 or c == 1):
            return 1
        
        return self.dp_rec(r - 1, c) + self.dp_rec(r, c - 1)
    
    def dp_iter_2d_arr(self, r:int, c:int) -> int:
        dp = [[0] * c for _ in range(r)]
        dp[0][0] = 1
        
        for row in range(r):
            dp[row][0] = 1
        for col in range(c):
            dp[0][col] = 1

        for row in range(1, r):
            for col in range(1, c):
                dp[row][col] = dp[row - 1][col] + dp[row][col - 1]

        return dp[r - 1][c - 1]

    def dp_iter_1d_arr(self, r:int, c:int) -> int:
        """Based on the 2d array above."""
        dp = [1] * c

        for row in range(1, r):
            for col in range(1, c):
                dp[col] += dp[col - 1]

        return dp[c - 1]
    
    from typing import List

class minCost:

    def dp(stairs:List[int]) -> int:
        top = len(stairs)
        dp:List[int] = [0] * (top + 1) # larger by 1 than the stairs
        for i in range(2, top + 1):
            dp[i] = min(dp[i - 1] + stairs[i - 1], dp[i - 2] + stairs[i - 2])

        return dp[top]
    
    def dp_dynamicArr(stairs:List[int]) -> int:
        
        former:int = 0
        latter:int = 0
        
        for i in range(2, len(stairs) + 1):
            dp = min(stairs[i - 2] + former,  stairs[i - 1] + latter)
            former, latter = latter, dp

        return latter