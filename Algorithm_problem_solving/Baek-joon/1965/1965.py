# https://www.acmicpc.net/problem/1965
import sys

input = sys.stdin.readline

"""
lcs리스트에서 찾는 값보다 큰 값이 처음으로 나타나는 부분을 찾는다.
"""
def lowerBound(arr, s, e, search):
    while e - s > 0:
        m = (e + s) // 2
        if arr[m] < search:
            s = m + 1
        else:
            e = m
    return (s + e) // 2

"""
상자를 순서대로 가장 큰 순서로 정리하는 방법, 즉 lcs를 통해 찾는다.
"""
def lcs(n, arr):
    lcsList = [arr[0]]      # 순서대로 커지는 가장 긴 상자 배열을 찾기 위한 리스트
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
