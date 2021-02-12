import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        int t;  // 테스트 케이스의 갯수
        int[] arr;  // 입력된 수의 배열
        int[] dp;   // g(x) 값을 저장할 배열
        int maxNum; // 입력된 수 중에서의 최대값

        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            t = Integer.parseInt(bf.readLine());

            arr = new int[t + 1];
            maxNum = 0;
            for (int i = 0; i < t; i++) {
                arr[i] = Integer.parseInt(bf.readLine());
                maxNum = Math.max(maxNum, arr[i]);
            }
            dp = fillDP(maxNum);

            for (int i = 0; i < t; i++) {
                System.out.println(dp[arr[i]]);
            }

            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    약수의 갯수 더하기
     */
    private static int[] fillDP(int maxNum) {
        int[] dp = new int[maxNum + 1];
        dp[1] = 1;
        dp[2] = 4;

        double sqrtNum;
        for (int i = 3; i < maxNum + 1; i++) {
            sqrtNum = Math.sqrt(i);
            for (int j = 1; j <= sqrtNum; j++) {
                if (i % j == 0) {
                    dp[i] += j;
                    if (i / j != j) {
                        dp[i] += i / j;
                    }
                }
            }
            dp[i] += dp[i - 1];
        }
        return dp;
    }
}