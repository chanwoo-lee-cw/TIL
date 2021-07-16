import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        long n;
        long sum;
        long answer = 0;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            n = Long.parseLong(bf.readLine());
            sum = 0;

            for (long i = 1; i <= n; i++) {
                sum = sum + i;
                if (sum > n) {
                    break;
                }
                answer = i;
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
        System.out.println(answer);
    }
}