# include <stdio.h>
# include <stdlib.h>
# include <stdbool.h>
# include <string.h>

typedef struct Stack{
    int capacity;
    int* arr;
    int top;
}Stack;

Stack* initStack(int capacity)
{
    Stack* stack = malloc(sizeof(Stack));
    stack->capacity = capacity;
    stack->arr = malloc(sizeof(int) * stack->capacity);
    stack->top = -1;
}

void push(Stack* stack, int value)
{
    stack->arr[++stack->top] = value;
}

void pop(Stack* stack)
{
    stack->top--;
}