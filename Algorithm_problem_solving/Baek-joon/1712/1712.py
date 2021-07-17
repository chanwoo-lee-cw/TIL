# https://www.acmicpc.net/problem/1712
import math
from sys import stdin

input = stdin.readline

"""
손익분기점을 반환한다.
"""
def getBreakEvenPoint(a, b, c):
    if c <= b:
        # 생산비가 이윤보다 크다면 -1
        return -1
    cnt = a / (c - b)
    answer = math.ceil(cnt)
    # 이윤이 생기는 시점이기 때문에 정확히 손익이 일치한다면 +1을 해서 리턴
    return answer + 1 if answer == math.floor(cnt) else answer


if __name__ == "__main__":
    A, B, C = map(int, input().strip().split())
    print(getBreakEvenPoint(A, B, C))
