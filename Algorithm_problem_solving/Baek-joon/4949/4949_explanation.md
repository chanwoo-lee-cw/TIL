## 백준 4949번 풀이

https://www.acmicpc.net/problem/4949

### 문제

*세계는 균형이 잘 잡혀있어야 한다. 양과 음, 빛과 어둠 그리고 왼쪽 괄호와 오른쪽 괄호처럼 말이다.*

*정민이의 임무는 어떤 문자열이 주어졌을 때, 괄호들의 균형이 잘 맞춰져 있는지 판단하는 프로그램을 짜는 것이다.*

*문자열에 포함되는 괄호는 소괄호("()") 와 대괄호("[]")로 2종류이고, 문자열이 균형을 이루는 조건은 아래와 같다.*

- *모든 왼쪽 소괄호("(")는 오른쪽 소괄호(")")와만 짝을 이뤄야 한다.*
- *모든 왼쪽 대괄호("[")는 오른쪽 대괄호("]")와만 짝을 이뤄야 한다.*
- *모든 오른쪽 괄호들은 자신과 짝을 이룰 수 있는 왼쪽 괄호가 존재한다.*
- *모든 괄호들의 짝은 1:1 매칭만 가능하다. 즉, 괄호 하나가 둘 이상의 괄호와 짝지어지지 않는다.*
- *짝을 이루는 두 괄호가 있을 때, 그 사이에 있는 문자열도 균형이 잡혀야 한다.*

*정민이를 도와 문자열이 주어졌을 때 균형잡힌 문자열인지 아닌지를 판단해보자.*

### 입력

*하나 또는 여러줄에 걸쳐서 문자열이 주어진다. 각 문자열은 영문 알파벳, 공백, 소괄호("( )") 대괄호("[ ]")등으로 이루어져 있으며, 길이는 100글자보다 작거나 같다.*

*입력의 종료조건으로 맨 마지막에 점 하나(".")가 들어온다.*

### 출력

*각 줄마다 해당 문자열이 균형을 이루고 있으면 "yes"를, 아니면 "no"를 출력한다.*

***

### 풀이

괄호를 세는 문제.

대표적인 스택으로 해결하는 문제이다.



문제 해결 방식은 간단한데

만약 해당 위치의 문자열이 `(`,`[`라면 스택에 넣고



`)`,`]`라면 스택에서 팝하고 쌍이 맞는지 검사하고 틀리다면 더이상 검사할 필요 없이 false를 리턴한다.



`if (stack.isEmpty() || stack.pop() != '[')`

그리고 스택에서 pop하기 전에 스택이 비어있는지 먼저 확인한다.



그리고 모두 끝난 후에도 모든 쌍이 다 맞았는지 알아내기 위해 스택이 비어있는지 확인한다.

`return stack.isEmpty() ? true : false;`



### 전체 코드

```java
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
```



