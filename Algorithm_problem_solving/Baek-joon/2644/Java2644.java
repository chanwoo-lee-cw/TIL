import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        int n;
        int a, b;
        int m;
        HashSet<Integer>[] rel;
        Relations relations;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            n = Integer.parseInt(bf.readLine());
            st = new StringTokenizer(bf.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(bf.readLine());
            rel = new HashSet[n + 1];
            for (int i = 0; i < n + 1; i++) {
                rel[i] = new HashSet();
            }
            {
                int temp1, temp2;
                for (int i = 0; i < m; i++) {
                    st = new StringTokenizer(bf.readLine());
                    temp1 = Integer.parseInt(st.nextToken());
                    temp2 = Integer.parseInt(st.nextToken());
                    rel[temp1].add(temp2);
                    rel[temp2].add(temp1);
                }
            }
            relations = new Relations();
            System.out.println(relations.getRel(n, a, b, rel));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Relations {
    public int getRel(int n, int a, int b, HashSet<Integer>[] rel) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        queue.add(new int[]{a, 0});
        visited[a] = true;

        int[] curr;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (curr[0] == b) {
                return curr[1];
            }
            for (int next : rel[curr[0]]) {
                if (visited[next])
                    continue;
                queue.add(new int[]{next, curr[1] + 1});
                visited[next] = true;
            }
        }
        return -1;
    }
}