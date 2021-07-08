# https://www.acmicpc.net/problem/1495
from sys import stdin

input = stdin.readline

if __name__ == "__main__":
    N, S, M = map(int, input().strip().split())
    volumns = list(map(int, input().strip().split()))
    dp = [[False] * (M + 1) for _ in range(N + 1)]      # 각 단계마다 가능한 불륨을 True로 체크한다.
    answer = -1     # 출력될 값. 마지막에 일치하는 값이 없었다면 -1을 반환

    # base케이스
    dp[0][S] = True

    # 점화식
    for i in range(N):
        for j in range(M + 1):
            if dp[i][j] and 0 <= j + volumns[i] <= M:
                dp[i + 1][j + volumns[i]] = True
            if dp[i][j] and 0 <= j - volumns[i] <= M:
                dp[i + 1][j - volumns[i]] = True
    
    # 만약 마지막에 True로 마크 되어 있는 것중 가장 큰 겂을 찾는다.
    for i in range(M + 1):
        if dp[N][i]:
            answer = i

    print(answer)
