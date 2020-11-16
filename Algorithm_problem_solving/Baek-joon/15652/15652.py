# https://www.acmicpc.net/problem/15652
N, M = map(int,input().strip().split())
output = []

# 깊이 우선 탐색
def dfs(pos):
    if len(output) == M:
        # 만약 끝에 도달한다면 출력한다.
        for item in output:
            print(f'{item}', end =' ')
        print()
    else:
        # 그 본인도 한번은 찾아 줘야 하니 그 본인 부터 검색
        for i in range(pos, N+1):
            output.append(i)
            dfs(i)
            output.pop()

if __name__ == "__main__":
    dfs(1)