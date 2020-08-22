def lower_bound(start, end, arr, find):
    while end - start > 0:
        mid = (end + start)//2
        if arr[mid] < find:
            start = mid+1
        else:
            end = mid
    return (end + start)//2 + 1

# def lower_bound(arr, start, end,  check) :
#     while end - start > 0 :
#         mid = (start + end) // 2
#         if arr[mid] < check :
#             start = mid + 1
#         else :
#             end = mid
#     return end+1
#     # return end

def upper_bound(start, end, arr, find):
    while end - start > 0:
        mid = (end + start)//2
        if arr[mid] <= find:
            start = mid+1
        else:
            end = mid
    return (end + start)//2 + 1

# def upper_bound(arr, start, end,  check) :
#     while end - start > 0 :
#         mid = (start + end) // 2
#         if arr[mid] <= check :
#             start = mid + 1
#         else :
#             end = mid
#     return end+1
#     # return end