## 백준 1495번 풀이

https://www.acmicpc.net/problem/1495

### 문제

[*Day Of Mourning](https://www.facebook.com/dayofmourningmetal)의 기타리스트 강토는 다가오는 공연에서 연주할 N개의 곡을 연주하고 있다. 지금까지 공연과는 다른 공연을 보여주기 위해서 이번 공연에서는 매번 곡이 시작하기 전에 볼륨을 바꾸고 연주하려고 한다.*

*먼저, 공연이 시작하기 전에 각각의 곡이 시작하기 전에 바꿀 수 있는 볼륨의 리스트를 만들었다. 이 리스트를 V라고 했을 때, V[i]는 i번째 곡을 연주하기 전에 바꿀 수 있는 볼륨을 의미한다. 항상 리스트에 적힌 차이로만 볼륨을 바꿀 수 있다. 즉, 현재 볼륨이 P이고 지금 i번째 곡을 연주하기 전이라면, i번 곡은 P+V[i]나 P-V[i] 로 연주해야 한다. 하지만, 0보다 작은 값으로 볼륨을 바꾸거나, M보다 큰 값으로 볼륨을 바꿀 수 없다.*

*곡의 개수 N과 시작 볼륨 S, 그리고 M이 주어졌을 때, 마지막 곡을 연주할 수 있는 볼륨 중 최댓값을 구하는 프로그램을 작성하시오. 모든 곡은 리스트에 적힌 순서대로 연주해야 한다.*

### 입력

*첫째 줄에 N, S, M이 주어진다. (1 ≤ N ≤ 100, 1 ≤ M ≤ 1000, 0 ≤ S ≤ M) 둘째 줄에는 각 곡이 시작하기 전에 줄 수 있는 볼륨의 차이가 주어진다. 이 값은 1보다 크거나 같고, M보다 작거나 같다.*

### 출력

*첫째 줄에 가능한 마지막 곡의 볼륨 중 최댓값을 출력한다. 만약 마지막 곡을 연주할 수 없다면 (중간에 볼륨 조절을 할 수 없다면) -1을 출력한다.*

***

### 풀이

다이나믹 프로그래밍 문제이다

각각 상황별로 볼륨이 있을 수 있는 경우의 수를 모두 구해야 한다.



예제를 그대로 구현하면 이런 형태가 된다.

![그림1](./그림1.png)

그래서 일단 할 수 있는 모든 경우의 수로 이진 배열로 만들었다.

```java
int[][] dp = new int[n + 1][maximum + 1];
```

즉, 모든 배열이 전부 0으로 초기화 되어있다.



일단, 시작점을 초기화한다.

```java
dp[0][start] = 1;
```



그리고 입력된 만큼 갈 수 있는 경우의 수를 전부 센다.

```java
	for (int i = 1; i <= n; i++) {
		int count = 0;
		for (int j = 0; j <= maximum; j++) {
			if (dp[i - 1][j] != 0) {
				if (j + volum[i] <= maximum) {
					dp[i][j + volum[i]] = 1;
					count++;
				}
				if (j - volum[i] >= 0) {
					dp[i][j - volum[i]] = 1;
					count++;
				}
			}
		}
		if (count == 0) {
			System.out.println("-1");
		}
	}
```


만약, 전 단계에서 0이 아닌 수라면 다음 경우의 수를 찾아간다.

```java
if (dp[i - 1][j] != 0) {
	if (j + volum[i] <= maximum) {
		dp[i][j + volum[i]] = 1;
		count++;
	}
	if (j - volum[i] >= 0) {
		dp[i][j - volum[i]] = 1;
		count++;
	}
}
```

하지만 이런 경우는 에러가 생겼다.

왜나면 `count++` 누적이 점점 쌓여 가면서 많은 경우가 달라진다.



즉, 횟수를 셀 필요 없이 단순히 여기까지 왔나 아니냐만 체크하면 된다.

int 대신 boolean으로 변경 하니 성공했다.



### 전체 코드

```java
import java.util.Scanner;

public class Main {
	public static void main(String args[]) {

		int n, start, maximum;

		Scanner in = new Scanner(System.in);
		String arr[] = in.nextLine().split(" ");

		n = Integer.parseInt(arr[0]);
		start = Integer.parseInt(arr[1]);
		maximum = Integer.parseInt(arr[2]);

		int[] volum = new int[n + 1];

		arr = in.nextLine().split(" ");
		for (int i = 1; i <= n; i++) {
			volum[i] = Integer.parseInt(arr[i - 1]);
		}

		boolean[][] dp = new boolean[n + 1][maximum + 1];

		dp[0][start] = true;
		for (int i = 1; i <= n; i++) {
//			boolean count = false;
			for (int j = 0; j <= maximum; j++) {
				if (dp[i - 1][j]) {
					if (j + volum[i] <= maximum) {
						dp[i][j + volum[i]] = true;
//						count = true;
					}
					if (j - volum[i] >= 0) {
						dp[i][j - volum[i]] = true;
//						count = true;
					}
				}
			}
//			if (count == false) {
//				System.out.println("-1");
//				break;
//			}
		}
		

		int max=-1;
		for (int i = 0; i <= maximum; i++) {
			if (dp[n][i])
				max = i;
		}
		if(max>-1)	System.out.print(max);
		else	System.out.print("-1");
	}

}
```



