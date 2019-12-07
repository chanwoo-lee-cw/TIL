# Lower bound

**찾고자 하는 값 이상이 처음 나타나는 위치.** 

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
	return e + 1;
}
```

 

### 예시

 먼저, 데이터가 입력되어있는 배열을 A[], 찾고자 하는 값을 k, lower bound를 찾고자 하는 구간을 [s, e]로 설정하면, 구간 내의 중간 위치를 m이라고 할때, A[m-1] < k이면서 A[m] >= k를 만족하는 최소 m을 찾는 문제가 된다.

1 3 5 7 7 에서 7을 탐색할 때, 7과 같거나 큰 수가 나오는 첫 위치가 바로 lower bound!

이때, m은 2이상인 값이 되는데, 일반적인 이분탐색 방법에서 A[m] == k인 부분을 포함시키면 된다.

또한, 모든 원소가 k보다 작을 때에는 n+1을 출력해야 하므로, **처음 구간을 잡을 때, [1, n]을 잡는 대신 [1, n+1]을 잡아야 한다.**

<img src="https://t1.daumcdn.net/cfile/tistory/22107C3F591D9C1A12" alt="img" style="zoom:67%;" />

데이터를 A[] 배열에 모두 입력 받은 상태에서 탐색 준비를 한다. 찾아야 할 데이터 k(6)와 같거나 큰 가장 첫 위치 lower bound, 탐색 구간의 시작위치와 마지막 위치는 s=0, e=7+1이 되고, 중간 위치를 계산하면 (0+8)/2 = 4가 된다.

<img src="https://t1.daumcdn.net/cfile/tistory/2730C441591D9C813E" alt="img" style="zoom:67%;" />



**A[4]의 값(7)이 찾고자 하는 6보다 크므로, 탐색 구간을 [0,4]로 바꾼다.** 일반적인 이분탐색이었다면 탐색 구간을 [0,3]으로 바꿔야 하지만, **lower bound는 k이상의 값이 나타나는 최소위치이므로, 마지막 위치 (e)까지 포함 시켜야 한다.** [0,4]의 중간 위치는 (0+4)/2 = 2가 된다.

<img src="https://t1.daumcdn.net/cfile/tistory/21181541591D9D4D33" alt="img" style="zoom:67%;" />

A[2]의 값(3)이 찾고자 하는 6보다 작으므로, 탐색 구간을 [3,4]로 바꾸고 다시 탐색해 나간다. [3, 4]의 중간 위치는 (3+4)/2=3이 된다.

<img src="https://t1.daumcdn.net/cfile/tistory/22044233591D9DED35" alt="img" style="zoom:67%;" />

이제 더이상 탐색 구간을 줄일 수 없으므로, 위치 4가 k이상인 원소의 최초 위치가 된다.





# Upper bound

**찾고자 하는 값보다 큰 값이 처음으로 나타나는 위치** 

- Lower바운드는 key값을 찾지만, Upper은 key 보다 큰 것을 찾는다.

```java
private static int upper_bound(int[] arr, int s, int e, int check) {
	int m;
	while (e - s > 0) {
		m = (s + e) / 2;
		if (arr[m] <= check)
			s = m + 1;
		else
			e = m;
	}
	return e + 1;
}
```



### 예시

먼저, 데이터가 입력되어있는 배열을 A[], 찾고자 하는 값을 k, upper bound를 찾고자 하는 구간을 [s,e]로 설정하면, 구간 내의 중간 위치를 m이라고 생각할 때, A[m-1] <= k 이면서 A[m]>k를 만족하는 최소 m을 찾는 문제가 된다.



1 3 5 7 7 에서 5를 탐색할 때, 5를 초과하는 첫 위치가 바로 upper bound!



이때, m은 2이상인 값이 되는데, 일반적인 이분탐색에서 A[m] == k인 부분을 포함시키면 된다. 또한, 모든 원소가 k보다 작을 때에는 n+1을 출력해야 하므로, 처음 구간을 잡을 때, [1, n]을 잡는 대신 [1, n+1]을 잡아야 한다.

<img src="https://t1.daumcdn.net/cfile/tistory/24503633591DAC9523" alt="img" style="zoom:67%;" />

데이터를 A[] 배열에 모두 입력 받은 상태에서 탐색 준비를 한다. 찾아야할 데이터 k(7)보다 큰 가장 첫 위치 upper bound, 탐색 구간의 시작위치와 마지막 위치는 s=0, e=7+1이 되고, 중간 위치를 계산하면 (0+8)/2 = 4가 된다.

<img src="https://t1.daumcdn.net/cfile/tistory/21458F40591DAD3505" alt="img" style="zoom:67%;" />

A[4]의 값(7)이 찾고자 하는 7과 같으므로 탐색 구간을 [5, 8]로 바꾼다. 일반적인 이분탐색이었다면 바로 탐색을 종료해야 하지만, upper bound는 k를 초과하는 위치이므로 위치 4를 포함시킬 필요가 없다. [5,8]의 중간 위치는 (5+8)/2=6이 된다.

<img src="https://t1.daumcdn.net/cfile/tistory/275C9145591DADDC0F" alt="img" style="zoom:67%;" />

A[6]의 값(11)이 찾고자 하는 7보다 크므로 탐색 구간을 [5,6]으로 바꾸고 재탐색한다. [5,6]의 중간위치는 (5+6)/2=5가 된다. A[5]의 값(7)이 찾고자 하는 7과 같으므로 탐색 구간을 [6,6]으로 바꾸고 재탐색 한다. [6,6]의 중간위치는 (6+6)/2=6이 된다.

<img src="https://t1.daumcdn.net/cfile/tistory/245C2434591DAE6F34" alt="img" style="zoom:67%;" />

[6,6]의 범위는 더 이상 탐색할 필요가 없으므로, 6번의 위치가 upper bound라는 것을 판단할 수 있다.



출처 :

https://12bme.tistory.com/120