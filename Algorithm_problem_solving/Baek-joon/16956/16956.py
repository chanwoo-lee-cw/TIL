# https://www.acmicpc.net/problem/16956

from sys import stdin

input = stdin.readline

WAY = ((-1, 0), (1, 0), (0, -1), (0, 1))    # 갈 수 있는 방향

"""
해당 늑대 위치 근처에 양이 한마리라도 있으며 True else Fasle
"""
def findNearWolf(r, c, pasture, wolfPos):
    for nextMv in WAY:
        nextY = nextMv[0] + wolfPos[0]
        nextX = nextMv[1] + wolfPos[1]
        if nextY < 0 or nextX < 0 or nextY >= r or nextX >= c:
            continue
        elif pasture[nextY][nextX] == 'S':
            return True
    return False


if __name__ == "__main__":
    R, C = map(int, input().strip().split())  # 세로, 가로
    pasture = [['.'] * C for _ in range(R)]  # 목초지의 형태
    wolves = []  # 늑대의 위치
    answer = False  # 양 근처에 늑대가 한마리라도 있으면 False, 아니면 True

    for i in range(R):
        inputline = input().strip()
        for j in range(C):
            if inputline[j] == '.':
                pasture[i][j] = 'D'
            else:
                pasture[i][j] = inputline[j]
                if pasture[i][j] == 'W':
                    wolves.append((i, j))

    for item in wolves:
        answer = findNearWolf(R, C, pasture, item)
    if answer:
        print(0)
    else:
        print(1)
        for i in range(R):
            for j in range(C):
                print(pasture[i][j], end='')
            print()
