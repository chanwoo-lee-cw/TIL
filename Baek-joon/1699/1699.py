import sys
input = sys.stdin.readline

if __name__=="__main__" :
    n = int(input().strip())

    dp = [0] * (n+1)
    # 배열을 원하는 갯수만 목표한 숫자만큼 할당한다.

    dp[1] = 1
    dp[2] = 2
    dp[3] = 3

    for i in range(4, n + 1):
        dp[i] = i
        for j in range(2, i):
            if (j * j) > i:
                break
            dp[i] = min(dp[i], dp[i - j * j] + 1)

    print(dp[n])