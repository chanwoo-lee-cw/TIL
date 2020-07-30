import sys
input = sys.stdin.readline


def find(u):
    if u == parents[u]:
        return u
    parents[u] = find(parents[u])
    return parents[u]


def merge(u, v):
    u_parent = find(u)
    v_parent = find(v)
    if u_parent == v_parent:
        return
    if levels[u_parent] > levels[v_parent]:
        u_parent, v_parent = v_parent, u_parent
    parents[u_parent] = v_parent
    if(levels[u_parent] == levels[v_parent]):
        levels[v_parent] = levels[v_parent] + 1


if __name__ == "__main__":
    N, M = map(int, input().strip().split())
    parents = [i for i in range(N+1)]
    levels = [1 for i in range(N+1)]

    for _ in range(M):
        a, b = map(int, input().strip().split())
        merge(a, b)

    for i in range(N):
        find(i)
    
    print(len(set(parents[1:])))
