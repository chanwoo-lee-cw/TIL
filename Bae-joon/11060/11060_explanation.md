## 백준 11060번 문제 풀이

#https://www.acmicpc.net/problem/11060

### 문제

*재환이가 1×N 크기의 미로에 갇혀있다. 미로는 1×1 크기의 칸으로 이루어져 있고, 각 칸에는 정수가 하나 쓰여 있다. i번째 칸에 쓰여 있는 수를 Ai라고 했을 때, 재환이는 Ai이하만큼 오른쪽으로 떨어진 칸으로 한 번에 점프할 수 있다. 예를 들어, 3번째 칸에 쓰여 있는 수가 3이면, 재환이는 4, 5, 6번 칸 중 하나로 점프할 수 있다.*

*재환이는 지금 미로의 가장 왼쪽 끝에 있고, 가장 오른쪽 끝으로 가려고 한다. 이때, 최소 몇 번 점프를 해야 갈 수 있는지 구하는 프로그램을 작성하시오. 만약, 가장 오른쪽 끝으로 갈 수 없는 경우에는 -1을 출력한다.*



### 입력

*첫째 줄에 N(1 ≤ N ≤ 1,000)이 주어진다. 둘째 줄에는 Ai (0 ≤ Ai ≤ 100)가 주어진다.*



### 출력

*재환이가 최소 몇 번 점프를 해야 가장 오른쪽 끝 칸으로 갈 수 있는지 출력한다. 만약, 가장 오른쪽 끝으로 갈 수 없는 경우에는 -1을 출력한다.*





### 풀이

다이나믹 프로그래밍의 가장 대중적인 스타일의 문제이다.



그래서 일단 가장 베이스 케이스 부터 세워보았다.

예제로 케이스를 직접 실험해 본 결과 시작점이 무엇인지 세어 보았는데 Ai[0]가 시작 점이라는 결과를 얻었다.

dp 배열의 첫 번째 자리인 dp[0]는 0

```java
dp[0] = 0;
```





그럼 할 수 있는 케이스 생각해보면

1. A[i+j]에 온 것이 처음이였을 때
2. A[i+j]에 오는 다른 방식이 있었을 때
3. A[i]로 오는 방법이 없었을 때
4. i+j로 끝에 도달하였을 때



그럼 2번을 기준으로 점화식을 세워보면

현재 서 있는 위치인 Ai[i]의 수에 이하까지 칸을 뛰어 넘는게 가능해진다.

```java
dp[i+j]=min(dp[i+j],dp[i]+1)	//j는 Ai[i]이하의 수
```



3번의 경우에는 만약 dp[i+j]로 오는 방법을 생각해 보면

처음에 배열을 -1로 초기화했다.

```java
Arrays.fill(dp, -1);	//c++의 경우엔 memset(dp,-1,arr.length)
```

만약 오는 경우가 있었을 경우 위에 2번의 점화식으로 -1 이 아닌 적어도 0 이상의 수로 덮어 씌워지게 되었을테고 -1로 그대로 남아 있는 자리는, 다음 Ai[i+1]의 경우를 고려해보면 된다.

현재 있는 Ai[i]의 자리에 오는 방법이 없다는 뜻이므로 예외 처리

```java
if (dp[i] == -1)
    continue;
```



4번 끝에 자리에 도달하였을 때, 그 이상의 수는 생각해 볼 경우 필요 없이 break해주면 된다.

```java
if (i+j >= n) 
    break;
```



### 전체 코드

```java
import java.util.Scanner;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = Integer.parseInt(in.nextLine());

		int[] ai = new int[n];
		int[] dp = new int[n];

		Arrays.fill(dp, -1);

		String[] aList = in.nextLine().split(" ");

		for (int i = 0; i < n; i++) {
			ai[i] = Integer.parseInt(aList[i]);
		}

		dp[0] = 0;

		for (int i = 0; i < n - 1; i++) {
			if (dp[i] == -1)
				continue;
			for (int j = 1; j <= ai[i]; j++) {
				if (i + j >= n)
					break;
				if (dp[i + j] == -1)
					dp[i + j] = dp[i] + 1;
				else {
					dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
				}

			}
		}
		System.out.print(dp[n - 1]);
	}

}
```

