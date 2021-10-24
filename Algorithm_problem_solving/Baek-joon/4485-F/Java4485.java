import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n;
        int[][] cave;
        Zelda zelda;
        StringBuilder answer = new StringBuilder();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            int idx = 0;
            while (true) {
                idx++;
                n = Integer.parseInt(bf.readLine());
                if (n == 0) {
                    break;
                }
                cave = new int[n][n];
                for (int i = 0; i < n; i++) {
                    st = new StringTokenizer(bf.readLine());
                    for (int j = 0; j < n; j++) {
                        cave[i][j] = Integer.parseInt(st.nextToken());
                    }
                }
                zelda = new Zelda(n, cave);
                answer.append(String.format("Problem %d: %d\n", idx, zelda.findMinLose()));
            }
            System.out.print(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Zelda {
    private int n;
    private int[][] cave;
    private static int[][] way = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public Zelda(int n, int[][] cave) {
        this.n = n;
        this.cave = cave;
    }

    public int findMinLose() {
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(((o1, o2) -> {
            if (o1[0] > o2[0])
                return 1;
            else if (o1[0] < o2[0])
                return -1;
            else
                return 0;
        }));
        dp[0][0] = cave[0][0];
        queue.add(new int[]{dp[0][0], 0, 0});

        int[] curr;
        int nextY, nextX;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (curr[0] > dp[curr[1]][curr[2]])
                continue;
            for (int[] next : way) {
                nextY = next[0] + curr[1];
                nextX = next[1] + curr[2];
                if (nextY < 0 || nextX < 0 || nextY >= n || nextX >= n) {
                    continue;
                } else if (dp[nextY][nextX] <= curr[0] + cave[nextY][nextX]) {
                    continue;
                }
                dp[nextY][nextX] = curr[0] + cave[nextY][nextX];
                queue.add(new int[]{curr[0] + cave[nextY][nextX], nextY, nextX});
            }
        }
        return dp[n - 1][n - 1];
    }
}