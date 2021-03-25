import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        BiranryTree bt;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            bt = new BiranryTree(bf);
            bt.inputNode(bf);
            System.out.println(bt.preorder());
            System.out.println(bt.inorder());
            System.out.println(bt.postorder());
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class BiranryTree {
    private int n;      // 노드의 갯수
    Node[] nodeList;    // 각 알파벳 별로 저장된 노드

    public BiranryTree(BufferedReader bf) throws IOException {
        this.n = Integer.parseInt(bf.readLine());
        this.nodeList = new Node[n];
        for (int i = 0; i < n; i++)
            nodeList[i] = new Node((char) (i + 'A'));
    }

    /*
    각 노드를 연결한다.
     */
    public void inputNode(BufferedReader bf) throws IOException {
        StringTokenizer st;
        char curr, next;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            curr = st.nextToken().charAt(0);
            next = st.nextToken().charAt(0);
            if (next != '.') {
                nodeList[curr - 'A'].setLeft(nodeList[next - 'A']);
            }
            next = st.nextToken().charAt(0);
            if (next != '.') {
                nodeList[curr - 'A'].setRight(nodeList[next - 'A']);
            }
        }
    }

    /*
    루트 노드부터 전위 순회
     */
    public String preorder() {
        StringBuilder sb = new StringBuilder();
        nodeList[0].preorder(sb);
        return sb.toString();
    }

    /*
    루트 노드부터 중위 순회
     */
    public String inorder() {
        StringBuilder sb = new StringBuilder();
        nodeList[0].inorder(sb);
        return sb.toString();
    }

    /*
    루트 노드부터 후위 순회
     */
    public String postorder() {
        StringBuilder sb = new StringBuilder();
        nodeList[0].posorder(sb);
        return sb.toString();
    }


}

class Node {
    private char alphabet;      // 현재 노드에 저장된 알파벳
    private Node left;          // 현재 노드의 왼쪽에 부착된 노드
    private Node right;         // 현재 노드의 오른쪽에 부착된 노

    public Node(char alphabet) {
        this.alphabet = alphabet;
    }

    public char getAlphabet() {
        return alphabet;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    /*
    전위순회
     */
    public void preorder(StringBuilder sb) {
        sb.append(alphabet);
        if (left != null)
            left.preorder(sb);
        if (right != null)
            right.preorder(sb);
    }

    /*
    중위 순회
     */
    public void inorder(StringBuilder sb) {
        if (left != null)
            left.inorder(sb);
        sb.append(alphabet);
        if (right != null)
            right.inorder(sb);
    }

    /*
    후위순회
     */
    public void posorder(StringBuilder sb) {
        if (left != null)
            left.posorder(sb);
        if (right != null)
            right.posorder(sb);
        sb.append(alphabet);
    }
}