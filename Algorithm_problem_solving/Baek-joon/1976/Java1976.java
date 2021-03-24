import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n, m;       // 저장해줄 프린트 물을 저
        MyTree mytree;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(bf.readLine());
            m = Integer.parseInt(bf.readLine());
            mytree = new MyTree(n, m);
            System.out.println(mytree.canTravel(bf));
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class MyTree {
    private int n;
    private int m;
    private int[] parents;
    private int[] levels;

    public MyTree(int n, int m) {
        this.n = n;
        this.m = m;
        this.parents = new int[n + 1];
        this.levels = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            this.parents[i] = i;
        }
    }

    private int find(int u) {
        if (parents[u] == u)
            return u;
        parents[u] = find(parents[u]);
        return parents[u];
    }

    private boolean merge(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v)
            return false;
        if (levels[u] > levels[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        parents[u] = v;
        if (levels[u] == levels[v])
            levels[u]++;
        return true;
    }

    public String canTravel(BufferedReader bf) throws IOException {
        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= n; j++) {
                if (Integer.parseInt(st.nextToken()) == 1)
                    merge(i, j);
            }
        }

        st = new StringTokenizer(bf.readLine());
        int visited = find(Integer.parseInt(st.nextToken()));
        for (int i = 1; i < m; i++) {
            if (find(Integer.parseInt(st.nextToken())) != visited)
                return "NO";
        }
        return "YES";
    }
}