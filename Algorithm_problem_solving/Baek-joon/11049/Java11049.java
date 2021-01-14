import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11049
public class Main {

    /*
    생각 정리
    1. 일단 dfs와 비슷한 방법으로 풀어볼까...
    2. 근데 그러면 중복 계산이 너무 많다.
    3. dp[i][j] i~j에서 최소 값을 구하는 방식으로 해볼까...
    4. 순서가 바뀌지 않으니 이게 맞는거 같고
    5. dp[i][k] + dp[k+1][j] 값이 최소
     */
    public static void main(String[] args) throws IOException {
        int n;      // 행렬의 갯수
        int[][] matrix;     // 행렬들을 저장할 배열
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(bf.readLine());
        matrix = new int[n + 1][2];
        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(bf.readLine());
            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
        }

        long[][] dp = getMinCalulate(matrix, n);

        System.out.println(dp[1][n]);
    }

    /*
    매개 변수 행렬의 행과 열 수를 저장한 변수
    반환 값 : dp[a][b] - a에서 b까지 연산 횟수의 최솟값

    베이스케이스 : start == end - 0
    점화식 :
    dp[start][end] = Min(dp[start][end], dp[start][k] + dp[k+1][end] + matrix[start][0]*matrix[start][1]*matrix[end][1])
     */
    private static long[][] getMinCalulate(int[][] matrix, int n) {
        long[][] dp = new long[n + 1][n + 1];   // dp
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[1][1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i][i] = 0;
            for (int j = i - 1; j > 0; j--) {
                for (int k = j; k < i; k++) {
//                    System.out.printf("[%d][%d] ~ [%d][%d] = %d\n",j,k,k+1,i,dp[j][k] + dp[k + 1][i] + matrix[j][0] * matrix[k][1] * matrix[i][1]);
                    dp[j][i] = Math.min(dp[j][i], dp[j][k] + dp[k + 1][i] + matrix[j][0] * matrix[k][1] * matrix[i][1]);
                }
            }
        }
        return dp;
    }
}