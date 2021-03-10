import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2482
public class Main {
    public static void main(String[] args) {
        Coloring coloring;

        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            coloring = new Coloring(bf);
            System.out.println(coloring.selectColorCaseNum());
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class Coloring {
    private int n;
    private int k;
    private boolean[] visited;

    public Coloring(BufferedReader bf) throws IOException {
        this.n = Integer.parseInt(bf.readLine());
        this.k = Integer.parseInt(bf.readLine());
    }

    /*
    dp[n][k] = dp[i - 2][j - 1] + dp[i - 1][j]
    2간 전에 선택한거 + 이전 칸을 선택한거
     */
    public long selectColorCaseNum() {
        long[][] dp = new long[n + 1][k + 1];
        dp[1][1] = 1;
        dp[1][0] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = (dp[i - 2][j - 1] + dp[i - 1][j]) % 1000000003;
            }
        }
        return dp[n][k];
    }

}