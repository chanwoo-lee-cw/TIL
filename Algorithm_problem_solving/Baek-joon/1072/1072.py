import sys
import math

input = sys.stdin.readline


def solutuin():
    min_win = 0
    max_win = X
    result = float("inf")
    if Z >= 99:
        print(-1)
        return
    while max_win - min_win >= 0:
        mid = (min_win + max_win)//2
        rating = 100 * (Y + mid) / (X + mid)
        if math.floor(rating - Z) != 0:
            result = min(mid, result)
        if rating - Z > 1:
            max_win = mid - 1
        else:
            min_win = mid + 1
    print(result)


if __name__ == "__main__":
    X, Y = map(int, input().strip().split())
    Z = 100 * Y // X
    solutuin()
