
# [3,7,8,1,9] => [1,3,7,8,9] Ascending
class Sort:

    def __init__(self):
        pass

    def selection_sort(self, arr):
        for i in range(len(arr)-1):
            minIndex = i
            for j in range(i + 1, len(arr)):
                if arr[j] < arr[minIndex]:
                    minIndex = j
            arr[minIndex], arr[i] = arr[i], arr[minIndex]

    def bubble_sort(self, arr):
        for i in range(len(arr) - 1, 0, -1):
            swapped = False
            for j in range(0, i):
                if arr[j] > arr[j + 1]:
                    arr[j], arr[j + 1] = arr[j + 1], arr[j]
                    swapped = True
            if swapped is False:
                break

    def insertion_sort(self, arr):
        for i in range(1, len(arr)):
            j = i - 1
            temp = arr[i]
            while j >= 0 and arr[j] > temp:
                arr[j + 1] = arr[j]
                j-=1
            arr[j + 1] = temp




if __name__ == "__main__":
    arr = [7,8,1,4,3,6,9,2]
    sorter = Sort()
    sorter.insertion_sort(arr)
    print(arr)