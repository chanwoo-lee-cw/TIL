# https://www.acmicpc.net/problem/1929
from sys import stdin

input = stdin.readline

"""
에라토스테네스의 체로 소수인지 체크
eratos(n) : n - 원하는 수까지 소수인지 체크
"""
def eratosthenes(n : int) -> list:
    isPrime = [True] * (n + 1)
    isPrime[0] = isPrime[1] = False
    sqrtN = int(n ** 0.5) + 1
    for i in range(2, sqrtN):
        if isPrime[i]:
            for j in range(i ** 2, n + 1, i):
                isPrime[j] = False
    return isPrime


if __name__ == "__main__":
    M, N = map(int, input().strip().split())
    isPrime : list = eratosthenes(N)
    output = []
    for i in range(M, N + 1):
        if isPrime[i]:
            output.append(str(i))
    print('\n'.join(output))
