import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Java11048 {
    public static int n;
    public static int m;
    public static int[][] maze;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        maze = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < m; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(getCandy());
    }

    public static int getCandy() {
        int[][] candy = new int[n][m];
        {
            int left = 0;
            int up = 0;
            int cross = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    up = i > 0 ? candy[i - 1][j] : 0;
                    left = j > 0 ? candy[i][j - 1] : 0;
                    cross = i > 0 && j > 0 ? candy[i - 1][j - 1] : 0;
                    candy[i][j] = Math.max(Math.max(left, up), cross) + maze[i][j];
                }
            }
        }
        return candy[n - 1][m - 1];
    }
}
