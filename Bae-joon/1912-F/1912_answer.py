import sys
input = sys.stdin.readline

n = int(input().strip())
num_list = list(map(int, input().strip().split()))
temp = [0] * n
result = -float('INF')

for i in range(n):
    temp[i] = max(temp[i - 1] + num_list[i], num_list[i])
    result = max(result, temp[i])

print(result)