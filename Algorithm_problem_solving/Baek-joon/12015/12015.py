import sys
input = sys.stdin.readline


def lower_bound(start, end, arr, find):
    while end - start > 0:
        mid = (end + start)//2
        # print(start, end, mid)
        # print(arr[mid], find, '\n')
        if arr[mid] < find:
            start = mid+1
        else:
            end = mid
    return (end + start)//2 + 1


def lis(arr):
    lis = []
    lis.append(arr[0])
    i = 1
    max_len = 1
    while i < N:
        if lis[-1] < arr[i]:
            lis.append(arr[i])
        else:
            pos = lower_bound(0, len(lis), lis, arr[i])
            lis[pos-1] = arr[i]
        i = i+1
        max_len = max(max_len, len(lis))
    return(max_len)


if __name__ == "__main__":
    N = int(input().strip())
    A = list(map(int, input().strip().split()))
    print(lis(A))
