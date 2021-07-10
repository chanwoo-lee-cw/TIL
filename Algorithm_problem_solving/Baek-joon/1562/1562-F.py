# https://www.acmicpc.net/problem/1562
from sys import stdin, setrecursionlimit

setrecursionlimit(10000)
input = stdin.readline


def getBinary(used):
    answer = 0
    for i in range(10):
        if used[i] > 0:
            answer += 2 ** i
    return answer


def dfs(dp, lastNum, digit, used):
    usedBinary = getBinary(used)
    if dp[digit][lastNum][usedBinary] != -1:
        return dp[digit][lastNum][usedBinary]
    elif digit == N:
        if usedBinary == 1023:
            return 1
        else:
            return 0
    answer = 0

    used[lastNum] += 1
    if lastNum == 0:
        answer = (answer + dfs(dp, 1, digit + 1, used)) % 1000000000
    elif lastNum == 9:
        answer = (answer + dfs(dp, 8, digit + 1, used)) % 1000000000
    else:
        answer = (answer + dfs(dp, lastNum - 1, digit + 1, used)) % 1000000000
        answer = (answer + dfs(dp, lastNum + 1, digit + 1, used)) % 1000000000

    used[lastNum] -= 1
    dp[digit][lastNum][usedBinary] = answer
    return answer


if __name__ == "__main__":
    N = int(input().strip())
    answer = 0
    used = [0] * 10
    dp = [[[-1 for _ in range(1024)] for _ in range(10)] for _ in range(N + 1)]
    # dp[digit][lastNum][binary]
    for i in range(1, 10):
        answer += dfs(dp, i, 0, used)
    print(answer)
