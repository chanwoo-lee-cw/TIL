import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static boolean[] visited;    // 합으로 나올 수 있는 수들 체크

    public static void main(String[] args) throws IOException {
        int n;      // 정수의 갯수
        int[] arr;  // 입력된 정수의 리스트
        int sum = 0;    // 모든 부분 집합의 총합

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(bf.readLine());
        arr = new int[n];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }
        visited = new boolean[sum + 1];     // 모든 부분 집합의 총 합 만큼 배열 선언
        st = null;
        bf.close();

        ArrayList<Integer> numList;
        boolean[] dfsvisited;
        // 조합의 경우의 수를 더해가며 출력
        for (int k = 0; k < n; k++) {
            numList = new ArrayList<>();
            dfsvisited = new boolean[n + 1];
            checkVisited(arr, n, k + 1, numList, dfsvisited);
        }

        // i를 증가 시켜 마켜 모든 자연수를 탐색한다.
        int i = 1;
        while (visited[i]) {
            i++;
        }
        System.out.println(i);

    }

    /*
    dfs로 가능한 조합의 경우의 수를 찾는다.
    매개변수 배열, 배열의 길이, 조합의 길이, 리스트를 만든 숫자의 배열, 만들어진 숫자의 배열
    */
    private static void checkVisited(int[] arr, int n, int k, ArrayList<Integer> numList, boolean[] dfsvisited) {
        if (numList.size() == k) {
            int sum = 0;
            for (int item : numList) {
                sum += item;
            }
            visited[sum] = true;
        } else {
            for (int i = 0; i < n; i++) {
                if (dfsvisited[i])
                    continue;
                numList.add(arr[i]);
                dfsvisited[i] = true;
                checkVisited(arr, n, k, numList, dfsvisited);
                dfsvisited[i] = false;
                numList.remove(numList.size() - 1);
            }
        }
    }
}