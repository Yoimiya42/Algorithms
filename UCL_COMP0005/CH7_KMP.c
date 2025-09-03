#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void buildDFA(char *text, char *pattern)
{
    int N = strlen(text);
    int M = strlen(pattern);
    int R = 256; // Number of characters in the alphabet

    // DFA table
    // dfa[charToRead][state] = next state
    // state: 0 - M, current index in pattern
    int dfa[R][M]; 

    dfa[pattern[0]][0] = 1; // Read the first char in pattern, and jump from state 0 to state 1

    for(int X = 0, j = 1; j < M; j++)
    {
        for(int c = 0; c < R; c++)
            dfa[c][j] = dfa[c][X]; // Copy the previous state

        dfa[pattern[j]][j] = j + 1; // Read the next char in pattern, and jump to the next state
        X = dfa[pattern[j]][X]; // Update the state
    }
}


int main()
{
    char text[] = "BCBAABACAABABACAA";
    char pattern[] = "ABABAC";

    buildDFA(text, pattern);

    return 0;
}