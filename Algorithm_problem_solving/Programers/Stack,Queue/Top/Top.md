# Programmers

## 스택, 큐

### 탑

- **문제 출처** : https://programmers.co.kr/learn/courses/30/lessons/42588



#### 풀이

모든 타워가 자신의 왼쪽 위 방향으로 신호를 쏘았을 때, 그 신호를 각각 무슨 타워가 받게 되는지에 대한 문제이다.

문제 카테고리가 스택이다 보니 스택으로 풀어보기 위해 고민을 해보았다.



일단, 타워를 자신과 인접한 타워 순서대로 찾기 위해서 현재 위치까지 타워를 순서대로 스택에 집어 넣는다.

```java
		for (int i = len - 1; i > 0; i--) {
			// 현재 탑의 바로 전까지 전부 스택에 주입
			while (stack.top < i - 1) {
				stack.push(heights[stack.top + 1]);
			}
            
			...
             
		}
```



현재 타워의 위치 바로 직전까지 타워가 전부 스택에 집어 넣어 졌으니, 자신보다 큰 탑이 나올때까지 스택에서 하나하나 검색한다.

```java
		for (int i = len - 1; i > 0; i--) {

            // 자신보다 큰 탑이 나올때까지 스택에서 하나하나 출력한다.
			int tower = 0;
			while (stack.top > -1 && (tower = stack.pop()) <= heights[i]) {}
            
			...
             
		}
```



그럼 원하는 타워가 나왔을 경우와 아닐 경우의 수가 나뉘게 되는데

1. 만약 현재 타워보다 큰 타워가 왼쪽에 존재한다면 타워에 넣는다.
2. 만약 현재 타워보다 큰 타워가 존재하지 않는다면 0을 집어 넣는다.

```java
		for (int i = len - 1; i > 0; i--) {
			
            ...
			
			// 만약 전부 뺐는데 없다면 0을 넣고 다음 탑 검색 시작
			if (stack.top == -1&& tower<=heights[i]) {
				sign.add(0);
				continue;
			}
			// 찾은 탑의 위치 넣는다.
			sign.add(stack.currPos());
		}
```



그리고 가장 왼쪽에 있는 타워라면 그냥 0을 집어 넣는다.

```java
sign.add(0);
```





#### 전체 코드

```java
import java.util.ArrayList;

//https://programmers.co.kr/learn/courses/30/lessons/42588

class Solution {

	class Stack<T> {
		// 스택 초기화
		// 위부터 최대 스택의 갯수, 현태 스택의 top 위치, 그리고 스택 배열
		final int STACK_MAX = 101;
		int top;
		T[] stack;
	
		// 초기화 하면서 안정성을 위해 object대신 제네릭으로 선언, 경고 메세지를 지우기 위해 annotaion 선언
		@SuppressWarnings("unchecked")
		Stack() {
			top = -1;
			stack = (T[]) new Object[STACK_MAX];
		}
	
		// 스택에 값 넣기
		public void push(T item) {
			if (top == 100) {
				System.out.println("스택이 가득참");
				return;
			}
			stack[++top] = item;
		}
		
		// 스택에서 값 빼고
		public T pop() {
			if (top == -1) {
				System.out.println("스택이 비었습니다.");
				return null;
			}
			return stack[top--];
		}
	
		// 탑의 위치를 반환한다. 하지만 스택을 -1씩 줄이고 하나 넣었을때 스택의 top이 0이므로 +2
		public int currPos() {
			return top + 2;
		}
	}

	public int[] solution(int[] heights) {
		// 찾은 탑을 넣어주기 위한, 어레이리스트
		ArrayList<Integer> sign = new ArrayList<>();
		// 탑의 갯수
		int len = heights.length;
		// 리턴될 배열
		int[] answer = new int[len];
		// 스택 선언
		Stack<Integer> stack = new Stack<>();
		
		// 마지막 탑부터 검색 
		for (int i = len - 1; i > 0; i--) {
			// 현재 탑의 바로 전까지 전부 스택에 주입
			while (stack.top < i - 1) {
				stack.push(heights[stack.top + 1]);
			}
			// 자신보다 큰 탑이 나올때까지 스택에서 하나하나 출력한다.
			int tower = 0;
			while (stack.top > -1 && (tower = stack.pop()) <= heights[i]) {}
			
			// 만약 전부 뺐는데 없다면 0을 넣고 다음 탑 검색 시작
			if (stack.top == -1&& tower<=heights[i]) {
				sign.add(0);
				continue;
			}
			// 찾은 탑의 위치 넣는다.
			sign.add(stack.currPos());
		}
		// 마지막 탑은 무조건 못 찾으므로 0
		sign.add(0);

		// 그리고 answer은 역순
		for (int i = 0; i < len; i++) {
			answer[i] = sign.get(len -i -1);
		}

		return answer;
	}
}
```

