# https://www.acmicpc.net/problem/17425
from sys import stdin

input = stdin.readline

if __name__ == "__main__":
    t = int(input().strip())
    tcase = [0] * t
    for i in range(t):
        tcase[i] = int(input().strip())

    maxNum = max(tcase)         # 입력된 테스트 케이스 중 가장 큰 수

    # basecase
    fx = [1] * (maxNum + 1)     # fx[i]: i의 모든 약수의 합
    gx = [0] * (maxNum + 1)     # gx[i]: fx[1] + fx[2] + ... + fx[i-1]
    fx[0] = 0

    # 점화식
    for i in range(2, maxNum + 1):
        for j in range(1, maxNum + 1):
            if i * j > maxNum:
                # 만약 범위 밖으로 나간다면 break
                break
            fx[i * j] += i
    for i in range(1, maxNum + 1):
        gx[i] = gx[i - 1] + fx[i]

    for item in tcase:
        print(gx[item])
