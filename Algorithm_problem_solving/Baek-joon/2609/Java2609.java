import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int num1, num2;
        try {
            st = new StringTokenizer(bf.readLine());
            num1 = Integer.parseInt(st.nextToken());
            num2 = Integer.parseInt(st.nextToken());
            GCD_LCD solution = new GCD_LCD(num1, num2);
            System.out.println(solution.getGcd());
            System.out.println(solution.getLcd());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class GCD_LCD {
    private int num1;
    private int num2;
    private int gcd;
    private int lcd;

    public GCD_LCD(int num1, int num2) {
        if (num1 > num2) {
            this.num1 = num1;
            this.num2 = num2;
        } else {
            this.num1 = num2;
            this.num2 = num1;
        }
        this.gcd = -1;
        this.lcd = -1;
    }

    public int getGcd() {
        /*
        num1과 num2의 최대 공약수를 반환하는 함수

        Return:
            int: 최대 공약수
        */
        if (gcd == -1) {
            int a = num1, b = num2;
            while (b > 0) {
                int r = a % b;
                a = b;
                b = r;
            }
            gcd = a;
        }
        return gcd;
    }

    public int getLcd() {
        /*
        num1과 num2의 최소 공배수를 반환하는 함수

        Return:
            int: 최소 공배수
        */
        if (lcd == -1) {
            lcd = num1 / gcd * num2;
        }
        return lcd;
    }

}