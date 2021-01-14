import java.util.Arrays;
import java.util.Scanner;

// https://www.acmicpc.net/problem/2839
public class Java2839 {
    public static int getSugarNum(int n) {
        int[] sugar = { 3, 5 };
        int[][] sugarNeed = new int[2][n + 1];
        for (int i = 0; i < sugar.length; i++) {
            Arrays.fill(sugarNeed[i], 1, n + 1, Integer.MAX_VALUE - 1);
        }
        for (int i = 0; i < sugar.length; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 0) {
                    if (j % sugar[i] == 0) {
                        sugarNeed[i][j] = j / sugar[i];
                    }
                } else {
                    if (j - sugar[i] < 0) {
                        sugarNeed[i][j] = sugarNeed[i - 1][j];
                        continue;
                    }
                    sugarNeed[i][j] = Math.min(sugarNeed[i][j - sugar[i]] + 1, sugarNeed[i - 1][j]);
                }
            }
        }
        return sugarNeed[sugar.length - 1][n];
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int sugur = getSugarNum(n);
        scan.close();
        if (sugur >= Integer.MAX_VALUE - 1)
            System.out.println(-1);
        else
            System.out.println(sugur);
        
    }
}