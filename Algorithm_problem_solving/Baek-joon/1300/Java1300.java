import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();

        int start = 1;
        int end = k;
        {
            int mid = 0;
            int cnt = 0;
            int result = 0;
            while (end - start >= 0) {
                mid = (start + end) / 2;
                cnt = 0;
                for (int i = 1; i < n + 1; i++) {
                    cnt += Math.min(mid / i, n);
                }
                if (cnt >= k) {
                    result = mid;
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            System.out.println(result);
        }
        in.close();
    }
}