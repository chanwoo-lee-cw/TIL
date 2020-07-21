import sys
sys.setrecursionlimit(1000000)

input = sys.stdin.readline


def solution(n):
    for i in range(2,n+1):
        dp[i] = dp[i-1] + 1
        if i%3 == 0 and dp[i//3] + 1 < dp[i]:
            dp[i] = dp[i//3] + 1
        if i%2 == 0 and dp[i//2] + 1 < dp[i]:
            dp[i] = dp[i//2] + 1


def output(n):
    if n < 1:
        return float('Inf')
    outputlist.append(n)
    num1 = float('Inf')
    num2 = float('Inf')
    num3 = float('Inf')
    if n % 3 == 0:
        num1 = dp[n//3]
    if n % 2 == 0:
        num2 = dp[n//2]
    num3 = dp[n-1]
    get = min(num1, num2, num3)
    if get==num1:
        output(n//3)
    elif get==num2:
        output(n//2)
    else :
        output(n-1)

if __name__ == "__main__":
    N = int(input().strip())
    dp = [float('Inf')] * (N+1)
    dp[1] = 0
    outputlist = []
    solution(N)
    print(dp[N])
    output(N)
    for item in outputlist:
        print(item, end =' ')
