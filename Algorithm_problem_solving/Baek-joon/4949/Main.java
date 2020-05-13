import java.io.*;
import java.util.Stack;

// https://www.acmicpc.net/problem/4949

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String str = bf.readLine();

			if (str.equals("."))
				break;

            //  입력 받은 결과값에 따라 무슨 값을 출력할지 정한다.
			String sprint = balance(str) ? "yes" : "no";

			System.out.println(sprint);
		}

	}

	public static boolean balance(String str) {
        // 괄호 카운트를 위한 스택 선언
		Stack<Character> stack = new Stack<>();

        int strsize = str.length();
        
        // 문자열에 길이에 만큼 전부 검사한다.
		for (int i = 0; i < strsize; i++) {
            char word = str.charAt(i);
            
            // 괄호의 시작이라면 스택에 집어 넣는다.
			if (word == '(') {
				stack.push(word);
            } 
            else if (word == '[') {
				stack.push(word);
            }
            
            // 괄호의 끝이라면 스택에서 빼낸다.
            // 만약 빈 괄호인지 확인하기 위해서 스택이 비어있는지 검사하는 것을 먼저 선언
            else if (word == ')') {
				if (stack.isEmpty() || stack.pop() != '(')
					return false;
            } 
            else if (word == ']') {
				if (stack.isEmpty() || stack.pop() != '[')
					return false;
			}

		}
        
        // 마지막에도 스택에 있는 모든 내용을 다 빼냈는지 검사한다.
		return stack.isEmpty() ? true : false;
	}

}