import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1005
public class Main {

    // 아직 푸는 중.
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
        {
            StringTokenizer st = null;
            int n = 0;
            int k = 0;
            int x = 0;
            int y = 0;
            int w = 0;
            int[] consume = null;
            ArrayList<Integer>[] nextList = null;
            ArrayList<Integer>[] requiredList = null;
            for (int cases = 0; cases < t; cases++) {
                st = new StringTokenizer(bf.readLine());
                n = Integer.parseInt(st.nextToken());
                k = Integer.parseInt(st.nextToken());
                consume = new int[n+1];
                st = new StringTokenizer(bf.readLine());
                for(int i=1; i < n+1; i++) {
                    consume[i] = Integer.parseInt(st.nextToken());
                }
                nextList = new ArrayList[n+1];
                requiredList = new ArrayList[n+1];
                for (int i = 0; i < n; i++) {
                    nextList[i] = new ArrayList<>();
                    requiredList[i] = new ArrayList<>();
                }
                for(int i = 0; i<k; i++) {
                    st = new StringTokenizer(bf.readLine());
                    x = Integer.parseInt(st.nextToken());
                    y = Integer.parseInt(st.nextToken());
                    nextList[x].add(y);
                    requiredList[y].add(x);
                }
                w = Integer.parseInt(bf.readLine());
                getTime(n, w, 0, requiredList, nextList);
            }
        }
        bf.close();
    }

    public static void getTime(int n, int w, int start, ArrayList<Integer>[] requiredList, ArrayList<Integer>[] nextList) {
        Queue<Integer> que = new LinkedList<>();
        boolean[] complete = new boolean[n+1];
        que.add(start);
        complete[start] = true;
        int curr = 0;
        queing : while (!que.isEmpty()) {
            curr = que.poll();
            for(int check : requiredList[curr]) {
                if (!complete[check]) {
                    continue queing;
                }

            }
        }
    }
}