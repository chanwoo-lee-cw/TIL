from sys import stdin
from queue import Queue

input = stdin.readline

# 트리의 정의
# 사이클이 없이 모든 정점이 연결되어 있는 그래프
class Tree:
    def __init__(self, n, m, con):
        self.n = n
        self.m = m
        self.con = con

    '''
    트리의 갯수를 세서 반환한다.
    '''
    def findTree(self):
        # 사이클이 생겼는지 확인은 이미 방문한 곳을 재 방문하는 지를 확인
        # 사이클이 생겼는지 확인확인하는 방법 2가지
        # 프림 알고리즘의 정점 기준을 사용하자.
        # 일단 큐를 사용해서 사이클을 찾는 방법
        visited = [False for _ in range(self.n + 1)]
        queue = Queue()
        treeCnt = 0
        for i in range(1, n + 1):
            if visited[i]:
                continue
            queue.put((i, 0))  # 앞 현재 위치, 뒤 온 위치
            visited[i] = True  # 방문 여부
            treeCnt += 1
            while not queue.empty():
                curr = queue.get()
                nextMv = self.con.get(curr[0])
                if not nextMv:
                    continue
                for move in nextMv:
                    if move == curr[1]:
                        continue
                    elif visited[move]:
                        # 만약 사이클이 생겼다면 이 트리는 버린다.
                        queue = Queue()
                        treeCnt -= 1
                        break
                    queue.put((move, curr[0]))
                    visited[move] = True

        return treeCnt


if __name__ == "__main__":
    testCase = 0
    while True:
        testCase += 1
        n, m = map(int, input().strip().split())
        if (n, m) == (0, 0):
            break
        con = {}
        for i in range(m):
            a, b = map(int, input().strip().split())
            if a in con:
                con[a].append(b)
            else:
                con[a] = [b]
            if b in con:
                con[b].append(a)
            else:
                con[b] = [a]
        tree = Tree(n, m, con)
        testTreeCase = tree.findTree()
        if testTreeCase == 0:
            print(f'Case {testCase}: No trees.')
        elif testTreeCase == 1:
            print(f'Case {testCase}: There is one tree.')
        else:
            print(f'Case {testCase}: A forest of {testTreeCase} trees.')
