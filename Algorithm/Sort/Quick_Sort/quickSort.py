def quickSort(arr ,start, end) :
    if (start < end) :
        pivot = start
        left = start
        right = end

        while left <= right :
            while arr[pivot] >= arr[left] and left <= end :
                left += 1
            while arr[pivot] < arr[right] :
                right -= 1

            if(left <= right) :
                temp = arr[left]
                arr[left] = arr[right]
                arr[right] = temp

        temp = arr[right]
        arr[right] = arr[pivot]
        arr[pivot] = temp

        quickSort(arr, start , right -1)
        quickSort(arr ,right + 1, end)

    return arr

if __name__ == "__main__":
    arr = [4,6,2,8,7,1,9]
    arrLen = len(arr)
    print("unsorted : ", arr)
    print("sorted : ", quickSort(arr ,0, arrLen -1 ))


# https://www.crocus.co.kr/440