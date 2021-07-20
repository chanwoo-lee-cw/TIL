# https://www.acmicpc.net/problem/22113
from sys import stdin

input = stdin.readline

if __name__ == "__main__":
    N, M = map(int, input().strip().split())
    bus = list(map(int, input().strip().split()))
    price = [[0] * (N + 1) for _ in range(N + 1)]
    priceSum = 0
    for i in range(N):
        line = list(map(int, input().strip().split()))
        for j in range(N):
            price[i + 1][j + 1] = line[j]

    for i in range(M - 1):
        priceSum += price[bus[i]][bus[i + 1]]
    print(priceSum)
