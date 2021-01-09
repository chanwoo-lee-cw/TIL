## 백준 9421 풀이

### 문제

*상근이는 감옥에서 죄수 두 명을 탈옥시켜야 한다. 이 감옥은 1층짜리 건물이고, 상근이는 방금 평면도를 얻었다.*

*평면도에는 모든 벽과 문이 나타나있고, 탈옥시켜야 하는 죄수의 위치도 나타나 있다. 감옥은 무인 감옥으로 죄수 두 명이 감옥에 있는 유일한 사람이다.*

*문은 중앙 제어실에서만 열 수 있다. 상근이는 특별한 기술을 이용해 제어실을 통하지 않고 문을 열려고 한다. 하지만, 문을 열려면 시간이 매우 많이 걸린다. 두 죄수를 탈옥시키기 위해서 열어야 하는 문의 개수를 구하는 프로그램을 작성하시오.*



### 입력

*첫째 줄에 테스트 케이스의 개수가 주어진다. 테스트 케이스의 수는 100개를 넘지 않는다.*

*첫째 줄에는 평면도의 높이 h와 너비 w가 주어진다. (2 ≤ h, w ≤ 100) 다음 h개 줄에는 감옥의 평면도 정보가 주어지며, 빈 공간은 '.', 지나갈 수 없는 벽은 '*', 문은 '＃', 죄수의 위치는 '\$'이다.*

*상근이는 감옥 밖을 자유롭게 이동할 수 있고, 평면도에 표시된 죄수의 수는 항상 두 명이다. 각 죄수와 감옥의 바깥을 연결하는 경로가 항상 존재하는 경우만 입력으로 주어진다.*



### 출력

*각 테스트 케이스마다 두 죄수를 탈옥시키기 위해서 열어야 하는 문의 최솟값을 출력한다.*



---

### 풀이

진짜 어려웠던 문제이다.

솔직히 풀면서 전혀 감을 못 잡았었다.



몇가지 방법으로 시도해 봤다.



