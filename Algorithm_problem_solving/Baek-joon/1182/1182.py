# https://www.acmicpc.net/problem/1182
from sys import stdin

input = stdin.readline

"""
dfs로 nCr의 조합을 뽑는다.
만약 모든 배열의 합이 S라면 answer을 하나 더한다.
"""
def dfs(output, r, pos):
    answer = 0  # 리턴될 값
    if len(output) == r:
        if sum(output) == S:
            answer += 1
    else:
        for i in range(pos, N):
            output.append(arr[i])
            answer += dfs(output, r, i + 1)
            output.pop()
    return answer


if __name__ == "__main__":
    N, S = map(int, input().strip().split())    # 수의 갯수, 부분 배열의 합으로 원하는 값
    arr = list(map(int, input().strip().split()))   # 입력된 수의 배열
    answer = 0
    for i in range(1, N + 1):
        output = []
        answer += dfs(output, i, 0)
    print(answer)
