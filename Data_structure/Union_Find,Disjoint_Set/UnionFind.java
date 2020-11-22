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
