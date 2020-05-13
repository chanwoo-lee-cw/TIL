## 백준 10872번 풀이

https://www.acmicpc.net/problem/10872

### 문제

*0보다 크거나 같은 정수 N이 주어진다. 이때, N!을 출력하는 프로그램을 작성하시오.*



### 입력

*첫째 줄에 정수 N(0 ≤ N ≤ 12)가 주어진다.*



### 출력

*첫째 줄에 N!을 출력한다.*

***

### 풀이

팩토리얼 문제이다.

그냥 무난하게 재귀나 반복문의 형태로 출력하면 되지만, 한번 정리를 해본 이유는

면접에서 팩토리얼 구하는 방법을 물어봤는데,



기억이 안 나서 팩토리얼을 빨리 구하는 방식이 있는지, 기억이 안나서 재귀로 풀게 되어서 정리하게 되었다.



**결론 : **

1. **팩토리얼은 그냥 재귀나 반복문으로 구하자**
2. **0!도 1**이다.



2개를 정리하고 싶어서 기록하게 되었다.




### 전체 코드

```java
import java.io.*;

// https://www.acmicpc.net/problem/10872

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(bf.readLine());

		System.out.print(factorial(n));
	}

// 	재귀의 형식으로 계산한다.
	public static int factorial(int n) {
//		System.out.println(n);
//		0!은 1이다.
		if (n <= 1)
			return 1;
		return n * factorial(n - 1);
	}

}
```

