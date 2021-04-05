import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Chocolate chocolate;
        int m, n;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            chocolate = new Chocolate(n, m);
            System.out.println(chocolate.makeCutting());
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class Chocolate {
    private int n, m;       // 초콜릿의 크기
    private int[][] dp;     // dp[n][m] - 초콜릿의 크기가 n,m 일때 잘라야 되는 횟수

    public Chocolate(int n, int m) {
        this.n = n;
        this.m = m;
        this.dp = new int[n + 1][m + 1];
    }

    /*
    초콜릿 크기가 n, m일 때 초콜릿을 잘라야 되는 횟수를 구한다.
    mid를 기준으로 [n,mid], [n, m-mid]식으로 구한다.
    */
    public int cutting(int n, int m) {
        if (m == 1 && n == 1)
            return 0;
        else if (dp[n][m] != 0)
            return dp[n][m];
        // else
        if (m > n) {
            int mid = m / 2;
            dp[n][m] = cutting(n, mid) + cutting(n, m - mid) + 1;
        } else {
            int mid = n / 2;
            dp[n][m] = cutting(mid, m) + cutting(n - mid, m) + 1;
        }
        return dp[n][m];

    }

    public int makeCutting() {
        return cutting(n, m);
    }
}