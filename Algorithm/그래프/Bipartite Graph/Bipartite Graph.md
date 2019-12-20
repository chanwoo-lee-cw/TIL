# Bipartite Graph

![img](https://gmlwjd9405.github.io/images/data-structure-graph/bipartite-graph1.gif)

- 인접한 정점끼리 서로 다른 색으로 칠해서 모든 정점을 두 가지 색으로만 칠할 수 있는 그래프.
  - 즉, 그래프의 모든 정점이 두 그룹으로 나눠지고 서로 다른 그룹의 정점이 간선으로 연결되어져 있는(<=> 같은 그룹에 속한 정점끼리는 서로 인접하지 않도록 하는) 그래프를 이분 그래프라고 한다.



### 이분 그래프의 특징

- 이분 그래프인지 확인하는 방법은 BFS, DFS탐색을 이용하면 된다.
- 이분 그래프는 BFS를 할 때 같은 정점끼리는 무조건 같은 색으로 칠해진다.
- 연결 성분의 개수(Connected Component)를 구하는 방법과 유사하다.
- 모든 정점을 방문하며 간선을 검사하기 때문에 시간 복잡도는 O(V+E)로 그래프 탐색 알고리즘과 같다.



### 이분 그래프인지 확인하는 방법

이분 그래프인지 확인하는 방법은 너비 우선 탐색(BFS), 깊이 우선 탐색(DFS)을 이용하면 된다.

- 서로 인접한 정점이 같은 색이면 이분 그래프가 아니다.

1. BFS, DFS로 탐색하면서 정점을 방문할 때마다 두 가지 색 중 하나를 칠한다.
2. 다음 정점을 방문하면서 자신과 인접한 정점은 자신과 다른 색으로 칠한다.
3. 탐색을 진행할 때 자신과 인접한 정점의 색이 자신과 동일하면 이분 그래프가 아니다.
   - BFS의 경우 정점을 방문하다가 만약 같은 레벨에서 정점을 다른 색으로 칠해야 한다면 무조건 이분 그래프가 아니다.
4. 모든 정점을 다 방문했는데 위와 같은 경우가 없다면 이분 그래프이다.

- 이때 주의할 점은 연결 그래프와 비연결 그래프를 둘 다 고려 해야한다는 것이다.
  - 그래프가 비연결 그래프인 경우 모든 정점에 대해서 확인하는 작업이 필요하다.



```java
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<ArrayList<Integer>> arrayLists; // 그래프

    static final int RED = 1;
    static final int BLUE = -1;
    static int[] colors; // 색 {RED 1 or BLUE -1}
    static boolean checkBipartite; // 이분 그래프인지 아닌지 확인

    public static void main(String[] args) {
        int testCase = scanner.nextInt(); // 테스트 케이스

        while (testCase-- > 0) {
            int V = scanner.nextInt(); // 정점의 개수 V (1≤V≤20,000)
            int E = scanner.nextInt(); // 간선의 개수 E (1≤E≤200,000)

            arrayLists = new ArrayList<>();
            colors = new int[V + 1]; // 각 정점의 색을 구분
            checkBipartite = true; // 초기: 이분 그래프이다.

            for (int i = 0; i < V + 1; i++) {
                arrayLists.add(new ArrayList<Integer>()); // 정점의 수 + 1만큼 초기화
                colors[i] = 0; // 방문하지 않은 정점의 색을 0으로 초기화
            }

            // 양방향 그래프 연결
            while (E-- > 0) {
                int v1 = scanner.nextInt();
                int v2 = scanner.nextInt();

                arrayLists.get(v1).add(v2);
                arrayLists.get(v2).add(v1);
            }

            // 이분 그래프: 같은 레벨의 꼭짓점끼리는 무조건 같은 색, 인접한 정점 사이는 다른 색
            // 주의! 연결 그래프와 비연결 그래프(모든 정점을 돌면서 확인) 모두 고려!!
            for (int i = 1; i < V + 1; i++) {
                // 이분 그래프가 아니면 반복문 탈출
                if (!checkBipartite)
                    break;

                // 방문하지 않은 정점에 대해서 탐색 수행
                if (colors[i] == 0) {
//                    dfs(i, RED); /* 깊이 우선 탐색 */
                    bfs(i, RED); /* 너비 우선 탐색 */
                }
            }

            System.out.println(checkBipartite ? "YES" : "NO");
        }

    }

    /* 깊이 우선 탐색 */
    static void dfs(int startV, int color) {
        colors[startV] = color; // 시작 정점의 색을 설정

        for (int adjV : arrayLists.get(startV)) {
            // 시작 정점의 색과 인접한 정점의 색이 같으면 이분 그래프가 아니다.
            if (colors[adjV] == color) {
                checkBipartite = false;
                return;
            }

            // 시작 정점과 인접한 정점이 방문하지 않은 정점이면 dfs 실행
            if (colors[adjV] == 0) {
                // 인접한 정점을 다른 색으로 지정
                dfs(adjV, -color);
            }
        }

    }

    /* 너비 우선 탐색 */
    static void bfs(int startV, int color) {
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(startV); // root 정점을 큐에 삽입
        colors[startV] = color; // root 정점 방문 표시 + 색 표시

        // 큐가 비어있지 않고 이분 그래프 == ture 면 반복
        while (!queue.isEmpty() && checkBipartite) {
            int v = queue.poll(); // 큐에서 정점 추출

            // 해당 정점과 연결된 모든 인접 정점을 방문
            for (int adjV : arrayLists.get(v)) {
                // 방문하지 않은 정점이면
                if (colors[adjV] == 0) {
                    queue.offer(adjV); // 인접 정점을 큐에 삽입
                    colors[adjV] = colors[v] * -1; // 인접한 정점을 다른 색으로 지정
                }
                // 서로 인접한 정점의 색이 같은 색이면 이분 그래프가 아니다.
                else if (colors[v] + colors[adjV] != 0) {
                    checkBipartite = false;
                    return;
                }
            }
        }
    }
}
// https://gmlwjd9405.github.io/2018/08/23/algorithm-bipartite-graph.html
```



### 이분매칭

- 이분 그래프에서 A 그룹의 정점에서 B 그룹의 정점으로 간선을 연결 할 때,
- A그래프의 하나의 정점이 B그래프 하나의 정점만 가지도록 구성된 것이 이분 매칭이다.

<img src="https://t1.daumcdn.net/cfile/tistory/217915475819DCC20C" alt="img" style="zoom:67%;" />



<img src="https://t1.daumcdn.net/cfile/tistory/251C624F5819DCF215" alt="img" style="zoom:67%;" />



이 이분 매칭의 원리는 다음과 같다.



<img src="https://t1.daumcdn.net/cfile/tistory/2654714D5819DEAF19" style="zoom:67%;" />

 우선 a1이 b1을 연결한다



<img src="https://t1.daumcdn.net/cfile/tistory/2658694D5819DEAF15" alt="img" style="zoom:67%;" />

그다음 a2가 b1을 연결하는데 a1과 겹친다. 이때 a1으로 돌아와서 문제를 해결해야 한다.

<img src="https://t1.daumcdn.net/cfile/tistory/244E564D5819DEB021" alt="img" style="zoom:67%;" />

다음과 같이 a1이 b2를 지정하도록 한다.

<img src="https://t1.daumcdn.net/cfile/tistory/2342514D5819DEB032" alt="img" style="zoom:67%;" />

a3가 가리킬 수 있는 첫번째 정점인 b1을 가리키도록한다. 이때 또 a2와 충돌하므로 a2를 해결한다.

<img src="https://t1.daumcdn.net/cfile/tistory/255D9B4D5819DEB110" alt="img" style="zoom:67%;" />

a2가 b2를 보도록 한다. 이때 또 a1과 충돌하니 a1이 또 연결할 수 있는 정점을 확인한다.

<img src="https://t1.daumcdn.net/cfile/tistory/274D324D5819DEB123" alt="img" style="zoom:67%;" />

a1이 b4와 연결될 수 있다.

<img src="https://t1.daumcdn.net/cfile/tistory/2464424D5819DEB207" alt="img" style="zoom:67%;" />

```c++
#include <iostream>
#include <vector>
 
using namespace std;
 
#define MAX_N 201
#define MAX_M 201
 
// A와 B의 정점의 개수
int n, m;
 
// adj[i][j] = Ai와 Bj가 연결되어 있는가?
bool adj[MAX_N][MAX_M];
 
// 각 정점에 매칭된 상대 정점의 번호를 지정한다.
vector<int> aMatch, bMatch;
 
// dfs()의 방문 여부
vector<bool> visited;
 
// A의 정점인 a에서 B의 매칭되지 않은 정점으로 가는 경로를 찾는다.
bool dfs(int a)
{
    if (visited[a])
        return false;
 
    visited[a] = true;
 
    for (int b = 0; b < m; b++)
    {
        if (adj[a][b])
        {
            // b가 매칭되어 있지 않다면 bMatch[b]에서부터 시작해 증가 경로를 찾는다.
            // 매칭되어 있다면 dfs에서 매칭되어있는 A정점이 다른 곳을 매칭 할 수 있는지 본다.
            if (bMatch[b] == -1 || dfs(bMatch[b]))
            {
                // 증가 경로를 발견하였을 때, a와 b를 매치시킨다.(이어준다.)
                aMatch[a] = b;
                bMatch[b] = a;
 
                return true;
            }
        }
    }
 
    return false;
}
 
// aMatch, bMatch 배열을 계산하고 최대 매칭 크기를 반환한다.
int bipartiteMatch()
{
    // -1로 초기화 (어떤 정점과도 연결되어 있지 않다는 의미)
    aMatch = vector<int>(n, -1);
    bMatch = vector<int>(m, -1);
 
    int size = 0;
 
    for (int start = 0; start < n; start++)
    {
        visited = vector<bool>(n, false);
 
        // 깊이 우선 탐색을 이용해 start에서 시작하는 증가 경로를 찾는다.
        if (dfs(start))
            size++;
    }
 
    return size;
}
 
int main()
{
    n = 4; // A 정점 수
    m = 4; // B 정점 수
 
    // A의 정점i와 B의 정점 j가 연결되어 있으면 1로 표시
    adj[0][0] = 1; // 1 == true (adj 타입이 bool)
    adj[0][1] = 1;
    adj[0][3] = 1;
 
    adj[1][0] = 1;
    adj[1][1] = 1;
 
    adj[2][0] = 1;
    adj[2][2] = 1;
 
    adj[3][2] = 1;
    adj[3][3] = 1;
 
    cout << bipartiteMatch() << endl;
 
    return 0;
}


//출처: https://www.crocus.co.kr/499 [Crocus]
```



### 출처

https://gmlwjd9405.github.io/2018/08/23/algorithm-bipartite-graph.html

https://jason9319.tistory.com/149

https://www.crocus.co.kr/499 