import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16236
public class Main {
    public static void main(String[] args) throws IOException {
        int n;              // 공간의 크기
        int[][] aquarium;   // 수조
        BabyShark cute = null;      // 상어의 상태를 나타내는 객체

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        aquarium = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < n; j++) {
                aquarium[i][j] = Integer.parseInt(st.nextToken());
                if (aquarium[i][j] == 9) {
                    cute = new BabyShark(i, j);
                    aquarium[i][j] = 0;
                }
            }
        }
        st = null;
        bf.close();

        System.out.println(startSharkEat(n, aquarium, cute));
    }

    // 상어가 먹을 것이 없을 때까지 반복해서 찾는다.
    private static int startSharkEat(int n, int[][] aquarium, BabyShark cute) {
        int sharkMoveSum = 0;       // 상어의 총 이동 횟수
        int sharkMove = 0;          // 상어의 이번 턴의 이동 횟수
        while (true) {
            sharkMove = cute.searhEatableFish(n, aquarium);
            if (sharkMove == 0)
                // 먹을 것을 못 찾았을 때
                break;
            else {
                // 먹을 것을 찾았을 때
                cute.eatFish++;
                if (cute.eatFish >= cute.sharkSize) {
                    cute.eatFish = 0;
                    cute.sharkSize++;
                }
                aquarium[cute.y][cute.x] = 0;
                sharkMoveSum += sharkMove;
            }
        }
        return sharkMoveSum;
    }
}

class BabyShark {
    int y;      // 상어의 현재 위치
    int x;      // 상어의 현재 위치
    int sharkSize;      // 현재 상어의 크기
    int eatFish;        // 현재 상어의 먹은 횟

    int[][] swim = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public BabyShark(int y, int x) {
        this.y = y;
        this.x = x;
        this.sharkSize = 2;
        this.eatFish = 0;
    }

    // bfs로 먹을 수 있는 물고기를 탐색
    public int searhEatableFish(int n, int[][] aquarium) {
        boolean[][] visited = new boolean[n][n];    // 방문 여부 표시수
        // 우선 순위 큐로 먹을 순서를 정렬한다.
        PriorityQueue<SharkPosition> priorityQueue = new PriorityQueue<>(new Comparator<SharkPosition>() {
            @Override
            public int compare(SharkPosition o1, SharkPosition o2) {
                // 우선 순위
                // 1. 이동 횟수가 가장 적을때 우선
                if (o1.moveCnt > o2.moveCnt)
                    return 1;
                else if (o1.moveCnt < o2.moveCnt)
                    return -1;
                // else
                // 2. 가장 위쪽인 것
                if (o1.y > o2.y)
                    return 1;
                else if (o1.y < o2.y)
                    return -1;
                // else
                // 3. 가장 왼쪽 인것
                if (o1.x > o2.x)
                    return 1;
                else if (o1.x < o2.x)
                    return -1;
                else
                    return 0;
            }
        });
        SharkPosition curr = new SharkPosition(this.y, this.x, 0);      // 상어의 현재 위치
        priorityQueue.add(curr);
        visited[curr.y][curr.x] = true;

        int nextY, nextX;
        while (!priorityQueue.isEmpty()) {
            curr = priorityQueue.poll();
            if (aquarium[curr.y][curr.x] != 0 && aquarium[curr.y][curr.x] < this.sharkSize) {
                // 먹을 수 있는걸 찾았으면 현재 위치 세팅
                this.y = curr.y;
                this.x = curr.x;
                return curr.moveCnt;
            }
            for (int[] next : swim) {
                nextY = next[0] + curr.y;
                nextX = next[1] + curr.x;
                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n)
                    continue;
                if (visited[nextY][nextX] || (aquarium[nextY][nextX] != 0 && this.sharkSize < aquarium[nextY][nextX]))
                    continue;
                // else
                priorityQueue.add(new SharkPosition(nextY, nextX, curr.moveCnt + 1));
                visited[nextY][nextX] = true;
            }
        }

        return 0;
    }
}

class SharkPosition {
    int y;      // 현재 상어의 y위치
    int x;      // 현재 상어의 x위치
    int moveCnt;    // y,x 점까지의 상어의 이동 거리

    public SharkPosition(int y, int x, int moveCnt) {
        this.y = y;
        this.x = x;
        this.moveCnt = moveCnt;
    }
}