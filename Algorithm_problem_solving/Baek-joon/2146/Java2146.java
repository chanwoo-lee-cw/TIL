import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        Land land;      // 답을 구할 객체
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            land = new Land(bf);
            System.out.println(land.getShortestBrige());
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class Land {
    private int n;          // 공간의 크기
    private int[][] matrix;     // 땅의 모양
    private int shortestBrige;      // 지금까지 만든 다리중 가장 짧은 다리
    public static int[][] way = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};  // 이동할 방향

    public Land(BufferedReader bf) throws IOException {
        this.n = Integer.parseInt(bf.readLine());
        this.matrix = new int[n][n];
        this.shortestBrige = Integer.MAX_VALUE;
        StringTokenizer st;
        for (int i = 0; i < this.n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < this.n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int landcolor = 2;
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                if (matrix[i][j] == 1)
                    landColoring(i, j, landcolor++);
            }
        }

    }

    /*
    만약 인접해 있는 땅이라면 같은 색으로 칠한다.
    */
    private void landColoring(int y, int x, int color) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y, x});
        matrix[y][x] = color;

        int[] curr;
        int nextY, nextX;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            for (int[] next : way) {
                nextY = curr[0] + next[0];
                nextX = curr[1] + next[1];
                if (nextY < 0 || nextY >= n || nextX < 0 || nextX >= n)
                    continue;
                if (matrix[nextY][nextX] > 1 || matrix[nextY][nextX] == 0)
                    continue;
                queue.add(new int[]{nextY, nextX});
                matrix[nextY][nextX] = color;
            }
        }
    }

    /*
    가장 짧은 다리의 길이를 리턴한다.
    */
    public int getShortestBrige() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != 0) {
                    shortestBrige = Math.min(shortestBrige, makeBridge(i, j, matrix[i][j]));
                }
            }
        }

        return shortestBrige;
    }

    /*
    현재 있는 위치에서 다른 대륙으로 갈 수 있는 다리의 길이를 리턴한다. 만약 이동할 수 없다면 Integer.Max값을 리턴
    */
    public int makeBridge(int y, int x, int color) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        queue.add(new int[]{y, x, 0});
        visited[y][x] = true;

        int[] curr;
        int nextY, nextX;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (curr[2] > shortestBrige)
                break;
            if (matrix[curr[0]][curr[1]] != 0 && matrix[curr[0]][curr[1]] != color)
                return curr[2] - 1;
            for (int[] next : way) {
                nextY = curr[0] + next[0];
                nextX = curr[1] + next[1];
                if (nextY < 0 || nextY >= n || nextX < 0 || nextX >= n)
                    continue;
                else if (visited[nextY][nextX])
                    continue;
                else if (matrix[nextY][nextX] == color)
                    continue;
                queue.add(new int[]{nextY, nextX, curr[2] + 1});
                visited[nextY][nextX] = true;
            }
        }

        return Integer.MAX_VALUE;
    }
}