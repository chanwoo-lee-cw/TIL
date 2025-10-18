import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        SortInside sortInside = new SortInside();
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ) {
            sortInside.splitAndInsert(
                    br.readLine()
            );
            sortInside.printSorted();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class SortInside {
    private PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());

    public void splitAndInsert(String numberString) {
        int len = numberString.length();

        for (int i = 0; i < len; i++) {
            Integer number = Integer.parseInt(numberString.substring(i, i + 1));
            queue.add(number);
        }
    }

    public void printSorted() {
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Integer number = queue.poll();
            sb.append(number);
        }
        System.out.println(sb);
    }
}