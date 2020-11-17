import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Java1010 {
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(bf.readLine());
        int N, M;
        dp = new long[31][31];
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            System.out.println(combination(M, N));
        }
    }

    public static long combination(int n, int r) {
        if (dp[n][r] != 0) {
            return dp[n][r];
        } else if (n == r || r == 0) {
            dp[n][r] = 1;
            return 1;
        } else {
            dp[n][r] = combination(n - 1, r - 1) + combination(n - 1, r);
            return dp[n][r];
        }
    }
}