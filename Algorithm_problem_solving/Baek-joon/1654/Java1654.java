import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static long getNeedLen(int n, int k, int[] currHave) {
        long start = 0;
        long end = currHave[0];
        long result = 0;
        for (int i = 1; i < k; i++) {
            end = Math.max(currHave[i], end);
        }
        {
            long mid = 0;
            long getCableCnt = 0;
            while (end - start >= 0) {
                mid = (start + end) / 2;
                if (mid == 0)
                    return end;
                getCableCnt = 0;
                for (int cable : currHave) {
                    getCableCnt += cable / mid;
                    if (getCableCnt > n)
                        break;
                }
                if (getCableCnt >= n) {
                    result = mid;
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] currHave = new int[k];

        for (int i = 0; i < k; i++) {
            currHave[i] = Integer.parseInt(bf.readLine());
        }
        bf.close();
        
        System.out.println(getNeedLen(n, k, currHave));
    }
}