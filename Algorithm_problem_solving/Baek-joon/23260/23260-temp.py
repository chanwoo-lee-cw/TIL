# https://www.acmicpc.net/problem/23260
from sys import stdin

input = stdin.readline


def ertos(n: int):
    primeNum = [True] * (n + 1)
    sqrtN = int(n ** 0.5)
    for i in range(2, sqrtN + 1):
        if not primeNum[i]:
            continue
        for j in range(i * i, n, i):
            primeNum[j] = False
    return primeNum


def paskal(n: int, k: int):
    if k == 0 or n == k:
        combination[n][k] = 1
        return 1
    if n < 10001 and k < 10001 and combination[n][k] != 0:
        return combination[n][k]
    combination[n][k] = (paskal(n - 1, k - 1) + paskal(n - 1, k)) % 1000000007
    return combination[n][k]


if __name__ == '__main__':
    n, k = map(int, input().strip().split())
    arr = list(map(int, input().strip().split()))
    arr.sort()
    seive = ertos(arr[-1])
    cntPrime = 0
    for item in arr:
        if seive[item]:
            cntPrime += 1
    combination = [[0] * (min(k, 10000) + 1) for _ in range(min(cntPrime, 10000) + 1)]
    answer = paskal(cntPrime, k)
    print(answer)
