import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[][] triangle = new int[n][n];
        {
            StringTokenizer st;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j <= i; j++) {
                    triangle[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }
        bf.close();

        long[][] dp = new long[n][n];
        System.out.println(getMaxdp(n, dp, triangle));
    }

    private static long getMaxdp(int n, long[][] dp, int[][] triangle) {
        dp[0][0] = triangle[0][0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0)
                    dp[i][j] = dp[i - 1][j] + triangle[i][j];
                else if (i == j)
                    dp[i][j] = dp[i - 1][j - 1] + triangle[i][j];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1]) + triangle[i][j];
            }
        }
        long dpMax = 0;
        for (int i = 0; i < n; i++) {
            dpMax = Math.max(dp[n - 1][i], dpMax);
        }
        return dpMax;
    }
}