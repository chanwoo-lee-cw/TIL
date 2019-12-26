# Minimum Spanning Tree



## Spanning tree

Given a connected graph G=(V,E).

a spanning tree of G is a subgraph of a that is tree where set is eduals to V

- 스패닝 트리란, 연결된 그래프중 가장 적은 경우(연결 상태를 우지 할 수 있는 최소 부분 그래프)



### 스패닝 트리의 특징

- DFS, BFS을 이용하여 그래프에서 신장 트리를 찾을 수 있다.
- 탐색 도중에 사용된 간선만 모으면 만들 수 있다.
- 하나의 그래프에는 많은 신장 트리가 존재할 수 있다.
- Spanning Tree는 트리의 특수한 형태이므로 **모든 정점들이 연결** 되어 있어야 하고 **사이클을 포함해서는 안된다.**
- 따라서 Spanning Tree는 그래프에 있는 n개의 정점을 정확히 (n-1)개의 간선으로 연결 한다.



### 스패닝 트리의 활용

- 네트워크에 주로 사용된다.
- 가장 적은 비용으로 모든 지역을 커버하는 전선을 깔고 싶을 때



## Minimum Spanning Tree

- 최소 신장 트리
- 각 간선의 가중치가 주어져 있을 때, 모든 간선의 합이 가장 작은 트리



### MST를 구하는 방법

#### Kurskal's Algorithm

greedy method를 이용해 MST를 구한다



##### 동작
  1. 그래프의 간선들을 가중치의 오름차순으로 정렬한다.
  2. 정렬된 간선들 중에서 가중치가 가장 작은 간선을 선택한다.
  3. 사이클을 이루지 않는지 확인한다.
     1. 이룬다면 현재의 간선을 MST에 추가한다.
     2. 아니라면 다음 간선을 탐색한다.
  4. 반복한다.

![Kruskal](.\Kruskal.webp)

##### 주의점

- 이미 선택된 간선들과 사이클을 이루는지 꼭 확인
  - 사이클을 이룬다면 MST가 아니다.

- 사이클 여부를 확인하는 방법

  - 추가하고자 하는 간선의 양 끝점이 같은 집합에 속해있는지 먼저 검사해야한다.

    -> union-find 알고리즘
    

1. Prim's algorithm









