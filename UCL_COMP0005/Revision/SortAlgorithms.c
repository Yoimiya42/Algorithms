#include <stdio.h>
#include <stdlib.h>

void swap(int *a, int *b)
{
    int temp = *a;
    *a = *b;
    *b = temp;
}


// ---------------------- Selection Sort ------------------
// ascending
void selectionSort(int *arr, int size)
{
    for(int i = 0; i < size-1; i++)
    {
        int *min = arr + 1;
        for(int j = i + 1; j < size; j++)
        {
            if(*(arr + j) < *min)
                min = arr + j;
        }
        swap(arr + 1, min);
    }
}

// ---------------------- Insertion Sort ------------------
// ascending
void insertionSort(int* arr, int size)
{
    for(int i = 1 ;i < size; i++)
    {
        int j = i;
        while(j > 0 && *(arr + j) < *(arr + j - 1))
        {
            swap(arr + j, arr + j - 1);
            j--;
        }
    }
}

// ---------------------- Bubble Sort ------------------
// ascending
void bubbleSort(int* arr, int size)
{
    for(int i = size-1; i > 0; i--)
    {   
        int sorted = 1;
        for(int j = 0; j < i; j++)
        {
            if(*(arr + j) > *(arr + j + 1))
            {
                swap(arr + j, arr + j + 1);
                sorted = 0;
            }
        }            
        if(sorted)
            break;
    }
}

//------------------------ MergeSort --------------------------------
// ascending
void mergeSort(int *arr, int *aux, int lo, int hi){
    if(lo >= hi)
        return;

    int mid = lo + (hi - lo) / 2;

    mergeSort(aux, arr, lo, mid);
    mergeSort(aux, arr, mid + 1, hi);
    merge(arr, aux, lo, mid, hi);
}

void merge(int *arr, int *aux, int lo, int mid, int hi)
{
    int i = lo;
    int j = mid + 1;
    
    for(int k = 0; k < hi; k++)
    {
        if(i > mid)
        {
            aux[k] = arr[j];
            j++;
        }
        if(j > hi)
        {
            aux[k] = arr[i];
            i++;
        }

        if(arr[i] < arr [j])
        {
            aux[k] = arr[i];
            i++;            
        }else{
            aux[k] = arr[j];
            j++;
        }
    }
}

// ------------------------ QuickSort --------------------------------
// ascending


void swap(int *a, int *b)
{
    int temp = *a;
    *a = *b;
    *b = temp;
}


int partition(int *arr, int lo, int hi){
    int l = lo;
    int r = hi;
    int p = *(arr + lo);

    while(l < r)
    {
        while(l < r && *(arr + r) > p)
            r--;

        while(l < r && *(arr + l) < p)
            l++;

        swap(*(arr + r), *(arr + l));
    }

    swap(&p, arr+l);
    return l;
}

void quickSort(int *arr, int left, int right){

    if (left >= right)
        return;

    int pivotIndex = partition(arr, left, right);

    quickSort(arr, left, pivotIndex - 1);
    quickSort(arr, pivotIndex + 1, right);
}


int main(){

    return 0;
}