import sys
import heapq

input = sys.stdin.readline


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
        dist = [float('inf')] * (self.v + 1)
        dist[k] = 0

        que = []
        heapq.heappush(que, (0, k))
        
        while que:
            curr = heapq.heappop(que)
            cost = curr[0]
            curr = curr[1]

            if dist[curr] < cost:
                continue
            elif curr not in self.way:
                continue
            for next in self.way.get(curr):
                nextCost = next[1] + cost
                nextMv = next[0]

                if dist[nextMv] > nextCost:
                    dist[nextMv] = nextCost
                    heapq.heappush(que, (nextCost, nextMv))

        return dist


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
