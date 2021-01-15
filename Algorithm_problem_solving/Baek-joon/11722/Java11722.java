import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = n - 1; i >= 0; i--) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = null;
        bf.close();

        System.out.println(getlisLen(n, arr));
    }

    private static int getlisLen(int n, int[] arr) {
        int[] lis = new int[n];
        lis[0] = arr[0];
        int lisLen = 1;

        for (int i = 1; i < n; i++) {
            if (lis[lisLen - 1] < arr[i]) {
                lis[lisLen] = arr[i];
                lisLen++;
            } else {
                int getpos = lowerBound(lis, 0, lisLen, arr[i]);
                lis[getpos] = arr[i];
            }
        }
        return lisLen;
    }

    private static int lowerBound(int[] arr, int s, int e, int search) {
        int m;
        while (e - s > 0) {
            m = (s + e) / 2;
            if (arr[m] < search)
                s = m + 1;
            else
                e = m;
        }
        return (s + e) / 2;
    }
}