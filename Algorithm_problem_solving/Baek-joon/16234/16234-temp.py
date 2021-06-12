from sys import stdin
from queue import Queue

input = stdin.readline

WAY = ((-1, 0), (1, 0), (0, -1), (0, 1))


def findOpenGate(board, visited, i, j):
    union = [(i, j)]
    unionSum = board[i][j]
    que = Queue()
    visited[i][j] = True
    for nextMv in WAY:
        nextY = i + nextMv[0]
        nextX = j + nextMv[1]
        if nextY < 0 or nextX < 0 or nextX >= N or nextY >= N:
            continue
        elif visited[nextY][nextX]:
            continue
        que.put((nextY, nextX, board[i][j]))
    while not que.empty():
        curr = que.get()
        if visited[curr[0]][curr[1]]:
            continue
        if not L <= abs(curr[2] - board[curr[0]][curr[1]]) <= R:
            continue
        # else
        print(f'{curr[2]} - {board[curr[0]][curr[1]]} = {abs(curr[2] - board[curr[0]][curr[1]])}')
        union.append((curr[0], curr[1]))
        unionSum += board[curr[0]][curr[1]]
        visited[curr[0]][curr[1]] = True
        for nextMv in WAY:
            nextY = curr[0] + nextMv[0]
            nextX = curr[1] + nextMv[1]
            if nextY < 0 or nextX < 0 or nextX >= N or nextY >= N:
                continue
            elif visited[nextY][nextX]:
                continue
            que.put((nextY, nextX, board[curr[0]][curr[1]]))
    for item in union:
        board[item[0]][item[1]] = unionSum // len(union)
    return True if len(union) > 1 else False


if __name__ == "__main__":
    N, L, R = map(int, input().strip().split())
    board = [[0] * N for _ in range(N)]
    allSum = 0
    for i in range(N):
        inputLine = list(map(int, input().strip().split()))
        for j in range(N):
            board[i][j] = inputLine[j]
            allSum += board[i][j]
    moveCnt = 0
    while True:
        isMoving = False
        visited = [[False] * N for _ in range(N)]
        for i in range(N):
            for j in range(N):
                if visited[i][j]:
                    continue
                isMoving = isMoving or findOpenGate(board, visited, i, j)
        if isMoving:
            moveCnt += 1
        else:
            break
    print(moveCnt)