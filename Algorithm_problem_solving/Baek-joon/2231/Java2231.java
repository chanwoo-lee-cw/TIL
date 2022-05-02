import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n;
        try {
            n = Integer.parseInt(bf.readLine());
            System.out.println(getSplitSum(n));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 분해 합이 n이 되는 가장 작은 수를 리턴한다
     * ex) n = 216, 198+1+9+8
     *
     * @param n 원하는 수.
     * @return 분해합이 n이 되는 가장 작은 수
     */
    public static int getSplitSum(int n) {
        for (int i = 1; i < n; i++) {
            int curr = i;
            int splitSum = i;

            while (curr > 0) {
                splitSum += curr % 10;
                curr /= 10;
            }
            if (splitSum == n) {
                return i;
            }
        }
        return 0;
    }
}