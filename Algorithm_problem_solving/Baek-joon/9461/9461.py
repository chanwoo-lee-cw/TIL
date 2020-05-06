# https://www.acmicpc.net/problem/1014

# DP[i]=DP[i-3]+DP[i-2]

t = int(input())
test = []
DP = [1, 1, 1, 2, 2, 3, 4, 5, 7, 9]

for i in range(t) :
    test.append(int(input()))

for i in range(10,max(test)) :
    DP.append(DP[i-3]+DP[i-2])

for i in range(t) :
    print(DP[test[i]-1])