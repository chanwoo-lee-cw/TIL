import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static boolean[] visited;    // 부분집합의 합 체크

    public static void main(String[] args) throws IOException {
        int n;      // 정수의 갯수
        int[] arr;  // 입력된 정수의 리스트
        int arrsum = 0; // 배열의 총합

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(bf.readLine());
        arr = new int[n];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            arrsum += arr[i];
        }
        visited = new boolean[arrsum + 1];  // 배열의 총합 만큼 배열을 선언한다.
        st = null;
        bf.close();

        int sum = 0;
        boolean[] dfsvisited = new boolean[n + 1];
        checkVisited(arr, n, sum, dfsvisited);

        // i를 증가시켜가며 모든 자연수 체크
        int i = 1;
        while (visited[i]) {
            i++;
        }
        System.out.println(i);

    }

    /*
    dfs 방식으로 모든 순열을 탐색해서 더해서 체크한다.
    매개 변수 : 입력된 수의 배열, 배열의 길이, 이전까지의 부분집합의 합, 순열을 위한 방문 여부 체크
    */
    private static void checkVisited(int[] arr, int n, int sum, boolean[] dfsvisited) {
        for (int i = 0; i < n; i++) {
            if (dfsvisited[i])
                continue;
            dfsvisited[i] = true;
            sum += arr[i];
            visited[sum] = true;
            checkVisited(arr, n, sum, dfsvisited);
            sum -= arr[i];
            dfsvisited[i] = false;
        }
    }
}