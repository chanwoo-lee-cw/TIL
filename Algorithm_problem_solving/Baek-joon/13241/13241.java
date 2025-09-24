import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class GcpLcm {

    /**
     * 최소 공배수를 반환한다.
     */
    public static long getLcm(long a, long b) {
        long gcb = getGcp(a, b);

        long aDiv = a / gcb;
        long bDiv = b / gcb;

        return aDiv * bDiv * gcb;
    }

    /**
     * 최대 공약수를 반환한다.
     */
    public static long getGcp(long a, long b) {
        if (b > a) {
            long temp = b;
            b = a;
            a = temp;
        }

        while (b != 0) {
            long remainDiv = a % b;
            a = b;
            b = remainDiv;
        }

        return a;
    }
}

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        long a = Long.parseLong(stringTokenizer.nextToken());
        long b = Long.parseLong(stringTokenizer.nextToken());

        System.out.println(GcpLcm.getLcm(a, b));
    }
}