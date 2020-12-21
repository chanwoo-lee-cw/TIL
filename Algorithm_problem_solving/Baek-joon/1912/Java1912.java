import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // 연속된 수의 최대 값을 구하는 함수
    public static int getConsecutiveArrSumMax(int n, int[] arrSum) {
        int leftMin = 0;
        int sumMax = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            sumMax = Math.max(arrSum[i] - leftMin, sumMax);
            leftMin = Math.min(leftMin, arrSum[i]);
        }
        return sumMax;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int[] arr = new int[n];
        int[] arrSum = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (i == 0)
                arrSum[i] = arr[i];
            else
                arrSum[i] = arr[i] + arrSum[i - 1];
        }
        bf.close();
        
        System.out.println(getConsecutiveArrSumMax(n, arrSum));
    }
}