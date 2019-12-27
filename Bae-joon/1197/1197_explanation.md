## 백준 1197

https://www.acmicpc.net/problem/1197

### 문제

*그래프가 주어졌을 때, 그 그래프의 최소 스패닝 트리를 구하는 프로그램을 작성하시오.*

*최소 스패닝 트리는, 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중에서 그 가중치의 합이 최소인 트리를 말한다.*

### 입력

*첫째 줄에 정점의 개수 V(1 ≤ V ≤ 10,000)와 간선의 개수 E(1 ≤ E ≤ 100,000)가 주어진다. 다음 E개의 줄에는 각 간선에 대한 정보를 나타내는 세 정수 A, B, C가 주어진다. 이는 A번 정점과 B번 정점이 가중치 C인 간선으로 연결되어 있다는 의미이다. C는 음수일 수도 있으며, 절댓값이 1,000,000을 넘지 않는다.*

*최소 스패닝 트리의 가중치가 -2147483648보다 크거나 같고, 2147483647보다 작거나 같은 데이터만 입력으로 주어진다.*

### 출력

*첫째 줄에 최소 스패닝 트리의 가중치를 출력한다.*



***

### 풀이

문제 이름 그대로 최소 스패닝 트리 문제이다.



최소 스패닝 트리는 머리 속에 있었지만, 직접 짜본 경험은 없어서 한번 직접 짜보았다.

최소 스패닝 트리를 구하는 알고리즘은 크루스칼 알고리즘, 프림즈 알고리즘 2가지가 있지만, 이번에는 크루스칼 알고리즘을 기반으로 설계해 보았다.



크루스칼 알고리즘이란, 간선의 가중치를 중심으로 정렬을 한 다음에 가중치가 작은 순서대로 선택하지만, 매 간선의 선택 순간마다 사이클이 만들어지는지 확인을 해야 하는 알고리즘이였다.



그래서 일단 처음에는 간단하게 생각을 해서 코드를 짜봤다.

1. 가중치가 작은 순서대로 모두 정렬을 한다.
2. 가중치가 작은 간선을 선택한다.
3. 양측 간선의 끝이 집합 S에 들어가 있는지 확인.
   1. 만약 들어가 있다면 사이클이 이루어 진다고 생각하고 선택하지 않는다
   2. 들어가 있지 않다면 사이클이 이루어지지 않는다고 생각하고 가중치의 합을 더하고 집합 S에 정점을 넣고 간선의 가중치를 더한다
4. 더이상 반복할 수 없을때까지 2번으로 가서 반복

```python
S = []
weight = 0
leng = len(matrix)

for i in range(leng) :
    if(i==0) :
        weight+=matrix[i][0]
        S.append(matrix[i][1]);
        S.append(matrix[i][2]);
        continue;
    Sin1 = matrix[i][1] in S
    Sin2 = matrix[i][2] in S
    if (Sin1 and Sin2) :
        continue;
    else :
        if(Sin1==False) :
            S.append(matrix[i][1]);
        if(Sin2==False) :
            S.append(matrix[i][2]);
        weight += matrix[i][0]
```

하지만, 당연히 오답이였다.



반례)

<img src=".\그림1.png" alt="그림1" style="zoom:67%;" />

이렇게 생긴 그래프일 경우 이미 (1,2), (3,4)을 선택 했다고 생각해 보자 다음 간선으로 (2,3)을 선택해야 하지만 이미 양 정점 2,3이 집합 S에 들어가 있으므로 선택되지 않는다.

즉, 아예 신장 트리를 만들지 않는다.



그래서 공책을 더 찾아본 결과 유니온 파인트 기법을 기억해냈다.

유니온 파인드 기법이란 각 정점이 이어져 있는 그룹으로 나눈 다음에 그룹이 다르다면 양 간선이 이어져 있지 않다고 판단하는 기법이였다.



유니온 파인드 기법을 이용하면 위의 예시는 정점 1,2의 그룹과 정점 3,4의 그룹이 다르게 묶여있기 때문에 간선 2,3이 정상적으로 선택이 된다.



유니온 파인드의 코드

```python
parent = [i for i in range(n+1)]
level = [i for i in range(n+1)]

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
```

위의 코드를 이용해서 같은 그룹인지 아닌지 판단하는 코드를 짜주면 된다.




### 전체 코드

```c++
def main() :
    n,m = input().split()

    n = int(n)
    m = int(m)

    matrix =[];

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
            continue;
        else :
            if merge(matrix[i][1], matrix[i][2], level, parent) :
                weight += matrix[i][0]


    print(weight)

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
```

