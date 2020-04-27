## 백준 2805번 풀이

### 문제

*상근이는 나무 M미터가 필요하다. 근처에 나무를 구입할 곳이 모두 망해버렸기 때문에, 정부에 벌목 허가를 요청했다. 정부는 상근이네 집 근처의 나무 한 줄에 대한 벌목 허가를 내주었고, 상근이는 새로 구입한 목재절단기을 이용해서 나무를 구할것이다.*

*목재절단기는 다음과 같이 동작한다. 먼저, 상근이는 절단기에 높이 H를 지정해야 한다. 높이를 지정하면 톱날이 땅으로부터 H미터 위로 올라간다. 그 다음, 한 줄에 연속해있는 나무를 모두 절단해버린다. 따라서, 높이가 H보다 큰 나무는 H 위의 부분이 잘릴 것이고, 낮은 나무는 잘리지 않을 것이다. 예를 들어, 한 줄에 연속해있는 나무의 높이가 20, 15, 10, 17이라고 하자. 상근이가 높이를 15로 지정했다면, 나무를 자른 뒤의 높이는 15, 15, 10, 15가 될 것이고, 상근이는 길이가 5인 나무와 2인 나무를 들고 집에 갈 것이다. (총 7미터를 집에 들고 간다)*

*상근이는 환경에 매우 관심이 많기 때문에, 나무를 필요한 만큼만 집으로 가져가려고 한다. 이때, 적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값을 구하는 프로그램을 작성하시오.*



### 입력

*첫째 줄에 나무의 수 N과 상근이가 집으로 가져가려고 하는 나무의 길이 M이 주어진다. (1 ≤ N ≤ 1,000,000, 1 ≤ M ≤ 2,000,000,000)*

*둘째 줄에는 나무의 높이가 주어진다. 나무의 높이의 합은 항상 M을 넘기 때문에, 상근이는 집에 필요한 나무를 항상 가져갈 수 있다. 높이는 1,000,000,000보다 작거나 같은 양의 정수 또는 0이다.*



### 출력

*적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값을 출력한다.*

***



### 풀이

***적어도 M미터***의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 ***최댓값***

적어도 M미터의 최대값을 구하는 문제, 이분 탐색의 **파라메트릭 서치** 문제이다.



일단 입력된 입력된 tree의 값들을 이분 탐색을 위해서 sort해준다.

```java
Arrays.sort(tree);
```



그럼 일단 파라매트릭 서치를 위해 길이의 최소 값과 최대 값을 설정해야 한다.

일단 최소 값은 나무 전체를 베어 갈 수도 있으니 0으로 하고

최대 값은 가장 높은 나무에서 아무것도 베이가지 않을 수도 있으니 최대 값으로 설정한다.

```JAVA
int low = 0;
int high = tree[n-1];
```



그럼 이제 파라매트릭 서치를 위한 사전 준비는 끝났고, 이제 파라매트릭 서치를 돌려준다ㅑ.

반복문을 돌릴 조건은 high가 low보다 높을 동안.

기준점으로 삼아줄 mid 는 low와 high를 더해준다.



그럼 이제 mid보다 높은 나무들을 잘라서 길이를 더해주면 된다.

하지만, mid보다 높은 나무를 아예 처음부터 찾는건 효율이 안 좋으므로 lower_bound로 mid보다 높은 나무가 나오는 첫 위치를 이분 탐색으로 찾는다.

```java
for(int i = lower_bound(tree,0,n,mid);i<n;i++) {
    sum += tree[i]-mid;
}
```

```java
private static int lower_bound(int[] arr, int s, int e, int check) {
		int m;
		while (e - s > 0) {
			m = (s + e) / 2;
			if (arr[m] < check)
				s = m + 1;
			else
				e = m;
		}
		return e;
	}
```



그렇게 다 잘라서 더해준 값이 원했던 것보다 길다면 그게 해답이 될 수도 있으므로 ans에 저장해 주고 low 값을 mid+1까지 올리고, 만약 짧다면 high값을 mid -1 값에 저장하고 대입해준다.



그렇게 반복하다 high가 low보다 작아지는 지점에서 끝나고 반환되는 ans가 답이다.



### 전체 코드

```java
import java.util.Scanner;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		String[] line = in.nextLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int m = Integer.parseInt(line[1]);

		int[] tree = new int[n];
		line = in.nextLine().split(" ");
		for (int i = 0; i < n; i++) {
			tree[i] = Integer.parseInt(line[i]);
		}
		line = null;

		Arrays.sort(tree);

		int low = 0;
		int high = tree[n - 1];
		int ans = 0;
		while (high >= low) {
			long sum = 0;
			int mid = (low + high) / 2;
			for (int i = lower_bound(tree, 0, n, mid); i < n; i++) {
				sum += tree[i] - mid;
			}
			if (sum >= m) {
				ans = mid;
				low = mid + 1;
			} else
				high = mid - 1;

		}
		System.out.print(ans);
	}

	private static int lower_bound(int[] arr, int s, int e, int check) {
		int m;
		while (e - s > 0) {
			m = (s + e) / 2;
			if (arr[m] < check)
				s = m + 1;
			else
				e = m;
		}
		return e;
	}
}
```

