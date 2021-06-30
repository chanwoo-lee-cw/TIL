import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static boolean binarySearch(int[] arr, int s, int e, int find) {
        int m;
        while (e > s) {
            m = (e + s) / 2;
            if (arr[m] == find) {
                return true;
            } else if (arr[m] < find) {
                s = m + 1;
            } else {
                e = m;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int aSize, bSize;
        int[] aSet;
        int[] bSet;
        int answer = 0;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(bf.readLine());
            aSize = Integer.parseInt(st.nextToken());
            bSize = Integer.parseInt(st.nextToken());
            aSet = new int[aSize];
            bSet = new int[bSize];
            st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < aSize; i++) {
                aSet[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < bSize; i++) {
                bSet[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(aSet);
            Arrays.sort(bSet);

            for (int item : aSet) {
                if (!binarySearch(bSet, 0, bSize, item))
                    answer++;
            }
            for (int item : bSet) {
                if (!binarySearch(aSet, 0, aSize, item))
                    answer++;
            }

            System.out.println(answer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
