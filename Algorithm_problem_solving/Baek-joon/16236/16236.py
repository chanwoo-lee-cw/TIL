# https://www.acmicpc.net/problem/16236
import sys
from queue import Queue
input = sys.stdin.readline

way = [(0, -1), (-1, 0), (1, 0), (0, 1)]
shark_pos = []
shark_size = 2
shark_mv = 0
eatcnt = 0


def bfs():
    visited = [[False] * N for _ in range(N)]
    global shark_size, eatcnt, shark_mv, shark_pos
    que = Queue()
    que.put([shark_pos[0], shark_pos[1], 0])
    while not que.empty():
        y, x, mv_cnt = que.get()
        if visited[y][x] or matrix[y][x] > shark_size:
            continue
        elif matrix[y][x] != 0 and matrix[y][x] < shark_size:
            # print(f"{y}, {x} : {matrix[y][x]}")
            matrix[y][x] = 0
            eatcnt += 1
            shark_mv += mv_cnt
            shark_pos = [y, x]
            if eatcnt == shark_size:
                # print(f"{eatcnt},{shark_size}")
                eatcnt = 0
                shark_size += 1
            del visited
            return True
        visited[y][x] = True
        for next in way:
            next_x = x + next[0]
            next_y = y + next[1]
            if next_x < 0 or next_x >= N or next_y < 0 or next_y >= N \
                    or matrix[next_y][next_x] > shark_size or visited[next_y][next_x]:
                continue
            que.put([next_y, next_x, mv_cnt+1])
    del visited
    return False


if __name__ == "__main__":
    N = int(input())
    matrix = [[0] * N for _ in range(N)]
    for i in range(N):
        tile = list(map(int, input().strip().split()))
        for j in range(N):
            matrix[i][j] = tile[j]
            if tile[j] == 9:
                shark_pos = (i, j)
                matrix[i][j] = 0
    while bfs():
        pass
    print(shark_mv)
