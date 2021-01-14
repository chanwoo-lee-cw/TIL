import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/17086
public class Main {
    public static int[][] swim = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};

    public static void main(String[] args) throws IOException {
        int n, m;       // 공간의 세로, 가로
        boolean[][] aquarium;       // 공간
        ArrayList<int[]> sharks = new ArrayList<>();    // 상어의 위치들

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        aquarium = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < m; j++) {
                aquarium[i][j] = (st.nextToken()).compareTo("1") == 0 ? true : false;
                if (aquarium[i][j]) {
                    sharks.add(new int[]{i, j});
                }
            }
        }
        st = null;
        bf.close();
        System.out.println(getMaxSafeDistance(n, m, aquarium, sharks));

    }

    // 최대 안전 거리를 반환한다.
    private static int getMaxSafeDistance(int n, int m, boolean[][] aquarium, ArrayList<int[]> sharks) {
        // 전체를 검사할 수는 없다.
        // 생각 1 : 상어를 기준으로 bfs를 돌린다. - 상어가 몇마리인지 모른다.
        // 생각 2 : dfs들 돌리고 나오는 거리를 저장한다. 이미 탐색된 곳을 다시
        // 생각 3 : 그럼 dp를 저장하면 어떨까... 그럼 좌우 왕복하면서 무한 재귀 발생
        // 생각 4 : visited를 생성해 방문 여부 체크 : 그럼 왼쪽에서 최고 값이 오는 경우 같은건 체크 못함...
        // 생각 5 : dp인가... dp로 어떻게 풀어야 될지 감도 안 온다.
        // 샌각 6 : 1번으로 돌아가서 bfs로 하되 물고기를 한 queue에 전부 넣고 돌릴까...
        int[][] dp = serachSafeDistance(n, m, aquarium, sharks);    // 안전 거리 공간 반환
        int maxSafeDistance = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                maxSafeDistance = Math.max(maxSafeDistance, dp[i][j]);
            }
        }
        return maxSafeDistance;
    }

    // 각 공간별 최소 안전 거리 반환
    private static int[][] serachSafeDistance(int n, int m, boolean[][] aquarium, ArrayList<int[]> sharks) {
        int[][] safeDistance = new int[n][m];       // 공간별 최소 안전 거리
        Queue<Position> queue = new LinkedList<>();
        for (int i = 0; i < n; i++)
            Arrays.fill(safeDistance[i], Integer.MAX_VALUE);
        Position curr;
        for (int[] shrark : sharks) {
            curr = new Position(shrark[0], shrark[1], 0);
            queue.add(curr);
            safeDistance[curr.y][curr.x] = 0;
        }

        int nextY, nextX;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            for (int[] next : swim) {
                nextY = next[0] + curr.y;
                nextX = next[1] + curr.x;
                if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n)
                    continue;
                if (safeDistance[nextY][nextX] <= curr.sharkDistance + 1)
                    // 이미 다른 상어와 더 가까우면 계싼할 필요 없으므로 conitunue
                    continue;
                queue.add(new Position(nextY, nextX, curr.sharkDistance + 1));
                safeDistance[nextY][nextX] = curr.sharkDistance + 1;
            }
        }
        return safeDistance;
    }
}

class Position {
    int y, x;  // 현재 좌표
    int sharkDistance;  // 상어와의 거리.

    public Position(int y, int x, int sharkDistance) {
        this.y = y;
        this.x = x;
        this.sharkDistance = sharkDistance;
    }
}