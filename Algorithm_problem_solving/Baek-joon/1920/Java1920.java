import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1920
public class Main {
    public static void main(String[] args) {
        NumList numList;    // 수열을 저장하고 있는 객체
        int searchCnt;      // 찾을 숫자의 길이
        int[] searchList;   // 수열에 포함되어 있는지 찾을 숫자들
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            numList = new NumList(bf);
            searchCnt = Integer.parseInt(bf.readLine());
            searchList = new int[searchCnt];
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < searchCnt; i++) {
                searchList[i] = Integer.parseInt(st.nextToken());
            }
            System.out.print(numList.searchNum(searchList));
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class NumList {
    private int n;
    private int[] arr;

    public NumList(BufferedReader bf) throws IOException {
        this.n = Integer.parseInt(bf.readLine());
        this.arr = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
    }

    // search 리스트에서 없는 숫자를 찾아내 출력한다.
    public String searchNum(int[] searchList) {
        StringBuilder sb = new StringBuilder();
        for (int search : searchList) {
            sb.append(binarySearch(search));
            sb.append("\n");
        }
        return sb.toString();
    }

    // 이진탐색으로 원하는 숫자가 포함되어 잇는지 찾는다.
    private int binarySearch(int search) {
        int s = 0;
        int e = n;
        int m;
        while (e - s > 0) {
            m = (s + e) / 2;
            if (arr[m] == search)
                return 1;
            else if (arr[m] < search)
                s = m + 1;
            else
                e = m;
        }
        return 0;
    }
}