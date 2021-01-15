import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();

        long[][] dp = new long[n + 1][2];

        memozation(dp, n);

        System.out.println(dp[n][0] + dp[n][1]);
    }

    // 마지막에 붙는 숫자가 0이면 1,0 둘 다 붙힐 수 있고, 1이면 0만 붙힐 수 있다.
    // 다른 풀이는 피보나치 수
    private static void memozation(long[][] dp, int n) {
        dp[1][1] = 1;

        for (int i = 2; i < n + 1; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
            dp[i][1] = dp[i - 1][0];
        }
    }
}