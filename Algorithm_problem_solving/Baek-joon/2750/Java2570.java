import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n;
        try {
            n = Integer.parseInt(bf.readLine());
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(bf.readLine());
            }
            ArraySort sort = new ArraySort(n, arr);
            sort.merge_sort(0, n);
            sort.printArr();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class ArraySort {
    private int n;
    private int[] arr;
    private int[] tmp;

    public ArraySort(int n, int[] arr) {
        this.n = n;
        this.arr = arr;
        this.tmp = new int[n];
    }

    private void merge(int s, int e) {
        int m = (s + e) / 2;
        int p = s;
        int q = m;
        int idx = s;
        while (p < m && q < e) {
            if (arr[p] < arr[q]) {
                tmp[idx++] = arr[p++];
            } else {
                tmp[idx++] = arr[q++];
            }
        }
        if (p < m) {
            // 왼쪽 배열이 남았을 때
            for (int i = p; i < m; i++) {
                tmp[idx++] = arr[i];
            }
        } else if (q < e) {
            // 오른쪽 배열이 남았을 때
            for (int i = q; i < e; i++) {
                tmp[idx++] = arr[i];
            }
        }
        for (int i = s; i < e; i++) {
            // 원본 배열에 모두 복사한다.
            arr[i] = tmp[i];
        }
    }

    public void merge_sort(int s, int e) {
        if (e - s <= 1) {
            return;
        }
        int m = (e + s) / 2;
        merge_sort(s, m);
        merge_sort(m, e);
        merge(s, e);
    }

    public void printArr() {
        StringBuilder sb = new StringBuilder();
        for (int item : arr) {
            sb.append(item);
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}