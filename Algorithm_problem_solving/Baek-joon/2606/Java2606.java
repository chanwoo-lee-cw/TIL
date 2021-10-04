import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        int n, k;
        Networks networks;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            n = Integer.parseInt(bf.readLine());
            k = Integer.parseInt(bf.readLine());
            networks = new Networks(n);
            int u, v;
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(bf.readLine());
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                networks.merge(u, v);
            }
            System.out.println(networks.infectedCom(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Networks {
    private int n;
    private int[] parent;
    private int[] level;

    public Networks(int n) {
        this.n = n;
        this.parent = new int[n + 1];
        this.level = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            this.parent[i] = i;
        }
    }

    public int find(int u) {
        if (parent[u] == u)
            return u;
        parent[u] = find(parent[u]);
        return parent[u];
    }

    public boolean merge(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v)
            return false;
        if (level[u] > level[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        parent[u] = v;
        if (level[u] == level[v]) {
            level[v]++;
        }
        return true;
    }

    public int infectedCom(int num) {
        int cnt = 0;
        int infectedParent = find(num);
        for (int i = 2; i < n + 1; i++) {
            if (find(i) == infectedParent)
                cnt++;
        }
        return cnt;
    }
}