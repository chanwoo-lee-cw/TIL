import sys
from collections import deque

input = sys.stdin.readline


def bfs(vertex):
    queue = deque()
    queue.append([vertex, 0])
    while queue:
        pos, cnt = queue.popleft()
        if visited[pos]:
            if group[pos] == cnt:
                continue
            else:
                return True
        visited[pos] = True
        group[pos] = cnt
        for i in range(len(link[pos])):
            if not visited[link[pos][i]]:
                queue.append([link[pos][i], (cnt+1) % 2])
    return False


def prinfall():
    for i in range(1, V+1):
        if not visited[i] and bfs(i):
            print("NO")
            return
    print("YES")


if __name__ == "__main__":
    K = int(input().strip())
    for _ in range(K):
        V, E = map(int, input().strip().split())
        link = [[] for i in range(V+1)]
        group = [-1] * (V+1)
        visited = [False] * (V+1)
        for i in range(E):
            edge1, edge2 = map(int, input().strip().split())
            link[edge1].append(edge2)
            link[edge2].append(edge1)
        prinfall()
