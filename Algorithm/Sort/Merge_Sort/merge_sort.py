def merge_sort(arr: list) -> list:
    def merge(left: list, right: list) -> list:
        result = []
        l_head = 0
        r_head = 0
        while l_head < len(left) and r_head < len(right):
            if left[l_head] < right[r_head]:
                result.append(left[l_head])
                l_head += 1
            else:
                result.append(right[r_head])
                r_head += 1
        if l_head < len(left):
            result.extend(left[l_head:])
        if r_head < len(right):
            result.extend(right[r_head:])
        return result

    arr_len = len(arr)
    if arr_len <= 1:
        return arr
    mid = arr_len // 2
    left = merge_sort(arr[:mid])
    right = merge_sort(arr[mid:])
    result = merge(left, right)
    return result


if __name__ == "__main__":
    arr = [4, 6, 2, 8, 7, 1, 9]
    print(merge_sort(arr))