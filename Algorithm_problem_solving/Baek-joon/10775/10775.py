import sys

input = sys.stdin.readline
print = sys.stdout.write

def find(u):
    if (u == parent[u]):
        return u;
    parent[u] = find(parent[u]);
    return parent[u];


def merge(u, v):
    u = find(u)
    v = find(v)

    parent[u] = v


if __name__ == "__main__":
    G = int(input().strip())
    P = int(input().strip())
    parent = [i for i in range(G+1)]
    level = [1] * (G+1)
    ans = 0

    for _ in range(P):
        docking = int(input().strip())
        k = find(docking)
        if k == 0: 
            break
        merge(k, k-1);
        ans = ans + 1;
    print(str(ans))