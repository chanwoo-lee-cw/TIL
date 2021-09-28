import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int n, m;
        Primes primes = new Primes();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            m = Integer.parseInt(bf.readLine());
            n = Integer.parseInt(bf.readLine());

            System.out.println(primes.solution(m, n));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Primes {
    public String solution(int m, int n) {
        boolean[] eratos = makeEratos(n);
        int minPrime = -1;
        int sumPrime = 0;
        String answer;

        for (int i = m; i < n + 1; i++) {
            if (eratos[i]) {
                sumPrime += i;
                if (minPrime == -1) {
                    minPrime = i;
                }
            }
        }

        answer = minPrime == -1 ? "-1" : String.format("%d\n%d", sumPrime, minPrime);
        return answer;
    }

    public boolean[] makeEratos(int n) {
        boolean[] eratos = new boolean[n + 1];
        Arrays.fill(eratos, true);
        eratos[0] = eratos[1] = false;
        int sqrtN = (int) Math.sqrt(n);
        for (int i = 2; i < sqrtN + 1; i++) {
            for (int j = i * i; j < n + 1; j += i) {
                eratos[j] = false;
            }
        }
        return eratos;
    }
}
