# https://www.acmicpc.net/problem/3197
from sys import stdin
from queue import Queue
import heapq

input = stdin.readline
way = ((-1, 0), (1, 0), (0, -1), (0, 1))

"""
각 얼음들이 녹는 시간을 계산
getMeltTime(r: 호수의 세로 크기, c: 호수의 사로 크기,, meltTime: 호수의 각 부분이 녹는 시간, que: Queue())
"""
def getMeltTime(r: int, c: int, meltTime: list, que: Queue()) -> list:
    while not que.empty():
        curr = que.get()
        for nextMv in way:
            nextY = curr[0] + nextMv[0]
            nextX = curr[1] + nextMv[1]
            if nextX < 0 or nextX >= c or nextY < 0 or nextY >= r:
                continue
            elif meltTime[nextY][nextX] != -1:
                continue
            que.put((nextY, nextX, curr[2] + 1))
            meltTime[nextY][nextX] = curr[2] + 1
    return meltTime


"""
백조가 다른 백조를 찾기 위한 시간 구해서 리턴
swanCanMeet(r: 호수의 세로 크기, c: 호수의 사로 크기, start: 시작점, dest: 도착점, meltTime: 각 얼음이 녹는데 걸리는 시간)
"""
def swanCanMeet(r: int, c: int, start: tuple, dest: tuple, meltTime: list):
    que:list = []
    visited:list = [[float("inf")] * C for _ in range(R)]
    heapq.heappush(que, (0, start[0], start[1]))
    visited[start[0]][start[1]] = 0

    while que:
        curr = heapq.heappop(que)
        if curr[1:] == dest:
            # 만약 목표점에 도달했다면 return
            return curr[0]
        for nextMv in way:
            nextY = curr[1] + nextMv[0]
            nextX = curr[2] + nextMv[1]
            if nextX < 0 or nextX >= c or nextY < 0 or nextY >= r:
                continue
            elif visited[nextY][nextX] <= curr[0] + 1:
                # 앞으로 이동한 시간보다 짧으면 이동
                continue
            if meltTime[nextY][nextX] <= curr[0]:
                heapq.heappush(que, (curr[0], nextY, nextX))
                visited[nextY][nextX] = curr[0]
            else:
                heapq.heappush(que, (meltTime[nextY][nextX], nextY, nextX))
                visited[nextY][nextX] = meltTime[nextY][nextX]
    return visited[dest[0]][dest[1]]


if __name__ == "__main__":
    R, C = map(int, input().strip().split())
    swanPos = []
    que = Queue()
    meltTime: list = [[-1] * C for _ in range(R)]
    for i in range(R):
        inputLine = input().strip()
        for j in range(C):
            if inputLine[j] == '.':
                que.put((i, j, 0))
                meltTime[i][j] = 0
            if inputLine[j] == 'L':
                que.put((i, j, 0))
                swanPos.append((i, j))
                meltTime[i][j] = 0

    meltTime = getMeltTime(R, C, meltTime, que)
    print(swanCanMeet(R, C, swanPos[0], swanPos[1], meltTime))
