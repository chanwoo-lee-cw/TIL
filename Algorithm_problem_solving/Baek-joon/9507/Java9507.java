import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static long[] dp;    // 피보나치를 저장할 dp

    public static void main(String[] args) {
        int n;  // 입력될 수의 갯수
        int[] numInputList;     // 입력된 수의 리스트
        int maxNum = 0;         // 입력된 수 중에 최소값

        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(bf.readLine());
            numInputList = new int[n];
            for (int i = 0; i < n; i++) {
                numInputList[i] = Integer.parseInt(bf.readLine());
                maxNum = Math.max(maxNum, numInputList[i]);
            }
            bf.close();

            dp = new long[maxNum + 1];

            for (int i = 0; i < n; i++) {
                System.out.println(koong(numInputList[i]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    입력된 수의 피보나치 수를 반환
     */
    private static long koong(int n) {
        if (dp[n] != 0) {
            return dp[n];
        } else if (n < 2) {
            dp[n] = 1;
            return dp[n];
        } else if (n == 2) {
            dp[n] = 2;
            return dp[n];
        } else if (n == 3) {
            dp[n] = 4;
            return dp[n];
        } else {
            dp[n] = koong(n - 1) + koong(n - 2) + koong(n - 3) + koong(n - 4);
            return dp[n];
        }
    }

}
