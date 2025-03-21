
import time


 # Exercise 1 – Coin change
    # Given an array of integer numbers that represent the values of each coin, and given a target amount, determine the minimum number of coins needed to create this amount. At first, try to solve this problem with regular coins (i.e., 1,5,10,20,50), then try with different irregular coin values (e.g., 1,7,12, 38).

    # Example 1
    # Given coin values (1, 2, 5,10,20,50), with
    # Target amount = 7; solution = 2 coins (5,2)
    # Target amount = 150; solution = 3 coins (50,50,50)
    # Target amount = 28; solution = 4 coins (20,5,2,1)

    # Example 2
    # Given coin values (1,4,6), with:
    # Target amount = 7; solution = 2 coins (6,1)
    # Target amount = 9; solution = 3 coins (4,4,1)

    # Only the number of coins is required, the coins used are not important for this exercise.
    # Hint: Which amounts require the fewest coins?


class CoinChange:
    def coinChange(self, coins:list[int], target:int): 
        dp = [float('inf')] * (target + 1)
        dp[0] = 0
        
        for value in range(1, target + 1):
            for coin in coins:
                if value >= coin:
                    dp[value] = min(dp[value - coin] + 1, dp[value])

        return dp[target]
    
    def coinChange2(self, coins:list[int], target:int): 
        dp = [float('inf')] * (target + 1)
        dp[0] = 0

        for coin in coins:
            for value in range(1, target + 1):
                if value >= coin:
                    dp[value] = min(dp[value - coin] + 1, dp[value])

        return dp[target]

if __name__ == "__main__":

    coins = [100, 500, 1000, 2000, 5000, 10000]
    target = 100000000
    cc = CoinChange();

    start_time = time.time()

    numbers = cc.coinChange2(coins, target)

    print("--- %s seconds ---" % (time.time() - start_time))

    print(numbers)
    
# inner: coin outer: value 34s

    list = [1,2,3]
