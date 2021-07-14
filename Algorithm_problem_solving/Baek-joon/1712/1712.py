# https://www.acmicpc.net/problem/1712
import math
from sys import stdin

input = stdin.readline


def getBreakEvenPoint(a, b, c):
    if c <= b:
        return -1
    cnt = a / (c - b)
    answer = math.ceil(cnt)
    return answer + 1 if answer == math.floor(cnt) else answer


if __name__ == "__main__":
    A, B, C = map(int, input().strip().split())
    print(getBreakEvenPoint(A, B, C))
