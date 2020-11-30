import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1260
public class Java1260 {
    private static ArrayList<Integer>[] matrix;
    private static boolean[] visited;
    private static StringBuffer sb;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        matrix = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            matrix[i] = new ArrayList<>();
        }
        {
            int a = 0;
            int b = 0;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(bf.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                matrix[a].add(b);
                matrix[b].add(a);
            }
        }
        bf.close();
        for (int i = 1; i < n + 1; i++) {
            Collections.sort(matrix[i]);
        }
        sb = new StringBuffer();
        visited = new boolean[n + 1];
        dfs(v);
        System.out.println(sb.toString());
        sb = new StringBuffer();
        visited = new boolean[n + 1];
        bfs(v);
        System.out.println(sb.toString());
    }

    public static void dfs(int pos) {
        sb.append(String.format("%d ", pos));
        visited[pos] = true;
        for (int next : matrix[pos]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }

    public static void bfs(int pos) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(pos);
        visited[pos] = true;
        {
            while (!queue.isEmpty()) {
                int curr_pos = queue.poll();
                sb.append(String.format("%d ", curr_pos));
                for (int next : matrix[curr_pos]) {
                    if (!visited[next]) {
                        queue.add(next);
                        visited[next] = true;
                    }
                }
            }
        }
    }
}