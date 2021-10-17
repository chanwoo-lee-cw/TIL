import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        int n = 12, m = 6;
        char[][] board = new char[n][m];
        PuyoPuyo puyoPuyo;
        int answer = 0;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            String inputLine;
            for (int i = 0; i < n; i++) {
                inputLine = bf.readLine();
                for (int j = 0; j < m; j++) {
                    board[i][j] = inputLine.charAt(j);
                }
            }
            puyoPuyo = new PuyoPuyo(n, m, board);
            while (puyoPuyo.PuyoCount()) answer++;
            System.out.println(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class PuyoPuyo {
    private int n, m;
    private char[][] board;
    private final int[][] way = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public PuyoPuyo(int n, int m, char[][] board) {
        this.n = n;
        this.m = m;
        this.board = board;
    }

    public ArrayList<int[]> bfs(int y, int x, boolean[][] visited) {
        ArrayList<int[]> boom = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{y, x});
        visited[y][x] = true;

        int[] curr;
        int nextY, nextX;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            boom.add(curr);
            for (int[] next : way) {
                nextY = next[0] + curr[0];
                nextX = next[1] + curr[1];
                if (nextY < 0 || nextY >= n || nextX < 0 || nextX >= m) {
                    continue;
                } else if (visited[nextY][nextX] || board[nextY][nextX] != board[y][x]) {
                    continue;
                }
                queue.add(new int[]{nextY, nextX});
                visited[nextY][nextX] = true;
            }
        }
        return boom.size() >= 4 ? boom : new ArrayList<>();
    }

    public void fallPuyo() {
        char[] temp;
        int pos;
        for (int j = 0; j < m; j++) {
            temp = new char[n];
            Arrays.fill(temp, '.');
            pos = n - 1;
            for (int i = n - 1; i >= 0; i--) {
                if (board[i][j] != '.') {
                    temp[pos--] = board[i][j];
                }
            }
            for (int i = 0; i < n; i++) {
                board[i][j] = temp[i];
            }
        }
    }

    public boolean PuyoCount() {
        ArrayList<int[]> boom = new ArrayList<>();
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '.' || visited[i][j])
                    continue;
                boom.addAll(bfs(i, j, visited));
            }
        }
        for (int[] item : boom) {
            board[item[0]][item[1]] = '.';
        }
        fallPuyo();
        return boom.size() >= 4;
    }
}