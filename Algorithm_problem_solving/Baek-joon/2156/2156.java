import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] wine = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            wine[i] = Integer.parseInt(bf.readLine());
        }
        bf.close();
        long[] dp = new long[n + 1];
        System.out.println(getMaxWine(n, wine, dp));
    }

    private static long getMaxWine(int n, int[] wine, long[] dp) {
        if (n==1)
            return wine[1];
        dp[0] = 0;
        dp[1] = wine[1];
        dp[2] = Math.max(dp[1] + wine[2], dp[1]);
        for (int i = 3; i < n + 1; i++) {
            dp[i] = Math.max(dp[i - 3] + wine[i - 1] + wine[i], dp[i - 2] + wine[i]);
            dp[i] = Math.max(dp[i - 1], dp[i]);
        }
        return dp[n];
    }

}