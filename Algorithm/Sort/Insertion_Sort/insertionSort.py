import sys
input = sys.stdin.readline

def insertionSort(arr) :
    arrLen = len(arr)
    for i in range(1,arrLen) :
        key = arr[i]
        for j in range(i-1, -1, -1) :
            if(arr[j] > key) :
                arr[j + 1] = arr[j]
                arr[j] = key
            else :
                break
    return arr

if __name__ == "__main__":
    arr = [4,6,2,8,7,1,9]
    print(insertionSort(arr))