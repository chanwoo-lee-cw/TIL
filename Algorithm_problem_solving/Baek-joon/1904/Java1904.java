import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1904
public class Main {
    public static void main(String[] args) {
        int n;
        BinNum binNum;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(bf.readLine());
            binNum = new BinNum();
            System.out.println(binNum.getCaseBinNum(n));
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class BinNum {
    /*
    점화식 : 이전에 만들어졌던 숫자들의 끝에 0을 붙히거나 11을 붙히면 된다. - dp[i - 1] + dp[i - 2]
     */
    public int getCaseBinNum(int n) {
        if(n == 1)
            return 1;
        int[] dp = new int[n + 1];
        // baseCase
        dp[1] = 1;
        dp[2] = 2;
        // 점화식
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 15746;
        }

        return dp[n];
    }
}
