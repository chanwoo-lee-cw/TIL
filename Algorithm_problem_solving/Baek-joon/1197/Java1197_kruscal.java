import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1197
// 최소 스패닝 트리
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int[][] matrix = new int[v + 1][v + 1];
        PriorityQueue<Edge> que = new PriorityQueue<>();
        {
            Edge temp;
            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(bf.readLine());
                temp = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()));
                que.add(temp);
            }
        }
        System.out.println(kruskal(v, e, que));
    }

    public static int kruskal(int v, int e, PriorityQueue<Edge> que) {
        int sum_weight = 0;
        UnionFind union_find = new UnionFind(v);
        Edge now_edge;
        for (int i = 0; i < e; i++) {
            now_edge = que.poll();
            if (!union_find.merge(now_edge.start, now_edge.end))
                sum_weight += now_edge.wieght;
        }
        return sum_weight;
    }

    public static class Edge implements Comparable<Edge> {
        int wieght;
        int start;
        int end;

        Edge(int wieght, int start, int end) {
            this.wieght = wieght;
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Edge o) {
            return this.wieght - o.wieght;
        }
    }

    public static class UnionFind {
        private int[] parent;
        private int[] level;

        UnionFind(int vertexNum) {
            this.parent = new int[vertexNum + 1];
            this.level = new int[vertexNum + 1];
            for (int i = 1; i < vertexNum + 1; i++) {
                this.parent[i] = i;
            }
            // level을 1로 초기화는 안 해보 됨. 어짜피 비교용이므로
            Arrays.fill(this.level, 1);
        }

        public int find(int u) {
            if (u == parent[u])
                return u;
            parent[u] = find(parent[u]);
            return parent[u];
        }

        public boolean merge(int u, int v) {
            u = parent[u];
            v = parent[v];
            if (u == v)
                return true;
            if (level[u] > level[v]) {
                int temp = u;
                u = v;
                v = temp;
            }
            parent[u] = v;
            if (level[u] == level[v])
                level[v]++;
            return false;
        }
    }
}