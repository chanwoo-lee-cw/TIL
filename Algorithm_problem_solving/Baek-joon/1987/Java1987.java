import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Alphabet alphabet;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            alphabet = new Alphabet(bf);
            System.out.println(alphabet.getMaxMove());
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class Alphabet {
    private int r, c;       // 알파벳 배열의 크기
    private int[][] matrix;     // 알파벳을 순서대로 서장
    private boolean[] visited;  // 해당 순번의 알파벳의 사용 여부
    private static int[][] way = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // 다음에 방문할 수 있는 위치
    private int maxMove;    // 최대로 이동할 수 있는 위치

    public Alphabet(BufferedReader bf) throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        this.r = Integer.parseInt(st.nextToken());
        this.c = Integer.parseInt(st.nextToken());
        this.matrix = new int[r][c];
        this.visited = new boolean[26];
        this.maxMove = 0;

        String input;
        for (int i = 0; i < r; i++) {
            input = bf.readLine();
            for (int j = 0; j < c; j++) {
                matrix[i][j] = input.charAt(j) - 'A';
            }
        }
    }

    /*
    dfs로 최대로 움직일 수 있는 경로를 추적한다.
     */
    public void dfs(int y, int x, int move) {
        int nextY, nextX;
        maxMove = Math.max(maxMove, ++move);
        visited[matrix[y][x]] = true;
        for (int[] next : way) {
            nextY = y + next[0];
            nextX = x + next[1];
            if (nextY < 0 || nextX < 0 || nextY >= r || nextX >= c)
                continue;
            else if (visited[matrix[nextY][nextX]])
                continue;
            dfs(nextY, nextX, move);
        }
        visited[matrix[y][x]] = false;
    }

    /*
    경로 추적을 실행하고 최대로 이동할 수 있는 경로를 출력한다.
     */
    public int getMaxMove() {
        dfs(0, 0, 0);
        return maxMove;
    }
}