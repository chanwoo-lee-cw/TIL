import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static int[][] direction = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[][] maze = new boolean[n + 1][m + 1];
        {
            for (int i = 1; i <= n; i++) {
                String inputLine = bf.readLine();
                for (int j = 1; j <= m; j++) {
                    if (inputLine.charAt(j - 1) == '1') {
                        maze[i][j] = true;
                    } else {
                        maze[i][j] = false;
                    }
                }
            }
        }
        st = null;
        bf.close();

        int[][] moveAbleMaze = searchMoveAbleMaze(n, m, maze);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                System.out.print(moveAbleMaze[i][j]);
            }
            System.out.println();
        }
    }

    // 벽을 하나하나 찾아서 벽 마다 bfs를 돌린다
    private static int[][] searchMoveAbleMaze(int n, int m, boolean[][] maze) {
        int[][] moveAbleMaze = new int[n + 1][m + 1];       // 새로 만든 메이즈
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (maze[i][j]) {
                    maze[i][j] = false;
                    moveAbleMaze[i][j] = (searchBFSMaze(n, m, maze, i, j)) % 10;
                    maze[i][j] = true;
                } else {
                    moveAbleMaze[i][j] = 0;
                }
            }
        }
        return moveAbleMaze;
    }

    // 현재 위치를 기준으로 bfs로 찾는다.
    private static int searchBFSMaze(int n, int m, boolean[][] maze, int y, int x) {
        Queue<int[]> queue = new LinkedList<>();                // bfs를 위한 큐
        boolean[][] visited = new boolean[n + 1][m + 1];        // 방문 며부
        int[] location = new int[]{y, x};
        queue.add(location);
        visited[y][x] = true;
        int ableLocationCnt = 0;
        {
            int nextY, nextX;
            while (!queue.isEmpty()) {
                location = queue.poll();
                ableLocationCnt++;
                for (int[] next : direction) {
                    nextY = location[0] + next[0];
                    nextX = location[1] + next[1];
                    if (nextX <= 0 || nextY <= 0 || nextY > n || nextX > m || visited[nextY][nextX] || maze[nextY][nextX])
                        continue;
                    //else
                    queue.add(new int[]{nextY, nextX});
                    visited[nextY][nextX] = true;
                }
            }
        }
        return ableLocationCnt;
    }
}