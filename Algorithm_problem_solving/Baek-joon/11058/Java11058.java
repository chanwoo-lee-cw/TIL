import java.util.Scanner;

// https://www.acmicpc.net/problem/11058
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();

        System.out.println(getMaxPrintNum(n));
    }

    // dynamic program
    // base case : 1 : 1, 2: 2 ,3: 3
    // 점화식 : dp[n] = max(dp[i-1]+1, dp[i-3]*2, dp[i-4]*3, dp[i-5]*4 ... dp[i-j]*(j-1)
    public static long getMaxPrintNum(int n) {
        long[] dp = new long[n + 1];
        for (int i = 1; i <= 3 && i <= n; i++) {
            dp[i] = i;
        }
        for (int i = 4; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int j = 1; j <= i - 3; j++) {
                dp[i] = Math.max(dp[i], dp[i - 2 - j] * (j + 1));
            }
        }
        return dp[n];
    }
}