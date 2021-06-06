# https://www.acmicpc.net/problem/14923

from sys import stdin
from queue import Queue

input = stdin.readline

WAY = ((-1, 0), (1, 0), (0, -1), (0, 1))


def findShortestRoute(n, m, hx, hy, ex, ey, maze):
    visited = [[float('inf')] * (m + 1) for _ in range(n + 1)]
    queue = Queue()
    queue.put((hy, hx, 0, 0))  # y, x, moveCnt, wallBreak,
    visited[hy][hx] = 0  # 벽을 부시고 도착한 횟수 저장

    while not queue.empty():
        curr = queue.get()
        if curr[0] == ey and curr[1] == ex:
            return curr[2]
        for nextMv in WAY:
            nextY = nextMv[0] + curr[0]
            nextX = nextMv[1] + curr[1]
            if nextY <= 0 or nextX <= 0 or nextY > n or nextX > m:
                # 맵 밖으로 나갈때
                continue
            elif visited[nextY][nextX] <= curr[3]:
                # 만약 이미 들린 장소
                continue
            elif maze[nextY][nextX] == 1:
                # 벽일 때
                if curr[3] >= 1:
                    continue
                elif visited[nextY][nextX] <= curr[3] + 1:
                    continue
                else:
                    queue.put((nextY, nextX, curr[2] + 1, curr[3] + 1))
                    visited[nextY][nextX] = curr[3] + 1
            else:
                queue.put((nextY, nextX, curr[2] + 1, curr[3]))
                visited[nextY][nextX] = curr[3]
    return -1


if __name__ == "__main__":
    n, m = map(int, input().strip().split())  # 세로, 가로
    hy, hx = map(int, input().strip().split())  # 시작점
    ey, ex = map(int, input().strip().split())  # 목표점
    maze = [[0] * (m + 1) for _ in range(n + 1)]

    for i in range(1, n + 1):
        inputLine = list(map(int, input().strip().split()))
        for j in range(1, m + 1):
            maze[i][j] = inputLine[j - 1]
    print(findShortestRoute(n, m, hx, hy, ex, ey, maze))
