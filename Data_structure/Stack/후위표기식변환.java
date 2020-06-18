// https://www.acmicpc.net/problem/1918
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		String s = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			s = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
        // 입력받은 자료형을 결과를 반환받아 출력한다.
		List<Character> output = calulater(s);
		for (int i = 0; i < output.size(); i++) {
			System.out.print(output.get(i));
		}
	}

    // 연산자 우선순위 반환해주는 함수.
	public static int prec(char op) {
		switch (op) {
		case '(':
		case ')':
			return 0;
		case '+':
		case '-':
			return 1;
		case '*':
        // case 'x':
		case '/':
			return 2;
		}
		return -1;
	}

	public static ArrayList<Character> calulater(String expression) {
        // 후위 표기식을 저장할 리스트
		ArrayList<Character> postfix = new ArrayList<>();
        // 연산자 우선순위에 따라 연산자를 저장할 스택
		Stack<Character> stk = new Stack<>();

		for (int i = 0; i < expression.length(); i++) {
			char ch = expression.charAt(i);
			switch (ch) {
            // 연산자인 경우
			case '+':
			case '-':
			case '*':
            // case 'x':
			case '/':
            // 현재 들고 있는 연산자보다 스택의 최상단에 있는 연산자의 우선 순위가 더 크다면 
            // pop해서 후위 표기식 리스트에 집어넣는다.
				while (!stk.isEmpty() && prec(stk.peek()) >= prec(ch))
					postfix.add(stk.pop());
                // 다 뽑았다면 스택의 최상단에 집어 넣는다.
				stk.push(ch);
				break;
            // 왼쪽 괄호인 경우 그냥 바로 스택에 집어 넣는다.
			case '(':
				stk.push(ch);
				break;
            // 우측 연산자가 나온 경우
			case ')':
                // 스택에 있는 연산자를 왼쪽 괄호가 나올때까지 전부 뽑늗다.
				char e = stk.pop();
				while (e != '(') {
					postfix.add(e);
					e = stk.pop();
				}
				break;
            // 피연산자의 경우에는 바로 후위 연산자 리스트에 집어 넣는다.
			default:
				postfix.add(ch);
				break;
			}
		}
        // 중위 연산자가 끝났을 경우 스택에 남아 있는 나머지 연산자를 모두 꺼낸다.
		while (!stk.isEmpty()) {
			postfix.add(stk.pop());
		}
		return postfix;
	}
}