import sys

input = sys.stdin.readline

node = int(input().strip())
edge = int(input().strip())
edgeDic = {}
bfsVisitNode = [False] * (node + 1)


def bfs(pos):
    queue = [pos]
    bfsVisitNode[pos] = True
    infected = -1
    while (queue):
        pos = queue.pop(0)
        infected += 1
        for i in range(1, node + 1):
            if not bfsVisitNode[i] and (pos, i) in edgeDic:
                queue.append(i)
                bfsVisitNode[i] = True

    return infected


if __name__ == "__main__":
    for _ in range(edge):
        a, b = map(int,input().strip().split())
        edgeDic[(a, b)] = True
        edgeDic[(b, a)] = True

    print(bfs(1))
