# https://www.acmicpc.net/problem/22113
from sys import stdin
from queue import Queue

input = stdin.readline


class Cattle:
    def __init__(self, n, m, wall):
        self.n = n
        self.m = m
        self.wall = wall
        self.way = ((0, -1), (-1, 0), (0, 1), (1, 0))
        self.roomNum = [[0] * n for _ in range(m)]
        self.roomAdjust = []

    def findRoomNum(self):
        roomCnt = 1
        visited = [[False] * n for _ in range(m)]
        roomSize = [0]
        maxRoomSize = 0
        adjust = [[]]

        for i in range(self.m):
            for j in range(self.n):
                if self.roomNum[i][j] != 0:
                    continue
                answer = self.findRoomSize(i, j, roomCnt, visited)
                roomSize.append(answer[0])
                maxRoomSize = max(maxRoomSize, answer[0])
                adjust.append(answer[1])
                roomCnt += 1

        maxUnionSize = 0
        for idx in range(roomCnt):
            for item in adjust[idx]:
                maxUnionSize = max(maxUnionSize, roomSize[idx] + roomSize[item])
        print(roomCnt - 1)
        print(maxRoomSize)
        print(maxUnionSize)

    def findRoomSize(self, y, x, roomCnt, visited):
        que = Queue()
        que.put((y, x))
        visited[y][x] = True
        roomSize = 0
        adjust = set()

        while not que.empty():
            curr = que.get()
            self.roomNum[curr[0]][curr[1]] = roomCnt
            roomSize += 1
            for i in range(4):
                nextY = self.way[i][0] + curr[0]
                nextX = self.way[i][1] + curr[1]
                if nextY < 0 or nextY >= self.m or nextX < 0 or nextX >= self.n:
                    continue
                if self.roomNum[nextY][nextX] != 0 and roomCnt != self.roomNum[nextY][nextX]:
                    adjust.add(self.roomNum[nextY][nextX])
                if visited[nextY][nextX]:
                    continue
                if self.wall[curr[0]][curr[1]] & 2 ** i:
                    continue

                que.put((nextY, nextX))
                visited[nextY][nextX] = True

        return roomSize, adjust


if __name__ == "__main__":
    n, m = map(int, input().strip().split())
    wall = []
    for i in range(m):
        wall.append(list(map(int, input().strip().split())))

    cattle = Cattle(n, m, wall)
    cattle.findRoomNum()
