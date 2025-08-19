import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 가장 긴 부분 순열(lis)를 계산하는 클래스
 */
class LIS {
    private int n;
    private long[] arr;
    private Result result = null;

    public LIS(int n, long[] arr) {
        this.n = n;
        this.arr = arr;
    }

    /**
     * lisLength와 lis의 결과
     */
    class Result {
        private int lisLength;
        private ArrayList<Long> lis;

        public Result(int lisLength, ArrayList<Long> lis) {
            this.lisLength = lisLength;
            this.lis = lis;
        }

        public int getLisLength() {
            return lisLength;
        }

        public ArrayList<Long> getLis() {
            return lis;
        }
    }


    /**
     * lis 정보를 Print 한다
     */
    public void printResult() {
        if (result == null) {
            result = makeResult();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(result.getLisLength()).append("\n");
        for (long lis : result.getLis()) {
            sb.append(lis).append(" ");
        }
        System.out.println(sb);
    }


    /**
     * @return 정렬된 배열 arr[start:end] 에서 find보다 크거나 같은 값이 나오는 첫번째 위치
     */
    private int lowerBound(
            long[] arr,
            long find,
            int start,
            int end
    ) {
        int s = start;
        int e = end;
        while (s < e) {
            int m = (s + e) / 2;
            if (arr[m] < find) {
                s = m + 1;
            } else {
                e = m;
            }
        }
        return e;
    }


    /**
     * arr에서 가장 긴 증가하는 수열 Result를 리턴
     */
    private Result makeResult() {
        // lis의 길이와 insert 위치를 구한다.
        long[] lisTemp = new long[n];
        int lastIndex = -1;
        ArrayDeque<Integer> traceInsertPosition = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            long curr = arr[i];
            if (lastIndex == -1) {
                lastIndex += 1;
                lisTemp[lastIndex] = curr;
                traceInsertPosition.addLast(lastIndex);
            } else if (arr[i] > lisTemp[lastIndex]) {
                lastIndex += 1;
                lisTemp[lastIndex] = curr;
                traceInsertPosition.addLast(lastIndex);
            } else {
                int insertPosition = lowerBound(lisTemp, arr[i], 0, lastIndex);
                lisTemp[insertPosition] = curr;
                traceInsertPosition.addLast(insertPosition);
            }
        }

        // 백 트래킹으로 가장 긴 증가하는 수열을 구한다.
        int lisLength = lastIndex + 1;
        ArrayList<Long> lis = new ArrayList<>();

        int traceTop = lastIndex;
        for (int i = n - 1; i >= 0; i--) {
            if (traceTop == traceInsertPosition.removeLast()) {
                lis.add(arr[i]);
                traceTop--;
            }
        }

        Collections.reverse(lis);
        return new Result(lisLength, lis);
    }

}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        long[] arr = new long[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        LIS lis = new LIS(n, arr);
        lis.printResult();

    }
}
