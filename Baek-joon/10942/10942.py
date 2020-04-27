import sys

input = sys.stdin.readline

n = int(input())

d = [int(i) for i in input().split()]
dp = [[0 for _ in range(n)] for _ in range(n)]


for i in range(n):
    dp[i][i] = 1

for i in range(n-1):
    if d[i] == d[i+1]:
        dp[i][i+1] = 1

for l in range(2,n):
    for i in range(n-l):
        if d[i] == d[i+l] and dp[i+1][i+l-1] == 1:
            dp[i][i+l] = 1

qn = int(input())

for _ in range(qn):
    i,j = [int(a) for a in sys.stdin.readline().split()]
    print(dp[i-1][j-1])