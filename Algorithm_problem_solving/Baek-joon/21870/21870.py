from sys import stdin

input = stdin.readline


def gcd(a, b):
    if b > a:
        a, b = b, a
    while b:
        r = a % b
        a, b = b, r
    return a


def getMaxGcd(n, arr):
    if n == 1:
        return arr[0]
    m = n // 2
    leftAnswer = 0
    left = arr[0]
    for i in range(1, m):
        left = gcd(left, arr[i])
    leftAnswer = left + getMaxGcd(n - m, arr[m:])

    rightAnswer = 0
    right = arr[-1]
    for i in range(1, m):
        right = gcd(right, arr[-(i + 1)])
    rightAnswer = right + getMaxGcd(n - m, arr[:n - m])

    return max(leftAnswer, rightAnswer)


if __name__ == '__main__':
    n = int(input().strip())
    arr = list(map(int, input().strip().split()))
    answer = getMaxGcd(n, arr)
    print(answer)