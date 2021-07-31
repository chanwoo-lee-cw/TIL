import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        int T;
        int A, B;
        GcdAndLcm gcdAndLcm = new GcdAndLcm();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            T = Integer.parseInt(bf.readLine());
            for (int i = 0; i < T; i++) {
                st = new StringTokenizer(bf.readLine());
                A = Integer.parseInt(st.nextToken());
                B = Integer.parseInt(st.nextToken());
                System.out.println(gcdAndLcm.lcm(A, B));
            }

        } catch (IOException error) {
            error.printStackTrace();
        }

    }
}

class GcdAndLcm {
    /*
    유클리드 호제법을 이용해 a,b의 최대 공약수를 찾아낸다.
    */
    public int gcd(int a, int b) {
        int div = 0;
        if (b > a) {
            int temp = b;
            b = a;
            a = temp;
        }

        while (b > 0) {
            div = a % b;
            if (div == 0) {
                div = b;
                break;
            }
            a = b;
            b = div;
        }
        return div;
    }

    /*
    최대 공약수를 이용해 최소 공배수를 찾아낸다.
    */
    public int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
}