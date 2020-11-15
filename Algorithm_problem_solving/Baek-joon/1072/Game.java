import java.util.*;
import java.io.*;

public class Game {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long X = Long.parseLong(st.nextToken());
        long Y = Long.parseLong(st.nextToken());

        long Z = (100*Y) / X;

        solution(X, Y, Z);
    }

    public static void solution(long X, long Y, long Z) {
        long min_win = 0;
        long max_win = X;
        long result = Integer.MAX_VALUE;
        if(Z >= 99) {
            System.out.println(-1);
            return;
        }
        {
            long mid;
            double rating;
            while (max_win - min_win >= 0) {
                mid = (min_win + max_win)/2;
                rating = 100 * (double)(Y + mid) / (double)(X + mid);
                if (Math.floor(rating - Z) != 0) {
                    result = Math.min(mid, result);
                }
                if(rating - Z > 1) {
                    max_win = mid - 1;
                }
                else {
                    min_win = mid + 1;
                }
            }
        }
        System.out.println(result);
    }
}
