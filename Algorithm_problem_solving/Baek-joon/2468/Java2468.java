import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n;
        int[][] area;
        SafeArea safeArea;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            n = Integer.parseInt(bf.readLine());
            safeArea = new SafeArea();
            area = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < n; j++) {
                    area[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            System.out.println(safeArea.getMaxSafeArea(n, area));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class SafeArea {
    private static int[][] way = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /*
    안전구역의 갯수가 최대가 되는 높이에서의 안전 구간의 갯수를 반환한다.
    */
    public int getMaxSafeArea(int n, int[][] area) {
        int answer = 1;
        HashSet<Integer> heights = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                heights.add(area[i][j]);
            }
        }

        for (int height : heights) {
            answer = Math.max(answer, getSafeArea(n, area, height));
        }
        return answer;
    }

    /*
    입력된 height에서 안전 구간의 갯수를 반환한다.
    */
    private int getSafeArea(int n, int[][] area, int height) {
        int answer = 0;
        boolean[][] visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && area[i][j] > height) {
                    bfs(n, area, height, i, j, visited);
                    answer++;
                }
            }
        }

        return answer;
    }

    /*
    bfs로 물에 잡기지 않는 부분을 체크
    */
    private void bfs(int n, int[][] area, int height, int y, int x, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        visited[y][x] = true;
        queue.add(new int[]{y, x});

        int[] curr;
        int nextY, nextX;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            for (int[] next : way) {
                nextY = next[0] + curr[0];
                nextX = next[1] + curr[1];
                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n || visited[nextY][nextX]) {
                    continue;
                } else if (area[nextY][nextX] <= height) {
                    continue;
                }
                // else
                queue.add(new int[]{nextY, nextX});
                visited[nextY][nextX] = true;
            }
        }
    }

}