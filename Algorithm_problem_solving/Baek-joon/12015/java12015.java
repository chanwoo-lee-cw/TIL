import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        int n;
        int[] arr;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;

            n = Integer.parseInt(bf.readLine());
            arr = new int[n];

            st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            LIS testCase = new LIS(n, arr);
            System.out.println(testCase.getLisLength());
        } catch (IOException e) {
            return;
        }
    }
}


/**
 * 최장 증가 부분 순열(Longest Increasing Subsequence) 를 구하는 class
 */
class LIS {
    private int n;
    private int[] arr;
    private int[] lis;

    /**
     * 초기화
     *
     * @param n   lis를 구하고 싶은 배열의 길이
     * @param arr lis를 구하고 싶은 배열
     */
    public LIS(int n, int[] arr) {
        this.n = n;
        this.arr = arr;
        this.lis = new int[n];
    }

    /**
     * 가장 긴 Lis를 만들고 길이를 반환
     *
     * @return 가장 긴 Lis의 길이
     */
    public int getLisLength() {
        int pos = 0;
        lis[0] = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > lis[pos]) {
                pos++;
                lis[pos] = arr[i];
            } else {
                lis[lower_bound(lis, 0, pos, arr[i])] = arr[i];
            }
        }
        return pos + 1;
    }

    private int lower_bound(int[] arr, int start, int end, int find) {
        int mid;
        while (end - start > 0) {
            mid = (start + end) / 2;
            if (arr[mid] < find) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
}