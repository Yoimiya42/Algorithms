
# include <stdlib.h>

typedef struct Node{
    int val;
    struct Node* next;
    struct Node* prev;
}Node;

typedef struct UnionFind{
    Node** nodes;
}UF;