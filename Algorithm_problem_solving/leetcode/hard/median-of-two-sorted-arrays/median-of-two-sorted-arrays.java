import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        Median median = new Median();

        median.addArrToQueue(nums1);
        median.addArrToQueue(nums2);

        return median.getMedian();
    }
}

class Median {
    PriorityQueue<Integer> leftQueue;
    PriorityQueue<Integer> rightQueue;

    Median() {
        this.leftQueue = new PriorityQueue<Integer>(Collections.reverseOrder());
        this.rightQueue = new PriorityQueue<Integer>();
    }

    private void addHeap(int number) {
        if (leftQueue.isEmpty()) {
            leftQueue.add(number);
        } else if (leftQueue.peek() >= number) {
            leftQueue.add(number);
        } else {
            rightQueue.add(number);
        }
    }

    private void blanceTwoHeap() {
        int leftSize = leftQueue.size();
        int rightSize = rightQueue.size();

        if (leftSize == rightSize) {
            // do nothing
        } else if (leftSize < rightSize + 1) {
            leftQueue.add(rightQueue.remove());
        } else if (leftSize > rightSize + 1) {
            rightQueue.add(leftQueue.remove());
        }
    }

    public void addArrToQueue(int[] numArr) {
        for (int number : numArr) {
            addHeap(number);
            blanceTwoHeap();
        }
    }

    public double getMedian() {
        int leftSize = leftQueue.size();
        int rightSize = rightQueue.size();

        int fullSize = leftSize + rightSize;

        if (fullSize % 2 == 1) {
            // 짝수인 경우
            return leftQueue.peek();
        } else {
            return (double) (leftQueue.peek() + rightQueue.peek()) / 2;
        }
    }
}