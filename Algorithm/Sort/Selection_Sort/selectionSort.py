import sys
input = sys.stdin.readline

def Selection_Sort(arr) :
    arrLen = len(arr)
    for i in range(arrLen) :
        min = arr[i]
        for j in range(i,arrLen) :
            if arr[i] > arr[j] :
                temp = arr[i]
                arr[i] = arr[j]
                arr[j] = temp
    return arr

if __name__ == "__main__":
    arr = [4,6,2,8,7,1,9]
    print(Selection_Sort(arr))