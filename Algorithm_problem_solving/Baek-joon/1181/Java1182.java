import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        int n;
        Dictionary dic = new Dictionary();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(bf.readLine());
            for (int i = 0; i < n; i++) {
                dic.addDic(bf.readLine());
            }
            System.out.println(dic.printDic());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Dictionary {
    PriorityQueue queue;

    public Dictionary() {
        this.queue = new PriorityQueue(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                int o1Len = ((String) o1).length();
                int o2Len = ((String) o2).length();
                if (o1Len < o2Len) {
                    return -1;
                } else if (o1Len > o2Len) {
                    return 1;
                }
                for (int i = 0; i < o1Len; i++) {
                    char o1Chr = ((String) o1).charAt(i);
                    char o2Chr = ((String) o2).charAt(i);
                    if (o1Chr < o2Chr)
                        return -1;
                    else if (o1Chr > o2Chr)
                        return 1;
                }
                return 0;
            }
        });
    }

    public void addDic(String sentece) {
        if (!queue.contains(sentece))
            queue.add(sentece);
    }

    public String printDic() {
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            sb.append(queue.poll());
            sb.append("\n");
        }
        return sb.toString();
    }
}