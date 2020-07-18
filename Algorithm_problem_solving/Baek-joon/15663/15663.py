# https://www.acmicpc.net/problem/15663
import sys

input = sys.stdin.readline

def dfs():
    if len(output) == M:
        # 한번 만든 배열인지 확인하기 위해서 딕셔너리에 저장
        # 단 리스트 형식은 Key가 될 수 없으므로 list로 만들어서 저장한다.
        visit[tuple(output)] = True
    else:
        for i in range(N):
            # 중복이 없는 순열이므로 방문한 장소를 표시
            if(dfsvisited[i]):
                continue
            dfsvisited[i] = True
            output.append(numlist[i])
            dfs()
            output.pop()
            dfsvisited[i] = False

if __name__ == "__main__":
    N, M = map(int, input().strip().split())
    numlist = list(map(int, input().strip().split()))
    dfsvisited = [False]*N
    numlist.sort()
    output = []
    # 만든 배열을 저장하기 위핸 딕셔너리
    visit = {}
    dfs()
    for item in visit:
        for element in item:
            print(element, end=' ')
        print()