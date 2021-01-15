import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int n;
    public static int[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        matrix = new int[n + 1][n + 1];
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j < n + 1; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        arr90Spin();
        for (int i = 1; i < n + 1; i++) {
            for (int j=1;j<n+1;j++) {
                System.out.printf("%-2d", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        arrreverse90Spin();
        for (int i = 1; i < n + 1; i++) {
            for (int j=1;j<n+1;j++) {
                System.out.printf("%-2d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    private static void arr90Spin() {
        int temp;
        for (int i = 1; i <= n / 2; i++) {
            for (int j = i; j <= n - i; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][n + 1 - i];
                matrix[j][n + 1 - i] = matrix[n + 1 - i][n + 1 - j];
                matrix[n + 1 - i][n + 1 - j] = matrix[n + 1 - j][i];
                matrix[n + 1 - j][i] = temp;
            }
        }
    }
    private static void arrreverse90Spin() {
        int temp;
        for (int i = 1; i <= n / 2; i++) {
            for (int j = i; j <= n - i; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[n + 1 - j][i];
                matrix[n + 1 - j][i] = matrix[n + 1 - i][n + 1 - j];
                matrix[n + 1 - i][n + 1 - j] = matrix[j][n + 1 - i];
                matrix[j][n + 1 - i] = temp;
            }
        }
    }
}