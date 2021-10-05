import sys

input = sys.stdin.readline


def lower_bound(arr, s, e, find):
    while e - s > 0:
        m = (s + e) // 2
        if arr[m] < find:
            s = m + 1
        else:
            e = m
    return (s + e) // 2


def lis(n, arr):
    lis = [arr[0]]
    for item in arr[1:]:
        if lis[-1] < item:
            lis.append(item)
        else:
            pos = lower_bound(lis, 0, len(lis), item)
            lis[pos] = item
    return len(lis)


def need_relocate(n, arr):
    lisLen = lis(n, arr)
    return n - lisLen


if __name__ == '__main__':
    n = int(input().strip())
    children = [0] * n
    for i in range(n):
        children[i] = int(input().strip())
    print(need_relocate(n, children))