일단 처음에는 [바이러스 문제](https://www.acmicpc.net/problem/14502)처럼 DFS로 각각 문을 열어 놓고 둘을 탈출 시키는 문제인줄 알았다.

그런데 이 방법은 방의 수도 가로, 세로의 수도 100개고, 열 수 있는 문의 수도 제한이 없다.

당연히 시간 초과

- 시간을 줄이기 위한 생각 : 
  - 외부 문이 하나쯤은 열려 있을때 진행.
  - 한번 나간 경우가 있을 경우, 그 문을 연 횟수보다 적을때만 진행
  - 죄수 1이 나간 경우, 죄수 2의 경로가 죄수 1의 경로에 도달한 경우 죄수 2도 나갈 수 있다는 의미므로 그대로 반환



두번째는 힌트를 본 결과 외부에서 한명을 투입하는 방법이 있다고 했다. 생각하지도 못했다.

그래서 두명을 각각 탈출 시키는 것이 아니라, 한명을 투입해서 문을 열며 돌아다니며 2명을 모두 만날 때까지 진행하는 방법으로 했다.

하지만, 이 방법도 문제가 있었던게, 죄수 2명이 같은 방향으로 갔을때만 카운트가 된다. 즉, 이미 중복 경로를 허용해야 한다.

그런데, 나는 입구가 여러가지 경로가 있다고 가정했고, 최악의 경우에는 100*4^100로 너무 많게 된다.



세번째는, 좀 더 힌트를 본 결과 3명이 모두 만나는 경우를 계산하라고 했다.

즉, 동시에 큐에 넣고 진행하는 와중에 만약 이미 다른 사람이 도달한 경로에 닿은 경우 삭제시키고 그 문을 연 횟수를 더해주고려고 했다.

근데, 큐에 이미 들어가 있는 경우들을 수정하는 방법은 큐에서 모두 빼내고 값을 수정한다음에 다시 넣는 수 밖에 없어서 이 방법도 안됬다.



네번째는 힌트를 확인해본 결과 3명을 각각 진행하고, 만나는 지점을 합치는 방법으로 하였다.

근데 이 방법도 문제가 있던게 나는 외부에서 접근하는 것을 외부와 연결되어 있는 `#`,`.` 는 리스트에 넣고 각각 위치에서 접근하는 방법으로 했다.

즉, bfs를 여러번 사용하지 않기 위해서 비 효율적인 방법을 해야 했다.



그래서 또 혁신을 만났는데 외부에 마진을 만들어 놓고 접근 하는 방식이였다.

그래서 마진을 넣으니 각각 3번째 사람의 경우에는 `{0, 0}` 으로 접근 시키면 손쉽게 문제가 해결되었다.



하지만 여기서 또 잘못한게 있었는데, 난 감옥 내부에서 3명이 만난다고 생각했는데, 이 경우에는 반례가 있었다.

위의 사용했던 1번 방법 빼고는 전부 반례가 된다.

```
1
3 5
*****
#$∗$#
*****
```

이런 경우는 되지도 않는다.



그래서 감옥 외부도 고려하게, 바꿨는데 틀렸습니다 가 나왔다.

하지만 `Queu`e로 계산했던 것을 `PriorityQueue`로 바꿨는데 [벽 부시고 이동하기](https://www.acmicpc.net/problem/2206) 같이 최단 경로를 찾는게 아니라, 벽을 가장 적게 부시는 것이 가장 좋은 것이므로 가장 빠른 경로는 필요 없이 항상 벽을 가장 적게 부시는 것만 계산하면 되기 때문이였다.





### 전체 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Comparator;

// https://www.acmicpc.net/problem/9376
public class Main {
    public static int[][] way = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        int t;      // 테스트 케이스 갯수
        int h, w;   // 높이와 너비
        char[][] prison;
        ArrayList<int[]> prisoners;

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String inputLine;       // 밑에 감옥 정보를 입력 받기 위한 임시 변수
        t = Integer.parseInt(bf.readLine());
        for (int testCase = 0; testCase < t; testCase++) {
            st = new StringTokenizer(bf.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            prison = new char[h + 2][w + 2];    // 외부에서 들어가는 경우이므로 각각 입구를 고려하는 것보다 각 외부 빈 공간을 둔다.
            prisoners = new ArrayList<>();
            for (int i = 1; i <= h; i++) {
                inputLine = bf.readLine();
                for (int j = 1; j <= w; j++) {
                    if (inputLine.charAt(j - 1) == '.') {
                        prison[i][j] = '.';
                    } else if (inputLine.charAt(j - 1) == '*') {
                        prison[i][j] = '*';
                    } else if (inputLine.charAt(j - 1) == '$') {
                        prison[i][j] = '.';
                        prisoners.add(new int[]{i, j});
                    } else {
                        prison[i][j] = '#';
                    }
                }
            }
            System.out.println(getMinDoorOpen(h, w, prison, prisoners));
        }
        st = null;
        bf.close();
    }

    private static int getMinDoorOpen(int h, int w, char[][] prison, ArrayList<int[]> prisoners) {
        int answer = Integer.MAX_VALUE;     // 반환값

        int[][] dist1 = searchDistOpenDoor(h, w, prison, new int[]{0, 0});      // 3번째 사람이 찾으러 들어가는 경우 각 장소마다 문을 열여야 되는 횟수
        int[][] dist2 = searchDistOpenDoor(h, w, prison, prisoners.get(0));     // 1번째 죄수의 경우 각 장소마다 문을 열여야 되는 횟수
        int[][] dist3 = searchDistOpenDoor(h, w, prison, prisoners.get(1));     // 2번째 죄수의 경우 각 장소마다 문을 열여야 되는 횟수
        {
            int openDoorNum;
            // 3사람이 감옥 안에서 만나지 않아도 되니 외부 범위도 포함
            for (int i = 0; i < h + 2; i++) {
                for (int j = 0; j < w + 2; j++) {
                    if (prison[i][j] == '*')
                        continue;
                    openDoorNum = dist1[i][j] + dist2[i][j] + dist3[i][j];      // i,j에서 3명이 만나는 경우 열어야 되는 문의 갯수
                    if (prison[i][j] == '#')    //  그 장소가 문이라면 -2
                        openDoorNum -= 2;
                    answer = Math.min(answer, openDoorNum);
                }
            }
        }
        return answer;
    }

    // 각 사람이 각 장소를 이동하는 경우의 열어야 되는 문의 갯수를 구한다.
    private static int[][] searchDistOpenDoor(int h, int w, char[][] prison, int[] person) {
        int[][] dist = new int[h + 2][w + 2];   // 3번째 사람의 경우 외부에서 시작하기 위해 빈 공간을 둔다.
        for (int i = 0; i < h + 2; i++)
            Arrays.fill(dist[i], Integer.MAX_VALUE);

        PriorityQueue<PersonPosition> priorityQueue = new PriorityQueue<>(new Comparator<PersonPosition>() {
            @Override
            public int compare(PersonPosition o1, PersonPosition o2) {
                return o1.openDoor - o2.openDoor;
            }
        });     // 문을 최소로 여는 것이니 우선순위 큐로 계산한다. - 큐로 방문한 장소라도 문연 횟수가 더 적으면 다시 계산해도 되지만, 우선 순위 큐가 더 효율적
        PersonPosition curr = new PersonPosition(person[0], person[1], 0);
        priorityQueue.add(curr);
        dist[person[0]][person[1]] = 0;
        {
            int nextY, nextX;
            while (!priorityQueue.isEmpty()) {
                curr = priorityQueue.poll();
                for (int[] next : way) {
                    nextY = curr.y + next[0];
                    nextX = curr.x + next[1];
                    if (nextY < 0 || nextX < 0 || nextY > h + 1 || nextX > w + 1
                            || prison[nextY][nextX] == '*' || dist[nextY][nextX] != Integer.MAX_VALUE)
                        continue;
                        // else
                    else if (prison[nextY][nextX] == '#') {
                        priorityQueue.add(new PersonPosition(nextY, nextX, curr.openDoor + 1));
                        dist[nextY][nextX] = curr.openDoor + 1;
                    } else {
                        priorityQueue.add(new PersonPosition(nextY, nextX, curr.openDoor));
                        dist[nextY][nextX] = curr.openDoor;
                    }
                }
            }
        }
        return dist;
    }
}

class PersonPosition {
    public int y;           // 현재 사람의 y위치
    public int x;           // 현재 사람의 x위치
    public int openDoor;    // 현재 위치까지의 문을 연 횟수

    PersonPosition(int y, int x, int openDoor) {
        this.y = y;
        this.x = x;
        this.openDoor = openDoor;
    }
}
```

