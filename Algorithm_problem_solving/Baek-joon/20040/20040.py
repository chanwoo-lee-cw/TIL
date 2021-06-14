from sys import stdin

input = stdin.readline

"""
몇번째에 사이클이 생기는지 확인한다.
"""
class CicleGame:
    def __init__(self, n, m, edge):
        self.n = n  # 정점의 갯수
        self.m = m  # 간선의 갯수
        self.edge = edge  # 앞으로 이어질 간선
        self.parent = [i for i in range(n)]  # 최상단 노드가 무엇인지
        self.level = [0 for _ in range(n)]  # 현재 노드의 길이

    """
    해당 노드의 최상단 부모를 리턴
    """
    def find(self, u):
        if u == self.parent[u]:
            return u
        self.parent[u] = self.find(self.parent[u])
        return self.parent[u]

    """
    merge 된다면 True 아니면 False를 리턴한다
    """
    def merge(self, u, v):
        u = self.find(u)
        v = self.find(v)
        if u == v:
            return False
        if self.level[u] > self.level[v]:
            u, v = v, u
        self.parent[u] = v
        if self.level[u] == self.level[v]:
            self.level[v] += 1
        return True

    """
    게임을 시작한다. 만약 사이클이 생성된다면 생성되는 턴을 리턴, 전부 이어도 생성되지 않는다면 False 리턴
    """
    def draw(self):
        for idx, (u, v) in enumerate(self.edge):
            if not self.merge(u, v):
                return idx + 1
        return 0


if __name__ == "__main__":
    n, m = map(int, input().strip().split())
    edge = []
    for i in range(m):
        conn = list(map(int, input().strip().split()))
        edge.append(conn)
    game = CicleGame(n, m, edge)
    print(game.draw())
