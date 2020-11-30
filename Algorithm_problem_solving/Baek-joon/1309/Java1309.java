import java.util.Scanner;

public class Java1309 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] dp = new int[n + 2];
        if (n != 0) {
            dp[1] = 3;
            dp[2] = 7;
        }
        for (int i = 3; i < n + 1; i++) {
            dp[i] = (dp[i - 2] * 3 + (dp[i - 1] - dp[i - 2]) * 2) % 9901;
        }
        System.out.println(dp[n]);
        in.close();
    }
}