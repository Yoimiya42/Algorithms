#include <stdio.h>
#include <stdlib.h>
#include <string.h>

# define R 256 // Character set size


/*R-way Trie:
Chars are implicitly defined by link index

*/

// ------------------- D  E  F  I  N  E ------------------------

typedef struct Node{
    int value;
    struct Node** children;
}Node;

typedef struct RwayTrie{
    Node* root;
}RwayTrie;

Node* createNode()
{
    Node* node  = calloc(1, sizeof(Node));
    node->value = -1;// -1 means no value
    node->children = calloc(R, sizeof(Node*));
    
    return node;
}

RwayTrie* initRwayTrie()
{
    RwayTrie *trie = calloc(1, sizeof(RwayTrie));
    trie->root = createNode();

    return trie;
}

//Insert - O(m) m: length of the key

void insert(RwayTrie* trie, char* key, int value){
    trie->root = insertRec(trie->root, key, value, 0);
}

Node* insertRec(Node* node, char *key, int value, int d)
{
    if(node == NULL)
        node = createNode();

    if(d == strlen(key))
    {
        node->value = value;
        return node;
    }

    char c = *(key + d);
    *(node->children + c) = insertRec(*(node->children + c), key,value, d + 1);

    return node;
}

//Search - O(m) m: length of the key
int search(RwayTrie *trie, char* key)
{
    Node* node = searchRec(trie->root, key, 0);
    if (node == NULL || node->value == -1)
        return -1; // Not matched or no value assigned(prefix only)
    return node->value;
}

Node* searchRec(Node* node, char *key, int d)
{
    if(node == NULL)
        return NULL;

    if(d == strlen(key))
        return node;
    
    char c = *(key + d);
    return searchRec(node->children[c], key, d + 1);
}

