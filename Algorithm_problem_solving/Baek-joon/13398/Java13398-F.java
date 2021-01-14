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
        st = null;
        bf.close();

        System.out.println(getMaxSum(n, arr));
    }

    private static long getMaxSum(int n, int[] arr) {
        long[] dp = new long[n + 1];
        long sumMax = Long.MIN_VALUE;

        // i빼 버린 숫자.
        // j 현재 계산하고 있는 숫자
        long minvalue;
        for (int i = 0; i < n + 1; i++) {
            dp[0] = 0;
            minvalue = 0;
            for (int j = 1; j < n + 1; j++) {
                if (j == i)
                    dp[j] = dp[j - 1];
                else
                    dp[j] = dp[j - 1] + arr[j];
                sumMax = Math.max(sumMax, dp[j] - minvalue);
                minvalue = Math.min(minvalue, dp[j]);
            }
        }
        return sumMax;
    }
}