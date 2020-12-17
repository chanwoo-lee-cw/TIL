import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3020
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[] stalagmite = new int[n / 2 + 2];
        int[] stalactite = new int[n / 2 + 2];
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0)
                stalagmite[i / 2 + 1] = Integer.parseInt(bf.readLine());
            else
                stalactite[i / 2 + 1] = h - Integer.parseInt(bf.readLine());
        }
        stalagmite[n / 2 + 1] = h + 1;
        stalactite[n / 2 + 1] = h;
        Arrays.sort(stalagmite, 1, stalagmite.length - 1);
        Arrays.sort(stalactite, 1, stalactite.length - 1);

        int[] breaker = new int[h + 1];

        {
            int getStalagmiteBroker;
            int getStalactiteBroker;
            for (int i = 1; i <= h; i++) {
                getStalagmiteBroker = n / 2 - (lowerBound(stalagmite, 0, n / 2 + 1, i) - 1);
                if (getStalagmiteBroker > 0)
                    breaker[i] += getStalagmiteBroker;
                breaker[i] += lowerBound(stalactite, 0, n / 2 + 1, i) - 1;
            }
        }

        Arrays.sort(breaker, 1, breaker.length);

        System.out.println(String.format("%d %d", breaker[1], upperBound(breaker, 1, breaker.length, breaker[1]) - lowerBound(breaker, 1, breaker.length, breaker[1])));

    }

    public static int lowerBound(int[] arr, int s, int e, int search) {
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

    public static int upperBound(int[] arr, int s, int e, int search) {
        int m;
        while (e - s > 0) {
            m = (s + e) / 2;
            if (arr[m] <= search)
                s = m + 1;
            else
                e = m;
        }
        return (s + e) / 2;
    }
}