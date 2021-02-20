import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1915
public class Main {
    public static void main(String[] args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Solution solution;

        try {
            solution = new Solution(bf.readLine());
            solution.fillMatrix(bf);
            System.out.println(solution.getMaxSquare());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class Solution {
    int n, m;
    int[][] matrix;

    public Solution(String input) {
        StringTokenizer st = new StringTokenizer(input);
        this.n = Integer.parseInt(st.nextToken());
        this.m = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];
    }

    public void fillMatrix(BufferedReader bf) throws IOException {
        String inputLine;
        for (int i = 0; i < n; i++) {
            inputLine = bf.readLine();
            for (int j = 0; j < m; j++) {
                if (inputLine.charAt(j) == '0')
                    matrix[i][j] = 0;
                else
                    matrix[i][j] = 1;
            }
        }
    }

    /*
    최대 크기 상자를 구하는 방법: 1을 만나면 대각선 위를 체크, 대각선 위가 정사각형이 만들어져 있으면, 그걸 이어 받으면 된다.크
    반환 : 정사각형의 크기
     */
    public int getMaxSquare() {
        int[][] dp = new int[n][m];     // 각각 사각형의 크기를 저장
        int maxSquare = 0;              // 지금까지 만들어진 정사각형중 최대 크기
        for (int i = 0; i < n; i++) {
            check:
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0)
                    dp[i][j] = 0;
                else {
                    dp[i][j] = 1;
                    // 대각선 위의 숫자를 보고 정사각형이 이루어 지는치 체크
                    if (i > 0 && j > 0 && dp[i - 1][j - 1] != 0) {
                        for (int k = 1; k <= dp[i - 1][j - 1]; k++)
                            if (matrix[i - k][j] != 1 || matrix[i][j - k] != 1) {
                                // 만약 변이 빈 곳이 있으면 대각선 크기와 변의 길이중 더 작은 것을 정 사각형으로 저장한다.
                                dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, k);
                                continue check;
                            }
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                    maxSquare = Math.max(maxSquare, dp[i][j]);
                }
            }
        }
        return maxSquare * maxSquare;
    }
}