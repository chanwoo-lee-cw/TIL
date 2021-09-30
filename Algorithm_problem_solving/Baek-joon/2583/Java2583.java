import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n, m, k;
        boolean[][] paper;
        Solution solution;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            paper = new boolean[n][m];

            {
                int x1, y1, x2, y2;
                for (int test = 0; test < k; test++) {
                    st = new StringTokenizer(bf.readLine());
                    x1 = Integer.parseInt(st.nextToken());
                    y1 = Integer.parseInt(st.nextToken());
                    x2 = Integer.parseInt(st.nextToken());
                    y2 = Integer.parseInt(st.nextToken());
                    for (int i = y1; i < y2; i++) {
                        for (int j = x1; j < x2; j++) {
                            paper[i][j] = true;
                        }
                    }
                }
            }
            solution = new Solution(n, m, paper);
            System.out.println(solution.countArea());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Solution {
    private int n, m;
    private boolean[][] paper;
    private ArrayList<Integer> areaSize;
    private static final int[][] way = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public Solution(int n, int m, boolean[][] paper) {
        this.n = n;
        this.m = m;
        this.paper = paper;
        this.areaSize = new ArrayList<>();
    }

    private void dfs(int y, int x) {
        int cnt = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y, x});
        paper[y][x] = true;


        int[] curr;
        int nextY, nextX;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            cnt++;
            for (int[] next : way) {
                nextY = curr[0] + next[0];
                nextX = curr[1] + next[1];
                if (nextY < 0 || nextX < 0 || nextY >= n || nextX >= m) {
                    continue;
                } else if (paper[nextY][nextX]) {
                    continue;
                }
                // else
                queue.add(new int[]{nextY, nextX});
                paper[nextY][nextX] = true;
            }
        }
        areaSize.add(cnt);
    }

    public String countArea() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!paper[i][j]) {
                    dfs(i, j);
                }
            }
        }
        sb.append(areaSize.size());
        sb.append("\n");
        Collections.sort(areaSize);
        for (int item : areaSize) {
            sb.append(item);
            sb.append(" ");
        }
        return sb.toString();
    }
}