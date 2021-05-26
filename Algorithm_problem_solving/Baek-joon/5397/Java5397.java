import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// https://www.acmicpc.net/problem/5397
public class Main {
    public static void main(String[] args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int testNum;    // 테스트 케이스의 갯수
        StringBuilder sb = new StringBuilder();     // 출력을 저장
        try {
            testNum = Integer.parseInt(bf.readLine());
            KeyLoger keyLoger;      // 키로그를 만들어 주기 위한 객체
            String inputLine;

            for (int test = 0; test < testNum; test++) {
                inputLine = bf.readLine();
                keyLoger = new KeyLoger();
                sb.append(keyLoger.solution(inputLine));
                sb.append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print(sb.toString());
    }
}

class KeyLoger {
    /*
    커서의 움직임 별로 무엇을 입력했는지 출력하는 메서드
    */
    public String solution(String inputLine) {
        Stack<Character> cussorFront = new Stack<>();   // 커서의 앞 부분을 저장할 배열
        Stack<Character> cussorBack = new Stack<>();    // 커서의 뒷 부분을 저장할 배열

        char item;
        for (int i = 0; i < inputLine.length(); i++) {
            item = inputLine.charAt(i);
            if (item == '<') {
                if (!cussorFront.isEmpty()) {
                    cussorBack.push(cussorFront.pop());
                }
            } else if (item == '>') {
                if (!cussorBack.isEmpty()) {
                    cussorFront.push(cussorBack.pop());
                }
            } else if (item == '-') {
                if (!cussorFront.isEmpty())
                    cussorFront.pop();
            } else {
                cussorFront.push(item);
            }
        }

        // 출력하기 전에 커서를 가장 앞으로 옮긴다.
        while (!cussorFront.isEmpty()) {
            cussorBack.push(cussorFront.pop());
        }

        StringBuilder sb = new StringBuilder();
        while (!cussorBack.isEmpty()) {
            sb.append(cussorBack.pop());
        }
        return sb.toString();
    }
}