import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/6087
public class Main {

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
        // 현재 점을 중심으로 4방향으로 일직선으로 뻗어나가면서 만나는 점이나 이미 지나간 점이 아니면 모두 큐에 집어 넣는다.
        // 큐에서 하나하나 뽑으면서 각각 그 위치에서 뻗어나가면서 목적지에 도착할때까지 반복
        int[] start = waypoint.remove(0);   // 시작점.
        int[] destination = waypoint.remove(0);     // 도착점.
        int[][] visited = new int[h][w];    // 이미 방문한 점인지 체크, 만약 더 낮은 수에서 방문한적이 있으면 할 필요 없다
        for (int i = 0; i < h; i++)
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        Queue<Beam> queue = new LinkedList<>();

        Beam curr = new Beam(start[0], start[1], 0);
        queue.add(curr);
        visited[curr.y][curr.x] = 0;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (curr.y == destination[0] && curr.x == destination[1])
                return curr.countMirror - 1;
            if (visited[curr.y][curr.x] < curr.countMirror - 1)
                continue;

            // -1, 0 방향으로 이동
            for (int i = curr.y - 1; i >= 0; i--) {
                if (matrix[i][curr.x] || visited[i][curr.x] < curr.countMirror)
                    break;
                queue.add(new Beam(i, curr.x, curr.countMirror + 1));
                visited[i][curr.x] = curr.countMirror;
            }
            // 1, 0 방향으로 이동
            for (int i = curr.y + 1; i < h; i++) {
                if (matrix[i][curr.x] || visited[i][curr.x] < curr.countMirror)
                    break;
                queue.add(new Beam(i, curr.x, curr.countMirror + 1));
                visited[i][curr.x] = curr.countMirror;
            }
            // 0, -1 으로 이동
            for (int j = curr.x - 1; j >= 0; j--) {
                if (visited[curr.y][j] < curr.countMirror || matrix[curr.y][j])
                    break;
                queue.add(new Beam(curr.y, j, curr.countMirror + 1));
                visited[curr.y][j] = curr.countMirror;
            }
            // 0, 1 으로 이동
            for (int j = curr.x + 1; j < w; j++) {
                if (visited[curr.y][j] < curr.countMirror || matrix[curr.y][j])
                    break;
                queue.add(new Beam(curr.y, j, curr.countMirror + 1));
                visited[curr.y][j] = curr.countMirror;
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