import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        MatrixSum matrixSum;    // 배열을 저장할 객체
        int n, m;           // 배열의 크기를 저장한다.
        int k;          // 테스트 케이스의 갯수
        int answer = 0;
        StringBuilder sb = new StringBuilder();     // 빠른 출력을 위해 답을 문자열로 저장
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            matrixSum = new MatrixSum(n, m);
            matrixSum.fillMatrix(bf);
            k = Integer.parseInt(bf.readLine());

            {
                int i, j, x, y;
                sb = new StringBuilder();
                for (int t = 0; t < k; t++) {
                    st = new StringTokenizer(bf.readLine());
                    i = Integer.parseInt(st.nextToken());
                    j = Integer.parseInt(st.nextToken());
                    x = Integer.parseInt(st.nextToken());
                    y = Integer.parseInt(st.nextToken());
                    answer = matrixSum.getSum(i, j, x, y);
                    sb.append(answer);
                    sb.append("\n");
                }
            }
            System.out.print(sb.toString());
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class MatrixSum {
    private int n, m;           // 배열의 크기
    private int[][] matrix;     // 각 배열에 저장된 수
    private int[][] dp;         // dp[i][j] - i,j 까지의 모든 수의 합

    public MatrixSum(int n, int m) {
        this.n = n;
        this.m = m;
        this.matrix = new int[n + 1][m + 1];
        this.dp = new int[n + 1][m + 1];
    }

    /*
    배열의 각 부분을 채우고, 각 위치까지의 배열의 합을 구한다.
     */
    public void fillMatrix(BufferedReader bf) throws IOException {
        StringTokenizer st;
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j < m + 1; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] + matrix[i][j] - dp[i - 1][j - 1];
            }
        }
    }

    /*
    a,b ~ x,y까지의 합을 반환한다.
    */
    public int getSum(int a, int b, int x, int y) {
        return dp[x][y] - dp[a - 1][y] - dp[x][b - 1] + dp[a - 1][b - 1];
    }
}

