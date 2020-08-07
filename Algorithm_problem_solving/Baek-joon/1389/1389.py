import sys
from copy import deepcopy
from collections import deque

input = sys.stdin.readline

def bfs(person):
    i_know = 0
    queue = deque()
    queue.append([person, 0])
    while queue:
        friend, cnt = queue.popleft()
        if visited[friend] != 0:
            continue
        visited[friend] = cnt
        i_know += 1
        for i in range(len(link[friend])):
            if not visited[link[friend][i]]:
                queue.append([link[friend][i], cnt+1])
    if i_know == N:
        return cnt
    else:
        return float("inf")

if __name__ == "__main__":
    N, M = map(int, input().strip().split())
    link = [[] for i in range(N+1)]
    
    for i in range(M):
        A, B = map(int, input().strip().split())
        link[A].append(B)
        link[B].append(A)
    min_know = float("inf")
    output = [0] * (N+1)
    for i in range(1,N+1):
        visited = [0] * (N+1)
        bfs(i)
        output[i] = sum(visited)
        min_know = min(min_know, output[i])
    print(output.index(min_know))
