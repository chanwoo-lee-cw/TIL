# https://www.acmicpc.net/problem/2798
from sys import stdin

input = stdin.readline
nearestSum = 0      # m에 가장 가까운 수의 합을 저장한다.

"""
백트래킹으로 m에 전부 더했을 때 가장 가까우면서 넘지는 않는 세 수를 찾는다.
n - 카드의 수
m - 원하는 수
cards - 현재 카드의 수
selected - 현재 선택된 카드의 배열
numSum -  selected에 있는 수를 전부 더한 수
pos -조합을 위한 반복문 시작 위치
"""
def getNearestM(n, m, cards, selected, numSum, pos):
    if len(selected) == 3:
        global nearestSum
        if m >= numSum and abs(m - nearestSum) > abs(m - numSum):
            nearestSum = numSum
    else:
        for i in range(pos, n):
            selected.append(cards[i])
            numSum += cards[i]
            getNearestM(n, m, cards, selected, numSum, i + 1)
            numSum -= cards[i]
            selected.pop()


if __name__ == "__main__":
    N, M = map(int, input().strip().split())        # N 카드의 수, M 원하는 수
    cards = list(map(int, input().strip().split())) # 카드의 배열
    selected = []       # 선택된 카드를 저장하는 배열
    getNearestM(N, M, cards, selected, 0, 0)
    print(nearestSum)
