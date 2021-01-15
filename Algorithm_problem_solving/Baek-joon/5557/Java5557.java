import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/5557
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] arr = new int[n + 1];
        {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            st = null;
        }
        bf.close();

        long result = getCollectfomular(n, arr);
        System.out.println(result);
    }

    // 옳은 식을 만드는 경우의 수를 반환하는 함수.
    private static long getCollectfomular(int n, int[] arr) {
        // 값이 나오는 경우의 수를 저장하는 해쉬맵
        HashMap<Integer, Long>[] saveFomularResult = new HashMap[n];
        for (int i = 1; i < n; i++) {
            saveFomularResult[i] = new HashMap<>();
        }
        saveFomularResult[1].put(arr[1], 1L);
        // 반복으로 포함된 계속 이전 키에서 값을 더하며 찾는다.
        for (int i = 2; i < n; i++) {
            for (int presaved : saveFomularResult[i - 1].keySet()) {
                if (presaved + arr[i] <= 20) {
                    // 더한 것이 20 보다 작을 때만 진행한다.
                    if (!saveFomularResult[i].containsKey(presaved + arr[i])) {
                        saveFomularResult[i].put(presaved + arr[i], saveFomularResult[i - 1].get(presaved));
                    } else {
                        saveFomularResult[i].put(presaved + arr[i], saveFomularResult[i].get(presaved + arr[i]) + saveFomularResult[i - 1].get(presaved));
                    }
                }
                if (presaved - arr[i] >= 0) {
                    // 뺀 것이 음수가 아닐 때만 진행한다.
                    if (!saveFomularResult[i].containsKey(presaved - arr[i])) {
                        saveFomularResult[i].put(presaved - arr[i], saveFomularResult[i - 1].get(presaved));
                    } else {
                        saveFomularResult[i].put(presaved - arr[i], saveFomularResult[i].get(presaved - arr[i]) + saveFomularResult[i - 1].get(presaved));
                    }
                }
            }
        }
        // 마지막 수와 같은 수의 갯수를 반환한다.
        return saveFomularResult[n - 1].get(arr[n]);
    }
}