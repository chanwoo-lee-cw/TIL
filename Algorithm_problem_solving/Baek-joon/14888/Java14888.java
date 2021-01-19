import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int minValue = Integer.MAX_VALUE;
    public static int maxValue = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        int n;      // 숫자의 갯수
        int[] nums;     // 입력된 숫자의 배열
        int[] operators = new int[4];   // 연산자의 갯수
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(bf.readLine());
        nums = new int[n];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < 4; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }
        bf.close();
        st = null;
        // 첫번째 자리는 무조건 양수로 들어간다.
        searchCalulate(n, nums, operators[0], operators[1], operators[2], operators[3], 1, nums[0]);
        System.out.printf("%d\n%d\n", maxValue, minValue);
    }

    /*
    모든 값을 재귀로 구한다.
    매개변수 : 수의 갯수, 수의 배열, 차례로 남은 연산자의 수, 현재 계산되는 위치, 현재 위치까지의 결과
    */
    private static void searchCalulate(int n, int[] nums, int plus, int minus, int multi, int div, int position, int result) {
        if (n == position) {
            // 만약 끝까지 왔다면 값 비교
            minValue = Math.min(minValue, result);
            maxValue = Math.max(maxValue, result);
            return;
        }
        // else
        // 연산자가 남아 있으면 계산
        if (plus > 0)
            searchCalulate(n, nums, plus - 1, minus, multi, div, position + 1, result + nums[position]);
        if (minus > 0)
            searchCalulate(n, nums, plus, minus - 1, multi, div, position + 1, result - nums[position]);
        if (multi > 0)
            searchCalulate(n, nums, plus, minus, multi - 1, div, position + 1, result * nums[position]);
        if (div > 0)
            searchCalulate(n, nums, plus, minus, multi, div - 1, position + 1, result / nums[position]);
    }
}