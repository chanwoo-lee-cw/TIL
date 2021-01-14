import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/9095
public class Main {

    public static void makeDP(int[] dp, int maxNum) {
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i = 4; i <= maxNum; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bf.readLine());
        int[] tCases = new int[t];
        int maxNum = -1;
        for (int i = 0; i < t; i++) {
            tCases[i] = Integer.parseInt(bf.readLine());
            maxNum = Math.max(maxNum, tCases[i]);
        }
        bf.close();

        int[] dp = new int[maxNum + 1];
        makeDP(dp, maxNum);
        for (int thisCase : tCases) {
            System.out.println(dp[thisCase]);
        }
    }
}