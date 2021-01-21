import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int[][] way = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    // T자형 4가지 형태
    public static int[][][] wayT = new int[][][]{
            {{0, 0}, {1, 0}, {1, 1}, {2, 0}},
            {{0, 0}, {1, 0}, {1, -1}, {2, 0}},
            {{0, 0}, {0, 1}, {0, 2}, {-1, 1}},
            {{0, 0}, {0, 1}, {0, 2}, {1, 1}},
    };

    public static void main(String[] args) throws IOException {
        int n, m;   // 종이의 크기
        int[][] paper;  // 종이에 써진 점수
        int maxValue = Integer.MIN_VALUE;   //테트로미노의 최대 점
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        paper = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < m; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = null;
        bf.close();

        boolean[][] visited = new boolean[n][m];    // 방문 여부
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                maxValue = Math.max(maxValue, getPositionMax(n, m, paper, i, j, 1, paper[i][j], visited));
                visited[i][j] = false;
                maxValue = Math.max(maxValue, getTMax(n, m, paper, i, j));
            }
        }
        System.out.println(maxValue);
    }

    /*
    T자 모양은 dfs로 구할 수 없으므로 따로 빼서 계산한다.
    매개변수 : n,m - 종이의 크기, paper - 종이의 점수, y,x - 탐색을 시작할 위치
     */
    public static int getTMax(int n, int m, int[][] paper, int y, int x) {
        int maxValue = Integer.MIN_VALUE;
        int value;
        int nextY, nextX;
        for (int[][] shapeT : wayT) {
            value = 0;
            for (int[] next : shapeT) {
                nextY = y + next[0];
                nextX = x + next[1];
                if (nextY < 0 || nextX < 0 || nextY >= n || nextX >= m)
                    continue;
                value += paper[nextY][nextX];
            }
            maxValue = Math.max(maxValue, value);
        }
        return maxValue;
    }

    /*
    나머지 방법은 dfs방식으로 탐색한다.
    매개변수 : n,m - 종이의 크기, paper - 종이의 점수, y,x - 탐색을 시작할 위치, 재귀의 횟수(크기), 현재까지의 점수, 방문 여부
    */
    private static int getPositionMax(int n, int m, int[][] paper, int y, int x, int size, int value, boolean[][] visited) {
        if (size == 4)
            return value;
        int maxValue = Integer.MIN_VALUE;

        int nextY, nextX;
        for (int next[] : way) {
            nextY = y + next[0];
            nextX = x + next[1];
            if (nextY < 0 || nextX < 0 || nextY >= n || nextX >= m)
                continue;
            if (visited[nextY][nextX])
                continue;
            visited[nextY][nextX] = true;
            maxValue = Math.max(maxValue, getPositionMax(n, m, paper, nextY, nextX, size + 1, value + paper[nextY][nextX], visited));
            visited[nextY][nextX] = false;
        }
        return maxValue;
    }
}