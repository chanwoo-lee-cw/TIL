import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static int memozation(int[][][] dp, int now, int l, int a, int n) {
        if (l >= 2 || a >= 3)
            return 0;
        if (n == now)
            return 1;
//        if (dp[n][l][a] != 0)
//            return dp[n][l][a];
        // else
        dp[n][l][a] = (memozation(dp, now + 1, l, 0, n)
                    +  memozation(dp, now + 1, l + 1, 0, n)
                    +  memozation(dp, now + 1,  l, a + 1, n)) % 1000000;
        return dp[n][l][a];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[][][] dp = new int[n + 1][2][3];

        System.out.println(memozation(dp, 0, 0, 0, n));
    }
}