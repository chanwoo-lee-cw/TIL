import java.util.*;

class Solution {
    public int[] queryResults(int limit, int[][] queries) {
        int[] ballToColor = new int[limit+1];
        Map<Integer, Integer> colorToBallCnt = new HashMap<>();
        LinkedList<Integer> answer = new LinkedList<>();

        for (int[] query : queries) {
            int ballNum = query[0];
            int colorNum = query[1];

            int preBallColor = ballToColor[ballNum];
            if (colorToBallCnt.containsKey(preBallColor)) {
                colorToBallCnt.put(preBallColor, colorToBallCnt.get(preBallColor) - 1);
                if (colorToBallCnt.get(preBallColor) == 0) {
                    colorToBallCnt.remove(preBallColor);
                }
            }
            ballToColor[ballNum] = colorNum;
            if (!colorToBallCnt.containsKey(colorNum)) {
                colorToBallCnt.put(colorNum, 0);
            }
            colorToBallCnt.put(colorNum, colorToBallCnt.get(colorNum) + 1);
            answer.add(colorToBallCnt.size());
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
