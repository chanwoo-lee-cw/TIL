import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        int n, k;
        AttendentsList attendentsList;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            attendentsList = new AttendentsList(n, k);
            while (true) {
                st = new StringTokenizer(bf.readLine());
                int classes = Integer.parseInt(st.nextToken());
                String name = st.nextToken();
                if (classes == 0 && name.equals("0")) {
                    break;
                }
                attendentsList.addAttendts(classes, name);
            }
            attendentsList.printAttendent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class AttendentsList {
    private int n;
    private int k;
    private PriorityQueue<Attendents> queue;
    private int[] classAtd;

    public AttendentsList(int n, int k) {
        this.n = n;
        this.k = k;
        this.queue = new PriorityQueue<>(((o1, o2) -> {
            int o1Team = o1.getClasses() % 2;
            int o2Team = o2.getClasses() % 2;
            if (o1Team < o2Team)
                return 1;
            else if (o1Team > o2Team)
                return -1;
            // else
            if (o1.getClasses() > o2.getClasses())
                return 1;
            else if (o1.getClasses() < o2.getClasses())
                return -1;
            // else
            if (o1.getName().length() < o2.getName().length())
                return -1;
            else if (o1.getName().length() > o2.getName().length())
                return 1;
            return o1.getName().compareTo(o2.getName());
        }));
        this.classAtd = new int[n + 1];
    }

    public void addAttendts(int classes, String name) {
        if (this.classAtd[classes] >= k) {
            return;
        }
        if (queue.size() >= 500) {
            return;
        }
        Attendents attendents = new Attendents(classes, name);
        this.classAtd[classes]++;
        this.queue.add(attendents);
    }

    public void printAttendent() {
        while (!queue.isEmpty()) {
            Attendents a = queue.poll();
            System.out.printf("%d %s\n", a.getClasses(), a.getName());
        }
    }
}

class Attendents {
    private int classes;
    private String name;

    public Attendents(int classes, String name) {
        this.classes = classes;
        this.name = name;
    }

    public int getClasses() {
        return classes;
    }

    public String getName() {
        return name;
    }
}