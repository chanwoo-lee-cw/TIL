import sys

input = sys.stdin.readline

way = [[-1, 0], [0, -1]]

'''
시도1 : 일단 역순으로 갈 수 있는 길을 찾는 방법
    - 안된다 -> 모든 방향으로 전부 갈 수 있어야 하니까.
'''
def dfs(num, y, x):
    if (y, x) == (1, 1):
        matrix[y][x] = num
        matrix[y][x] = 0
        return 1
    if num < 1:
        return 0
    answer = 0
    matrix[y][x] = num
    for next in way:
        nextY = y + next[0]
        nextX = x + next[1]
        if nextY <= 0 or nextX <= 0:
            continue

        answer += dfs(num - 1, nextY, nextX)
    matrix[y][x] = 0
    return answer


if __name__ == "__main__":
    n = int(input())
    answer = 0
    matrix = [[0]*(n+1) for _ in range(3)]
    for i in range(2 * n, 0, -1):
        answer += dfs(i, 2, n)
    print(answer)