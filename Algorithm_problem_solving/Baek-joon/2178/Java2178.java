import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        Maze maze;  // 미로의 탐색을 위한 객체
        int n, m;   // 미로의 크기
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            maze = new Maze(n, m);
            maze.fillMaze(bf);
            System.out.println(maze.findShortCut());
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class Maze {
    private int n, m;       // 미로의 크기
    private boolean[][] matrix;     // maze[i][j] 가 true 갈수 있는 길, false 갈 수 없는 길
    private static int[][] way = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};     // 방문 방문 위치를 체크하기 위한 변수

    public Maze(int n, int m) {
        this.n = n;
        this.m = m;
    }

    public void fillMaze(BufferedReader bf) throws IOException {
        matrix = new boolean[n][m];
        String input;
        for (int i = 0; i < n; i++) {
            input = bf.readLine();
            for (int j = 0; j < m; j++) {
                matrix[i][j] = input.charAt(j) == '1' ? true : false;
            }
        }
    }

    /*
    bfs를 통해 n-1, m-1 까지 가는 최단 거리를 반환하는 함수
    */
    public int findShortCut() {
        boolean[][] visited = new boolean[n][m];    // 해당 위치 방문 여부 체크
        Queue<int[]> queue = new LinkedList<>();    // bfs를 위한 큐
        queue.add(new int[]{0, 0, 1});
        visited[0][0] = true;

        int[] curr = queue.peek();
        int nextY, nextX;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (curr[0] == n - 1 && curr[1] == m - 1)
                break;
            for (int[] next : way) {
                nextY = curr[0] + next[0];
                nextX = curr[1] + next[1];
                if (nextY < 0 || nextX < 0 || nextY >= n || nextX >= m)
                    continue;
                if (!matrix[nextY][nextX] || visited[nextY][nextX])
                    continue;
                queue.add(new int[]{nextY, nextX, curr[2] + 1});
                visited[nextY][nextX] = true;
            }
        }
        return curr[2];
    }
}