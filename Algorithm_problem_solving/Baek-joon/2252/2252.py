# https://www.acmicpc.net/problem/2252
from sys import stdin
from queue import Queue

input = stdin.readline

if __name__ == "__main__":
    N, M = map(int, input().strip().split())
    needs = [0] * (N + 1)
    child = [[] for _ in range(N + 1)]
    for i in range(M):
        item = list(map(int, input().strip().split()))
        needs[item[1]] += 1
        child[item[0]].append(item[1])

    que = Queue()
    for i in range(1, N + 1):
        que.put(i)

    while not que.empty():
        curr = que.get()
        if needs[curr] == 0:
            print(curr, end=' ')
            for item in child[curr]:
                needs[item] -= 1
        else:
            que.put(curr)
