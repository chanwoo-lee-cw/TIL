# https://www.acmicpc.net/problem/1963
import sys
from queue import Queue

input = sys.stdin.readline


def eratos(n):
    primeNum = [True] * (n + 1)
    primeNum[0] = False
    primeNum[1] = False
    for i in range(2, int(n ** 0.5) + 1):
        if primeNum[i]:
            for j in range(i ** 2, n + 1, i):
                primeNum[j] = False
    return primeNum


def shortestDistance(primeNum, start, end):
    visited = [False] * 10000
    answer = 0
    que = Queue()
    que.put((start, 0))
    visited[int(start)] = True
    while not que.empty():
        curr = que.get()
        if curr[0] == end:
            answer = curr[1]
            break
        for i in range(4):
            for j in range(10):
                temp = curr[0][:i] + str(j) + curr[0][i + 1:]
                if 1000 <= int(temp) < 10000 and primeNum[int(temp)] and not visited[int(temp)]:
                    que.put((temp, curr[1] + 1))
                    visited[int(temp)] = True
    return answer


if __name__ == "__main__":
    T = int(input().strip())
    testCase = []
    for i in range(T):
        testCase.append(input().strip().split())
    primeNum = eratos(10000)

    for i in range(T):
        print(shortestDistance(primeNum, testCase[i][0], testCase[i][1]))
