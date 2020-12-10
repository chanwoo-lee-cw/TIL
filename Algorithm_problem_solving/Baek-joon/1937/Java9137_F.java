import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/1937
public class Main {
    final static int[][] way = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int n;
    static int[][] field;

    public static int dfs(int y, int x, int move, int[][] dp) {
        if (dp[y][x] != 0) {
            return dp[y][x];
        }
        dp[y][x] = 1;
        int nextX = 0;
        int nextY = 0;
        for (int[] next : way) {
            nextY = y + next[0];
            nextX = x + next[1];
            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n || field[nextY][nextX] <= field[y][x])
                continue;
            // 이미 한번 방문한 장소는 저장하고, 리턴 받은 것에 + 1을 더해준다.
            // dp[y][x]에 저장된 값은 field[y][x]에서 출발했을 경우 탐색이 가능한 max의 수이다.
            // 왜냐면 dp[y][x]에서 탐색한 경우의 수에+1, 왜냐면 옆에 칸으로 이동하는 것까지 더해줘야 하기 때문에.
            dp[y][x] = Math.max(dfs(nextY, nextX, move + 1, dp) + 1, dp[y][x]);
        }
        return dp[y][x];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        n = Integer.parseInt(bf.readLine());
        field = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < n; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int maxMove = 0;
        int[][] dp;
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maxMove = Math.max(dfs(i, j, 1 , dp), maxMove);
            }
        }
        System.out.println(maxMove);
        bf.close();
    }
}
