import sys
import queue
# python 재귀의 한계를 풀어준다
sys.setrecursionlimit(3000000)
input = sys.stdin.readline

n, m, v = map(int, input().strip().split())
# 점의 수만큼 간선의 갯수를 확인하는 매트릭스
matrix = [[False] * (n + 1) for _ in range(n + 1)]

# 방문했는지 검사하는 배열
dfsVisited = [False] * (n + 1)
bfsVisited = [False] * (n + 1)
# 너비 우선 탐색을 위한 큐
bfsQue = queue.Queue()


def dfs(pos):
    # 만약 이미 방문한 장소면 바로 리턴한다.
    if dfsVisited[pos]:
        return
    # 여기 왔다는 뜻으로 위치 출력
    print(pos, end=" ")
    # 다시 안 오기 위해서 왔다고 체크 해준다.
    dfsVisited[pos] = True
    for i in range(1, n + 1):
        # 해당 정점에서 간선이 이어져있는지 확인하고 이어져 있다면 그곳으로 깊이 우선 탐색
        if matrix[pos][i]:
            dfs(i)


def bfs(pos):
    # 너비 우선 탐색 방문했다는 뜻으로 체크해준다.
    bfsVisited[pos] = True
    # 방문 했으니 출력한다.
    print(pos, end=" ")

    for i in range(1, n + 1):
        # 만약 간선이 이어진 장소이고 방문하지 않은 장소면 그 장소를 큐에 집어 넣는다.
        if matrix[pos][i] and not bfsVisited[i]:
            bfsQue.put(i)

    while not bfsQue.empty():
        # 큐가 빌때까지 큐 탐색을 한다.
        nextSearch = bfsQue.get()
        if bfsVisited[nextSearch]:
            # 만약 방문한 장소면 다시 뽑는다.
            continue
        else:
            # 방문하지 않은 장소면 들어가고 그것부터 다시 너비 우선 탐색을 한다.
            bfs(nextSearch)


if __name__ == "__main__":

    for _ in range(m):
        a, b = map(int, input().strip().split())
        matrix[a][b] = True
        matrix[b][a] = True

    dfs(v)
    print("")
    bfs(v)
