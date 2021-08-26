# https://www.acmicpc.net/problem/3197
from sys import stdin
from collections import Counter
import heapq

input = stdin.readline


if __name__ == "__main__":
    N = int(input().strip())
    arr = [0] * N
    for i in range(N):
        arr[i] = int(input().strip())
    arr.sort()
    # 평균
    numAvg = round(sum(arr) / N)
    # 즁앙
    numMid = arr[len(arr) // 2]
    # 최빈값
    numCnt = Counter(arr)
    if N >= 2:
        heap = []
        for item in numCnt:
            heapq.heappush(heap, [-numCnt[item], item])
        fisrt = heapq.heappop(heap)
        second = heapq.heappop(heap)
        numCnt = second[1] if fisrt[0] == second[0] else fisrt[1]
    else:
        numCnt = arr[0]
    # 범위
    numRange = arr[-1] - arr[0]
    print(numAvg)
    print(numMid)
    print(numCnt)
    print(numRange)
