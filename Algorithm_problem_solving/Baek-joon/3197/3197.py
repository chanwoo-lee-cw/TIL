# https://www.acmicpc.net/problem/3197
from sys import stdin
from queue import Queue

input = stdin.readline
way = ((-1, 0), (1, 0), (0, -1), (0, 1))

"""
각 얼음들이 녹는 시간을 계산하여 리턴
"""
def getMeltTime(r, c, lake):
    que = Queue()
    visited = [[-1] * C for _ in range(R)]
    for i in range(R):
        for j in range(C):
            if lake[i][j] == '.' or lake[i][j] == 'L':
                que.put((i, j, 0))
                visited[i][j] = 0

    while not que.empty():
        curr = que.get()
        for nextMv in way:
            nextY = curr[0] + nextMv[0]
            nextX = curr[1] + nextMv[1]
            if nextX < 0 or nextX >= c or nextY < 0 or nextY >= r:
                continue
            elif visited[nextY][nextX] != -1:
                continue
            que.put((nextY, nextX, curr[2] + 1))
            visited[nextY][nextX] = curr[2] + 1
    return visited


"""
백조가 다른 백조를 찾기 위한 시간 구해서 리턴
"""
def swanCanMeet(r, c, start, dest, meltTime):
    que = Queue()
    visited = [[float("inf")] * C for _ in range(R)]
    que.put((start[0], start[1], 0))
    visited[start[0]][start[1]] = 0

    while not que.empty():
        curr = que.get()
        for nextMv in way:
            nextY = curr[0] + nextMv[0]
            nextX = curr[1] + nextMv[1]
            if nextX < 0 or nextX >= c or nextY < 0 or nextY >= r:
                continue
            elif visited[nextY][nextX] <= curr[2] + 1:
                continue
            if meltTime[nextY][nextX] <= curr[2]:
                que.put((nextY, nextX, curr[2]))
                visited[nextY][nextX] = curr[2]
            else:
                que.put((nextY, nextX, curr[2] + 1))
                visited[nextY][nextX] = curr[2] + 1
    return visited[dest[0]][dest[1]]


if __name__ == "__main__":
    R, C = map(int, input().strip().split())
    swanPos = []
    lake = [['.'] * C for _ in range(R)]
    for i in range(R):
        inputLine = input().strip()
        for j in range(C):
            lake[i][j] = inputLine[j]
            if lake[i][j] == 'L':
                swanPos.append((i, j))

    meltTime = getMeltTime(R, C, lake)
    print(swanCanMeet(R, C, swanPos[0], swanPos[1], meltTime))
