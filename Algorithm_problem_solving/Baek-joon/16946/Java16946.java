import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/16946
public class Main {

    public static int[][] direction = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] maze = new int[n + 1][m + 1];
        {
            for (int i = 1; i <= n; i++) {
                String inputLine = bf.readLine();
                for (int j = 1; j <= m; j++) {
                    if (inputLine.charAt(j - 1) == '1') {
                        maze[i][j] = 1;
                    } else {
                        maze[i][j] = 0;
                    }
                }
            }
        }
        st = null;
        bf.close();

        int[][] moveAbleMaze = searchMoveAbleMaze(n, m, maze);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                sb.append(moveAbleMaze[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    // 벽을 하나하나 찾아서 벽 마다 bfs를 돌린다
    private static int[][] searchMoveAbleMaze(int n, int m, int[][] maze) {
        int[][] moveAbleMaze = new int[n + 1][m + 1];
        boolean[][] visited = new boolean[n + 1][m + 1];        // 방문 며부
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (maze[i][j] == 0) {
                    searchBFSMaze(n, m, maze, i, j, moveAbleMaze, visited);
                } else if (maze[i][j] == 1) {
                    moveAbleMaze[i][j] = (moveAbleMaze[i][j] + 1) % 10;
                }
            }
        }
        return moveAbleMaze;
    }

    // 현재 위치를 기준으로 bfs로 찾는다.
    private static void searchBFSMaze(int n, int m, int[][] maze, int y, int x, int[][] moveAbleMaze, boolean[][] visited) {
        // 빈 공간을 중심으로 탐색
        // 한번 방문한 빈 공간은 -1로 세팅
        // 방문하는 동안 만나는 벽은 ArrList에 저장
        // 방문한 빈 공간들의 갯수를 센다.
        Queue<int[]> queue = new LinkedList<>();    // bfs를 위한 큐

        int[] location = new int[]{y, x};
        ArrayList<int[]> wallList = new ArrayList<>();
        int ableLocationCnt = 0;    // 이동할 수 있는 벽의 위
        queue.add(location);
        moveAbleMaze[y][x] = 0;
        visited[y][x] = true;
        {
            int nextY, nextX;
            while (!queue.isEmpty()) {
                location = queue.poll();
                ableLocationCnt++;
                maze[location[0]][location[1]] = -1;
                moveAbleMaze[location[0]][location[1]] = 0;
                for (int[] next : direction) {
                    nextY = location[0] + next[0];
                    nextX = location[1] + next[1];
                    if (nextX <= 0 || nextY <= 0 || nextY > n || nextX > m
                            || visited[nextY][nextX] || maze[nextY][nextX] == -1) {
                        continue;
                    }
                    visited[nextY][nextX] = true;
                    if (maze[nextY][nextX] == 1) {
                        wallList.add(new int[]{nextY, nextX});
                        continue;
                    }
                    //else
                    queue.add(new int[]{nextY, nextX});
                }
            }
        }
        for (int[] wall : wallList) {
            moveAbleMaze[wall[0]][wall[1]] = (moveAbleMaze[wall[0]][wall[1]] + ableLocationCnt) % 10;
            visited[wall[0]][wall[1]] = false;
        }
    }
}
