import sys
input = sys.stdin.readline

def bubbleSort(arr) :
    arrLen = len(arr)
    for i in range(arrLen - 1) :
        for j in range(arrLen -i -1) :
            if(arr[j]> arr[j+1]) :
                temp = arr[j]
                arr[j] = arr[j+1]
                arr[j+1] = temp
    return arr

if __name__ == "__main__":
    arr = [4,6,2,8,7,1,9]
    print(bubbleSort(arr))