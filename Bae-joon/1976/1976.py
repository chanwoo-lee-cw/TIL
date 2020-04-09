def main() :
    n = int(input())
    m = int(input())

    matrix =[[0 for i in range(n+1)]]

    for i in range(n) :
        temp = input().split()
        temp2 = [0]
        for j in range(n) :
            temp2.append(int(temp[j]))
        matrix.append(temp2)

    parent = [i for i in range(n+1)]
    level = [i for i in range(n+1)]

    for i in range(1, n + 1) :
        for j in range(1, n + 1):
            if(matrix[i][j]==1) :
                merge(i, j, level, parent)

    temp = input().split()
    travel = 0
    for i in range(len(temp)) :
        temp[i]=int(temp[i])
        if(i==0) :
            travel=find(temp[i],parent)
        else :
            if(travel != find(temp[i], parent)) :
                print("NO")
                return
    print("YES")

def find(u,parent) :
    if u==parent[u] :
        return u;
    parent[u] = find(parent[u],parent);
    return parent[u];

def merge(u, v,level,parent) :
    u = find(u,parent)
    v = find(v,parent)
    if (u == v) :
        return False
    if (level[u] > level[v]) :
        temp =u
        u=v
        v=temp
    parent[u] = v
    if (level[u] == level[v]) :
        level[v]+=1
    return True

main()