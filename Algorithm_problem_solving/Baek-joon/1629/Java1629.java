import java.util.Scanner;

public class Main {

    public static long squared(long a, long b, long c) {
        if (b == 0)
            return 1;
        else if (b == 1)
            return a;
        else if (b % 2 == 0) {
            // 꼭 long을 따로 저장해 준다음에 거듭 제곱을 해야 한다.
            // Math.pow로 했을 경우에는 반환형이 double이기 때문에 오버플로우가 발생
            long getPow = squared(a, b / 2, c);
            return getPow * getPow % c;
        } else
            return (squared(a, b - 1, c) * a) % c;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long a = in.nextLong();
        long b = in.nextLong();
        long c = in.nextLong();
        in.close();
        
        System.out.println(squared(a, b, c) % c);
    }
}