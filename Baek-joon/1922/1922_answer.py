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
    vertexDic = {}
    check = [False] * (n+1)
    check[1] = True
    for i in range(m) :
        s,e,w = map(int,input().strip().split())
        vertexDic[s,e] = w

    prim = []
    pos = 1
    result = 0
    flag = False
    while(True) :
        for i in range(1,n+1) :
            if (pos, i) in vertexDic :
                heappush(prim, [vertexDic[pos, i], pos, i])
                del vertexDic[pos,i ]
            if (i, pos) in vertexDic :
                heappush(prim, [vertexDic[i, pos], i, pos])
                del vertexDic[i,pos]
        while(True) :
            if (not prim):
                flag = True
                break
            temp = heappop(prim)
            u = find(temp[1], parent)
            v = find(temp[2], parent)
            if(u == v) :
                continue
            else :
                break
        if(flag) : break
        result += temp[0]
        merge(temp[1],temp[2],level, parent)
        if(check[temp[1]]) :
            pos = temp[2]
            check[pos] = True
        else :
            pos = temp[1]
            check[pos] = True

    print(result)