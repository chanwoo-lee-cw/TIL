import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        Semiconductor semiconductor;    // 반도체 객체
        int n;  // 반도
        String input;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(bf.readLine());
            input = bf.readLine();

            semiconductor = new Semiconductor(n);
            semiconductor.setLinkablePort(input);
            System.out.println(semiconductor.maxLink());

            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

/*
lis 문제
 */
class Semiconductor {
    private int n;
    private int[] linkablePort;

    public Semiconductor(int n) {
        this.n = n;
        this.linkablePort = new int[n + 1];
    }

    public void setLinkablePort(String input) {
        StringTokenizer st = new StringTokenizer(input);
        for (int i = 1; i < n + 1; i++) {
            linkablePort[i] = Integer.parseInt(st.nextToken());
        }
    }

    private int lowerBound(int s, int e, int[] arr, int find) {
        int m;
        while (e - s > 0) {
            m = (s + e) / 2;
            if (arr[m] < find)
                s = m + 1;
            else {
                e = m;
            }
        }
        return (s + e) / 2;
    }

    public int maxLink() {
        int[] lis = new int[n];
        lis[0] = linkablePort[1];
        int len = 1;

        for (int i = 2; i < n + 1; i++) {
            if (linkablePort[i] > lis[len - 1])
                lis[len++] = linkablePort[i];
            else {
                int pos = lowerBound(0, len, lis, linkablePort[i]);
                lis[pos] = linkablePort[i];
            }
        }
        return len;
    }
}