import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        int n, m;
        Tree tree;
        int testNum = 0;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            while (true) {
                testNum++;
                st = new StringTokenizer(bf.readLine());
                n = Integer.parseInt(st.nextToken());
                m = Integer.parseInt(st.nextToken());
                if (n == 0 && m == 0)
                    break;
                tree = new Tree(n);
                for (int i = 0; i < m; i++) {
                    st = new StringTokenizer(bf.readLine());
                    tree.addBranch(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                }
                System.out.println(tree.countTree(testNum));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Tree {
    private int n;
    private ArrayList<Integer>[] route;

    public Tree(int n) {
        this.n = n;
        this.route = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            this.route[i] = new ArrayList<>();
        }
    }

    public void addBranch(int a, int b) {
        route[b].add(a);
        route[a].add(b);
    }

    public String countTree(int testNum) {
        int treeCnt = 0;
        boolean[] visited = new boolean[n + 1];
        String answer;
        for (int i = 1; i <= n; i++) {
            if (visited[i]) {
                continue;
            }
            if (dfs(visited, i)) {
                treeCnt++;
            }
        }
        if (treeCnt == 0) {
            answer = String.format("Case %d: No trees.", testNum);
        } else if (treeCnt == 1) {
            answer = String.format("Case %d: There is one tree.", testNum);
        } else {
            answer = String.format("Case %d: A forest of %d trees.", testNum, treeCnt);
        }
        return answer;
    }

    private boolean dfs(boolean[] visited, int i) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, 0});
        visited[i] = true;

        int[] curr;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            for (int next : this.route[curr[0]]) {
                if (next == curr[1])
                    continue;
                else if (visited[next])
                    return false;
                queue.add(new int[]{next, curr[0]});
                visited[next] = true;

            }
        }
        return true;
    }

}