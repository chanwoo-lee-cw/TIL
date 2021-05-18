import heapq

'''
유니온 파인드로 사이클이 만들어 졌는지 확인
'''
def find(u, parents):
    if u == parents[u]:
        return u
    parents[u] = find(parents[u], parents)
    return parents[u]


def union(u, v, parents, levels):
    u = find(u, parents)
    v = find(v, parents)
    if u == v:
        return False
    if levels[u] > levels[v]:
        u, v = v, u
    parents[u] = v
    if levels[u] == levels[v]:
        levels[v] += 1
    return True

'''
모든 도시를 최소의 비용으로 연결하는 방법
'''
def solution(n, costs):
    answer = 0
    parents = [i for i in range(n)]
    levels = [0 for i in range(n)]

    queue = []
    for item in costs:
        heapq.heappush(queue, (item[2], item[0], item[1]))

    while queue:
        curr = heapq.heappop(queue)
        if find(curr[1], parents) == find(curr[2], parents):
            continue
        answer += curr[0]
        union(curr[1], curr[2], parents, levels)

    return answer


if __name__ == "__main__":
    n = 4
    costs = [[0, 1, 1], [0, 2, 2], [1, 2, 5], [1, 3, 1], [2, 3, 8]]
    print(solution(n, costs))
