import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int[][] way = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        int n, m, k;
        boolean[][] maze;

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
        }
        maze = new boolean[n][m];

        {
            String inputLine;
            for (int i = 0; i < n; i++) {
                inputLine = bf.readLine();
                for (int j = 0; j < m; j++) {
                    if (inputLine.charAt(j) == '1')
                        maze[i][j] = true;
                    else
                        maze[i][j] = false;
                }
            }
        }
        bf.close();

        int result = getShortedtRoute(n, m, k, maze);
        System.out.println(result);
    }

    // 가장 짧은 이동 경로를 반환하는 함수
    private static int getShortedtRoute(int n, int m, int k, boolean[][] maze) {
        // 한번 방문한 장소에 1번이상 머무르면 안됨.
        // move%2 == 1 낮, move%2 == 0 밤
        // 만약 벽을 만났을 때 낮으면 부셔보고 들어가고 밤이면 보류.
        Queue<Location> queue = new LinkedList<>();
        int[][] visited = new int[n][m];    // 방문을 int로 저장, 벽을 부신 횟수를 저장한다.
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        Location curr = new Location(0, 0, 0, 1);
        queue.add(curr);
        visited[0][0] = 0;
        int nextY, nextX;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (curr.y == n - 1 && curr.x == m - 1)
                return curr.moveCnt;
            for (int[] next : way) {
                nextY = next[0] + curr.y;
                nextX = next[1] + curr.x;
                if (nextY < 0 || nextX < 0 || nextY >= n || nextX >= m || visited[nextY][nextX] <= curr.broken)
                    continue;
                else if (maze[nextY][nextX]) {
                    // 만약 벽일 경우 부실 수 있나, 없나 검사하고 방문을 현재까지 벽을 부신 갯수를 저장한다.
                    if (curr.broken >= k || visited[nextY][nextX] <= curr.broken + 1) {
                        // 넣을 broken을 기준으로 계산한다.
                        continue;
                    } else if (curr.moveCnt % 2 == 0) {
                        // 만약 벽을 만났을 때, 밤이라면 그 자리에 보류
                        queue.add(new Location(curr.y, curr.x, curr.broken, curr.moveCnt + 1));
                    } else {
                        // 낮이라면 벽을 부수고 진행한다.
                        queue.add(new Location(nextY, nextX, curr.broken + 1, curr.moveCnt + 1));
                        visited[nextY][nextX] = curr.broken + 1;
                    }
                } else {
                    queue.add(new Location(nextY, nextX, curr.broken, curr.moveCnt + 1));
                    visited[nextY][nextX] = curr.broken;
                }
            }
        }
        return -1;
    }
}

class Location {
    int y;  // 현재 위치의 y좌표
    int x;  // 현재 위치의 x좌표
    int broken;     // 부신 벽의 갯수
    int moveCnt;    // 현재 움직인 간의 갯수

    Location(int y, int x, int broken, int moveCnt) {
        this.x = x;
        this.y = y;
        this.broken = broken;
        this.moveCnt = moveCnt;
    }
}