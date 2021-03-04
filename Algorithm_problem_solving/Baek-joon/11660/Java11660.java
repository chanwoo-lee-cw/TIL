import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11660
public class Main {
    public static void main(String[] args) {
        IntervalSum button;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            button = new IntervalSum(bf);
            System.out.println(button.getInvervalSum());
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class IntervalSum {
    private int n;      // 배열의 크기
    private int m;      // 테스트 케이스의 개수
    private int[][] matrix;     // 입력된 배열의 수들
    private int[][] range;      // 테스트 케이스의 구간
    private int[][] leftSum;    // 좌측 부터 시작했을때 그 열의 수의 합

    public IntervalSum(BufferedReader bf) throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        this.n = Integer.parseInt(st.nextToken());
        this.m = Integer.parseInt(st.nextToken());
        this.matrix = new int[n + 1][n + 1];
        this.leftSum = new int[n + 1][n + 1];
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j < n + 1; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                leftSum[i][j] = leftSum[i][j - 1] + matrix[i][j];
            }
        }

        this.range = new int[m][5];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= 4; j++) {
                range[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    /*
    구간들의 수의 합을 출력
     */
    public String getInvervalSum() {
        StringBuilder sb = new StringBuilder();     // 출력될 답
        int currSum;        // 현재 구간의 수의 합을 구한다.
        for (int[] testCase : range) {
            currSum = 0;
            for (int i = testCase[1]; i <= testCase[3]; i++) {
                currSum += leftSum[i][testCase[4]] - leftSum[i][testCase[2] - 1];
            }
            sb.append(currSum);
            sb.append("\n");
        }

        return sb.toString();
    }
}