# https://www.acmicpc.net/problem/1328
# 고층빌딩
import sys
input = sys.stdin.readline

if __name__ == "__main__" :
    n,l,r = map(int,input().strip().split())

    # dp[n][l][r] 빌딩이 n개 있을때, 왼쪽에서 보이는 빌딩의 수가 l개 , 오른쪽에서 보이는 빌딩의 수가 r개
    dp = [[[0]*(r+1) for _ in range(l+1)]for _ in range(n+1)]
    dp[1][1][1] = 1
    # 빌딩의 갯수의 수가 n이 될때까지 반복한다.
    for i in range(2, n + 1):
        for j in range(1, l + 1):
            for k in range(1, r + 1):
                # 가장 큰 빌딩부터 세우는 순서로 역순으로 계산한다.
                # n길이의 빌딩까지 세워져 있다고 가정하였을 때
                # 왼쪽에 n-1빌딩을 세웠을 때, 왼쪽에서 보는 빌딩의 수 증가
                # 오른쪽에 n-1의 빌딩을 세웠을 때, 오른쪽에서 보는 빌딩의 수 증가
                # 가운데에 n-1의 빌딩을 세웠을 때, 보이는 빌딩의 수 변화 없음
                dp[i][j][k] = (dp[i - 1][j][k - 1] + dp[i - 1][j - 1][k] + (i - 2) * dp[i - 1][j][k])%1000000007
    print(dp[n][l][r])