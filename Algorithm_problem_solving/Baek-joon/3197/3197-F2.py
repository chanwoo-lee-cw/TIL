# https://www.acmicpc.net/problem/3197
from sys import stdin
from queue import Queue

input = stdin.readline
way = ((-1, 0), (1, 0), (0, -1), (0, 1))

"""
각 얼음들이 녹는 시간을 계산
getMeltTime(r: 호수의 세로 크기, c: 호수의 사로 크기,, meltTime: 호수의 각 부분이 녹는 시간, que: Queue())
return 각 얼음이 녹는데 걸리는 시간, 마지막 얼음이 녹는 시간
"""
def getMeltTime(r: int, c: int, meltTime: list, que: Queue()) -> (list, int):
    maxMeltTime = 0
    while not que.empty():
        curr = que.get()
        maxMeltTime = max(maxMeltTime, curr[2])
        for nextMv in way:
            nextY = curr[0] + nextMv[0]
            nextX = curr[1] + nextMv[1]
            if nextX < 0 or nextX >= c or nextY < 0 or nextY >= r:
                continue
            elif meltTime[nextY][nextX] != -1:
                continue
            que.put((nextY, nextX, curr[2] + 1))
            meltTime[nextY][nextX] = curr[2] + 1
    return meltTime, maxMeltTime

"""
각 얼음들이 녹는 시간을 계산
swanCanMeet(r: 호수의 세로 크기, c: 호수의 가로 크기, start: 한 백조의 위치, dest:다른 백조의 위치, meltTime:각 얼음이 녹는데 걸리는 시간, time:이동할 수 있는 시간)
return 각 얼음이 녹는데 걸리는 시간, 마지막 얼음이 녹는 시간
"""
def swanCanMeet(r: int, c: int, start:tuple, dest:tuple, meltTime:list, time:int):
    que = Queue()
    visited = [[False] * C for _ in range(R)]
    que.put(start)
    visited[start[0]][start[1]] = True

    while not que.empty():
        curr = que.get()
        if curr == dest:
            return True
        for nextMv in way:
            nextY = curr[0] + nextMv[0]
            nextX = curr[1] + nextMv[1]
            if nextX < 0 or nextX >= c or nextY < 0 or nextY >= r:
                continue
            elif visited[nextY][nextX] == True or meltTime[nextY][nextX] > time:
                continue
            que.put((nextY, nextX))
            visited[nextY][nextX] = True
    return False

"""
바이너리 서치로 최소 시간을 찾는다.
"""
def findMinTime(r: int, c: int, swanPos:list, meltTime:list, maxMeltTime:int):
    s = 0
    e = maxMeltTime
    answer = maxMeltTime
    while s < e:
        m = (s + e) // 2
        if swanCanMeet(r, c, swanPos[0], swanPos[1], meltTime, m):
            answer = m
            e = m
        else:
            s = m + 1
    return answer


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

    meltTime, maxMeltTime = getMeltTime(R, C, meltTime, que)
    print(findMinTime(R, C, swanPos, meltTime, maxMeltTime))
