import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Java1012 {
    static int[][] way = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(bf.readLine());
        {
            int N, M, K;
            int x, y;
            int[][] field = null;
            int cnt = 0;
            for (int i = 0; i < T; i++) {
                cnt = 0;
                st = new StringTokenizer(bf.readLine());
                M = Integer.parseInt(st.nextToken());
                N = Integer.parseInt(st.nextToken());
                K = Integer.parseInt(st.nextToken());
                field = new int[N][M];

                for (int j = 0; j < K; j++) {
                    st = new StringTokenizer(bf.readLine());
                    x = Integer.parseInt(st.nextToken());
                    y = Integer.parseInt(st.nextToken());
                    field[y][x] = 1;
                }

                for (int a = 0; a < N; a++) {
                    for (int b = 0; b < M; b++) {
                        if (field[a][b] == 1) {
                            dfs(field, a, b, N, M);
                            cnt++;
                        }
                    }
                }
                System.out.println(cnt);
            }
        }
    }

    private static void dfs(int[][] field, int y, int x, int n, int m) {
        if (field[y][x] != 1) {
            return;
        } else {
            field[y][x] = -1;
        }
        {
            int xpos, ypos;
            for (int[] move : way) {
                xpos = x + move[0];
                ypos = y + move[1];
                if (xpos < 0 || ypos < 0 || xpos >= m || ypos >= n)
                    continue;
                dfs(field, ypos, xpos, n, m);
            }
        }
    }
}