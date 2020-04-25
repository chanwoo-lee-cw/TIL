# https://www.acmicpc.net/problem/1309
# 동물원

n = int(input())

dp = [0,3,7]

for i in range(3,n+1) :
    # 경우의 수 3가지
    # 1. 사자를 넣는 경우
    # 2. 사자를 왼쪽에 넣는 경우
    # 3. 사자를 오른쪽에 넣는 경우
    dp.append((dp[i-2]*3 + (dp[i-1]-dp[i-2])*2)%9901)

print(dp[n])