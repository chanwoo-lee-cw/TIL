import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Java1003 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        int[] arr = new int[T];
        int maxnum = 0;

        for (int i = 0; i < T; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
            maxnum = Math.max(maxnum, arr[i]);
        }

        int[][] fibonacci = new int[maxnum + 1][2];
        fibonacci[0][0] = 1;
        fibonacci[0][1] = 0;

        fibonacci[1][0] = 0;
        fibonacci[1][1] = 1;

        for (int i = 2; i < maxnum + 1; i++) {
            fibonacci[i][0] = fibonacci[i - 1][0] + fibonacci[i - 2][0];
            fibonacci[i][1] = fibonacci[i - 1][1] + fibonacci[i - 2][1];
        }

        for (int i = 0; i < T; i++) {
            System.out.println(String.format("%d %d", fibonacci[arr[i]][0], fibonacci[arr[i]][1]));
        }

    }
}