import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int[][] dp = new int[n + 1][10];
        System.out.println(memozation(n, dp));

        in.close();
    }

    private static int memozation(int n, int[][] dp) {
        Arrays.fill(dp[1], 1);

        for (int i = 2; i < n + 1; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k <= j; k++)
                    dp[i][j] = (dp[i][j] + dp[i - 1][k]) % 10007;
            }
        }
        int result = 0;
        for (int i = 0; i < 10; i++) {
            result = (result + dp[n][i]) % 10007;
        }
        return result;
    }
}