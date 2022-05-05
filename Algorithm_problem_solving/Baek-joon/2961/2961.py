# 개선의 여지 있음.
# https://www.acmicpc.net/problem/2961
from sys import stdin

input = stdin.readline

diff = []


def dfs(pos, bitter, sour):
    diff.append(abs(bitter - sour))
    for i in range(pos, n):
        this_taste = ingredients[i]
        dfs(i + 1, bitter * this_taste[0], sour + this_taste[1])


if __name__ == '__main__':
    n = int(input())
    ingredients = []
    for _ in range(n):
        ingredients.append(list(map(int, input().split())))

    dfs(0, 1, 0)

    print(min(diff[1:]))
