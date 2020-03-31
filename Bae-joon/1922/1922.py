from sys import stdin
from heapq import heappush,heappop

input = stdin.readline

def find(u, parent):
    if (u == parent[u]):
        return u;
    parent[u] = find(parent[u], parent);
    return parent[u];

def merge(u, v, level, parent):
    u = find(u, parent)
    v = find(v, parent)
    if (u == v):
        return
    if (level[u] > level[v]) :
        temp = u;
        u = v;
        v = temp;

    parent[u] = v

    if(level[u]==level[v]) :
        level[v] = level[v] +1

if __name__=='__main__':
    n = int(input().strip())
    m = int(input().strip())

    parent = [i for i in range(n + 1)]
    level = [1] * (n + 1)
    vertexList =[]
    check = [False] * (n+1)
    check[1] = True
    for i in range(m) :
        vertexList.append(list(map(int,input().strip().split())))

    prim = []
    pos = 1
    result = 0
    while(True) :
        i = 0
        while i < len(vertexList) :
            temp = vertexList[i]
            if temp[0] == pos or temp[1] == pos :
                heappush(prim, [temp[2],temp[0],temp[1]])
                vertexList.remove(temp)
                i = i-1
            i = i + 1
        if(not prim) :
            break
        while(True) :
            temp = heappop(prim)
            u = find(temp[1], parent)
            v = find(temp[2], parent)
            if(u == v) :
                continue
            else :
                break
        result += temp[0]
        merge(temp[1],temp[2],level, parent)
        if(check[temp[1]]) :
            pos = temp[2]
        else :
            pos = temp[1]

    print(result)