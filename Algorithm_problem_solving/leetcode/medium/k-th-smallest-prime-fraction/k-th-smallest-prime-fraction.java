import java.util.Objects;
import java.util.PriorityQueue;

class NumberValue implements Comparable<NumberValue> {
    private int[] numbers;
    private Double value;

    public NumberValue(int head, int tail) {
        this.numbers = new int[]{head, tail};
        this.value = (double) head / tail;
    }


    @Override
    public int compareTo(NumberValue o) {
        return this.value.compareTo(o.value);
    }

    public int[] getNumbers() {
        return numbers;
    }
}

class Solution {
    PriorityQueue<NumberValue> priorityQueue = new PriorityQueue<>();

    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int arrLength = arr.length;

        for (int i = 0; i < arrLength; i++) {
            for (int j = i + 1; j < arrLength; j++) {
                priorityQueue.add(new NumberValue(arr[i], arr[j]));
            }
        }

        for (int i = 0; i < k - 1; i++) {
            priorityQueue.poll();
        }

        return Objects.requireNonNull(priorityQueue.poll()).getNumbers();
    }
}