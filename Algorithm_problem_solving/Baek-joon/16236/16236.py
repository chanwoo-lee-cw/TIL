# https://www.acmicpc.net/problem/16236
import sys
from queue import Queue
input = sys.stdin.readline


class Shark:
    def __init__(self):
        self.way = [(0, -1), (-1, 0), (1, 0), (0, 1)]
        self.shark_pos = []
        self.shark_size = 2
        self.shark_mv = 0
        self.eatcnt = 0

    def bfs(self):
        visited = [[False] * N for _ in range(N)]
        que = Queue()
        que.put([self.shark_pos[0], self.shark_pos[1], 0])
        visited[self.shark_pos[0]][self.shark_pos[1]] = True
        while not que.empty():
            y, x, mv_cnt = que.get()
            if matrix[y][x] > self.shark_size:
                continue
            elif matrix[y][x] != 0 and matrix[y][x] < self.shark_size:
                self.what_shark_eat(que, y, x, mv_cnt)
                del visited
                return True
            for next in self.way:
                next_x = x + next[0]
                next_y = y + next[1]
                if next_x < 0 or next_x >= N or next_y < 0 or next_y >= N \
                        or matrix[next_y][next_x] > self.shark_size or visited[next_y][next_x]:
                    continue
                que.put([next_y, next_x, mv_cnt+1])
                visited[next_y][next_x] = True
        del visited
        return False

    def what_shark_eat(self, que, y, x, mv_cnt):
        eatable = (y, x)
        while not que.empty():
            y, x, cnt = que.get()
            if cnt > mv_cnt:
                break
            else:
                if eatable[0] > y:
                    eatable = (y, x)
                elif eatable[0] == y and eatable[1] > x:
                    eatable = (y, x)
        y, x = eatable
        matrix[y][x] = 0
        self.eatcnt += 1
        self.shark_mv += mv_cnt
        self.shark_pos = [y, x]
        self.shark_is_grow()

    def shark_is_grow(self):
        if self.eatcnt == self.shark_size:
            self.eatcnt = 0
            self.shark_size += 1


if __name__ == "__main__":
    shark = Shark()
    N = int(input())
    matrix = [[0] * N for _ in range(N)]
    for i in range(N):
        tile = list(map(int, input().strip().split()))
        for j in range(N):
            matrix[i][j] = tile[j]
            if tile[j] == 9:
                shark.shark_pos = (i, j)
                matrix[i][j] = 0
    while shark.bfs():
        pass
    print(shark.shark_mv)
