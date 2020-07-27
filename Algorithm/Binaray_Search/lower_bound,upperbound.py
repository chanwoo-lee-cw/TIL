def lower_bound(arr, start, end,  check) :
    while end - start > 0 :
        mid = (start + end) // 2
        if arr[mid] < check :
            start = mid + 1
        else :
            end = mid
    return end+1
    # return end

def upper_bound(arr, start, end,  check) :
    while end - start > 0 :
        mid = (start + end) // 2
        if arr[mid] <= check :
            start = mid + 1
        else :
            end = mid
    return end+1
    # return end