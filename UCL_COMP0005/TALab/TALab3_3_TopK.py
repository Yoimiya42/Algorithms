"""
Exercise 3 - Sort distances for K-nearest-neighbours classifier 
    K-nearest-neighbours (KNN) is a very common machine-learning technique used to classify a new data point x, based on the classes that the K data points nearest to x belong to. The algorithm works by first determining the K (already classified) data points nearest (i.e., most similar) to x; then taking a “majority vote” from these K points; and, finally assigning to x the class that the majority of its K nearest neighbours belong to. Implement some of the sorting algorithms seen at lectures, to efficiently find the most similar data points (i.e., the nearest neighbours), out of a dataset of potentially millions of datapoints. Analyse the performance of the sorting algorithms on two different applications: classifying apples based on size and weight, and classifying images of hand-written digits. Note: the classification framework, in which the sorting algorithms will be applied, is already provided in the notebook.
"""


def parent(index):
    return (index - 1) // 2

def lChild(index):
    return index * 2 + 1

def rChild(index):
    return index * 2 + 2


def sink(arr, index, K):
 
  
    while(True):
        dest = index
        l = lChild(index)
        r = rChild(index)

        if(l < K and arr[dest] < arr[l]):
            dest = l
        if(r < K and arr[dest] < arr[r]):
            dest = r

        if(dest == index):
            break
        else:
            arr[index] , arr[dest] = arr[dest], arr[index]
            index = dest


def k_nearest_heapsort(array, K):
    for i in range(K//2 -1, -1, -1):
        sink(array, i, K)

    for i in range(K, len(array)):
        if(array[i] < array[0]):
            array[0] = array[i]
            sink(array, 0, K)


    return sorted(array[:K])
    

if __name__ == "__main__":
    arr = [4,15,24,12,9,14,11,64,32,1]

    arr = k_nearest_heapsort(arr, 3)
    print(arr)

