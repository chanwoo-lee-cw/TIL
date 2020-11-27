import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        PriorityQueue<Jewel> jewel = new PriorityQueue<>();
        {
            Jewel input_jewel = null;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(bf.readLine());
                input_jewel = new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                jewel.add(input_jewel);
            }
        }
        int back[] = new int[k];
        for (int j = 0; j < k; j++) {
            st = new StringTokenizer(bf.readLine());
            back[j] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(back);
        System.out.println(solution(n, k, jewel, back));
    }

    public static int solution(int n, int k, PriorityQueue<Jewel> jewel, int[] back) {
        PriorityQueue<Integer> able_jewel = new PriorityQueue<>();
        int answer = 0;
        int curr_jewel = 0;
        for (int j = 0; j < k; j++) {
            int curr_back = back[j];
            while (!jewel.isEmpty() && curr_back >= jewel.peek().weight) {
                able_jewel.add(-jewel.poll().value);
            }
            if (!able_jewel.isEmpty()) {
                answer -= able_jewel.poll();
            } else if (jewel.isEmpty()) {
                break;
            }
        }
        return answer;
    }
}

class Jewel implements Comparable<Jewel> {
    int weight;
    int value;

    Jewel(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    @Override
    public int compareTo(Jewel o) {
        return this.weight - o.weight;
    }
}