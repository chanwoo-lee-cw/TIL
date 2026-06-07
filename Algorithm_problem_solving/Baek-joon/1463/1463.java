import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;


/*
엣지 케이스 : 1
엣지 케이스 : 0
케이스 2 : 1
케이스 3: 1
케이스 5: 3
 */
public class Main {
    public static void main(String[] args) throws IOException {
        int x;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            x = Integer.parseInt(br.readLine());
            NumberToOne n2o = new NumberToOne(x);
            System.out.println(n2o.excute());
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}

class NumberToOne {
    private final int x;

    NumberToOne(int x) {
        this.x = x;
    }

    public int excute() {
        Queue<NumberSet> bfs = new ArrayDeque<>();
        HashMap<Integer, Integer> visited = new HashMap<>();

        bfs.add(new NumberSet(x, 0));
        visited.put(x, 0);

        while (!bfs.isEmpty()) {
            NumberSet curr = bfs.poll();

            if (curr.n == 1) {
                return curr.count;
            } else if (curr.n < 1) {
                return -1;
            }


            if (curr.n % 3 == 0) {
                if (isVisited(visited, curr.n / 3)) continue;
                visited.put(curr.n / 3, curr.count + 1);
                bfs.add(new NumberSet(curr.n / 3, curr.count + 1));
            }
            if (curr.n % 2 == 0) {
                if (isVisited(visited, curr.n / 2)) continue;
                visited.put(curr.n / 2, curr.count + 1);
                bfs.add(new NumberSet(curr.n / 2, curr.count + 1));
            }
            if (isVisited(visited, curr.n - 1)) continue;
            visited.put(curr.n - 1, curr.count + 1);
            bfs.add(new NumberSet(curr.n - 1, curr.count + 1));
        }
        return -1;
    }

    private boolean isVisited(
            HashMap<Integer, Integer> visited,
            int n
    ) {
        return visited.containsKey(n);
    }

    class NumberSet {
        private final int n;
        private final int count;

        public NumberSet(int n, int count) {
            this.n = n;
            this.count = count;
        }
    }
}