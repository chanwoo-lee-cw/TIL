import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n, k;
        int[] coins;
        Solution solution;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            coins = new int[n + 1];
            for (int i = 0; i < n; i++) {
                coins[i + 1] = Integer.parseInt(bf.readLine());
            }
            Arrays.sort(coins);
            solution = new Solution(n, k, coins);
            System.out.println(solution.makeDp());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Solution {
    private int n;
    private int k;
    private int[] coins;

    public Solution(int n, int k, int[] coins) {
        this.n = n;
        this.k = k;
        this.coins = coins;
    }

    public int makeDp() {
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j <= k; j++) {
            if (j % coins[1] == 0) {
                dp[1][j] = 1;
            }
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = j >= coins[i] ? dp[i - 1][j] + dp[i][j - coins[i]] : dp[i - 1][j];
            }
        }
        return dp[n][k];
    }
}
