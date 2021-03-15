import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1780
public class Main {
    public static void main(String[] args) {
        IntervalSum intervalSum;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            intervalSum = new IntervalSum(bf);
            System.out.println(intervalSum.minLenGreaterS());
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class IntervalSum {
    private int n;      // 배열의 크기
    private int s;      // 목표로한 숫자
    private int[] arr;  // 각 배열의 수
    private int[] arrSum;  // 0번부터 차례로 더한 수의 합

    public IntervalSum(BufferedReader bf) throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        this.n = Integer.parseInt(st.nextToken());
        this.s = Integer.parseInt(st.nextToken());
        this.arr = new int[n + 1];
        this.arrSum = new int[n + 1];

        st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            arrSum[i] = arrSum[i - 1] + arr[i];
        }
    }

    /*
    각 자리를 순서대로 탐색하면서 s가 가장 짧아지는 자리를 찾는다.
     */
    public int minLenGreaterS() {
        int answer = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (arrSum[i] - arrSum[j] > s)
                    answer = Math.min(answer, i - j);
            }
        }
        return answer;
    }
}
