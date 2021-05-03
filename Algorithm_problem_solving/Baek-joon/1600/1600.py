import sys
from queue import Queue

input = sys.stdin.readline

jump = ((2, -1), (2, 1), (1, 2), (-1, 2), (-2, 1), (-2, -1), (1, -2), (-1, -2))
way = ((1, 0), (-1, 0), (0, 1), (0, -1))


def getMinMove(k, w, h, board):
    que = Queue();
    visited = [[float('inf')] * w for _ in range(h)]
    curr = (0, 0, 0, 0)  # y, x, 이동, 점프
    que.put(curr)
    visited[0][0] = 0

    while que:
        curr = que.get()
        if curr[0] == h - 1 and curr[1] == w - 1:
            return curr[2]
        for nextMv in way:
            nextY = curr[0] + nextMv[0]
            nextX = curr[1] + nextMv[1]
            if nextY < 0 or nextX < 0 or nextY >= h or nextX >= w:
                continue
            if board[nextY][nextX] == 1 or visited[nextY][nextX] <= curr[3]:
                continue
            que.put((nextY, nextX, curr[2] + 1, curr[3]))
            visited[nextY][nextX] = curr[3]
        if curr[3] < k:
            for nextMv in jump:
                nextY = curr[0] + nextMv[0]
                nextX = curr[1] + nextMv[1]
                if nextY < 0 or nextX < 0 or nextY >= h or nextX >= w:
                    continue
                if board[nextY][nextX] == 1 or visited[nextY][nextX] <= curr[3] + 1:
                    continue
                que.put((nextY, nextX, curr[2] + 1, curr[3] + 1))
                visited[nextY][nextX] = curr[3] + 1


if __name__ == "__main__":
    k = int(input())
    w, h = map(int, input().strip().split())
    board = [[0]*w for _ in range(h)]

    for i in range(h):
        inline = list(map(int, input().strip().split()))
        for j in range(w) :
            board[i][j] = inline[j]
    print(getMinMove(k, w, h, board))
