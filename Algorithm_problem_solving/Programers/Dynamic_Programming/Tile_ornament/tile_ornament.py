def solution(N):
    # base case
    dp = [0]*(N+1)
    dp[1] = 1

    # 점화식
    # 피보나치 순열
    for i in range(2,N+1) :
        dp[i] = dp[i-1] + dp[i-2]

    # N개의 타일로 구성된 타일의 크기는 
    # 가로 : dp[N] + dp[N-1]
    # 세로 : dp[N]
    return 2*(2*dp[N]+dp[N-1])


if __name__ == "__main__":
    N = 3
    print(solution(N))
