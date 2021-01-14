import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        int[] arr = new int[n];
        int maxNum = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
            maxNum = Math.max(maxNum, arr[i]);
        }
        
        int[][] dp = memozation(maxNum);

        StringBuilder sb = new StringBuilder();
        for (int item : arr) {
            sb.append(dp[item][1] + dp[item][2] + dp[item][3]);
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    // maxNum까지 dp 값을 구한다.
    // 가장 자주 쓰이는 중복 제거 방법, 오름차 순으로 정렬한다.
    private static int[][] memozation(int maxNum) {
        int[][] dp = new int[maxNum + 1][4];
        dp[1][1] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 2;
        dp[3][3] = 1;
        for (int i = 4; i < maxNum + 1; i++) {
            dp[i][1] = dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][3];
            dp[i][2] = dp[i - 2][2] + dp[i - 2][3];
            dp[i][3] = dp[i - 3][3];
        }
        return dp;
    }
}