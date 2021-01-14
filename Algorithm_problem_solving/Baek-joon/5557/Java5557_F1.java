import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/5557
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] arr = new int[n];
        {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int i = n - 1; i >= 0; i--) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            st = null;
        }
        bf.close();

        System.out.println(getCollectfomular(n, arr));
    }

    private static long getCollectfomular(int n, int[] arr) {
        // 값이 나오는 경우의 수를 저장하는 해쉬맵
        HashMap<Integer, Long>[] saveFomularResult = new HashMap[n];
        for (int i = 0; i < n; i++) {
            saveFomularResult[i] = new HashMap<>();
        }
        saveFomularResult[1].put(arr[1], 1L);
        // 반복으로 포함된 계속 이전 키에서 값을 더하며 찾는다.
        for (int i = 2; i < n; i++) {
            for (int presaved : saveFomularResult[i - 1].keySet()) {
                if (presaved + arr[i] <= 20) {
                    if (!saveFomularResult[i].containsKey(presaved + arr[i])) {
                        saveFomularResult[i].put(presaved + arr[i], saveFomularResult[i - 1].get(presaved));
                    } else {
                        saveFomularResult[i].put(presaved + arr[i], saveFomularResult[i].get(presaved + arr[i]) + saveFomularResult[i - 1].get(presaved));
                    }
                }
                if (presaved - arr[i] >= 0) {
                    if (!saveFomularResult[i].containsKey(presaved - arr[i])) {
                        saveFomularResult[i].put(presaved - arr[i], saveFomularResult[i - 1].get(presaved));
                    } else {
                        saveFomularResult[i].put(presaved - arr[i], saveFomularResult[i].get(presaved - arr[i]) + saveFomularResult[i - 1].get(presaved));
                    }
                }
            }
        }
        return saveFomularResult[n - 1].get(arr[0]);
    }
}