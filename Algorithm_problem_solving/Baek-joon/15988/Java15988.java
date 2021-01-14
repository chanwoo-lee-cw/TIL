import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/9095
public class Main {

    // 다이나믹 프로그램 방식으로 dp 값을 채워 넣는다.
    public static void momozation(long[] dp, int maxNum) {
        // BaseCase
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        // 점화식
        for (int i = 4; i <= maxNum; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1000000009;
        }
    }

    public static void main(String[] args) throws IOException {
        int t;              // 테스트 케이스의 갯수
        int[] tCases;       // 테스트 케이스 리스트를 저장하는 변수
        int maxNum = -1;    // 테스트 케이스 중에 최대값까지 구한다.
        long[] dp;          // dp값을 저장하는 변수.
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(bf.readLine());
        tCases = new int[t];
        for (int i = 0; i < t; i++) {
            tCases[i] = Integer.parseInt(bf.readLine());
            maxNum = Math.max(maxNum, tCases[i]);
        }
        bf.close();

        dp = new long[maxNum + 1];
        momozation(dp, maxNum);
        for (int thisCase : tCases) {
            System.out.println(dp[thisCase]);
        }
    }
}