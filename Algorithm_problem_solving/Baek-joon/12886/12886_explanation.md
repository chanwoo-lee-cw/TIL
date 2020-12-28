## 백준 12886번 풀이

https://www.acmicpc.net/problem/12886

### 문제

*오늘 강호는 돌을 이용해 재미있는 게임을 하려고 한다. 먼저, 돌 세개는 그룹으로 나누어져 있으며 각각의 그룹에는 돌이 A, B, C개가 있다. 강호는 모든 그룹에 있는 돌의 개수를 같게 만들려고 한다.*

*강호는 돌을 단계별로 움직이며, 각 단계는 다음과 같이 이루어져 있다.*

*크기가 같지 않은 두 그룹을 고른다. 그 다음, 돌의 개수가 작은 쪽을 X, 큰 쪽을 Y라고 정한다. 그 다음, X에 있는 돌의 개수를 X+X개로, Y에 있는 돌의 개수를 Y-X개로 만든다.*

*A, B, C가 주어졌을 때, 강호가 돌을 같은 개수로 만들 수 있으면 1을, 아니면 0을 출력하는 프로그램을 작성하시오.*

### 입력

*첫째 줄에 A, B, C가 주어진다. (1 ≤ A, B, C ≤ 500)*

### 출력

*돌을 같은 개수로 만들 수 있으면 1을, 아니면 0을 출력한다.*



***

### 풀이

- DFS, BFS 문제
- 하지만, 평범하게 bfs등을 짜면 무조건 메모리 초과 발생
  - A+B+C는 **무조건 고정**, 거기다 내 코드에서는 항상 정렬을 하게 해놨기 때문에 A,B만 고려하면 된다.
  - 내가 가장 고민한 부분이 이 부분, 기억해두자.
- A,B,C 중 가장 작은 것에서 나머지 둘을 계산하기 위해서 배열로 항상 정렬.



- 만약 A, B, C 전부 더한게 3의 배수가 아니하면 0 출력
- 만약 A, B, C가 전부 같다면 true 반환
- A, B, C를 오름차 순으로 정렬
- B-A나 C-A 가 0보다 클 때만 진행 → 이유 : X+X이기 때문, 0+0은 변하지 않는다.





### 전체 코드

```java
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

public class Main {

    public static boolean[][] visited;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        in.close();

        int stoneSum = a+b+c;
        visited = new boolean[1001][1001];
        if (stoneSum % 3 != 0)
            System.out.println(0);
        else
            System.out.println(checkMakeSame(stoneSum, a, b, c) ? 1 : 0);
    }

    public static boolean checkMakeSame(int stoneSum, int a, int b, int c) {
        // 일단 수가 전부 같다면 1 반환
        // visited로 방문한 장소 체크 현재 상태에서 방문할 수 있는 곳 방문 했으면 0 반환
        // 만약 y-x가 0보다 작다면 그쪽으로 탐색 x
        // 하나라도 1 반환했으면 그거 반
        // 가장 작은 것만 찾아서 나머지 둘과 계산하면 된다.
        Queue<int[]> queue = new LinkedList<>();
        int[] curr = new int[]{a,b,c};
        int[] next;
        Arrays.sort(curr);
        queue.add(curr);
        visited[curr[0]][curr[1]] = true;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if(curr[0] == curr[1] && curr[1] == curr[2])
                return true;
            if (curr[1] - curr[0] > 0) {
                next = new int[]{curr[0] + curr[0], curr[1] - curr[0], curr[2]};
                Arrays.sort(next);
                if(!visited[next[0]][next[1]]) {
                    queue.add(next);
                    visited[next[0]][next[1]] = true;
                }
            }
            if (curr[2] - curr[0] > 0) {
                next = new int[]{curr[0] + curr[0], curr[1], curr[2] - curr[0]};
                Arrays.sort(next);
                if(!visited[next[0]][next[1]]) {
                    queue.add(next);
                    visited[next[0]][next[1]] = true;
                }
            }
        }
        return false;
    }
}
```


