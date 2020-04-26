# https://www.acmicpc.net/problem/2096
from copy import deepcopy
import sys

input = sys.stdin.readline

n = int(input())

matrix = [[0]*3 for _ in range(2)]
maxMatrix = [[0]*3 for _ in range(2)]
minMatrix =  [[0]*3 for _ in range(2)]

matrix[0] = list(map(int,input().split()))
minMatrix[1] = maxMatrix[0] = deepcopy(matrix[0])
maxMatrix[1] = minMatrix[0] = deepcopy(matrix[0])

for i in range(n-1) :
    matrix[1] = list(map(int,input().split()))
    for j in range(3) :
        if (j == 0):
            maxMatrix[1][j] = deepcopy(max(maxMatrix[0][0:2]) + matrix[1][j])
        elif (j == 2):
            maxMatrix[1][j] = deepcopy(max(maxMatrix[0][1:3]) + matrix[1][j])
        else:
            maxMatrix[1][j] = deepcopy(max(maxMatrix[0]) + matrix[1][j])
    for j in range(3):
        if (j == 0):
            minMatrix[1][j] = deepcopy(min(minMatrix[0][0:2]) + matrix[1][j])
        elif (j == 2):
            minMatrix[1][j] = deepcopy(min(minMatrix[0][1:3]) + matrix[1][j])
        else:
            minMatrix[1][j] = deepcopy(min(minMatrix[0]) + matrix[1][j])

    matrix[0]=deepcopy(matrix[1])
    maxMatrix[0]=deepcopy(maxMatrix[1])
    minMatrix[0] = deepcopy(minMatrix[1])

print(max(maxMatrix[1]),min(minMatrix[1]))