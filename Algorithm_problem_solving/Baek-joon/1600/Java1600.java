import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1600
public class Main {
    public static int[][] walk = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static int[][] jump = {{2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {1, -2}, {-1, -2}};

    public static void main(String[] args) throws IOException {
        int k;      // 가능한 점프 횟수
        int w, h;    // 가로, 세로
        boolean[][] matrix;     // 공간의 형태

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        k = Integer.parseInt(bf.readLine());
        st = new StringTokenizer(bf.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        matrix = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < w; j++) {
                matrix[i][j] = st.nextToken().compareTo("1") == 0 ? true : false;
            }
        }
        st = null;
        bf.close();

        System.out.println(getMinMove(h, w, k, matrix));

    }

    public static int getMinMove(int h, int w, int k, boolean[][] matrix) {
        // 가로 세로 방향으로 움직이는 경로와 나이트와 같은 방식으로 이동하는 경우를 따로 센다.
        // visited[][] 계산은 이전에 방문한 k미만이면 재 방문 허용
        int[][] visited = new int[h][w];
        for (int i = 0; i < h; i++)
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        Queue<MonkeyPosition> queue = new LinkedList<>();
        MonkeyPosition monkey = new MonkeyPosition(0, 0, 0, 0);
        queue.add(monkey);
        visited[monkey.y][monkey.x] = monkey.jumpCnt;
        {
            int nextX, nextY;
            while (!queue.isEmpty()) {
                monkey = queue.poll();
                if (monkey.y == h - 1 && monkey.x == w - 1)
                    // 도착한 경우
                    return monkey.moveCnt;
                for (int[] next : walk) {
                    nextY = next[0] + monkey.y;
                    nextX = next[1] + monkey.x;
                    if (nextX < 0 || nextX >= w || nextY < 0 || nextY >= h)
                        continue;
                    else if (matrix[nextY][nextX] || visited[nextY][nextX] <= monkey.jumpCnt)
                        continue;
                    // else
                    queue.add(new MonkeyPosition(nextY, nextX, monkey.jumpCnt, monkey.moveCnt + 1));
                    visited[nextY][nextX] = monkey.jumpCnt;
                }
                if(monkey.jumpCnt < k) {
                    // 점프가 가능할 때
                    for (int[] next : jump) {
                        nextY = next[0] + monkey.y;
                        nextX = next[1] + monkey.x;
                        if (nextX < 0 || nextX >= w || nextY < 0 || nextY >= h)
                            continue;
                        else if (matrix[nextY][nextX] || visited[nextY][nextX] <= monkey.jumpCnt + 1)
                            continue;
                        // else
                        queue.add(new MonkeyPosition(nextY, nextX, monkey.jumpCnt + 1, monkey.moveCnt + 1));
                        visited[nextY][nextX] = monkey.jumpCnt + 1;
                    }
                }
            }
        }
        return -1;      //  도착할 수 없는 경우
    }
}

class MonkeyPosition {
    int y;      // 원숭이의 y좌표
    int x;      // 원숭이의 x좌표
    int jumpCnt;    // 현재 위치까지 오는데 점프한 횟수
    int moveCnt;    // 현재 위치까지 오는데 이동한 횟수

    MonkeyPosition(int y, int x, int jumpCnt, int moveCnt) {
        this.y = y;
        this.x = x;
        this.jumpCnt = jumpCnt;
        this.moveCnt = moveCnt;
    }
}