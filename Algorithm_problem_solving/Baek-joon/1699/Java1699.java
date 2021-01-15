import java.util.Scanner;

public class Main {

    public static int getSqrtCnt(int n) {
        int[] dp = new int[n + 1];
        int getPreNum;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j * j <= i; j++) {
                getPreNum = 1 + dp[i - j * j];
                if (dp[i] != 0)
                    dp[i] = Math.min(getPreNum, dp[i]);
                else
                    dp[i] = getPreNum;
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();
        System.out.println(getSqrtCnt(n));
    }
}