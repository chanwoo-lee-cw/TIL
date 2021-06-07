# https://www.acmicpc.net/problem/16466

from sys import stdin
import heapq

input = stdin.readline

if __name__ == "__main__":
    N = int(input().strip())
    ticket = list(map(int, input().strip().split()))
    queue = []
    for i in range(N):
        heapq.heappush(queue, ticket[i])

    i = 1
    while queue:
        if i != heapq.heappop(queue):
            break
        i += 1
    print(i)
