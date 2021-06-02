# https://www.acmicpc.net/problem/2636

from sys import stdin
import heapq

input = stdin.readline

WAY = ((-1, 0), (1, 0), (0, -1), (0, 1))

"""
치즈가 완전히 녹을 때까지의 시간과, 완전히 녹기 직전에 치즈의 갯수를 리턴한다.
매개변수 : 세로, 가로, 치즈의 모양
리턴 : (치즈가 다 녹을때까지 걸리는 시간, 마지막 남은 치즈의 크기)
"""
def meltingChease(row, col, cheese):
    timeForMelting = [[-1] * col for _ in range(row)]  # 해당 칸에 있는 치즈가 녹는데 걸리는 시간
    que = []
    heapq.heappush(que, (0, 0, 0))  # 1번째, 녹는데 걸리는 시간, 2번째, y좌표, 3 x좌표
    timeForMelting[0][0] = 0
    while que:
        curr = heapq.heappop(que)
        for nextMv in WAY:
            nextY = nextMv[0] + curr[1]
            nextX = nextMv[1] + curr[2]
            if nextX < 0 or nextY < 0 or nextX >= col or nextY >= row:
                continue
            elif timeForMelting[nextY][nextX] != -1:
                continue
            # else 만약 치즈나 빈 공간일때
            if cheese[nextY][nextX] == 0:
                heapq.heappush(que, (curr[0], nextY, nextX))
                timeForMelting[nextY][nextX] = curr[0]
            else:
                heapq.heappush(que, (curr[0] + 1, nextY, nextX))
                timeForMelting[nextY][nextX] = curr[0] + 1

    # 만약 치즈가 아예 없었을 때
    if curr[0] == 0:
        return 0, 0
    # else
    lastCheese = 0  # 마지막에 남은 치즈의 갯수
    for i in range(row):
        for j in range(col):
            # 만약 처음부터 빈 공간인 경우는 녹지 않지만, 그 공간까지 차는데 시간이 걸려서 그것을 제외
            if timeForMelting[i][j] == curr[0] and cheese[i][j] != 0:
                lastCheese += 1
    return curr[0], lastCheese


if __name__ == "__main__":
    row, col = map(int, input().strip().split())  # 세로, 가로
    cheese = [[0] * col for _ in range(row)]  # 치즈의 모양
    for i in range(row):
        inputLine = list(map(int, input().strip().split()))
        for j in range(col):
            cheese[i][j] = inputLine[j]
    spendTime, lastCheese = meltingChease(row, col, cheese)
    print(spendTime)
    print(lastCheese)
