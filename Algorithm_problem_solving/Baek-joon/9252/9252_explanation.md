## 백준 9252번 풀이

https://www.acmicpc.net/problem/9252

### 문제

*LCS(Longest Common Subsequence, 최장 공통 부분 수열)문제는 두 수열이 주어졌을 때, 모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제이다.*

*예를 들어, ACAYKP와 CAPCAK의 LCS는 ACAK가 된다.*



### 입력

*첫째 줄과 둘째 줄에 두 문자열이 주어진다. 문자열은 알파벳 대문자로만 이루어져 있으며, 최대 1000글자로 이루어져 있다.*

### 출력

*첫째 줄에 입력으로 주어진 두 문자열의 LCS의 길이를, 둘째 줄에 LCS를 출력한다.*

*LCS가 여러 가지인 경우에는 아무거나 출력한다.*

***



### 풀이

LCS문제,

9251번 문제의 계속이다.



일단 1단계로 LCS의 최대 길이, 그리고 LCS표가 필요하다.

그것은 9251번에서 했으니 그 것을 그대로 가져오도록 한다.

| 0    | 0    | A    | C    | A    | Y    | K    | P    |
| :--- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| 0    | 0    | 0    | 0    | 0    | 0    | 0    | 0    |
| C    | 0    | 0    | 1    | 1    | 1    | 1    | 1    |
| A    | 0    | 1    | 1    | 2    | 2    | 2    | 2    |
| P    | 0    | 1    | 1    | 2    | 2    | 2    | 3    |
| C    | 0    | 1    | 2    | 2    | 2    | 2    | 3    |
| A    | 0    | 1    | 2    | 3    | 3    | 3    | 3    |
| K    | 0    | 1    | 2    | 3    | 3    | 4    | 4    |

이 표를 그대로 사용하면 되는데 표의 오른쪽 아래의 4가 LCS라는 것을 알 수가 있다.

```java
if (c1 == c2) {
	dp[i][j] = dp[i - 1][j - 1] + 1;
} else {
	dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
}
```

이 공식을 기억하고 있으면 쉬운데, dp\[i][j]가 바뀌는 기준이 글자가 같을때 대각선 아래로 내려갔고, 한쪽이 크다면 더 큰쪽의 숫자를 넣는 것을 반복했다.

그렇다면, 이것을 역순으로 따라가면 된다

LCS가 4인 부분에서 작은 부분으로 따라 가면 된다.



일단 왼쪽 아래의 4인 부분에서 왼쪽이 4로 현재 위치와 같다는 것을 알 수 있다.

즉, 현재 위치의 4는 왼쪽에서 받아온 것이므로 그대로 왼쪽으로 간다.



이번에 위치는 4의 위치는 양측이 모두 같으므로 이번엔 내려왔던 부분을 기억하고 왼쪽 대각선 위로 간다.

이의 과정을 반복하면 이런 그림이 된다.

![그림1](./그림1.png)

즉 0이 될때까지 반복하면 된다.



그리고 기억해 둔 것을 꺼내는 것을 역순으로 밟아오는 것이므로 스택을 사용하면 된다.

```java
Stack<Character> stack = new Stack<>();

int i = str1.length();
int j = str2.length();

while (true) {
	if (dp[i][j] == 0)
		break;
	if (dp[i][j] > dp[i][j - 1] && dp[i][j] > dp[i - 1][j]) {
		stack.push(str2.charAt(j - 1));
		i--;
		j--;
	} else if (dp[i][j] > dp[i][j - 1])
		i--;
	else if (dp[i][j] > dp[i - 1][j])
		j--;
	else
		i--;
}
```



하지만 스택에서 꺼낼 때 주의할 점이 하나 있다.

```java
for (int k = 0; k < stack.size(); k++) {
	System.out.print(stack.pop());
}
```

위와 같이 하게 되면 스택에 저장 된 것이 전부 나오지 않게 되는데

stack에서 pop을 할 수록 스택 사이즈가 줄어 들기 때문이다.





### 전체 코드

```java
import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String str1 = sc.nextLine();
		String str2 = sc.nextLine();

		sc.close();

		int[][] dp 
            = new int[str1.length() + 1][str2.length() + 1];

		for (int i = 1; i <= str1.length(); i++) {
			char c1 = str1.charAt(i - 1);
			for (int j = 1; j <= str2.length(); j++) {
				char c2 = str2.charAt(j - 1);
				if (c1 == c2) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] 
                        = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		Stack<Character> stack = new Stack<>();

		int i = str1.length();
		int j = str2.length();

		while (true) {
			if (dp[i][j] == 0)
				break;
			if (dp[i][j] > dp[i][j - 1] 
                && dp[i][j] > dp[i - 1][j]) {
				stack.push(str2.charAt(j - 1));
				i--;
				j--;
			} else if (dp[i][j] > dp[i][j - 1])
				i--;
			else if (dp[i][j] > dp[i - 1][j])
				j--;
			else
				i--;
		}
		
		int alpha = stack.size();
		
		System.out.println(alpha);

		for (int k = 0; k < alpha; k++) {
			System.out.print(stack.pop());
		}
	}
}
```



### 주의점

1. stack.size()를 기준으로 반복문을 돌리려면 아예 따로 변수에 시작 사이즈를 입력해 놓고 해야한다.

   -> 스택 사이즈가 줄어들기 때문이다.