import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());

        Solution.findBrother(n, k);
    }
}

class Solution {


    public static void findBrother(int n, int k) {
        HashMap<Integer, Integer> visited = new HashMap<>();
        Deque<Position> queue = new ArrayDeque<>();

        queue.add(
                new Position(n, 0)
        );
        visited.put(n, true);
        int answer = 0;
        int moveMin = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Position currPosition = queue.poll();
            if (currPosition.getMoveCount() > moveMin) {
                break;
            } else if (currPosition.getCurrPosition() == k) {
                answer++;
                moveMin = currPosition.getMoveCount();
            }

            moveFront(queue, currPosition, visited);
            moveBack(queue, currPosition, visited);
            moveTeleport(queue, currPosition, visited);
        }
        System.out.println(
                moveMin + "\n" + answer
        );
    }

    public static boolean isVisitedPosition(int position, HashMap<Integer, Integer> visited) {
        return visited.containsKey(position);
    }

    public static void updateVisited(int position, HashMap<Integer, Integer> visited) {
        if (!visited.containsKey(position)) {
            visited.put(position, 0);
        }
        visited.put(position, visited.get(position) + 1);
    }


    public static void moveFront(
            Deque<Position> queue,
            Position currPosition,
            HashMap<Integer, Integer> visited
    ) {
        int next = currPosition.getCurrPosition() + 1;
        if (isVisitedPosition(next, visited)) return;
        queue.add(
                new Position(next, currPosition.getMoveCount() + 1)
        );
        updateVisited(next, visited);
    }

    public static void moveBack(
            Deque<Position> queue,
            Position currPosition,
            HashMap<Integer, Integer> visited
    ) {
        int next = currPosition.getCurrPosition() - 1;
        if (isVisitedPosition(next, visited)) return;
        queue.add(
                new Position(next, currPosition.getMoveCount() + 1)
        );
        updateVisited(next, visited);
    }

    public static void moveTeleport(
            Deque<Position> queue,
            Position currPosition,
            HashMap<Integer, Integer> visited
    ) {
        int next = currPosition.getCurrPosition() * 2;
        if (isVisitedPosition(next, visited)) return;
        queue.add(
                new Position(next, currPosition.getMoveCount() + 1)
        );
        updateVisited(next, visited);
    }


}

class Position {
    private int currPosition;
    private int moveCount;

    public Position(int currPosition, int moveCount) {
        this.currPosition = currPosition;
        this.moveCount = moveCount;
    }

    public int getCurrPosition() {
        return currPosition;
    }

    public int getMoveCount() {
        return moveCount;
    }
}