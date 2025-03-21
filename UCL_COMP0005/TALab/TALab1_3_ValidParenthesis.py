 # Exercise 3 – Balanced parenthesis

    # Write an algorithm to check whether, given an input sequence of characters, the sequence contains balanced parenthesis. For example:
    # ((x*2)+(x-2)) – a[3] / a[10] is balanced
    # (((x*2)+(x-2)) – a[3] / a[10] is not balanced

    # Hint
    # Use a stack to keep track of relevant information.

class Ex3:
    def balancedParenthesis(self, str):
        parenthesis = ['(',  ')',  '[',  ']']
        for char in str:
            if char not in parenthesis:
                str = str.replace(char, '')
        print(f"After the First Deletion: {str}")

        while "()" in str or "[]" in str:
            str = str.replace("()", "")
            str = str.replace("[]", "")
        print(f"After Second Deletion: {str}")
        return str == ""
    
    def balancedParenthesis_stack(self, str):
        stack = []
        parenthesis = {'(' : ')',
                       '[' : ']',
                       '{' : '}'}
        for char in str:
            if char in parenthesis.keys():
                stack.append(char)
            elif char in parenthesis.values():
                if not stack or parenthesis[stack.pop()] != char:
                    return False 
                
        return not stack
                

    
if __name__ == "__main__":

    str = "((x*2)+(x-2)) - a[3] / a[10]"
    ex3 = Ex3()

    res1 = ex3.balancedParenthesis_stack(str)
    res2 = ex3.balancedParenthesis(str)

    print(f"Solution1: {res1}")
    print(f"Solution2: {res2}")


    



                
