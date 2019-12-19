# Minimum Vertex Cover



### Vertex Cover

- 정점의 부분 집합을 택했을 때 모든 간선에 대하여 두 끝점 중 최소 하나는 이 집합체 포함된다면 이 집합을 Vertex Cover라고 한다.
- 정점 집합 S가 있을 때, **모든 간선은 양 끝점 중 하나가 S에 포함**되어야 한다.



<img src="./그림1.png" alt="그림1" style="zoom:75%;" />

예를 들면 이런 그림의 경우에는 vertex cover에 포함된다.

모든 간선이 집합 S={A,B,C,D,E} 에 적어도 하나의 끝은 걸쳐 있기 때문이다.

<img src="./그림2.png" alt="그림2" style="zoom:75%;" />

이런 경우엔 Vertex Cover에 포함되지 않는다.

모든 간선이 집합 S={D,F} 에 포함되지 않기 때문이다. 즉, A-E의 간선이 포함되지 않기 때문이다.

이런 경우 vertexCover로 만들려면 어떻게 해야 될까. 

<img src="./그림3.png" alt="그림3" style="zoom:75%;" />

이렇게 되면 vertex Cover가 된다. 이제 간선 A-E가 집합 S={D,E,F} 에 포함 되기 때문이다.



### Minimum Vertex Cover

- 정점 집합 S가 있을 때, 모든 간선은 양 끝점 중 **적어도** 하나가 S에 포함 되어야 한다.
- 즉, 집합 S가 최소화 되는 것을 Minimum Vertex Cover 라고 한다.
- 버텍스 커버의 의미와 최소 버텍스 커버의 의미는 **'적어도'**라는 말이 있냐 없냐 차이이다.

<img src="./그림4.png" alt="그림4" style="zoom:75%;" />

집합 S={E,F} 에는 모든 간선이 전부 포함된다.



## König's Theorem

- **이분 그래프에서 최대 매칭은 최소 버텍스 커버와 같다**

1. 임의의 vertex cover는 모든 간선을 cover해야 한다.
2. 임의의 matching은 일부의 간선을 cover한다.
3. 따라서 vertex cover의 갯수는 항상 어떤 matching의 갯수보다 크거나 같다. (각각의 매칭된 간선에서 점을 하나씩 고른다고 생각해보자.)
4. 즉 (Minimum vertex cover) ≥ (Maximum matching).
5. 따라서 어떤 Maximum matching M에 대해서, 크기가 \(|M|\)인 vertex cover를 만든다면 그것이 최소일 것이다.









출처 :

 https://namnamseo.tistory.com/entry/K%C3%B6nigs-theorem

https://www.crocus.co.kr/756