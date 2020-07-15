public class UnionFind {

    static int[] parent;
    static int[] level;

    static int find(int u) {
        if (u == parent[u])
            return u;
        parent[u] = find(parent[u]);
        return parent[u];
    }

    static void merge(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v)
            return
        if (level[u] > level[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        parent[u] = v;
        if(level[u] == level[v])
            level[v] = level[v] + 1;
    }
}
