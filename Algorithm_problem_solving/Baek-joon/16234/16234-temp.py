from sys import stdin
from queue import Queue

input = stdin.readline

WAY = ((1, 0), (0, 1), (0, -1), (-1, 0))


def findOpenGate(board, visited, i, j):
    union = [(i, j)]
    unionSum = board[i][j]
    que = Queue()
    visited[i][j] = True
    for nextMv in WAY[:2]:
        nextY = i + nextMv[0]
        nextX = j + nextMv[1]
        if nextY < 0 or nextX < 0 or nextX >= N or nextY >= N:
            continue
        elif visited[nextY][nextX]:
            continue
        if not L <= abs(board[nextY][nextX] - board[i][j]) <= R:
            continue
        que.put((nextY, nextX, board[i][j]))
        visited[nextY][nextX] = True
    while not que.empty():
        curr = que.get()
        union.append((curr[0], curr[1]))
        unionSum += board[curr[0]][curr[1]]
        for nextMv in WAY:
            nextY = curr[0] + nextMv[0]
            nextX = curr[1] + nextMv[1]
            if nextY < 0 or nextX < 0 or nextX >= N or nextY >= N:
                continue
            elif visited[nextY][nextX]:
                continue
            if not L <= abs(board[nextY][nextX] - board[curr[0]][curr[1]]) <= R:
                continue
            que.put((nextY, nextX, board[curr[0]][curr[1]]))
            visited[nextY][nextX] = True
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
                isMoving = findOpenGate(board, visited, i, j) or isMoving   # 무조건 isMoving이 뒤로 와야한다. 먼저 나온게 True라면 뒤를 검사하지 않기 때문
        if isMoving:
            moveCnt += 1
        else:
            break
    print(moveCnt)
