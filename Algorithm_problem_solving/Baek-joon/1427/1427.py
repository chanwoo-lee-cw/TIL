# https://www.acmicpc.net/problem/1427
import sys
import heapq

input = sys.stdin.readline

if __name__ == "__main__":
    N = input().strip()
    arr = []
    output = []
    for item in N:
        heapq.heappush(arr, -int(item))

    while arr:
        output.append(str(-heapq.heappop(arr)))

    print(''.join(output))
