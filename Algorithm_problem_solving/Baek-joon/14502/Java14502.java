import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[][] way = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
    private static int n, m;
    private static int maxSafe;
    private static ArrayList<int[]> wall = null;

    public static void main(String[] agrs) throws IOException {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();

        int lab[][] = new int[n][m];
        int safe = 0;
        int wallCnt = 3;
        ArrayList<int[]> virus = new ArrayList<int[]>();
        {
            BufferedReader bf = null;
            StringTokenizer st = null;
            for (int i = 0; i < n; i++) {
                bf = new BufferedReader(new InputStreamReader(System.in));
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < n; j++) {
                    lab[i][j] = Integer.parseInt(st.nextToken());
                    if (lab[i][j] == 2) {
                        lab[i][j] = 0;
                        virus.add(new int[] { i, j, 0 });
                        safe += 1;
                    } else if (lab[i][j] == 0) {
                        safe += 1;
                        wall.add(new int[] { i, j });
                    }
                }
            }
        }
        maxSafe = 0;
        dfs(lab, safe, virus, 0, wallCnt);
        System.out.println(maxSafe);
    }

    private static void dfs(int[][] lab, int safe, ArrayList<int[]> virus, int pos, int wallCnt) {
        if (wallCnt == 0) {
            maxSafe = Math.max(maxSafe, bfs(lab, safe, virus));
        } else {
            for (int i = pos; i < wall.size(); i++) {
                int[] ouput = wall.get(i);
                lab[ouput[0]][ouput[1]] = 1;
                wallCnt--;
                dfs(lab, safe, virus, pos + 1, wallCnt);
                wallCnt++;
                lab[ouput[0]][ouput[1]] = 0;
            }
        }
    }

    private static int bfs(int[][] lab, int safe, ArrayList<int[]> virus) {
        int[][] simul = lab.clone();
        Queue<int[]> que = new LinkedList<>();

        for (int i = 0; i < virus.size(); i++) {
            que.add(virus.get(i));
        }
        {
            int[] output = null;
            while (!que.isEmpty()) {
                output = que.poll();
                if (simul[output[0]][output[1]] != 0)
                    continue;
                simul[output[0]][output[1]] = 2;
                safe -= 1;
                for (int[] next : way) {
                    int next_x = output[1] + next[1];
                    int next_y = output[0] + next[0];
                    if (next_x < 0 || next_x >= m || next_y < 0 || next_y >= n || simul[next_y][next_x] != 0)
                        continue;
                    que.add(new int[] { next_y, next_x, output[2] + 1 });
                }
            }
        }
        return safe - 3;
    }
}