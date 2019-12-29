# Union Find

Disjoint Set이라고도 불리는 자료구조

- Disjoint Set은 **서로소 집합** 또는 **상호 배타 집합**이라는 의미이다.

Union{x,y}연산을 했을때 x,y는 같은 집합에 속하게 되고 같은 집합에 속한다면 같은 값을 반환하게 된다.



연관이 있는 노드들을 뭉치는 자료 구조이다.



## Union Find 과정

### 초기화 과정(init)

일단 초기 상태는 각 노드가 집합을 이루지 않고 따로 있다.

<img src=".\K-005.png" style="zoom:80%;" />

그렇지만, 각각 노드가 각자 서로의 집합을 이루고 있는 것이라고 생각하면 된다. 즉, 현재 상태의 집합의 갯수는 총 7개 이다.



```python
parent = [i for i in range(n+1)]
level = [i for i in range(n+1)]
```



 ### Union 과정

#### 1.union(1,2)

그렇다면 결과는 이렇게 된다.

<img src=".\K-006.png" style="zoom:80%;" />



1 노드 자식으로 2가 들어가고 2의 부모 노드를 찾는 find를 돌리면 1이 반환된다.

#### 2. union(3,4), union (4,5), union (5,6), union (4,5)

<img src=".\K-007.png" style="zoom:80%;" />

각자 4 노드의 자식 노드가 되었다.



3의 부모 노드를 찾는 find를 돌렸을 때 반환 되는 건 4

5의 부모 노드를 찾는 find를 돌렸을 때 반환 되는 건 4

6의 부모 노드를 찾는 find를 돌렸을 때 반환 되는 건 4



즉, 부모 노드는 무조건 최상위 루트 노드가 반환된다.



#### 3. union(2,5)

일단 각자 노드의 부모 노드를 find한다.



2의 부모 노드는 1

6의 부모 노드는 4



각자 반환된 값을 바탕으로 합친다.

<img src=".\K-008.png" style="zoom:80%;" />



루트 노드 밑으로 1,2,3,5,6 노드가 하위 노드로 속하게 되었고,



1의 부모 노드를 찾는 find를 돌렸을 때 반환 되는 건 4

2의 부모 노드를 찾는 find를 돌렸을 때 반환 되는 건 4

3의 부모 노드를 찾는 find를 돌렸을 때 반환 되는 건 4

5의 부모 노드를 찾는 find를 돌렸을 때 반환 되는 건 4

6의 부모 노드를 찾는 find를 돌렸을 때 반환 되는 건 4

이 때의 집합의 갯수는 2개가 된다.



즉, 루트 노드가 같은 노드들은 find를 했을 때 모두 부모 루트 노드의 값이 반환 된다.


반환 된 값을 바탕으로 union함수를 했을 경우 만약 부모 노드가 같다면 이미 같은 집합이라는 의미로 False를 반환하고, 부모 노드가 다르다면 True값이 반환 되고 Union을 하게 된다.



#### Union 과정의 문제점

<img src=".\K-009.png" style="zoom:80%;" />

하지만, 트리의 공통적인 문제점이 있다.

만약 Union(1,2),Union(2,3),Union(3,4),Union(4,5),Union(5,6)을 했을 때, 이런 식으로 트리의 장점은 사라지고 시간 복잡도가 그대로 N이 되는 문제가 있는데,

이런 경우를 방지해서 만약 트리 레벨을 정해주고 더 짧은 방향으로 붙혀 주는 코드가 필요하다



```python
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





### 참고한 자료



출처: https://www.crocus.co.kr/683 [Crocus]