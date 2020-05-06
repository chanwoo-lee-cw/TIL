class Solution {
    public int[] solution(int[] heights) {
        int[] answer = {};
        return answer;
    }
}

class Stack<T> {
	final int STACK_MAX= 101;
	int top;
	T[] stack;
	
	@SuppressWarnings("unchecked")
	Stack() {
		top = -1;
		stack = (T[]) new Object[STACK_MAX];
	}
	
	public void push(T item) {
		if(top == 100) {
			System.out.println("스택이 가득참");
			return;
		}
		stack[++top] = item;
	}
	
	public T pop() {
		if(top == -1) {
			System.out.println("스택이 비었습니다.");
			return null;
		}
		return stack[top++];
	}
}

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution result = new Solution();
		
		
		int[] input = {6,9,5,7,4};
		
		System.out.println(result.solution(input));
	}

}