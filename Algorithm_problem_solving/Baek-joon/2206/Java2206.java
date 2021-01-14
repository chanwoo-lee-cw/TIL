import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int[][] way = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws IOException {
        int n;
        int m;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        boolean[][] maze;
        {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
        }
        maze = new boolean[n + 1][m + 1];
        {
            String input;
            for (int i = 1; i <= n; i++) {
                input = bf.readLine();
                for (int j = 1; j <= m; j++) {
                    if (input.charAt(j - 1) == '1')
                        maze[i][j] = true;
                    else
                        maze[i][j] = false;
                }
            }
        }
        int result = getShortestDistance(maze, n, m);
        System.out.println(result);
    }

    // BFS방식으로 경로 탐색
    private static int getShortestDistance(boolean[][] maze, int n, int m) {
        boolean[][][] visited = new boolean[n + 1][m + 1][2];   // 방문한 지역 표시 - 부신 여부도 체크
        Queue<CurrentPosition> queue = new LinkedList<>();      // BFS를 위한 큐
        queue.add(new CurrentPosition(1, 1, 1, false));
        visited[1][1][0] = true;
        CurrentPosition curr = null;
        int nextY, nextX;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (curr.y == n && curr.x == m)
                return curr.move;
            for (int i = 0; i < 4; i++) {
                nextY = curr.y + way[i][0];
                nextX = curr.x + way[i][1];
                if (nextY <= 0 || nextX <= 0 || nextY > n || nextX > m || visited[nextY][nextX][curr.broken ? 1 : 0])
                    continue;
                if (maze[nextY][nextX]) {
                    if (curr.broken)
                        continue;
                    queue.add(new CurrentPosition(nextY, nextX, curr.move + 1, true));
                    visited[nextY][nextX][1] = true;
                } else {
                    queue.add(new CurrentPosition(nextY, nextX, curr.move + 1, curr.broken));
                    visited[nextY][nextX][curr.broken ? 1 : 0] = true;
                }
            }
        }
        return -1;
    }
}

class CurrentPosition {
    public int y;           // y위치
    public int x;           // x위치
    public int move;        // 움직인 횟수
    public boolean broken;  // 벽을 부신 여부 체크

    CurrentPosition(int y, int x, int move, boolean broken) {
        this.y = y;
        this.x = x;
        this.move = move;
        this.broken = broken;
    }
}