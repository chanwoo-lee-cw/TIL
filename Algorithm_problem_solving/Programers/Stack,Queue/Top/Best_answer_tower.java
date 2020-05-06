import java.util.*;

class BestSolution {
    // 타워의 위치와 높이를 저장할 클래스
    class Tower {
        int idx;
        int height;

        public Tower(int idx, int height) {
            this.idx = idx;
            this.height = height;
        }

        @Override
        public String toString() {
            return "idx : " + idx + " height : " + height;
        }
    }

    // 답
    public int[] solution(int[] heights) {
        Stack<Tower> st = new Stack<>();
        int[] answer = new int[heights.length];

        // 타워의 갯수만큼 반복
        for (int i = 0; i < heights.length; i++) {
            // 타워 클래스의 현재 신호를 주는 타워의 위치와 신호 곰색.
            Tower tower = new Tower(i + 1, heights[i]);
            int receiveIdx = 0;

            // 스택이 빌 때까지 찾는다.
            while (!st.isEmpty()) {
                Tower top = st.peek();

                // 신호를 보내는 타워보다 큰 타워를 찾는다면 리턴
                if (top.height > tower.height) {
                    receiveIdx = top.idx;
                    break;
                }

                st.pop();
            }

            st.push(tower);
            answer[i] = receiveIdx;
        }

        return answer;
    }
}
