import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Java1021 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = 0, m = 0;
        int[] wants = null;
        int answer = 0;

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        wants = new int[m];
        Deque<Integer> que = new ArrayDeque<Integer>(n);
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            que.addLast(i + 1);
        }

        for (int i = 0; i < m; i++) {
            wants[i] = Integer.parseInt(st.nextToken());
        }

        {
            int idx = 0;
            Iterator<Integer> iter = null;
            for (int want : wants) {
                idx = 0;
                iter = que.iterator();
                while (iter.hasNext()) {
                    if (iter.next() == want)
                        break;
                    idx++;
                }

                if (idx <= que.size() - idx) {
                    for (int i = 0; i < idx; i++) {
                        que.addLast(que.pollFirst());
                        answer++;
                    }
                } else {
                    for (int i = 0; i < que.size() - idx; i++) {
                        que.addFirst(que.pollLast());
                        answer++;
                    }
                }
                que.pollFirst();
            }
            System.out.println(answer);
        }
    }
}