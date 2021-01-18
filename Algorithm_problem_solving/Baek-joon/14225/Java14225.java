import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static boolean[] visited;    // 부분집합의 합 체크

    public static void main(String[] args) throws IOException {
        int n;      // 정수의 갯수
        int[] arr;  // 입력된 정수의 리스트
        int arrsum = 0;     // 배열의 총 길

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(bf.readLine());
        arr = new int[n];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            arrsum += arr[i];   // 배열의 모든 수를 더한 길
        }
        visited = new boolean[arrsum+2];    // 밑에서 i++로 만들었으니, 모든 수가 만들어지는 경우 모든 배열의 총 합이 모두 통과되는 경우에는 런타임 에러
        st = null;
        bf.close();

        checkVisited(arr, n, 0, 0);

        int i = 1;
        while (visited[i]) {
            i++;
        }
        System.out.println(i);

    }

    /*
    dff로 만들어 지는 숫자의 경우의 수를 모두 체크
    매개 변수 : 입력된 수의 배열, 배열의 길이, 이전까지의 부분집합의 합, 조합을 위한 현재 위치
     */
    private static void checkVisited(int[] arr, int n, int sum, int pos) {
        for (int i = pos; i < n; i++) {
            sum += arr[i];
            visited[sum] = true;
            checkVisited(arr, n, sum, i+1);
            sum -= arr[i];
        }
    }
}