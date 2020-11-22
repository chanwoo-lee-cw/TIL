public class Kruskal {
    int v;
    int e;
    PriorityQueue<Edge> que;

    // edgedata : e*3 matrix
    // edgedata[e][0] : weight
    // edgedata[e][1] : start
    // edgedata[e][1] : end
    Kruskal(int[][] edgedata) {
        Edge temp;
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(bf.readLine());
            temp = new Edge(edgedata[i][0], edgedata[i][1], edgedata[i][2]);
            que.add(temp);
        }
    }

    public int kruskal() {
        int sum_weight = 0;
        UnionFind union_find = new UnionFind(this.v);
        Edge now_edge;
        for (int i = 0; i < this.e; i++) {
            now_edge = this.que.poll();
            if (!union_find.merge(now_edge.start, now_edge.end))
                sum_weight += now_edge.wieght;
        }
        return sum_weight;
    }

    public class Edge implements Comparable<Edge> {
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

    public class UnionFind {
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
