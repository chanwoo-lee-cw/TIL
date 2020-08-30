# https://www.acmicpc.net/problem/2352
import sys
input = sys.stdin.readline


def lower_bound(start, end, arr, find):
    while end - start > 0:
        mid = (end + start)//2
        if arr[mid] < find:
            start = mid+1
        else:
            end = mid
    return (end + start)//2 + 1


def lis(arr, n):
    lis = []
    lis.append(arr[0])
    for pos in range(n):
        if arr[pos] > lis[-1]:
            lis.append(arr[pos])
        else:
            get_pos = lower_bound(0, len(lis), lis, arr[pos])
            lis[get_pos-1] = arr[pos]
    return len(lis)


if __name__ == "__main__":
    n = int(input().strip())
    ports = list(map(int, input().strip().split()))
    print(lis(ports, n))