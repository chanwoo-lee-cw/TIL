import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class PipeMoveSolution {
    
    /**
     * 파이프를 가로, 세로, 대각선으로 밀면서 우측하단까지 옮기는 방법의 경우의 수
     * @param n 벽의 넓이
     * @param wall 벽의 형태
     * @return 시작점에서 목적지까지 올 수 있는 경우의 수
     */
    public static long getPipeLineAble(
            int n,
            int[][] wall
    ) {
        long[][] horizonDp = new long[n + 1][n + 1];      // 가로
        long[][] verticalDp = new long[n + 1][n + 1];     // 세로
        long[][] diagonalDp = new long[n + 1][n + 1];     // 대각선
        horizonDp[1][2] = 1;
        for (int i = 3; i <= n; i++) {
            if (wall[1][i] == 1) continue;
            horizonDp[1][i] = horizonDp[1][i - 1];
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (wall[i][j] == 1) {
                    // 목적지가 막혀 있는 경우는 가지 못한다.
                    continue;
                }
                horizonDp[i][j] = diagonalDp[i][j - 1] + horizonDp[i][j - 1];
                verticalDp[i][j] = diagonalDp[i - 1][j] + verticalDp[i - 1][j];
                if (wall[i - 1][j] == 0 && wall[i][j - 1] == 0) {
                    // 가로로 갈땐 좌우가 막혀있는지 확인
                    diagonalDp[i][j] = verticalDp[i - 1][j - 1] + horizonDp[i - 1][j - 1] + diagonalDp[i - 1][j - 1];
                }
            }
        }

        return horizonDp[n][n] + verticalDp[n][n] + diagonalDp[n][n];
    }
}


public class Main {
    public static void main(String[] args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        try {
            int n = Integer.parseInt(bf.readLine());

            int[][] wall = new int[n + 1][n + 1];
            for (int i = 1; i < n + 1; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 1; j < n + 1; j++) {
                    wall[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.println(
                    PipeMoveSolution.getPipeLineAble(n, wall)
            );


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}