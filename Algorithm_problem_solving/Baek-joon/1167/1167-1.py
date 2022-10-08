from sys import stdin
import heapq

input = stdin.readline

"""
실패: 사유 시간 초과
"""

def get_diameter(v: int, graph: list, visited: list, start: int):
    depth_list = [0]
    visited[start] = True
    for item in range(1, v + 1):
        if visited[item] or graph[start][item] == 0:
            continue
        res: list = get_diameter(v, graph, visited, item)
        heapq.heappush(depth_list, -(-heapq.heappop(res) + graph[start][item]))
    visited[start] = False
    return depth_list


if __name__ == "__main__":
    v: int = int(input())
    graph: list = [[0] * (v + 1) for _ in range(v + 1)]
    visited = [False] * (v + 1)
    for _ in range(v):
        edge = list(map(int, input().split()))
        start, edge = edge[0], edge[1:]
        while edge:
            if edge[0] == -1:
                break
            dist, cost = edge[0], edge[1]
            edge = edge[2:]
            graph[start][dist] = cost

    answer = 0
    for item in range(1, v + 1):
        res = get_diameter(v, graph, visited, item)
        answer = max(answer, -(heapq.heappop(res)))
    print(answer)
