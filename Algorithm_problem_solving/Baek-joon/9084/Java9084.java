import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/9084
public class Main {
    public static void main(String[] args) {
        int testcase;       // 테스트 케이스의 개수
        Coins coin;         // 문제의 객
        long answer = 0;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            testcase = Integer.parseInt(bf.readLine());

            for (int t = 0; t < testcase; t++) {
                coin = new Coins(bf);
                answer = coin.makeWantCaseNum(Integer.parseInt(bf.readLine()));
                System.out.println(answer);
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Coins {
    int n;          // 동전의 갯수
    int[] coins;    // 동전의 종류

    public Coins(BufferedReader bf) throws IOException {
        this.n = Integer.parseInt(bf.readLine());
        this.coins = new int[n + 1];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= n; i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }
    }

    // 원하는 금액을 주어진 동전으로 만들 수 있는 경우의 수를 반환
    public long makeWantCaseNum(int wants) {
        long[][] dp = new long[n + 1][wants + 1];       // 경우의 수
        // basecase
        for (int j = 1; j < wants + 1; j++)
            if (j % coins[1] == 0)
                dp[1][j] = 1;
        for (int i = 1; i < n + 1; i++)
            dp[i][0] = 1;
        // 점화식
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j < wants + 1; j++) {
                if (j - coins[i] < 0)
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]];
            }
        }
        return dp[n][wants];
    }
}