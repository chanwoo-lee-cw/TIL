import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n;
        Node[] nodes;
        Solution solution;

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        try {
            n = Integer.parseInt(bf.readLine());
            nodes = new Node[n + 1];
            for (int i = 1; i < n + 1; i++) {
                nodes[i] = new Node();
            }
            {
                int parents, chile, weight;
                for (int i = 0; i < n - 1; i++) {
                    st = new StringTokenizer(bf.readLine());
                    parents = Integer.parseInt(st.nextToken());
                    chile = Integer.parseInt(st.nextToken());
                    weight = Integer.parseInt(st.nextToken());
                    nodes[parents].addChild(chile, weight);
                }
            }
            solution = new Solution(nodes);
            solution.solution(nodes[1]);
            System.out.println(solution.getMaxLegs());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class Solution {
    private int maxLegs;
    Node[] nodes;

    public Solution(Node[] nodes) {
        this.maxLegs = 0;
        this.nodes = nodes;
    }

    public int solution(Node node) {
        if (node.getChild().size() == 0)
            return 0;
        ArrayList<Integer> legs = new ArrayList<>();
        for (int[] item : node.getChild()) {
            legs.add(solution(nodes[item[0]]) + item[1]);
        }
        legs.sort(Comparator.reverseOrder());
        if (legs.size() >= 2)
            maxLegs = Math.max(maxLegs, legs.get(0) + legs.get(1));
        else
            maxLegs = Math.max(maxLegs, legs.get(0));
        return legs.get(0);
    }

    public int getMaxLegs() {
        return maxLegs;
    }
}

class Node {
    private ArrayList<int[]> child;

    public Node() {
        this.child = new ArrayList<>();
    }

    public void addChild(int chile, int weight) {
        this.child.add(new int[]{chile, weight});
    }

    public ArrayList<int[]> getChild() {
        return child;
    }
}