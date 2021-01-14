import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17141
public class Main {
    public static int[][] spread = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static int n;  // 연구소의 크기
    public static int m;  // 바이러스의 갯수
    public static boolean[][] lab;    // 연구소
    public static ArrayList<Position> virusPosition = new ArrayList<>();    // 바이러스의 위

    public static void main(String[] args) throws IOException {
        int clearRoom = 0;

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        lab = new boolean[n][n];
        {
            int tempNum;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < n; j++) {
                    tempNum = Integer.parseInt(st.nextToken());
                    if (tempNum == 1) {
                        lab[i][j] = true;
                    } else if (tempNum == 0) {
                        lab[i][j] = false;
                        clearRoom++;
                    } else {
                        // 2일 때
                        virusPosition.add(new Position(i, j, 0));
                        lab[i][j] = false;
                        clearRoom++;
                    }
                }
            }
        }

        int result = getMinVirusSpreadTime(m, clearRoom, Integer.MAX_VALUE, new Position[m], 0);
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    // 바이러스가 뿌려지는데 걸리는 최소 시간 dfs로 탐색
    private static int getMinVirusSpreadTime(int m, int clearRoom, int minVirusSpreadTime, Position[] virusDeploy, int pos) {
        if (m == 0) {
            // 바이러스 배치 완료되면 시작
            minVirusSpreadTime = Math.min(minVirusSpreadTime, seachVirusSpread(clearRoom, virusDeploy, minVirusSpreadTime));
        } else {
            for (int i = pos; i < virusPosition.size(); i++) {
                virusDeploy[m - 1] = virusPosition.get(i);
                minVirusSpreadTime = Math.min(minVirusSpreadTime, getMinVirusSpreadTime(m - 1, clearRoom, minVirusSpreadTime, virusDeploy, i + 1));
            }
        }
        return minVirusSpreadTime;
    }

    // 바이러스 감염 시간 탐색
    private static int seachVirusSpread(int clearRoom, Position[] virusDeploy, int preMinVirusSpreadTime) {
        boolean[][] dist = new boolean[n][n];
        Queue<Position> queue = new LinkedList<>();
        for (Position curr : virusDeploy) {
            // 큐에 넣을 때가 아니라 뺄 때 체크한다.
            queue.add(curr);
            dist[curr.y][curr.x] = true;
        }
        Position curr;
        int nextY, nextX;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            clearRoom--;
            if (curr.timeCnt > preMinVirusSpreadTime)
                // 만약 이전에 계산된 최소 시간 보다 길다면 더이상 계산 안 한다.
                break;
            if (clearRoom == 0)
                // 모든 방을 감염 시켰다면 return
                return curr.timeCnt;
            for (int[] next : spread) {
                nextY = next[0] + curr.y;
                nextX = next[1] + curr.x;
                if (nextY < 0 || nextY >= n || nextX < 0 || nextX >= n)
                    continue;
                else if (dist[nextY][nextX] || lab[nextY][nextX])
                    continue;
                // else
                queue.add(new Position(nextY, nextX, curr.timeCnt + 1));
                dist[nextY][nextX] = true;
            }
        }
        return Integer.MAX_VALUE;   //방이 남아 있을 때
    }
}

class Position {
    int y;      // y좌표
    int x;      // x좌표
    int timeCnt;    // y,x까지 바이러스가 도달하는데 걸리는 시간

    public Position(int y, int x, int timeCnt) {
        this.y = y;
        this.x = x;
        this.timeCnt = timeCnt;
    }
}