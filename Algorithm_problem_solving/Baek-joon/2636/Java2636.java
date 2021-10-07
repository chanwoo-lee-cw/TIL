import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        int n, m;
        int[][] board;
        MeltCheese meltCheese;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            board = new int[n][m];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < m; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            meltCheese = new MeltCheese(n, m, board);
            System.out.println(meltCheese.melt());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class MeltCheese {
    private int n, m;
    private int[][] board;
    private static final int[][] way = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public MeltCheese(int n, int m, int[][] board) {
        this.n = n;
        this.m = m;
        this.board = board;
    }

    public String melt() {
        int[][] visited = new int[n][m];
        PriorityQueue<int[]> queue = new PriorityQueue<>(((o1, o2) -> {
            return o1[2] - o2[2];
        }));
        int lastTime = 0;
        int meltCnt = 0;

        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        visited[0][0] = 0;
        queue.add(new int[]{0, 0, 0});

        int[] curr;
        int nextY, nextX;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (lastTime != curr[2]) {
                lastTime = curr[2];
                meltCnt = 0;
            }
            if (board[curr[0]][curr[1]] == 1) {
                meltCnt++;
            }
            for (int[] next : way) {
                nextY = curr[0] + next[0];
                nextX = curr[1] + next[1];
                if (nextY < 0 || nextX < 0 || nextY >= n || nextX >= m)
                    continue;
                else if (visited[nextY][nextX] <= curr[2] + 1)
                    continue;
                // else
                if (board[nextY][nextX] == 1) {

                    queue.add(new int[]{nextY, nextX, curr[2] + 1});
                    visited[nextY][nextX] = curr[2] + 1;
                } else {
                    queue.add(new int[]{nextY, nextX, curr[2]});
                    visited[nextY][nextX] = curr[2];
                }
            }
        }
        return String.format("%d\n%d", lastTime, meltCnt);
    }
}
