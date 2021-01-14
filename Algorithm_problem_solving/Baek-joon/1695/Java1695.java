import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
        int n;  // inputed n
        int[] arr;      // inputed int arr
        {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(bf.readLine());
            arr = new int[n];
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            bf.close();
        }

        int[][] dp = new int[n][n];
        getMinInsertNumForPalindrome(arr, 0, n - 1, dp);
        System.out.println(dp[0][n - 1]);
    }

    // 최소 추가해야 되는 수를 반환하는 함수
    private static int getMinInsertNumForPalindrome(int[] arr, int start, int end, int[][] dp) {
        // 팰린드롬안자 확인하려면 양쪽 끝을 비교한다.
        // 만약 양쪽 끝이 같다면 +1이 필요 없다.
        // 만약 양쪽 끝이 다르다, 왼쪽에 붙히느냐 오른쪽에 붙히느냐로 나뉜다.
        if (start == end || start > end)
            return 0;
        if (dp[start][end] != 0)
            return dp[start][end];
        else if (arr[start] == arr[end])
            return dp[start][end] = getMinInsertNumForPalindrome(arr, start + 1, end - 1, dp);
        else
            return dp[start][end] = Math.min(getMinInsertNumForPalindrome(arr, start + 1, end, dp) + 1, getMinInsertNumForPalindrome(arr, start, end - 1, dp) + 1);
    }
}