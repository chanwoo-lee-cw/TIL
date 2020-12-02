import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Java1351 {
    private static long n;
    private static int p;
    private static int q;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Long.parseLong(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        bf.close();
        long[] dp = new long[30001];
        if (n != 0) {
            System.out.println(recursion(dp, (int) (n / p)) + recursion(dp, (int) (n / q)));
        } else {
            System.out.println(1);
        }
    }

    public static long recursion(long[] dp, int num) {
        if (num == 0)
            return 1;
        else if (num < 30000) {
            if (dp[num] != 0) {
                return dp[num];
            } else {
                return dp[num] = recursion(dp, num / p) + recursion(dp, num / q);
            }
        }
        return recursion(dp, num / p) + recursion(dp, num / q);
    }
}
