# https://www.acmicpc.net/problem/1197
# 최소 스패닝 트리

matrix =[]

# 유니온 파인드 - 같은 부모를 가지는 노드를 찾는 알고리즘
def find(u,parent) :
    if u==parent[u] :
        return u
    parent[u] = find(parent[u],parent)
    return parent[u]

# 유니온 파인드 - 병합 과정 같은 부모를 가지게 하는 알고리즘
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

def kruskal(n,m,parent,level) :
    # 최소 비용의 간선을 선택하기 위해서 간선들을 가중치의 오름차 순으로 전열
    matrix.sort()
    weight = 0
    # 모든 간선을 찾아본다
    for i in range(m) :
        # 만약 i가 0 이라면 아무 간선도 연결 안 되 있는 것이니 가장 작은 정점을 연결한다.
        if(i==0) :
            merge(matrix[i][1],matrix[i][2],level,parent)
            weight+=matrix[i][0]
        else :
            # 만약 연결해서 사이클이 되는 것이라면 그 간선을 버린다.
            if merge(matrix[i][1], matrix[i][2], level, parent) :
                weight += matrix[i][0]

    return weight

if __name__ == "__main__":
    n,m = input().split()

    n = int(n)
    m = int(m)

    parent = [i for i in range(n+1)]
    level = [i for i in range(n+1)]

    for i in range(m) :
        # 순서을 뒤집은 이유, 간선을 가중치의 순서대로 정렬 하기 위해서, 가중치를 매트릭스의 가장 앞으로 빼주었다.
        temp = input().split()
        matrix.append([int(temp[2]),int(temp[0]),int(temp[1])])

    temp = None

    print(kruskal(n,m,parent,level))