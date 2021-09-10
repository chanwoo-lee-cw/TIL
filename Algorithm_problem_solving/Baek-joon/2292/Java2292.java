import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int N;
        Solution solution;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            N = Integer.parseInt(bf.readLine());
            solution = new Solution(N);
            System.out.println(solution.getNSize());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Solution {
    private int n;

    public Solution(int n) {
        this.n = n;
    }

    public int getNSize() {
        int need = 1;
        int combSize = 1;
        while (combSize < n) {
            combSize += 6 * need;
            need++;
        }
        return need;
    }
}
