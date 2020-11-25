import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/1202
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        ArrayList<int[]> itemlist = new ArrayList<>();
        itemlist.add(new int[] { 0, 0 });
        {
            int w = 0;
            int v = 0;
            int[] inlist = null;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(bf.readLine());
                w = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                inlist = new int[] { w, v };
                itemlist.add(inlist);
            }
        }
        int[][] dp = new int[n + 1][k + 1];
        int[] item = null;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < k + 1; j++) {
                item = itemlist.get(i);
                if (j >= item[0]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - item[0]] + item[1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        System.out.println(dp[n][k]);
    }
}