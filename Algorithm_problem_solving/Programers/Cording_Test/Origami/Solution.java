import java.util.ArrayList;

class Solution {
    public int[] solution(int n) {
        ArrayList<Integer> origami = new ArrayList<Integer>();
        origami.add(0);
        int[] answer = null;
        int len = 0;

        for (int i = 1; i < n; i++) {
            len = origami.size();
            origami.add(0);
            for (int j = 0; j < len; j++) {
                origami.add( (origami.get(len-1-j) == 0 ? 1 : 0) );

            }
        }
        answer = new int[origami.size()];
        for (int i = 0; i < origami.size(); i++) {
            answer[i] = origami.get(i);
        }

        return answer;
    }
}