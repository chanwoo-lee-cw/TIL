import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Java1495 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] music = new int[n+1];
        st = new StringTokenizer(bf.readLine());
        for(int i =1; i<n+1; i++) {
            music[i] = Integer.parseInt(st.nextToken());
        }
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][s] = true;

        for(int i=1; i<n+1; i++){
            for(int j=0; j <= m; j++) {
                if(Arrays.asList(dp[i-1]).contains(true)){
                    System.out.println(-1);
                    return;
                }
                if (dp[i-1][j]) {
                    if(j + music[i] <= m) {
                        dp[i][j + music[i]] = true;
                    }
                    if(j - music[i] >= 0) {
                        dp[i][j - music[i]] = true;
                    }
                }
            }
        }
        for(int i = m; i>=0; i--) {
            if(dp[n][i]) {
                System.out.println(i);
                return;
            }
        }
    }
}