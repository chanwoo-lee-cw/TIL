import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;


class Permutation {
    private final int[] arr;
    private final int n;
    private final int m;
    private final boolean[] vistied;
    private final ArrayDeque<Integer> queue = new ArrayDeque<>();
    private final StringBuilder sb = new StringBuilder();

    Permutation(
            int n,
            int m,
            int[] arr
    ) {
        this.n = n;
        this.m = m;
        this.arr = arr;
        this.vistied = new boolean[n];
        Arrays.sort(this.arr);
    }


    /**
     * DFS로 순열 계산
     */
    private void dfs() {
        if (queue.size() == m) {
            sb.append(queue.stream().map(String::valueOf).collect(Collectors.joining(" ")))
                    .append("\n");
        } else {
            for (int i = 0; i < n; i++) {
                if (vistied[i]) continue;
                vistied[i] = true;
                queue.add(arr[i]);
                dfs();
                queue.pollLast();
                vistied[i] = false;
            }
        }
    }

    /**
     * 모든 순열 리스트를 출력한다
     */
    public void printPermutation() {
        if (queue.isEmpty()) {
            dfs();
        }
        System.out.println(sb.toString());
    }

}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Permutation permutation = new Permutation(n, m, arr);
        permutation.printPermutation();
    }
}
