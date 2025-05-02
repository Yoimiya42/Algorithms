
# include <stdio.h>

int bruteForce(char *pattern, char *text)
{
    int N = strlen(text);
    int M = strlen(pattern);

    for (int i = 0; i <= N - M; i++) // i is in text
    {
        int j;
        for (j = 0; j < M; j++) // j is in pattern
        {
            if (text[i + j] != pattern[j])
                break;
        }
        if (j == M) // Pattern matched at index i
            return i;
    }
    return -1; // Pattern not matched
    // return -1 in java String.indexOf() method
}


int bruteForce2(char *pattern, char *text)
{
    int N = strlen(text);
    int M = strlen(pattern);

    for(int i = 0; i <= N - M; i++) // i is in text
    {
        int j = 0;
        while(j < M && text[i+j] == pattern[j])
            j++;

        if (j == M) // Pattern matched at index i
            return i;
    }

    return -1; // Pattern not matched
}