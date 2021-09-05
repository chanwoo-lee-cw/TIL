import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n, k;
        Solution solution;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            solution = new Solution();
            System.out.println(solution.run(n, k));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Solution {
    public int run(int n, int k) {
        int[][] dp;
        dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][1] = 1;
        }
        for (int j = 1; j <= k; j++) {
            dp[1][j] = j;
        }

        for (int j = 2; j <= k; j++) {
            for (int i = 2; i <= n; i++) {
                for (int l = 1; l < j + 1; l++) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][l]) % 1000000000;
                }
            }
        }
        return dp[n][k];
    }
}
