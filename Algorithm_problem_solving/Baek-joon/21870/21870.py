from sys import stdin

input = stdin.readline

"""
gcd(a, b) - a와 b의 최대공약수를 계산한다.
"""
def gcd(a, b):
    if b > a:
        a, b = b, a
    while b:
        r = a % b
        a, b = b, r
    return a


"""
getMaxGcd(n, arr) - n길이의 배열 arr에서의 답을 구한다.
"""
def getMaxGcd(n, arr):
    if n == 1:
        return arr[0]
    m = n // 2
    # 좌측에서 GCD를 계산하는 경우
    left = arr[0]
    for i in range(1, m):
        left = gcd(left, arr[i])
    leftAnswer = left + getMaxGcd(n - m, arr[m:])
    # 우측에서 GCD를 계산하는 경우
    right = arr[-1]
    for i in range(1, n - m):
        right = gcd(right, arr[-(i + 1)])
    rightAnswer = right + getMaxGcd(m, arr[:-(n - m)])

    return max(leftAnswer, rightAnswer)


if __name__ == '__main__':
    n = int(input().strip())
    arr = list(map(int, input().strip().split()))
    answer = getMaxGcd(n, arr)
    print(answer)
