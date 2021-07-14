# https://www.acmicpc.net/problem/1695
from sys import stdin, setrecursionlimit

setrecursionlimit(5010)
input = stdin.readline


def dfs(arr, start, end):
    answer = 0
    if dp[start][end] != -1:
        return answer
    elif start >= end:
        return answer
    elif arr[start] == arr[end]:
        answer = dfs(arr, start + 1, end - 1)
    else:
        left = dfs(arr, start + 1, end) + 1
        right = dfs(arr, start, end - 1) + 1
        answer = min(left, right)
    dp[start][end] = answer
    return answer


if __name__ == "__main__":
    N = int(input().strip())
    dp = [[-1] * N for _ in range(N)]
    arr = tuple(map(int, input().strip().split()))
    answer = dfs(arr, 0, N - 1)
    print(answer)
