import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1965
public class Main {
    public static int lowerBound(int arr[], int s, int e, int search) {
        int m = 0;
        while (e - s > 0) {
            m = (e + s) / 2;
            if (arr[m] < search)
                s = m + 1;
            else
                e = m;
        }
        return (e + s) / 2 + 1;
    }

    private static int lcs(int n, int[] arr) {
        int[] lcsList = new int[n];
        int lcsLen = 1;
        lcsList[0] = arr[0];

        for (int i = 1; i < n; i++) {
            if (arr[i] < lcsList[lcsLen - 1]) {
                lcsList[lowerBound(lcsList, 0, lcsLen, arr[i])-1] = arr[i];
            } else if (arr[i] > lcsList[lcsLen - 1]) {
                lcsList[lcsLen] = arr[i];
                lcsLen += 1;
            }
        }
        return lcsLen;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(lcs(n, arr));
    }

}