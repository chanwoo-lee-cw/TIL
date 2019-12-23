n = int(input())

dp = [0,3,7]

for i in range(3,n+1) :
    dp.append((dp[i-2]*3 + (dp[i-1]-dp[i-2])*2)%9901)

print(dp[n])