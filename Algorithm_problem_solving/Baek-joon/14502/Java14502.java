import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int n, m;     // 연구소의 좌우 너비를 저장
    public static int maxSafe = 0;  // 최대 안전 공간의 너비
    public static int[][] way = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        int[][] lab;     // 연구실 상황
        int safeArea = 0;   // 현재 안전 공간의 넓이
        ArrayList<int[]> virus;     // 바이러스의 위치를 저장하는 arr
        ArrayList<int[]> space ;     // 연구소 내의 빈공간(벽을 세우는 공간)

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        lab = new int[n][m];   
        virus = new ArrayList<>();     
        space = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < m; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
                if (lab[i][j] == 2) {
                    virus.add(new int[]{i, j});
                    safeArea++;
                } else if (lab[i][j] == 0) {
                    safeArea++;
                    space.add(new int[]{i, j});
                }
            }
        }
        makeWall(lab, virus, 3, 0, space, safeArea);
        System.out.println(maxSafe);
    }

    // DFS - 현재 벽을 세우는 경우를 탐색
    private static void makeWall(int[][] lab, ArrayList<int[]> virus, int wallCnt, int pos, ArrayList<int[]> space, int safeArea) {
        if (wallCnt == 0) {
            // 더이상 세울 수 있는 벽이 없을 때 탐색
            maxSafe = Math.max(maxSafe, searchSafeArea(lab, virus, safeArea));
        } else {
            for (int i = pos; i < space.size(); i++) {
                int[] thisMakeWall = space.get(i);
                lab[thisMakeWall[0]][thisMakeWall[1]] = 1;
                makeWall(lab, virus, wallCnt - 1, i + 1, space, safeArea - 1);
                lab[thisMakeWall[0]][thisMakeWall[1]] = 0;
            }
        }
    }

    // BFS - 바이러스가 퍼졌을 때, 남아있는 안전지대의 수 반환
    private static int searchSafeArea(int[][] lab, ArrayList<int[]> virus, int safeArea) {
        boolean[][] visited = new boolean[n][m];    // 방문 여부
        Queue<int[]> queue = new LinkedList<>(); 
        for (int[] virusPos : virus) {
            queue.add(virusPos);
            visited[virusPos[0]][virusPos[1]] = true;
        }
        int[] curr;
        int nextY, nextX;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            safeArea--;
            for (int i = 0; i < 4; i++) {
                nextY = curr[0] + way[i][0];
                nextX = curr[1] + way[i][1];
                if (nextY < 0 || nextY >= n || nextX < 0 || nextX >= m || lab[nextY][nextX] != 0 || visited[nextY][nextX])
                    continue;
                queue.add(new int[]{nextY, nextX});
                visited[nextY][nextX] = true;
            }
        }
        return safeArea;
    }
}