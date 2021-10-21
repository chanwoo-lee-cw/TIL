import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int n;
        List<Integer> answer;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(bf.readLine());
            answer = factorization(n);

            StringBuilder sb = new StringBuilder();
            for (int item : answer) {
                sb.append(item);
                sb.append("\n");
            }
            System.out.print(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Integer> factorization(int n) {
        ArrayList<Integer> answer = new ArrayList<>();
        int sqrtN = (int) Math.sqrt(n);

        for (int i = 2; i < sqrtN + 1; i++) {
            while (n % i == 0) {
                answer.add(i);
                n /= i;
            }
            if (n == 1)
                break;
        }
        if (n != 1) {
            answer.add(n);
        }
        return answer;
    }
}