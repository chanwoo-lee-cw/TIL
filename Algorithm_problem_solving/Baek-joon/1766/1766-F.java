import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;


class Workbook implements Comparable<Workbook> {
    private int thisNumber;
    private int preNumber;

    public Workbook(int thisNumber, int preNumber) {
        this.thisNumber = thisNumber;
        this.preNumber = preNumber;
    }

    public int getThisNumber() {
        return thisNumber;
    }

    public void setPreNumber(int preNumber) {
        this.preNumber = preNumber;
    }

    @Override
    public int compareTo(Workbook o) {
        // 오름차순 this - other, 즉 -가 오름차순
        if (this.preNumber > 0 && this.preNumber == o.thisNumber) {
            return 1;
        } else if (o.preNumber > 0 && this.thisNumber == o.preNumber) {
            return -1;
        }
        return thisNumber - o.thisNumber;
    }
}

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        // n, m
        stringTokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());

        // quetionList
        Workbook[] workbooks = new Workbook[n + 1];

        for (int i = 1; i <= n; i++) {
            workbooks[i] = new Workbook(i, 0);
        }
        int thisNumber;
        int preNumber;
        for (int i = 1; i <= m; i++) {
            stringTokenizer = new StringTokenizer(reader.readLine());
            preNumber = Integer.parseInt(stringTokenizer.nextToken());
            thisNumber = Integer.parseInt(stringTokenizer.nextToken());
            workbooks[thisNumber].setPreNumber(preNumber);
        }

        PriorityQueue<Workbook> priorityQueue = new PriorityQueue<>();
        priorityQueue.addAll(Arrays.stream(workbooks).filter(it -> it != null).collect(Collectors.toList()));

        StringBuilder builder = new StringBuilder();
        while (!priorityQueue.isEmpty()) {
            builder.append(priorityQueue.poll().getThisNumber()).append(" ");
        }

        System.out.println(builder.toString());
    }
}