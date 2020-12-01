import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Java1325 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] relation = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            relation[i] = new ArrayList<>();
        }
        // 입력
        {
            int a = 0;
            int b = 0;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(bf.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                relation[b].add(a);
            }
        }
        bf.close();
        st = null;
        // bfs로 계산
        StringBuilder sb = new StringBuilder();
        int getChild;
        int maxChild = -1;
        for (int i = 1; i < n + 1; i++) {
            getChild = bfs(n, i, relation);
            if (getChild > maxChild) {
                sb.delete(0, sb.length());
                sb.append(i);
                sb.append(" ");
                maxChild = getChild;
            } else if (getChild == maxChild) {
                sb.append(i);
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }

    public static int bfs(int n, int pos, ArrayList<Integer>[] relation) {
        Queue<Integer> que = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        que.add(pos);
        visited[pos] = true;
        int answer = 0;
        {
            int curr_pos;
            int next;
            while (!que.isEmpty()) {
                curr_pos = que.poll();
                answer++;
                for (int i = 0; i < relation[curr_pos].size(); i++) {
                    next = relation[curr_pos].get(i);
                    if (visited[next])
                        continue;
                    que.add(next);
                    visited[next] = true;
                }
            }
        }
        return answer;
    }
}