# https://www.acmicpc.net/problem/1965
import sys

input = sys.stdin.readline


def lowerBound(arr, s, e, search):
    while e - s > 0:
        m = (e + s) // 2
        if arr[m] < search:
            s = m + 1
        else:
            e = m
    return (s + e) // 2


def lcs(n, arr):
    lcsList = [arr[0]]
    for i in range(1, n):
        if arr[i] < lcsList[-1]:
            lcsList[lowerBound(lcsList, 0, len(lcsList), arr[i])] = arr[i]
        elif arr[i] > lcsList[-1]:
            lcsList.append(arr[i])
    return len(lcsList)


if __name__ == "__main__":
    n = int(input().strip())
    boxes = list(map(int, input().strip().split()))
    print(lcs(n, boxes))
