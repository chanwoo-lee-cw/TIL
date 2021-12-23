# https://www.acmicpc.net/problem/16931
import sys

input = sys.stdin.readline

WAY = ((-1, 0), (1, 0), (0, -1), (0, 1))    # 인접한 블록의 좌표를 구하기 위해

if __name__ == "__main__":
    n, m = map(int, input().strip().split())    # 블록의 가로, 세로 사이즈
    answer = 0  # 겉에서 보이는 블록의 갯수
    blocks = [[0] * (m + 2) for _ in range(n + 2)]  # 각 좌표별 쌓여 있는 블록의 갯수, 외부도 체크하기 위해 한칸씩 빈칸을 둔다.
    
    for i in range(1, n + 1):
        inLine = list(map(int, input().strip().split()))
        for j in range(1, m + 1):
            blocks[i][j] = inLine[j - 1]
    # 위와 아래에서 봤을 때, 블록의 갯수
    for i in range(1, n + 1):
        for j in range(1, m + 1):
            answer += 1 if blocks[i][j] > 0 else 0
    answer *= 2
    # 각 블록을 앞뒤좌우를 봐서 가려지지 않으면 +1을 한다.
    for i in range(1, n + 1):
        for j in range(1, m + 1):
            for k in range(1, blocks[i][j] + 1):
                for next in WAY:
                    nextY = i + next[0]
                    nextX = j + next[1]
                    if blocks[nextY][nextX] < k: 
                        answer += 1
    print(answer)
