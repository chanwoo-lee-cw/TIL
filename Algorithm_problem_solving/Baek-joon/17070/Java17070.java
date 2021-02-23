import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        Solution solution;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            solution = new Solution(bf);
            System.out.println(solution.getMoveCase());
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Solution {
    private int n;  // 공간의 크기
    private boolean[][] matrix; // 벽이 세워져 있는 위치

    public Solution(BufferedReader bf) throws IOException {
        n = Integer.parseInt(bf.readLine());

        matrix = new boolean[n + 1][n + 1];
        StringTokenizer st;

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= n; j++) {
                if (Integer.parseInt(st.nextToken()) == 1)
                    matrix[i][j] = true;
                else
                    matrix[i][j] = false;
            }
        }
    }

    /*
    n,n까지 움직이는 경우의 수를 반환
     */
    public int getMoveCase() {
        int[][][] dp = new int[3][n + 1][n + 1];    // 각각 파이브의 상황을 3가지를 따로 저장, 0 가로, 1 세로, 2 대각
        // basecase
        dp[0][1][2] = 1;
        // 점화식
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i][j])
                    continue;
                else if (i == 1 && j <= 2)  // 1,2 부터 시작이므로 그 이하는 전부 빼준다.
                    continue;
                dp[0][i][j] = dp[0][i][j - 1] + dp[2][i][j - 1];
                dp[1][i][j] = dp[1][i - 1][j] + dp[2][i - 1][j];
                if (!matrix[i - 1][j] && !matrix[i][j - 1]) {
                    dp[2][i][j] = dp[0][i - 1][j - 1] + dp[1][i - 1][j - 1] + dp[2][i - 1][j - 1];
                }
            }
        }

        return dp[0][n][n] + dp[1][n][n] + dp[2][n][n];
    }
}