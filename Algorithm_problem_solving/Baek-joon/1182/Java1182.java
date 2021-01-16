import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        int n;      // 정수의 갯수
        int s;      // 구해야 되는 수
        int[] arr;  // 입력된 정수의 리스트
        int answer = 0; // s가 되는 부분 집합의 갯
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        arr = new int[n];

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = null;
        bf.close();

        ArrayList<Integer> numList;
        for (int k = 0; k < n; k++) {
            numList = new ArrayList<>();
            answer += getSMakeCase(arr, n, k + 1, s, numList, 0);
        }
        System.out.println(answer);
    }


    /*
    nCk일때 합이 s가 되는 부분 집합의 갯수
    매개변수 : 수의 배열, 배열의 길이, 부분 집합의 길이, 합 s, 현재 완성된 부분 집합, DFS를 위한 수의 시작 위치
    */
    private static int getSMakeCase(int[] arr, int n, int k, int s, ArrayList<Integer> numList, int pos) {
        int result = 0; // 지금까지 구해진 수의 배열의 길
        if (numList.size() == k) {
            int sum = 0;
            for (int item : numList) {
                sum += item;
            }
            // 만약 완성 되었다면 1반환
            if (sum == s) {
                return 1;
            } else {
                return 0;
            }
        } else {
            for (int i = pos; i < n; i++) {
                numList.add(arr[i]);
                result += getSMakeCase(arr, n, k, s, numList, i + 1);
                numList.remove(numList.size() - 1);
            }
        }
        return result;
    }
}