import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

// https://www.acmicpc.net/problem/10826
public class Main {
    public static void main(String[] args) {
        Fibonacci fibonacci;    // 피보나치 객체
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            fibonacci = new Fibonacci(Integer.parseInt(bf.readLine()));
            System.out.println(fibonacci.fillFibonacciN());
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class Fibonacci {
    private int n;          // 구할 수
    private BigInteger[] fibonacci;     // 100번만 해도 초과가 뜨니 BigInteger사용. n번까지 피보나치 수를 저장

    public Fibonacci(int n) {
        this.n = n;
        this.fibonacci = new BigInteger[n + 1];
        this.fibonacci[0] = new BigInteger("0");
        if (n > 0)
            this.fibonacci[1] = new BigInteger("1");
    }

    /*
    빅 인테저 객체
    */
    public BigInteger fillFibonacciN() {
        for (int i = 2; i < n + 1; i++) {
            fibonacci[i] = fibonacci[i - 1].add(fibonacci[i - 2]);
        }
        return fibonacci[n];
    }
}