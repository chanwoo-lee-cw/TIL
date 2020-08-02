import sys
sys.setrecursionlimit(10000000)
input = sys.stdin.readline


def dfs(a, b):
    root[a][b] = True

    for i in range(N):
        if not root[a][i] and link[b][i]:
            link[a][i] = True
            dfs(a, i)


if __name__ == "__main__":
    N = int(input().strip())
    link = [[False]*N for _ in range(N)]
    root = [[False]*N for _ in range(N)]
    for i in range(N):
        temp = list(map(int, input().strip().split()))
        for j in range(N):
            if temp[j] == 1:
                link[i][j] = True

    for i in range(N):
        for j in range(N):
            if link[i][j] and not root[i][j]:
                dfs(i, j)

    for items in root:
        for item in items:
            print(1, end=' ') if item else print(0, end=' ')
        print()
