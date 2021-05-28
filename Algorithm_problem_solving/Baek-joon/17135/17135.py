import copy
from sys import stdin
from queue import Queue

input = stdin.readline

"""
각 병사 마다 어느 칸에서 사정거리가 되는지 확인한다.
모든 칸에서 제거할 수 있는 병사의 수를 카운트 한다.
"""

way = ((0, -1), (-1, 0), (0, 1))


class DefenceGame:
    def __init__(self, N, M, D):
        self.N = N
        self.M = M
        self.D = D
        self.archorList = []
        self.board = None

    """
    재귀로 배치될 수 있는 궁수의 모든 위치를 반환한다.
    """
    def archorPos(self, archors, pos):
        if len(archors) == 3:
            temp = copy.deepcopy(archors)
            self.archorList.append(temp)
        else:
            for i in range(pos, self.M):
                archors.append(i)
                self.archorPos(archors, i + 1)
                archors.pop()

    """
    매개변수로 받은 궁수의 위치에서 다음 목표를 bfs방식으로 탐색
    """
    def targeting(self, archor):
        visited = [[False] * self.M for _ in range(self.N + 1)]
        queue = Queue()
        queue.put((self.N - 1, archor, 1))
        visited[self.N][archor] = True
        while not queue.empty():
            curr = queue.get()
            if curr[2] > self.D:
                return -1, -1
            if self.board[curr[0]][curr[1]] == 1:
                return curr[0], curr[1]
            for nextMv in way:
                nextY = nextMv[0] + curr[0]
                nextX = nextMv[1] + curr[1]
                if nextX < 0 or nextY < 0 or nextX >= M or nextY >= N:
                    continue
                elif visited[nextY][nextX]:
                    continue
                queue.put((nextY, nextX, curr[2] + 1))
                visited[nextY][nextX] = True
        return -1, -1

    """
    게임을 하여 모든 병사가 사라질 때까지 최대 몇명을 없앨 수 있는지 센다.
    """
    def runGame(self):
        self.archorPos([], 0)
        maxKill = 0  # 최대로 제거 할 수 있는 병사 수
        for archors in self.archorList:
            kill = 0  # 현재의 궁수 위치에서 제거할 수 있는 병사 수
            self.board = copy.deepcopy(board)
            while True:
                target = set()  # 각 궁수가 제거하는 병사
                for archor in archors:
                    temp = self.targeting(archor)
                    if temp != (-1, -1):
                        target.add(self.targeting(archor))
                for item in target:
                    if self.board[item[0]][item[1]] == 1:
                        self.board[item[0]][item[1]] = 0
                        kill += 1
                if self.moveInfantry():
                    break
            maxKill = max(maxKill, kill)
        return maxKill

    """
    턴의 끝에 병사들을 한칸씩 전진 시킨다.
    만약 True가 반환하면 병사가 안 남았다는 뜻이므로 게임 끝
    """
    def moveInfantry(self):
        flag = True
        for i in range(self.N - 1, -1, -1):
            for j in range(M):
                if self.board[i][j] == 1:
                    flag = False
                    self.board[i + 1][j] = 1
                    self.board[i][j] = 0
        return flag


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
