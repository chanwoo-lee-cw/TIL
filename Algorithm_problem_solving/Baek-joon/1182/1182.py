# https://www.acmicpc.net/problem/1182
from sys import stdin

input = stdin.readline


def dfs(output, c, pos):
    answer = 0
    if len(output) == c:
        if sum(output) == S:
            answer += 1
    else:
        for i in range(pos, N):
            output.append(arr[i])
            answer += dfs(output, c, i + 1)
            output.pop()
    return answer


if __name__ == "__main__":
    N, S = map(int, input().strip().split())
    arr = list(map(int, input().strip().split()))
    answer = 0
    for i in range(1, N + 1):
        output = []
        answer += dfs(output, i, 0)
    print(answer)
