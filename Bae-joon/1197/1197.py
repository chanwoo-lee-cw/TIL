def main() :
    n,m = input().split()

    n = int(n)
    m = int(m)

    matrix =[]

    for i in range(m) :
        temp = input().split()
        matrix.append([int(temp[2]),int(temp[0]),int(temp[1])]);

    parent = [i for i in range(n+1)]
    level = [i for i in range(n+1)]

    temp = None
    matrix.sort()

    weight = 0
    leng = len(matrix)
    for i in range(leng) :
        if(i==0) :
            weight+=matrix[i][0]
            merge(matrix[i][1],matrix[i][2],level,parent)
            continue
        else :
            if merge(matrix[i][1], matrix[i][2], level, parent) :
                weight += matrix[i][0]


    print(weight)

def find(u,parent) :
    if u==parent[u] :
        return u
    parent[u] = find(parent[u],parent)
    return parent[u]

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