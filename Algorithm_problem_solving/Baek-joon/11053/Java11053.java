import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int lowerBound(int[] arr, int start, int end, int search) {
        int e = end;
        int s = start;
        int m;
        while (e - s > 0) {
            m = (e + s) / 2;
            if (arr[m] < search) {
                s = m + 1;
            } else {
                e = m;
            }
        }
        return (s + e) / 2;
    }

    public static int getLis(int n, int[] arr) {
        int[] lis = new int[n];
        lis[0] = arr[0];
        int lisLen = 1;
        {
            int pos;
            for (int i = 1; i < n; i++) {
                if (lis[lisLen - 1] >= arr[i]) {
                    pos = lowerBound(lis, 0, lisLen, arr[i]);
                    lis[pos] = arr[i];
                } else {
                    lis[lisLen] = arr[i];
                    lisLen++;
                }

            }
        }
        return lisLen;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(getLis(n, a));
    }
}