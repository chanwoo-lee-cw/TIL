import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 시간 초과
// https://www.acmicpc.net/problem/9376
public class Main {
    public static int minDoorOpenCnt;
    public static int[][] way = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        int t;  // 테스트 케이스 갯수
        int h, w;    // 높이와 너비
        boolean[][] prison;
        ArrayList<int[]> doorList;
        ArrayList<int[]> prisoners;

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String inputLine;       // 밑에 감옥 정보를 입력 받기 위한 임시 변수
        t = Integer.parseInt(bf.readLine());
        for (int testCase = 0; testCase < t; testCase++) {
            st = new StringTokenizer(bf.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            prison = new boolean[h][w];
            doorList = new ArrayList<>();
            prisoners = new ArrayList<>();
            minDoorOpenCnt = Integer.MAX_VALUE;
            boolean outside = false;

            for (int i = 0; i < h; i++) {
                inputLine = bf.readLine();
                for (int j = 0; j < w; j++) {
                    if (inputLine.charAt(j) == '.') {
                        prison[i][j] = false;
                    } else if (inputLine.charAt(j) == '*') {
                        prison[i][j] = true;
                    } else if (inputLine.charAt(j) == '$') {
                        prison[i][j] = false;
                        prisoners.add(new int[]{i, j});
                    } else {
                        prison[i][j] = true;
                        doorList.add(new int[]{i, j});
                    }
                    if (!prison[i][j] && (j <= 0 || i <= 0 || i >= h - 1 || j >= w - 1)) {
                        outside = true;
                    }
                }
            }
            getMinDoorOpen(h, w, prison, doorList, prisoners, 0, 0, outside);
            System.out.println(minDoorOpenCnt);
        }
        st = null;
        bf.close();
    }

    // dfs로 벽을 세울수 있는 경우의 수를 구하는 매서드
    private static void getMinDoorOpen(int h, int w, boolean[][] prison, ArrayList<int[]> doorList, ArrayList<int[]> prisoners, int pos, int doorOpenCnt, boolean outside) {
        if (outside && doorOpenCnt < minDoorOpenCnt) {
            int[][] visited = new int[h][w];    // 나갈 수 있나 탐색하는 변수
            if (seachPrisonBreak(h, w, prison, prisoners.get(0), 1, visited) && seachPrisonBreak(h, w, prison, prisoners.get(1), 2, visited))
                minDoorOpenCnt = Math.min(minDoorOpenCnt, doorOpenCnt);
        }
        boolean thisOutside;
        for (int i = pos; i < doorList.size(); i++) {
            int[] door = doorList.get(i);
            prison[door[0]][door[1]] = false;
            thisOutside = outside;
            if (door[1] <= 0 || door[0] <= 0 || door[0] >= h - 1 || door[1] >= w - 1)
                thisOutside = true;
            getMinDoorOpen(h, w, prison, doorList, prisoners, i + 1, doorOpenCnt + 1, thisOutside);
            prison[door[0]][door[1]] = true;
        }
    }

    // bfs로 세워진 벽에 대해 탐색을 하는 매서드
    private static boolean seachPrisonBreak(int h, int w, boolean[][] prison, int[] prisoner, int i, int[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(prisoner);
        visited[prisoner[0]][prisoner[1]] = i;

        int nextX, nextY;
        while (!queue.isEmpty()) {
            prisoner = queue.poll();
            for (int[] next : way) {
                nextY = prisoner[0] + next[0];
                nextX = prisoner[1] + next[1];
                if (nextX < 0 || nextY < 0 || nextY > h - 1 || nextX > w - 1) {
                    return true;
                } else if (prison[nextY][nextX] || visited[nextY][nextX] == i)
                    continue;
                else if (i == 2 && visited[nextY][nextX] == 1)
                    // 만약 2번 탐색을 한다는 것부터 1번은 나갔다는 뜻이고 1번과 동신이 겹친다면 나갈 수 있다.
                    return true;
                else if (nextX <= 0 || nextY <= 0 || nextY >= h - 1 || nextX >= w - 1) {
                    return true;
                }
                // else
                queue.add(new int[]{nextY, nextX});
                visited[nextY][nextX] = i;
            }
        }
        return false;
    }
}