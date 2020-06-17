def solution(n):
    answer = 0
    dp = [0] * (n+1)
    # base case
    dp[1] = 1
    dp[2] = 2
    # 점화식
    # dp[n] = dp[n-1] + dp[n-2]
        # 예를 들면 n=3이면
        # dp[3] = 3 : |=, |||, =|
        # 이런 형식인데 순서대로 dp[1] |의 오른쪽에 = 가 붙은 모양
        # dp[2] = ||, = 의 오른쪽에 |가 붙은 모양이다.
    for i in range(3,n+1) :
        dp[i] = dp[i-1] + dp[i-2]

    return dp[n]


if __name__ == "__main__":
    print(solution(4))
