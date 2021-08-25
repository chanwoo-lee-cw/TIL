# https://www.acmicpc.net/problem/1261
from sys import stdin
import heapq

input = stdin.readline

way = ((-1, 0), (1, 0), (0, 1), (0, -1))  # 가는 방향을 체크하기 위한 리스트


def find_min_broken_wall(n: int, m: int, maze: list):
    que: list = []  # 가장 벽을 적게 부수는 경우를 카운트 하기 위한 우선순위 큐
    visited = [[float("inf")] * (m + 1) for _ in range(n + 1)]
    heapq.heappush(que, (0, 1, 1))
    visited[1][1] = 0
    while que:
        curr: tuple = heapq.heappop(que)
        for next in way:
            nextY = next[0] + curr[1]
            nextX = next[1] + curr[2]
            if nextY <= 0 or nextX <= 0 or nextY > N or nextX > M:
                continue
            elif maze[nextY][nextX]:
                if visited[nextY][nextX] <= curr[0] + 1:
                    # visited[nextY][nextX]로 이동하는데 부수는 벽의 갯수가 더 많다면 continue
                    continue
                else:
                    heapq.heappush(que, (curr[0] + 1, nextY, nextX))
                    visited[nextY][nextX] = curr[0] + 1
            elif not maze[nextY][nextX]:
                if visited[nextY][nextX] <= curr[0]:
                    # visited[nextY][nextX]로 이동하는데 부수는 벽의 갯수가 더 많다면 continue
                    continue
                else:
                    heapq.heappush(que, (curr[0], nextY, nextX))
                    visited[nextY][nextX] = curr[0]
    return visited[N][m]


if __name__ == '__main__':
    M, N = map(int, input().strip().split())
    maze = [[False] * (M + 1) for _ in range(N + 1)]
    for i in range(N):
        inputLine = input().strip()
        for j in range(M):
            maze[i + 1][j + 1] = True if inputLine[j] == '1' else False
    answer = find_min_broken_wall(N, M, maze)
    print(answer)
