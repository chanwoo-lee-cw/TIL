import sys
import heapq

input = sys.stdin.readline

spred = ((-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1))

"""
해당 나무 리스트에 한 해에 처리되는 경우
"""
def oneYearAgo(trees, arc):
    prduction = {}      # 번식 할 나무의 수
    for item in trees:
        # 봄
        area = trees[item]  # (item[0], item[1]) 자리에 심어 있는 나무 리스트
        reIn = []   # 영양을 빨아드린 나무
        dieTree = 0     # 죽은 나무 -> 즉 다시 영양분으로 바"뀔 수치
        while area:
            tree = heapq.heappop(area)
            if arc[item[0]][item[1]] >= tree:
                arc[item[0]][item[1]] -= tree
                heapq.heappush(reIn, tree + 1)
                if (tree + 1) % 5 == 0:
                    if not (item[0], item[1]) in prduction:
                        prduction[(item[0], item[1])] = 1
                    else:
                        prduction[(item[0], item[1])] += 1
            else:
                dieTree += tree // 2
        trees[item] = reIn
        # 여름
        arc[item[0]][item[1]] += dieTree
    # 가을
    for item in prduction:
        for next in spred:
            nextY = next[0] + item[0]
            nextX = next[1] + item[1]
            if nextY <= 0 or nextX <= 0 or nextY > n or nextX > n:
                continue
            for _ in range(prduction[item]):
                if not (nextY, nextX) in trees:
                    trees[(nextY, nextX)] = [1]
                else:
                    heapq.heappush(trees[(nextY, nextX)], 1)
    # 겨울
    for i in range(n):
        for j in range(n):
            arc[i + 1][j + 1] += a[i + 1][j + 1]


if __name__ == "__main__":
    n, m, k = map(int, input().strip().split())
    a = [[0] * (n + 1) for _ in range(n + 1)]
    arc = [[5] * (n + 1) for _ in range(n + 1)]
    trees = {}
    for i in range(n):
        inputLine = list(map(int, input().strip().split()))
        for j in range(n):
            a[i + 1][j + 1] = inputLine[j]
    inputLine = None
    for _ in range(m):
        x, y, age = map(int, input().strip().split())
        if not (y, x) in trees:
            trees[(y, x)] = [age]
        else:
            heapq.heappush(trees[(y, x)], age)

    for _ in range(k):
        oneYearAgo(trees, arc)
    treeCnt = 0
    for item in trees:
        treeCnt += len(trees[item])
    print(treeCnt)
