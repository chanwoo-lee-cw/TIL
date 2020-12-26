import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = null;
        bf.close();
        // 증가하는 경우와 감소하는 경우 모두 lis를 구한다.
        int[] increaseList = getlisLen(n, arr);
        reverseArray(n, arr);
        int[] decreaseList = getlisLen(n, arr);
        reverseArray(n, decreaseList);

        int arrMax = 0;
        // 그 자리에서 숫자가 겹친다는 뜻이므로 -1을 한다.
        for (int i = 0; i < n; i++)
            arrMax = Math.max(increaseList[i] + decreaseList[i] - 1, arrMax);

        System.out.println(arrMax);
    }

    // 배열을 뒤집는 메소드
    private static void reverseArray(int n, int[] arr) {
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left++] = arr[right];
            arr[right--] = temp;
        }
    }

    // 각각 배열의 위치에서 lis의 길이가 얼마나 되는지 출력
    private static int[] getlisLen(int n, int[] arr) {
        int[] lis = new int[n];
        lis[0] = arr[0];
        int lisLen = 1;
        int[] lisLenList = new int[n];
        lisLenList[0] = 1;

        for (int i = 1; i < n; i++) {
            if (lis[lisLen - 1] < arr[i]) {
                lis[lisLen] = arr[i];
                lisLen++;
            } else {
                int getpos = lowerBound(lis, 0, lisLen, arr[i]);
                lis[getpos] = arr[i];
            }
            lisLenList[i] = lisLen;
        }
        return lisLenList;
    }

    private static int lowerBound(int[] arr, int s, int e, int search) {
        int m;
        while (e - s > 0) {
            m = (s + e) / 2;
            if (arr[m] < search)
                s = m + 1;
            else
                e = m;
        }
        return (s + e) / 2;
    }
}