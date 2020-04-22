# https://www.acmicpc.net/problem/1149
import sys
input = sys.stdin.readline

sys.setrecursionlimit(1000000000)

# 다이나믹 프로그래밍 문제

dp = []

def memo(color, pos, coloring, n) :
    # 배열의 마지막에 도달했을 경우, 즉 더이상 계산 할 것이 없을 경우
    if(pos == n-1) :
        return color[pos][coloring]

    # 이미 한번 계산 됬던 집이라면 그냥 그대로 dp에 저장되 있는 값을 반환.
    if(dp[pos][coloring]!=0) :
        return dp[pos][coloring]

    # 바로 전에 전에 색칠한 집이 빨강일 경우, 지금의 위치에선 다른 두 색 중에 무엇으로 칠해야 더 저렴한지 알아낸다.
    if(coloring==0) :
        dp[pos][coloring] = min(memo(color, pos + 1, 1, n), memo(color, pos + 1, 2, n)) + color[pos][coloring]
        return dp[pos][coloring]
    
    # 바로 전에 색칠한 집이 초록색일 경우
    elif (coloring==1) :
        dp[pos][coloring] = min(memo(color, pos + 1, 0, n), memo(color, pos + 1, 2, n)) + color[pos][coloring]
        return dp[pos][coloring]

    # 바로 전에 색칠한 집이 파랑색인 경우
    else :
        dp[pos][coloring] = min(memo(color, pos + 1, 0, n), memo(color, pos + 1, 1, n)) + color[pos][coloring]
        return dp[pos][coloring]

if __name__ == "__main__" :
    n = int(input().strip())
    color = []
    for _ in range(n) :
        temp = input().strip().split()
        for i in range(3) :
            temp[i] = int(temp[i])
        color.append(temp)
    dp = [[0 for _ in range(3)]for _ in range(n)]

    print(min(memo(color, 0, 0, n), memo(color, 0, 1, n), memo(color, 0, 2, n)))