import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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
        System.out.println(result);
    }

    // 최소 거울의 갯수를 반환하는 매서드.
    private static int getNeedMirrorNum(int h, int w, boolean[][] matrix, ArrayList<int[]> waypoint) {
        // 현재 그 지점까지 오기 위해서 몇개나 거울이 푤요한가를 해당 점에서 일직선 상으로 연결 된 것에 표시
        int[] start = waypoint.remove(0);   // 시작점.
        int[] destination = waypoint.remove(0);     // 도착점.
        int[][] visited = new int[h][w];    // 그 점까지 오는데 거울이 몇개가
        for (int i = 0; i < h; i++)
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        Queue<Beam> queue = new LinkedList<>();

        Beam curr = new Beam(start[0], start[1], 0);
        queue.add(curr);
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (curr.y == destination[0] && curr.x == destination[1])
                return curr.countMirror;
            if (visited[curr.y][curr.x] < curr.countMirror)
                continue;
            visited[curr.y][curr.x] = curr.countMirror;
            for (int i = 0; i < h; i++) {
                if (visited[i][curr.x] < curr.countMirror || matrix[i][curr.x])
                    break;
                queue.add(new Beam(i, curr.x, curr.countMirror + 1));
            }
            for (int j = 0; j < w; j++) {
                if (visited[curr.y][j] < curr.countMirror || matrix[curr.y][j])
                    break;
                queue.add(new Beam(curr.y, j, curr.countMirror + 1));
            }
        }

        return 0;
    }
}

class Beam {
    int x;      // x좌표
    int y;      // y좌표
    int countMirror;    // 현재 위치까지 오는데 거울의 갯수.

    Beam(int y, int x, int countMirror) {
        this.y = y;
        this.x = x;
        this.countMirror = countMirror;
    }

}