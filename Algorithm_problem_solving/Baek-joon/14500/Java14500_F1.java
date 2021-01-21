import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int[][] way = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

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

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                maxValue = Math.max(maxValue, getPositionMax(n, m, paper, i, j));
            }
        }
        System.out.println(maxValue);
    }

    public static int getPositionMax(int n, int m, int[][] paper, int y, int x) {
        int maxValue = 0;
        Position curr = new Position(y, x, 1, paper[y][x]);
        Queue<Position> queue = new LinkedList<>();
        queue.add(curr);

        int nextY, nextX;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (curr.getSize() == 4) {
                maxValue = Math.max(maxValue, curr.getPoint());
                continue;
            }
            for (int[] next : way) {
                nextY = curr.getY() + next[0];
                nextX = curr.getX() + next[1];
                if (nextY < 0 || nextX < 0 || nextY >= n || nextX >= m)
                    continue;
                queue.add(new Position(nextY, nextX, curr.getSize() + 1, curr.getPoint() + paper[nextY][nextX]));
            }
        }
        return maxValue;
    }
}

class Position {
    private int y;
    private int x;
    private int size;
    private int point;

    public Position(int y, int x, int size, int point) {
        this.y = y;
        this.x = x;
        this.size = size;
        this.point = point;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}