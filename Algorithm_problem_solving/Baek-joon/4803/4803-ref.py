from sys import stdin
from queue import Queue

input = stdin.readline

# 다른 사람의 풀이
# 방향이 있는 트리일 경우 :: 간선 == 정점 - 1
# 방향이 없는 트리일 경우 :: 간선/2 == 정점 - 1
if __name__ == "__main__":
    testCase = 0
    while True:
        n, m = map(int, input().strip().split())
        if (n, m) == (0, 0):
            break
        con = [[] for _ in range(n + 1)]
        que = Queue()
        visited = [False] * (n + 1)
        cnt = 0
        testCase += 1
        for i in range(m):
            vFrom, vTo = map(int, input().strip().split())
            con[vFrom].append(vTo)
            con[vTo].append(vFrom)

        for i in range(1, n + 1):
            # 방문 한적 없는 노드는 방문
            # cnt에 의해 연결 요소의 개수가 정해진다.
            # 연결 요소의 개수 :: 트리 + 사이클
            if visited[i]:
                continue
            edge = 0
            vertex = 0
            cnt += 1
            que.put(i)
            while not que.empty():
                here = que.get()
                # 방문한 적이 있는 정점이면, 정점에 추가해주지 않는다.
                if visited[here]:
                    continue
                vertex += 1
                visited[here] = True
                # 양방향 그래프의 간선에 추가된다.
                for item in con[here]:
                    edge += 1
                    que.put(item)
            # 트리이기에 의해서는 '간선 / 2 == 정점 - 1' 이여야한다.
            if edge // 2 != vertex - 1:
                cnt -= 1

        if cnt >= 2:
            print(f'Case {testCase}: A forest of {cnt} trees.')
        elif cnt == 1:
            print(f'Case {testCase}: There is one tree.')
        else:
            print(f'Case {testCase}: No trees.')

# 출처: https://www.crocus.co.kr/630?category=159837