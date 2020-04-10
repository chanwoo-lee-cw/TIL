def merge(left, right):
    result = []
    while len(left) > 0 or len(right) > 0:
        if len(left) > 0 and len(right) > 0:
            if left[0] <= right[0]:
                result.append(left[0])
                left = left[1:]
            else:
                result.append(right[0])
                right = right[1:]
        elif len(left) > 0:
            result.append(left[0])
            left = left[1:]
        elif len(right) > 0:
            result.append(right[0])
            right = right[1:]
    return result

def mergeSort(arr) :
    arrLen = len(arr)
    if arrLen <= 1 :
        return arr
    mid = arrLen//2
    leftSorted = mergeSort(arr[:mid])
    rightSorted = mergeSort(arr[mid:])

    result = merge(leftSorted, rightSorted)
    return result

if __name__ == "__main__":
    arr = [4,6,2,8,7,1,9]
    print(mergeSort(arr))