import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17254
class KeyboardEvents {
    /*
    우선순위 큐를 사용하여 시간, 컴퓨터 번호 순서대로 정렬하여 출력값을 리턴
     */
    public String soulution(int n, int m, String[] inputs) {
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] > o2[1])
                    return 1;
                else if (o1[1] < o2[1])
                    return -1;
                // else
                if (o1[0] > o2[0])
                    return 1;
                else if (o1[0] < o2[0])
                    return -1;
                return 0;
            }
        });

        {
            StringTokenizer st;
            int[] temp;
            for (String item : inputs) {
                st = new StringTokenizer(item);
                temp = new int[3];
                temp[0] = Integer.parseInt(st.nextToken());
                temp[1] = Integer.parseInt(st.nextToken());
                temp[2] = st.nextToken().charAt(0);
                priorityQueue.add(temp);
            }
        }

        StringBuilder answer = new StringBuilder();
        while (!priorityQueue.isEmpty()) {
            answer.append((char) priorityQueue.poll()[2]);
        }
        return answer.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n, m;       // n: 키보드의 갯수, m : 입력의 갯수
        String[] inputs;    // 입력의 배열
        String answer;      // 나올 출력
        KeyboardEvents keyboard = new KeyboardEvents();     // 키보드 객체
        try {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            inputs = new String[m];
            for (int i = 0; i < m; i++) {
                inputs[i] = bf.readLine();
            }
            answer = keyboard.soulution(n, m, inputs);
            System.out.println(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
