import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16194
public class Main {

    // 필요한 최소 비용을 리턴하는 함수.
    public static int getMaxPay(int n, int[] p) {
        int[][] dp = new int[n + 1][n + 1];

        // i 들어 있는 카드의 수,
        // j 구매하고 싶은 카드의 수
        int get1, get2;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (j >= i){
                    // 둘 중 더 0이 아닌 더 작은 수를 넣는다.
                    get1 = dp[i - 1][j];
                    get2 = dp[i][j - i] + p[i];
                    if(get1 != 0 && get2 != 0)
                        dp[i][j] = Math.min(get1, get2);
                    else if(get1 != 0)
                        dp[i][j] = get1;
                    else if(get2 != 0)
                        dp[i][j] = get2;
                }
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[n][n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        int[] p = new int[n + 1];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 1; i < n + 1; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(getMaxPay(n, p));
    }

}