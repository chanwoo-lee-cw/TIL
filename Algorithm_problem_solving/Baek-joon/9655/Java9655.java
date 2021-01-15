import java.util.Arrays;
import java.util.Scanner;

// https://www.acmicpc.net/problem/9655
public class Main {

    // left : number of left stone
    // turn : ture-sk, false-cy
    // return : 0-cyWin, 1-skWin, -1-noresult
    public static int[][] dp;

    public static int stoneGame(int left, boolean turn) {
        if (left == 0)
            dp[left][turn ? 1 : 0] = turn ? 0 : 1;
        else if (left < 0)
            return -1;
        else if (dp[left][turn ? 1 : 0] != -2) {
            return dp[left][turn ? 1 : 0];
        } else if (turn) {
            if (stoneGame(left - 1, false) == 1 || stoneGame(left - 3, false) == 1)
                dp[left][turn ? 1 : 0] = 1;
            else
                dp[left][turn ? 1 : 0] = 0;
        } else {
            if (stoneGame(left - 1, true) == 0 || stoneGame(left - 3, true) == 0)
                dp[left][turn ? 1 : 0] = 0;
            else
                dp[left][turn ? 1 : 0] = 1;
        }
        return dp[left][turn ? 1 : 0];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        dp = new int[n + 1][2];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(dp[i], -2);
        }

        System.out.println(stoneGame(n, true) == 0 ? "CY" : "SK");
        in.close();
    }
}