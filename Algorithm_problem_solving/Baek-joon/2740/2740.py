# https://www.acmicpc.net/problem/2740
import sys
input = sys.stdin.readline

N, M = map(int, input().split())
A = [[0]*M for _ in range(N)]

for i in range(N):
    temp = list(map(int, input().split()))
    for j in range(M):
        A[i][j] = int(temp[j])

M2, K = map(int, input().split())
B = [[0]*K for _ in range(M2)]

for i in range(M2):
    temp = list(map(int, input().split()))
    for j in range(K):
        B[i][j] = int(temp[j])

C = [[0]*K for _ in range(N)]
for i in range(N):
    for j in range(K):
        for k in range(M):
            C[i][j] += A[i][k]*B[k][j]

for items in C:
    for item in items:
        print(item,end=' ') 
    print()