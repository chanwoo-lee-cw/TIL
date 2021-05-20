import sys
import heapq

input = sys.stdin.readline

# 틀렸습니다... 반례가 뭐지
class Dijkstra:
    def __init__(self, v, e):
        self.v = v
        self.e = e
        self.way = {}

    def addLink(self, u, v, w):
        if u in self.way:
            self.way.get(u).append((v, w))
        else:
            self.way[u] = [(v, w)]

    def runDijkstra(self, k):
        distance = [float('inf')] * (self.v + 1)
        distance[k] = 0
        que = []
        heapq.heappush(que, (0, k))

        while que:
            curr = heapq.heappop(que)
            next = self.way.get(curr[1])
            if not next:
                continue
            for item in next:
                if distance[item[0]] > item[1] + curr[0]:
                    distance[item[0]] = item[1] + curr[0]
                    heapq.heappush(que, (distance[item[0]], item[0]))
        return distance


if __name__ == "__main__":
    v, e = map(int, input().strip().split())
    k = int(input())
    dijkstra = Dijkstra(v, e)

    for i in range(e):
        u, v, w = map(int, input().strip().split())
        dijkstra.addLink(u, v, w)
    answer = dijkstra.runDijkstra(k)
    for item in range(1, v+2):
        print(answer[item])
