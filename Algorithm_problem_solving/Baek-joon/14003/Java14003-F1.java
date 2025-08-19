import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static int lowerBound(int[] arr, int start, int end, int search) {
        int e = end;
        int s = start;
        int m;
        while (e - s > 0) {
            m = (e + s) / 2;
            if (arr[m] < search) {
                s = m + 1;
            } else {
                e = m;
            }
        }
        return (s + e) / 2;
    }

    public static int[] getLis(int n, int[] arr) {
        int[] lis = new int[n];
        // 주입된 위치를 저장하는 스택
        Stack<Integer> stk = new Stack<>();
        lis[0] = 0;
        stk.add(0);
        lis[0] = arr[0];
        int lisLen = 1;
        {
            int pos;
            for (int i = 1; i < n; i++) {
                if (lis[lisLen - 1] >= arr[i]) {
                    pos = lowerBound(lis, 0, lisLen, arr[i]);
                    lis[pos] = arr[i];
                    stk.add(pos);
                } else {
                    lis[lisLen] = arr[i];
                    stk.add(lisLen);
                    lisLen++;
                }
            }
        }
        System.out.println(lisLen);
        // 스택을 역순으로 되짚어 가며, 각각 마지막에 위치에 들어간 것들을 출력한다.
        // lis를 찾을때 위치가 바뀐다곤 해도 lisList에 2가 들어간 위치에, 2를 찾은 위치보다 앞에 있는 1은 해당 위치의 값보다는 확실히 작다.
        int[] lisList = new int[lisLen];
        {
            int find = lisLen-1;
            int currpos;
            while (find > -1 && !stk.isEmpty()) {
                currpos=stk.pop();
                if(currpos==find) {
                    lisList[find--] = arr[stk.size()];
                }
            }
        }
        return lisList;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int[] lisList = getLis(n, a);
        for (int item: lisList) {
            System.out.printf("%d ",item);
        }
    }
}