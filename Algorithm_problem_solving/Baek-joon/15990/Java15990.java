import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();
        int largestCase = 0;
        int[] cases = new int[t];
        for (int i = 0; i < t; i++) {
            cases[i] = in.nextInt();
            largestCase = Math.max(largestCase, cases[i]);
        }
        in.close();

        long[][] dp = new long[largestCase + 1][4];

        memozation(dp, largestCase);

        for (int i = 0; i < t; i++) {
            System.out.println((dp[cases[i]][1] + dp[cases[i]][2] + dp[cases[i]][3]) % 1000000009);
        }
    }

    // 테스트케이스의 최대 값까지 dp 값을 일괄적으로 구하는 함수
    private static void memozation(long[][] dp, int largestCase) {
        // dp는 각각 1을 더했을 때, 2를 더했을 때, 3을 더했을때 케이스를 각각 나눠서 생각한다.
        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;
        {
            for (int i = 4; i < largestCase + 1; i++) {
                dp[i][1] = (dp[i - 1][2] + dp[i - 1][3]) % 1000000009;
                dp[i][2] = (dp[i - 2][1] + dp[i - 2][3]) % 1000000009;
                dp[i][3] = (dp[i - 3][1] + dp[i - 3][2]) % 1000000009;
            }
        }
    }
}