import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        Midian midian = new Midian();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            midian.pushHeap(
                    Integer.parseInt(bufferedReader.readLine())
            );
            stringBuilder.append(midian.getMedian()).append("\n");
        }

        System.out.println(stringBuilder.toString());

    }
}

class Midian {
    private int fullSize = 0;
    private final PriorityQueue<Integer> halfLeftHeap = new PriorityQueue<>(Collections.reverseOrder());
    private final PriorityQueue<Integer> halfRightHeap = new PriorityQueue<>();

    public void pushHeap(int value) {
        if (halfLeftHeap.isEmpty()) {
            halfLeftHeap.add(value);
        } else if (halfLeftHeap.peek() >= value) {
            halfLeftHeap.add(value);
        } else {
            halfRightHeap.add(value);
        }
        fullSize++;

        if (fullSize % 2 == 0) {
            checkAndMoveValue(halfLeftHeap.size(), halfRightHeap.size());
        } else {
            checkAndMoveValue(halfLeftHeap.size() - 1, halfRightHeap.size());
        }
    }

    private void checkAndMoveValue(
            int leftSize,
            int rightSize
    ) {
        if (leftSize > rightSize) {
            halfRightHeap.add(halfLeftHeap.poll());
        } else if (leftSize < rightSize) {
            halfLeftHeap.add(halfRightHeap.poll());
        }
    }

    public int getMedian() {
        if (halfLeftHeap.isEmpty()) {
            throw new EmptyStackException();
        }
        return halfLeftHeap.peek();
    }

}