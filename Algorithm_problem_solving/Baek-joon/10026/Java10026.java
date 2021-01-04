import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static int[][] way = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        int n;      // 그림의 크기
        int[][] paint;  // N * N의 그림, 1 빨강, 2 초록, 3 파랑

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        paint = new int[n][n];

        {
            String inputLine;   // 그림을 입력 받는 변수
            for (int i = 0; i < n; i++) {
                inputLine = bf.readLine();
                for (int j = 0; j < n; j++) {
                    if (inputLine.charAt(j) == 'R') {
                        paint[i][j] = 1;
                    } else if (inputLine.charAt(j) == 'G') {
                        paint[i][j] = 2;
                    } else {
                        paint[i][j] = 3;
                    }
                }
            }
        }
        bf.close();

        System.out.println(getColorArea(n, paint));


    }

    // 샐 영역을 반환하는 함수
    // 매개변수 : n 도화지의 크기, paint 그림
    private static String getColorArea(int n, int[][] paint) {
        StringBuilder sb = new StringBuilder();     // 출력을 위해 저장
        int nomalSeeAreaCnt = 0;    // 색약이 아닌 사람이 보는 구간 갯수
        int[][] nomalSee = new int[n][n];   // 색맹이 아닌 사람이 보는 그림
        int colorBlindSeeAreaCnt = 0;       // 색맹인 사람이 보는 구간의 갯구
        int[][] colorBlindSee = new int[n][n];      // 색맹인 사람이 보는 그

        for (int i = 0; i < n; i++) {
            System.arraycopy(paint[i], 0, nomalSee[i], 0, n);
            System.arraycopy(paint[i], 0, colorBlindSee[i], 0, n);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (nomalSee[i][j] != 0) {
                    executeBFS(n, nomalSee, i, j, false);
                    nomalSeeAreaCnt++;
                }
                if (colorBlindSee[i][j] != 0) {
                    executeBFS(n, colorBlindSee, i, j, true);
                    colorBlindSeeAreaCnt++;
                }
            }
        }
        sb.append(nomalSeeAreaCnt);
        sb.append(" ");
        sb.append(colorBlindSeeAreaCnt);
        return sb.toString();
    }

    // 볼 수 있는 구간을 bfs로 탐색하는 함수
    private static void executeBFS(int n, int[][] paint, int y, int x, boolean isColorBlind) {
        int thisColor = paint[y][x];
        Queue<int[]> queue = new LinkedList<>();
        int[] curr = new int[]{y, x};
        queue.add(curr);
        paint[curr[0]][curr[1]] = 0;

        int nextY, nextX;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            for (int[] next : way) {
                nextY = curr[0] + next[0];
                nextX = curr[1] + next[1];
                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n || paint[nextY][nextX] == 0 || checkDifferentColor(thisColor, paint[nextY][nextX], isColorBlind))
                    continue;
                queue.add(new int[]{nextY, nextX});
                paint[nextY][nextX] = 0;
            }
        }
    }

    // 색이 다르면 true를 반환한다.
    private static boolean checkDifferentColor(int thisColor, int checkColor, boolean isColorBlind) {
        if (isColorBlind) {
            if (thisColor == 1 || thisColor == 2) {
                if (checkColor == 1 || checkColor == 2)
                    return false;
                else
                    return true;
            } else {
                if (thisColor != checkColor)
                    return true;
                else
                    return false;
            }
        } else {
            if (thisColor != checkColor)
                return true;
            else
                return false;
        }
    }
}