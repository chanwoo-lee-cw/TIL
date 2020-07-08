def merge(left, right):
    sorted_arr = []
    while len(left) > 0 or len(right) > 0:
        if len(left) > 0 and len(right) > 0:
            if left[0] <= right[0]:
                sorted_arr.append(left[0])
                left = left[1:]
            else:
                sorted_arr.append(right[0])
                right = right[1:]
        elif len(left) > 0:
            sorted_arr.append(left[0])
            left = left[1:]
        elif len(right) > 0:
            sorted_arr.append(right[0])
            right = right[1:]
    return sorted_arr


def merge_sort(arr):
    arrlen = len(arr)
    if arrlen <= 1:
        return arr
    mid = arrlen//2
    left = merge_sort(arr[:mid])
    right = merge_sort(arr[mid:])
    sorted_arr = merge(left, right)
    return sorted_arr


if __name__ == "__main__":
    n = int(input().strip())
    arr = [0] * n

    for i in range(n):
        arr[i] = int(input().strip())
    arr = merge_sort(arr)

    for i in range(n):
        print(arr[i])