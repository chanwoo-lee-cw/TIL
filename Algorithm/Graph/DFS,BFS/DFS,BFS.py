import sys
input = sys.stdin.readline

n, m, v = map(int, input().strip().split())
matrix = [[False] * (n + 1) for _ in range(n + 1)]
dfsVisitEdge = [False] * (n+1)
bfsVisitEdge = [False] * (n+1)


def dfs(pos):
    print(pos, end=" ")
    dfsVisitEdge[pos] = True
    for i in range(1, n+1):
        if matrix[pos][i] and not dfsVisitEdge[i]:
            dfs(i)


def bfs(pos):
    queue = [pos]
    bfsVisitEdge[pos] = True

    while(queue):
        pos = queue.pop(0)
        print(pos, end=" ")
        for i in range(1, n+1):
            if not bfsVisitEdge[i] and matrix[pos][i]:
                queue.append(i)
                bfsVisitEdge[i] = True


if __name__ == "__main__":
    for _ in range(m):
        a, b = map(int, input().strip().split())
        matrix[a][b] = True
        matrix[b][a] = True

    dfs(v)
    print()
    bfs(v)