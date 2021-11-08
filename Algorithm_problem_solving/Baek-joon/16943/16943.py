# https://www.acmicpc.net/problem/16943
# 틀렸습니다. 사유 - 메모리 초과
from sys import stdin

input = stdin.readline


"""dfs로 모든 수의 조합을 전부 실험해 본다."""
def dfs(output: list):
    answer = -1
    if len(output) == n:
        answer = int(''.join(str(item) for item in output))
        return answer if answer < b else -1
    else:
        for i in range(n):
            if visited[i]:
                continue
            elif arrA[i] == 0 and not output:
                continue
            output.append(arrA[i])
            visited[i] = True
            answer = max(answer, dfs(output))
            visited[i] = False
            output.pop()
        return answer


if __name__ == "__main__":
    a, b = input().strip().split()
    arrA = [int(item) for item in a]
    arrB = [int(item) for item in b]
    a = int(a)
    b = int(b)
    n = len(arrA)
    visited = [False] * a

    print(dfs([]))
