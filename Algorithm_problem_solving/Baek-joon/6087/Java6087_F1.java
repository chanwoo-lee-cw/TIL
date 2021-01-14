import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/6087
public class Main {
    public static int[][] way = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        int h, w;   // 세로, 가로
        boolean[][] matrix;     // 좌표 공간
        ArrayList<int[]> waypoint;  // 레이저로 연결해야 하는 칸

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        matrix = new boolean[h][w];

        st = null;

        waypoint = new ArrayList<>();

        {
            String inputLine;
            for (int i = 0; i < h; i++) {
                inputLine = bf.readLine();
                for (int j = 0; j < w; j++) {
                    if (inputLine.charAt(j) == '*')
                        matrix[i][j] = true;
                    else {
                        matrix[i][j] = false;
                        if (inputLine.charAt(j) == 'C') {
                            waypoint.add(new int[]{i, j});
                        }
                    }
                }
            }
            bf.close();
        }

        int result = getNeedMirrorNum(h, w, matrix, waypoint);
    }

    // 최소 거울의 갯수를 반환하는 매서드.
    private static int getNeedMirrorNum(int h, int w, boolean[][] matrix, ArrayList<int[]> waypoint) {
        // 레이저가 벽에 부딪히면 튕기는 방식으로 했다.
        // 거울을 벽에만 설치할 수 있는게 아니기 때문에 Fail
        int[] start = waypoint.remove(0);   // 시작점.
        int[] destination = waypoint.remove(0);     // 도착점.
        Queue<Beam> queue = new LinkedList<>();
        for (int[] next : way) {
            queue.add(new Beam(start[0], start[1], next, 0));
        }
        Beam curr;
        int nextY, nextX;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            nextY = curr.y;
            nextX = curr.x;
            do {
                if (nextY == destination[0] && nextX == destination[1])
                    return curr.countMirror;
                nextY = nextY + curr.direction[0];
                nextX = nextX + curr.direction[1];
            } while (0 <= nextX && nextX < w && 0 <= nextY && nextY < h && !matrix[nextY][nextX]);
            if(curr.y == nextY - curr.direction[0] && curr.x == nextX - curr.direction[1])
                continue;
            if (curr.direction[0] != 0) {
                queue.add(new Beam(nextY - curr.direction[0], nextX - curr.direction[1], way[2], curr.countMirror + 1));
                queue.add(new Beam(nextY - curr.direction[0], nextX - curr.direction[1], way[3], curr.countMirror + 1));
            } else {
                queue.add(new Beam(nextY - curr.direction[0], nextX - curr.direction[1], way[0], curr.countMirror + 1));
                queue.add(new Beam(nextY - curr.direction[0], nextX - curr.direction[1], way[1], curr.countMirror + 1));
            }
        }

        return 0;
    }
}

class Beam {
    int x;      // x좌표
    int y;      // y좌표
    int[] direction;    // 빔의 진행 방향
    int countMirror;    // 현재 위치까지 오는데 거울의 갯수.

    Beam(int y, int x, int[] direction, int countMirror) {
        this.y = y;
        this.x = x;
        this.direction = direction;
        this.countMirror = countMirror;
    }

}