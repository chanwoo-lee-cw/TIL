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

        int result = getMinInsertNumForPalindrome(n, arr);
        System.out.println(result);
    }

    private static int getMinInsertNumForPalindrome(int n, int[] arr) {
        // right와 left를 설정
        // left를 right와 같은걸 찾을때까지 반복
        // 만약 찾는다면 left와 right 둘 다 한칸씩 이동
        // 만약 right >= n break
        // 만약 left < 0 일때 right < n 이라면 center++
        int left = 0;
        int right = 1;
        int center = 1;
        int needToAddNum = 0;
        while (right < n) {
            while (left >= 0) {
                if (arr[left] == arr[right]) {
                    left--;
                    right++;
                    if (right >= n) {
                        return needToAddNum;
                    }
                } else {
                    left--;
                    needToAddNum++;
                }
            }
            right = ++center;
            left = right - 1;
            needToAddNum = 0;
        }
        return n - 1;
    }
}