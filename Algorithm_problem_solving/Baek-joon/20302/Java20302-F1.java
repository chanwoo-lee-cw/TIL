import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        int n;
        LinkedList<String> formula;
        StringTokenizer st;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        st = new StringTokenizer(bf.readLine());
        formula = new LinkedList<>();
        for (int i = 0; i < 2 * n - 1; i++) {
            formula.add(st.nextToken());
        }
        System.out.println(checkMint(n, formula) ? "mint chocolate" : "toothpaste");
    }

    public static boolean checkMint(int n, Queue<String> formula) {
        float result = Integer.parseInt(formula.poll());

        String cal;
        int num;
        while (!formula.isEmpty()) {
            cal = formula.poll();
            num = Integer.parseInt(formula.poll());
            if (cal.equals("*")) {
                result = result * num;
            } else if (cal.equals("/")) {
                if (result == 0) result = 100000;
                result = result / num;
            }
            result %= 100000;
        }
        return (int) result == result;
    }
}