import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2407
public class Main {
    public static void main(String[] args) {
        Combination combination;
        int n, m;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            combination = new Combination(n, m);
            System.out.println(combination.getNM(n, m));
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Combination {
    private int n, m;       // 구해야 하는 n과 m
    private long[][] dp;    // dp[n][m] = nCm

    public Combination(int n, int m) {
        this.n = n;
        this.m = m;
        this.dp = new long[n + 1][m + 1];
    }

    public long getNM(int n, int m) {
        if (m == 0)
            return 1;
        else if (n == m)
            return 1;
        else if (dp[n][m] != 0)
            return dp[n][m];
        // else
        dp[n][m] = getNM(n - 1, m - 1) + getNM(n - 1, m);
        return dp[n][m];
    }
}