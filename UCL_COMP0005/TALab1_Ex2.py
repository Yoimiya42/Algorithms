 # Exercise 2 – Fibonacci Sequence

    # Given an integer number n, write two functions (one recursively and another iteratively) that output the nth Fibonacci number. A Fibonacci sequence is a series of numbers which starts from 0 and 1 and continues with each number (Fibonacci number) being the sum of the two preceding numbers. What’s the difference in space complexity between the iterative and recursive method?

    # Examples:
    # · Fibonacci(5) = 3
    # · Fibonacci(10) = 34

class Fibonacci:
    def recursion(self, n):
        if(n <= 0):
            print("ERROR")
            return

        if(n == 1):
            return 0
        if(n == 2):
            return 1
        
        return self.recursion(n - 1) + self.recursion(n - 2)
        
    
    def iteration(self, n):
        if (n <= 0):
            print("ERROR")
            return
        
        former = 0
        latter = 1

        if (n == 1):
            return former

        if (n == 2):
            return latter
        
        for _ in range(3, n + 1):
            former, latter = latter, former + latter

        return latter
    

if __name__ == "__main__":
    Fib = Fibonacci()

    n_th = 5
    fib_recur = Fib.recursion(n_th)
    fib_iter = Fib.iteration(n_th)

    print(f"Recursion_{n_th} = {fib_recur}")
    print(f"Iteration_{n_th} = {fib_iter}")