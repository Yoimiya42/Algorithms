# [3,2,5,4,1] => [1,2,3,4,5]
# sort a list in ascending order
def sort(arr):
    for i in range(len(arr)):
        min = arr[i]
        min_index = i
        for j in range(i,len(arr)):
            if arr[j] < min:
                min = arr[j]
                min_index = j
        if (min_index != i):
            arr[i], arr[min_index] = min, arr[i]
        print(arr)
        




arr = [32,16,4,64,8] 
print(arr)

sort(arr)
print(arr)