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
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        bf.close();
        System.out.println(getMaxNum(n, arr));
    }

    public static long getMaxNum(int n, int[] arr) {
        int[] lisList = new int[n];
        lisList[0] = arr[0];
        int lisLen = 1;
        int currSum = arr[0];
        int maxSum = arr[0];
        int getPos;
        for (int i = 1; i < n; i++) {
            if (lisList[lisLen - 1] < arr[i]) {
                lisList[lisLen++] = arr[i];
                currSum += arr[i];
            } else {
                getPos = lowerBound(lisList, 0, lisLen, arr[i]);
                currSum = currSum - lisList[getPos] + arr[i];
                lisList[getPos] = arr[i];
            }
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }

    private static int lowerBound(int[] arr, int s, int e, int check) {
        int m;
        while (e - s > 0) {
            m = (s + e) / 2;
            if (arr[m] < check)
                s = m + 1;
            else
                e = m;
        }
        return (s + e) / 2;
    }
}