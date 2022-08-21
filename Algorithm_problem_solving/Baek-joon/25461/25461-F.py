from sys import stdin, setrecursionlimit

setrecursionlimit(10000000)

way = ((-1, 0), (1, 0), (0, -1), (0, 1))
input = stdin.readline


class Board:
    def __init__(self, n, m, c, d):
        self.n = n
        self.m = m
        self.dir_y = c
        self.dir_x = d
        self.max_move = 0
        self.visited = [[False] * (m + 1) for _ in range(n + 1)]

    def dfs(self, y, x, move):
        move += 1
        if y == self.dir_y and x == self.dir_x:
            self.max_move = max(self.max_move, move)
        else:
            for nxt in way:
                nxt_y = y + nxt[0]
                nxt_x = x + nxt[1]
                if nxt_y <= 0 or nxt_x <= 0 or nxt_y > n or nxt_x > m:
                    continue
                elif self.visited[nxt_y][nxt_x]:
                    continue
                self.visited[nxt_y][nxt_x] = True
                self.dfs(nxt_y, nxt_x, move)
                self.visited[nxt_y][nxt_x] = False


if __name__ == "__main__":
    test_case = int(input())
    for _ in range(test_case):
        n, m, a, b, c, d = map(int, input().strip().split())
        new_board = Board(n, m, c, d)
        new_board.visited[a][b] = True
        new_board.dfs(a, b, 0)
        print(new_board.max_move)
