import java.util.Scanner;
 
public class Main {
 
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
         
        int[][] matrix = new int[n+1][m+1];
        int[][] dp = new int[n+1][m+1];
        int[][] temp = new int[2][m+2];
         
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++){
                matrix[i][j] = in.nextInt();
            }
        }
         
        dp[1][1] = matrix[1][1];
         
        for(int j = 2; j <= m; j++) dp[1][j] = matrix[1][j]+ dp[1][j-1];
         
        for(int i = 2; i <= n; i++) {
             
            temp[0][0] = dp[i-1][1];
            for(int j = 1; j <= m; j++) {
                temp[0][j] = Math.max(temp[0][j-1], dp[i-1][j])+ matrix[i][j];
            }
             
            temp[1][m+1] = dp[i-1][m];
            for(int j = m; j >= 1; j--) {
                temp[1][j] = Math.max(temp[1][j+1], dp[i-1][j]) + matrix[i][j];
            }
             
            for(int j = 1; j <= m; j++) {
                dp[i][j] = Math.max(temp[0][j], temp[1][j]);
            }
        }
//        for(int i = 1; i <= n; i++) {
//            for(int j = 1; j <= m; j++){
//                System.out.printf("%4d",dp[i][j]);
//            }
//            System.out.println();
//        }
        
        System.out.println(dp[n][m]);
         
        in.close();
    }
 
}