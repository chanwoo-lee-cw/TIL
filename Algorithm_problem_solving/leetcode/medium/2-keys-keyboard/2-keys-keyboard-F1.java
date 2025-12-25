import java.util.LinkedList;
import java.util.Queue;


class Clipboard {
    private int size;
    private int copyNum;
    private int actionCount;

    public Clipboard(int size, int copyNum, int actionCount) {
        this.size = size;
        this.copyNum = copyNum;
        this.actionCount = actionCount;
    }

    public int getSize() {
        return size;
    }

    public int getCopyNum() {
        return copyNum;
    }

    public int getActionCount() {
        return actionCount;
    }
}

class Solution {
    private Queue<Clipboard> queue = new LinkedList<>();

    public int minSteps(int n) {
        queue.add(new Clipboard(1, 0, 0));
        while (!queue.isEmpty()) {
            Clipboard curr = queue.poll();
            if (curr.getSize() == n) {
                return curr.getActionCount();
            }
            copyClipboard(curr);
            appendText(curr);
        }
        return 0;
    }


    private void copyClipboard(Clipboard curr) {
        queue.add(new Clipboard(curr.getSize(), curr.getSize(), curr.getActionCount() + 1));
    }

    private void appendText(Clipboard curr) {
        if (curr.getCopyNum() == 0) {
            return;
        }
        Clipboard newClipboard = new Clipboard(curr.getSize() + curr.getCopyNum(), curr.getCopyNum(), curr.getActionCount() + 1);
        queue.add(newClipboard);
    }
}