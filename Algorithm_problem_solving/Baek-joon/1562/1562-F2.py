# https://www.acmicpc.net/problem/1562
from sys import stdin, setrecursionlimit

setrecursionlimit(10000)
input = stdin.readline

output = []

def getBinary(used):
    answer = 0
    for i in range(10):
        if used[i] > 0:
            answer += 2 ** i
    return answer


def dfs(dp, lastNum, digit, used):
    usedBinary = getBinary(used)
    if digit == N:
        if usedBinary == 1023:
            print(output)
            return 1
        else:
            return 0
    answer = 0
    output.append(lastNum)
    if lastNum == 0:
        used[1] += 1
        answer = answer + dfs(dp, 1, digit + 1, used)
        used[1] -= 1
    elif lastNum == 9:
        used[8] += 1
        answer = answer + dfs(dp, 8, digit + 1, used)
        used[8] -= 1
    else:
        used[lastNum + 1] += 1
        answer = answer + dfs(dp, lastNum + 1, digit + 1, used)
        used[lastNum + 1] -= 1
        used[lastNum - 1] += 1
        answer = answer + dfs(dp, lastNum - 1, digit + 1, used)
        used[lastNum - 1] -= 1
    answer %= 1000000000
    dp[digit][lastNum][usedBinary] = answer
    output.pop()
    return answer


if __name__ == "__main__":
    # allSum = 0
    # for N in range(1, 40):
    N = int(input().strip())
    answer = 0
    used = [0] * 10
    dp = [[[-1 for _ in range(1024)] for _ in range(10)] for _ in range(N + 1)]
    # dp[digit][lastNum][binary]
    for i in range(1, 10):
        used[i] += 1
        answer += dfs(dp, i, 1, used)
        used[i] -= 1
    print(answer)
    #     allSum += answer
    # print(allSum)
