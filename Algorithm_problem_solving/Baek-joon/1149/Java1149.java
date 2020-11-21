import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1149
public class Main {
    public static int N ;
    public static int[][] color ;
    public static long[][] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        long result = Integer.MAX_VALUE;
        N = Integer.parseInt(bf.readLine());
        color = new int[N][3];
        dp = new long[N][3];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < 3; j++) {
                color[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i =0 ; i<3; i++) {
            result = Math.min(result, memo(color, 0 , i));
        }
        System.out.println(result);
    }

    // argments
    // color : inputed pating cost
    // pos : now painting house
    // prePainted : a color for the present house
    public static long memo(int[][] color, int pos, int prePainted) {
        if(N - 1 == pos){
            return color[pos][prePainted];
        }
        else if(dp[pos][prePainted] != 0) {
            return dp[pos][prePainted];
        }

        long min_value = Integer.MAX_VALUE;
        for(int i=0; i<3; i++) {
            if (i != prePainted) {
                min_value = Math.min(min_value, memo(color, pos + 1, i));
            }
        }
        dp[pos][prePainted] = min_value + color[pos][prePainted];
        return dp[pos][prePainted];
    }
}