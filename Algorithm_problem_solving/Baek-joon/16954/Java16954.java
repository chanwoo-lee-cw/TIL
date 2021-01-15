import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static int[][] way = new int[][]{{0, 0}, {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {1, -1}, {0, 1}, {1, 0}, {1, 1}};

    public static void main(String[] args) throws IOException {
        boolean[][] maze = new boolean[8][8];
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        {
            String inputLine;
            for (int i = 0; i < 8; i++) {
                inputLine = bf.readLine();
                for (int j = 0; j < 8; j++) {
                    if (inputLine.charAt(j) == '#')
                        maze[i][j] = true;
                    else
                        maze[i][j] = false;
                }
            }
        }
        bf.close();
        System.out.println(checkGameClear(maze) ? 1 : 0);
    }

    // 움직일 있는 경로를 찾는 매서드
    private static boolean checkGameClear(boolean[][] maze) {
        Queue<int[]> queue = new LinkedList<>();
        int[] curr = new int[]{7, 0, 0};    // {y, x, turn}을 집어 넣는다.
        queue.add(curr);
        int turn = 0;       // 현재 몇번째 턴인지 명시
        int nextY, nextX;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (turn != curr[2]) {
                // 만약 턴이 지나갔으면 돌 들을 움직인다.
                moveWall(maze);
                turn++;
            }
            if (maze[curr[0]][curr[1]])
                // 만약 돌에 깔렸으면 이 부분은 continue
                continue;
            if (curr[0] < turn || curr[0] == 0 && curr[1] == 7) {
                // 만약 목적지에 도착했으면 true리턴
                return true;
            }
            for (int[] next : way) {
                nextY = next[0] + curr[0];
                nextX = next[1] + curr[1];
                if (nextY < 0 || nextX < 0 || nextY >= 8 || nextX >= 8 || maze[nextY][nextX])
                    continue;
                queue.add(new int[]{nextY, nextX, curr[2] + 1});
            }
        }
        return false;
    }

    // 돌을 움직이는 매서드
    private static void moveWall(boolean[][] maze) {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                if (maze[i][j]) {
                    if (i < 7)
                        maze[i + 1][j] = true;
                    maze[i][j] = false;
                }
            }
        }
    }
}