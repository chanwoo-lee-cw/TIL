import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();

        long[][] dp = new long[n + 1][10];
        long result = 0;

        memozation(dp, n);

        for (int i = 0; i < 10; i++) {
            result = (result + dp[n][i]) % 1000000000;
        }

        System.out.println(result);
    }

    // dp 값을 구하는 함수 : dp[n][m] n자리 수일 경우 마지막에 m이 들어가는 n자리의 수
    private static void memozation(long[][] dp, int n) {
        for (int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }
        for (int i = 2; i < n + 1; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0)
                    dp[i][j] = dp[i - 1][j + 1];
                else if (j == 9)
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1000000000;
            }
        }
    }
}