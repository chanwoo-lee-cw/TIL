# https://www.acmicpc.net/problem/2581
import sys

input = sys.stdin.readline


def erasto(n):
    primeList = [True] * (n + 1)
    primeList[0] = primeList[1] = False
    for i in range(2, int(n ** 0.5) + 1):
        for j in range(i ** 2, n + 1, i):
            primeList[j] = False
    return primeList


def get_min_sum_Prime(primeList, n, m):
    minPrime = 0
    sumPrime = 0
    for i in range(m, n + 1):
        if primeList[i]:
            if minPrime == 0:
                minPrime = i
            sumPrime += i

    return sumPrime, minPrime


if __name__ == "__main__":
    m = int(input().strip())
    n = int(input().strip())

    primeList = erasto(n)
    sumPrime, minPrime = get_min_sum_Prime(primeList, n, m)
    if minPrime != 0:
        print(sumPrime)
        print(minPrime)
    else:
        print(-1)
