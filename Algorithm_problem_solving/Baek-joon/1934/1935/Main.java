// https://www.acmicpc.net/problem/1935
import java.io.*;
import java.util.*;

public class Main {
    // 각 알파벳의 숫자를 저장할 해시맵
	static HashMap<Character, Integer> map = null;

	public static void main(String[] args) {
		int n = 0;
		String postfix = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		map = new HashMap<Character, Integer>();
        // 입력받은 것을 저장.
		try {
			n = Integer.parseInt(in.readLine());
			postfix = in.readLine();
			char alpha = 'A';
			for (int i = 0; i < n; i++) {
				map.put(alpha, Integer.parseInt(in.readLine()));
				alpha++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(String.format("%.2f", eval(postfix)));
	}
    // 후위표기식을 계산하는 함수
	public static double eval(String postfix) {
		double op1, op2, value;
		int fixlen = postfix.length();
		char ch;
        // 숫자를 저장할 스택
		Stack<Double> stack = new Stack<>();

		for (int i = 0; i < fixlen; i++) {
			ch = postfix.charAt(i);
            // 피연산자라면 스택에 집어 넣는다.
			if (ch != '+' && ch != '-' && ch != '*' && ch != '/') {
				value = map.get(ch);
				stack.push(value);
			} 
            // 연산자라면 스택에서 스택 최상단의 숫자 2개를 팝하여 연산하고 다시 스택에 집어 넣는다.
            else {
				op2 = stack.pop();
				op1 = stack.pop();
				switch (ch) {
				case '+':
					stack.push(op1 + op2);
					break;
				case '-':
					stack.push(op1 - op2);
					break;
				case '*':
					stack.push(op1 * op2);
					break;
				case '/':
					stack.push(op1 / op2);
					break;
				}
			}
		}
        // 마지막으로 남은 값 리턴
		return stack.pop();
	}
}