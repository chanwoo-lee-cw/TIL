## 백준 2110 풀이

### 문제

*도현이의 집 N개가 수직선 위에 있다. 각각의 집의 좌표는 x1, ..., xN이고, 집 여러개가 같은 좌표를 가지는 일은 없다.*

*도현이는 언제 어디서나 와이파이를 즐기기 위해서 집에 공유기 C개를 설치하려고 한다. 최대한 많은 곳에서 와이파이를 사용하려고 하기 때문에, 한 집에는 공유기를 하나만 설치할 수 있고, 가장 인접한 두 공유기 사이의 거리를 가능한 크게 하여 설치하려고 한다.*

*C개의 공유기를 N개의 집에 적당히 설치해서, 가장 인접한 두 공유기 사이의 거리를 최대로 하는 프로그램을 작성하시오.*

### 입력

*첫째 줄에 집의 개수 N (2 ≤ N ≤ 200,000)과 공유기의 개수 C (2 ≤ C ≤ N)이 하나 이상의 빈 칸을 사이에 두고 주어진다. 둘째 줄부터 N개의 줄에는 집의 좌표를 나타내는 xi (1 ≤ xi ≤ 1,000,000,000)가 한 줄에 하나씩 주어진다.*

### 출력

*첫째 줄에 가장 인접한 두 공유기 사이의 최대 거리를 출력한다.*

***



### 풀이 전개 과정

처음에 어떻게 풀어야 하는지 전혀 감이 잡히지 않아서 인터넷을 찾아본 결과, 이 문제는 파라메트릭 서치를 통해 최적화 문제를 결정 문제로 바꿔주는 문제라는 것을 알았다.

하지만, 파라메트릭 서치가 뭔지 아무리 읽어도 감이 안 잡혀서 참고를 많이 했다.



일단 이분 탐색  기법으로 left를 시작점(house[0])으로 right는 끝점(house[n-1])으로 잡는 코드를 짰다.

그래서 파라메트릭 서치 기법을 위해 left가 right 보다 커지는 점을 기준으로 루프를 돌리는 방식을 채택했다.

```java
int left = house[0];
int right = house[n - 1];

while (left <= right) {
    ...
}
```

그래서 mid는 left와 right를 더한 다음에 1/2 하는 것을 사용했고,

```java
mid = (left + right)/2;
```

하지만, 이 다음 부터가 제대로 안 풀려서 고민을 해야 됬는데,

과연 구해진 이 mid를 어떻게 사용하느냐를 알 수가 없었다.



그래서, 인터넷을 참고한 결과, 첫 라우터를 house[0]에 설치 한 후에 그 다음 거리들 부터 라우터를 설치하는 방식이라는 것을 알았고, 집을 순서대로 되짚어 가되, 만약 전에 라우터가 설치된 집과 거리를 mid를 기준보다 멀거나 같으면 설치하고, 아니면 그냥 다음 집으로 넘어가는 방식이였다.

```java
for (int i = 0; i < n; i++) {
	if (i == 0)
		pre = 0;
	else {
		if (house[i] - house[pre] >= mid) {
			cnt++;
			pre = i;
		}
	}
}
```

이러면 설치된 라우터의 갯수를 알 수가 있는데

이 라우터 갯수를 기준으로 만약 설치된 라우터가 사전에 설치할 수 있도록 입력된 라우터의 갯수보다 적다면 left를 mid+1까지, 많다면 원하는 것보다 촘촘히 설치 됬거나 아니면 원하는 거리 만큼 도달했다는 뜻이므로 리턴 값으로 mid를 대입해 주고 right를 mid -1 까지 바꾸는 코드를 짰다.

```java
if (cnt >= m) {
	ans = mid;
	left = mid + 1;			
} else {
	right = mid - 1;
}
```

하지만 중간에 실패가 떴다.



그래서 다시 고민을 해본 결과,

거리의 최소, 최대값을 구하는 것이기 때문에

while 돌리는 기준이 mid를 기준으로 어느 방면에 집이 있으냐가 아니라, 거리를 기준으로 바꿔여 됬었다.

그리서 left, right를 low, high로 바꾸고 거리를 기준으로 구하는 것으로 바꾸었다.

```java
int low = 1;
int high = house[n - 1] - house[0];
while (low <= high) {
	mid = (high + low) / 2;
	cnt = 1;
	for (int i = 0; i < n; i++) {
		if (i == 0)
			pre = 0;
		else {
			if (house[i] - house[pre] >= mid) {
			cnt++;
			pre = i;
		}
	}
}

if (cnt >= c) {
	ans = mid;
	low = mid + 1;
} else {
	high = mid - 1;
}
```



### 전체 코드

```java
import java.util.Scanner;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		String[] line = in.nextLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int c = Integer.parseInt(line[1]);

		int[] house = new int[n];
		for (int i = 0; i < n; i++) {
			house[i] = in.nextInt();
		}
		Arrays.sort(house);

		int low = 1;
		int high = house[n - 1] - house[0];
		int mid;
		int cnt;
		int pre = 0;
		int ans = 0;
		while (low <= high) {
			mid = (high + low) / 2;
			cnt = 1;
			for (int i = 0; i < n; i++) {
				if (i == 0)
					pre = 0;
				else {
					if (house[i] - house[pre] >= mid) {
						cnt++;
						pre = i;
					}
				}
			}

			if (cnt >= c) {
				ans = mid;
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		System.out.print(ans);
	}
}
```



### 유의점

1. 파라메트릭 서치는 최적화 문제를 결정 문제로 바꿔주는 방법
2. 파라메트릭 서치를 통해 반복을 돌려주는 기준은 최소, 최대값을 기준으로 삼아서 돌려야 한다.



#### 참고한 사이트

https://mygumi.tistory.com/301

[https://romanceboong.tistory.com/entry/%EB%B0%B1%EC%A4%80-2110%EB%B2%88-%EB%AC%B8%EC%A0%9C-%EA%B3%B5%EC%9C%A0%EA%B8%B0-%EC%84%A4%EC%B9%98](https://romanceboong.tistory.com/entry/백준-2110번-문제-공유기-설치)

https://www.crocus.co.kr/1000