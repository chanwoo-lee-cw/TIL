import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n;
        int[] solutions;
        Solution solution;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;

            n = Integer.parseInt(bf.readLine());
            solutions = new int[n];
            st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < n; i++) {
                solutions[i] = Integer.parseInt(st.nextToken());
            }
            solution = new Solution(n, solutions);
            int[] answer = solution.getSolutions();
            System.out.printf("%d %d", answer[0], answer[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Solution {
    private int n;
    private int[] solution;

    public Solution(int n, int[] solution) {
        this.n = n;
        this.solution = solution;
        Arrays.sort(this.solution);
    }

    public int[] getSolutions() {
        int left = 0;
        int right = n - 1;
        int[] answer = new int[]{solution[left], solution[right]};

        int nearZero = Integer.MAX_VALUE;
        int sumSolution;
        while (left < right) {
            sumSolution = solution[left] + solution[right];
            if (Math.abs(sumSolution) < Math.abs(nearZero)) {
                nearZero = sumSolution;
                answer[0] = solution[left];
                answer[1] = solution[right];
            }
            if (sumSolution > 0) {
                right--;
            } else {
                left++;
            }
        }
        return answer;
    }
}