import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] arr = new int[n + 1];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 1; i < n + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        boolean[][] dp = memozation(n, arr);
        StringBuilder sb = new StringBuilder();
        {
            int m = Integer.parseInt(bf.readLine());
            int a, b;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(bf.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                sb.append(dp[a][b] ? 1 : 0);
                sb.append("\n");
            }
        }
        System.out.print(sb.toString());
    }

    // 모든 구간의 팰린드롬을 구하는 함수
    private static boolean[][] memozation(int n, int[] arr) {
        boolean[][] dp = new boolean[n + 1][n + 1];
        dp[1][1] = true;
        for (int j = 2; j < n + 1; j++) {
            // 자기 자신만이면 무조건 팰린드롭
            dp[j][j] = true;
            if (arr[j - 1] == arr[j])
                dp[j - 1][j] = true;
            // 가장자리가 같을 때 그 안이 펠린드롬이라면 그건 팰린드롬
            for (int i = 1; i < j; i++) {
                if (arr[i] == arr[j] && dp[i + 1][j - 1])
                    dp[i][j] = true;
            }
        }
        return dp;
    }
}