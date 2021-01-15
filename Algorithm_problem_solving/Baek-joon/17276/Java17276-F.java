import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int n;
    public static int[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(bf.readLine());

        for (int k = 0; k < t; k++) {
            st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            matrix = new int[n + 1][n + 1];
            for (int i = 1; i < n + 1; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 1; j < n + 1; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            spinArr(r);
        }
    }

    private static void spinArr(int r) {
        // 360, 0도 그냥 그대로 반환.
        if (r == 0 || r == 360) {
            // 돌리는 방향 지정
            printArr();
        } else {
            boolean direction = r > 0 ? true : false;
            r = Math.abs(r);
            // 180도 이상 정방향 역방향 전환
            // 180도 이하 정방량 역방향 그대로 출력
            if (r > 180) {
                direction = direction ? false : true;
                r = 360 - r;
            }
            r = r / 45;
            for (int i = 0; i < r; i++) {
                if (direction)
                    trun45Degree();
                else
                    trunUn45Degree();
            }
            printArr();
        }
    }

    // 시계 방향으로 45도 회전시키는 함수
    private static void trunUn45Degree() {
        int temp;
        for (int i = 1; i <= n / 2; i++) {
            temp = matrix[i][i];
            matrix[i][i] = matrix[i][(n + 1 - i) / 2 + 1];
            matrix[i][(n + 1 - i) / 2 + 1] = matrix[i][n + 1 - i];

            matrix[i][n + 1 - i] = matrix[(n + 1 - i) / 2 + 1][n + 1 - i];
            matrix[(n + 1 - i) / 2 + 1][n + 1 - i] = matrix[n + 1 - i][n + 1 - i];

            matrix[n + 1 - i][n + 1 - i] = matrix[n + 1 - i][(n + 1 - i) / 2 + 1];
            matrix[n + 1 - i][(n + 1 - i) / 2 + 1] = matrix[n + 1 - i][i];

            matrix[n + 1 - i][i] = matrix[(n + 1 - i) / 2 + 1][i];
            matrix[(n + 1 - i) / 2 + 1][i] = temp;
        }
    }

    // 시계 반대 방향 45도로 회전 시키는 함수
    private static void trun45Degree() {
        int temp;
        for (int i = 1; i <= n / 2; i++) {
            temp = matrix[i][i];
            matrix[i][i] = matrix[(n + 1 - i) / 2 + 1][i];
            matrix[(n + 1 - i) / 2 + 1][i] = matrix[n + 1 - i][i];

            matrix[n + 1 - i][i] = matrix[n + 1 - i][(n + 1 - i) / 2 + 1];
            matrix[n + 1 - i][(n + 1 - i) / 2 + 1] = matrix[n + 1 - i][n + 1 - i];

            matrix[n + 1 - i][n + 1 - i] = matrix[(n + 1 - i) / 2 + 1][n + 1 - i];
            matrix[(n + 1 - i) / 2 + 1][n + 1 - i] = matrix[i][n + 1 - i];

            matrix[i][n + 1 - i] = matrix[i][(n + 1 - i) / 2 + 1];
            matrix[i][(n + 1 - i) / 2 + 1] = temp;
        }
    }

    // 배열을 출력하는 함수
    private static void printArr() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                sb.append(matrix[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}