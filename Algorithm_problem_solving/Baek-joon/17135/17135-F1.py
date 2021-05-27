import copy
from sys import stdin
from queue import Queue

input = stdin.readline

"""
각 병사 마다 어느 칸에서 사정거리가 되는지 확인한다.
모든 칸에서 제거할 수 있는 병사의 수를 카운트 한다.
"""

# way = ((0, -1), (-1, 0), (0, 1))
way = ((0, -1), (0, 1), (-1, 0))


class DefenceGame:
    def __init__(self, N, M, D):
        self.N = N
        self.M = M
        self.D = D
        self.archorList = []

    def archorPos(self, archors, pos):
        if len(archors) == 3:
            temp = copy.deepcopy(archors)
            self.archorList.append(temp)
        else:
            for i in range(pos, self.M):
                archors.append(i)
                self.archorPos(archors, i + 1)
                archors.pop()

    def targeting(self, archor):
        visited = [[False] * self.M for _ in range(self.N + 1)]
        queue = Queue()
        queue.put((self.N - 1, archor, 1))
        visited[self.N][archor] = True
        while not queue.empty():
            curr = queue.get()
            if curr[2] > self.D:
                return (-1, -1)
            if self.board[curr[0]][curr[1]] == 1:
                return (curr[0], curr[1])
            for next in way:
                nextY = next[0] + curr[0]
                nextX = next[1] + curr[1]
                if nextX < 0 or nextY < 0 or nextX >= M or nextX >= N:
                    continue
                elif visited[nextY][nextX]:
                    continue
                queue.put((nextY, nextX, curr[2] + 1))
                visited[nextY][nextX] = True

    def runGame(self):
        self.archorPos([], 0)
        maxKill = 0
        for archors in self.archorList:
            kill = 0
            self.board = copy.deepcopy(board)
            target = []
            while True:
                for archor in archors:
                    temp = self.targeting(archor)
                    if temp != (-1, -1):
                        target.append(self.targeting(archor))
                for item in target:
                    if self.board[item[0]][item[1]] == 1:
                        self.board[item[0]][item[1]] = 0
                        kill += 1
                if self.moveInfantry():
                    break
            maxKill = max(maxKill, kill)
        return maxKill

    def moveInfantry(self):
        flag = True
        for i in range(self.N - 1, -1, -1):
            for j in range(M):
                if self.board[i][j] == 1:
                    flag = False
                    self.board[i + 1][j] = 1
                    self.board[i][j] = 0
        return True if flag else False


# https://www.acmicpc.net/problem/17135
if __name__ == "__main__":
    N, M, D = map(int, input().strip().split())
    board = [[0] * M for _ in range(N + 1)]

    for i in range(N):
        inputLine = list(map(int, input().strip().split()))
        for j in range(M):
            board[i][j] = inputLine[j]

    game = DefenceGame(N, M, D)
    print(game.runGame())
