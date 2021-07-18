import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        long n;     // 입력된 숫자
        long sum;   // 1~i까지 전부 더했을 때의 합
        long answer = 0;    // 정답을 저장하는 변수
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            n = Long.parseLong(bf.readLine());
            sum = 0;

            for (long i = 1; i <= n; i++) {
                sum = sum + i;
                if (sum > n) {
                    break;
                }
                // 만약 현재 다 더한 합이 더 맞다면, 그 전단계에서 ++를 하면 되기 때문에 그 전 단계의 값을 저장한다.
                answer = i;
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
        System.out.println(answer);
    }
}