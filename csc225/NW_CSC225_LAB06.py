# CSC 225: Fall 2019: Lab 6
# Python Template
# Creator: Yichun :)
# Modified By: Ali Akber
# Completed by: Nick Wurzer, V00958568

def left(i):
    return 2 * i + 1  # considering 0 indexed array


def right(i):
    return 2 * i + 2  # considering 0 indexed array


# Heapify to maintain maximum heap property
def max_heapify(arr, cur, heap_size):
    largest = cur
    l = left(cur)
    r = right(cur)

    # Find largest key out of the current key at i and its two children
    # If left child is larger than root
    if(l < heap_size):
        if (arr[l] > arr[largest]):  #?
            largest = l

    # If right child is larger than largest so far
    if(r < heap_size):
        if (arr[r] > arr[largest]):  # ?
            largest = r

    # If largest is not root
    if largest != cur:
        # swap values at i and largest
        temp = arr[largest]
        arr[largest] = arr[cur]
        arr[cur] = temp
        # Recursively heapify the affected sub-tree // Bubble Down // maintain heap property
        #print(arr)
        max_heapify(arr, largest, heap_size)  # ?


# To start building a max heap, we convert the input array to a heap by maintaining heap property
def build_max_heap(arr, heap_size):
    # We need to maintain heap property by using a for loop and calling max_heapify
    # For loop starts from middle of array down to 0 because we do not need to care about leaf nodes!
    # Remember, the assumption is that both left subtrees and right subtrees are already max heaps.

    # Index of last non-leaf node
    middle = int(len(arr) / 2) - 1

    # Perform reverse level order traversal from last non-leaf node and max_heapify each node
    for i in range(middle, -1, -1):
        max_heapify(arr, i, heap_size)


def heap_sort(arr):
    # We need to define the current heap size, which is decreasing as we sort
    curr_heap_size = len(arr)
    #print(curr_heap_size)

    # Steps to do in each iteration:
    # 1. Put current max value in the correct position by swapping
    # 2. Reduce heap size by 1
    # 3. Maintain heap property
    for i in range(len(arr)-1, 0, -1):
        # swap and put max in correct position
        temp = arr[i]
        arr[i] = arr[0]
        arr[0] = temp
        # reduce heap size by 1
        curr_heap_size = curr_heap_size - 1
        # maintain heap property
        max_heapify(arr, 0, curr_heap_size)  # ?


# TESTS
print("----------------------------------------------------")
arr1 = [3, 1, 6, 5, 2, 4]
print("arr1: ", arr1)
# Build the heap in arr1 first
build_max_heap(arr1, len(arr1))
print("Max heaped arr1: ", arr1)
# Sort the heap
heap_sort(arr1)
print("Sorted arr1: ", arr1)

print("----------------------------------------------------")
arr2 = [3, 2, 7, 9, 1, 19, 3, 2, 99, 11]
print("arr2: ", arr2)
# Build the heap in arr2 first
build_max_heap(arr2, len(arr2))
print("Max heaped arr2: ", arr2)
# Sort the heap
heap_sort(arr2)
print("Sorted arr2: ", arr2)
