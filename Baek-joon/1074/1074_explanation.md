## 백준 1074번 풀이

https://www.acmicpc.net/problem/1074

### 문제

*한수는 2차원 배열 (항상 2^N * 2^N 크기이다)을 Z모양으로 탐색하려고 한다. 예를 들어, 2*2배열을 왼쪽 위칸, 오른쪽 위칸, 왼쪽 아래칸, 오른쪽 아래칸 순서대로 방문하면 Z모양이다.*

![img](https://www.acmicpc.net/upload/201003/z1.JPG)

*만약, 2차원 배열의 크기가 2^N * 2^N라서 왼쪽 위에 있는 칸이 하나가 아니라면, 배열을 4등분 한 후에 (크기가 같은 2^(N-1)로) 재귀적으로 순서대로 방문한다.*

*다음 예는 2^2 * 2^2 크기의 배열을 방문한 순서이다.*

![img](https://www.acmicpc.net/upload/201003/z2.JPG)

*N이 주어졌을 때, (r, c)를 몇 번째로 방문하는지 출력하는 프로그램을 작성하시오.*

*다음 그림은 N=3일 때의 예이다.*

![img](https://www.acmicpc.net/upload/201003/z3.JPG)



### 입력

*첫째 줄에 N r c가 주어진다. N은 15보다 작거나 같은 자연수이고, r과 c는 0보다 크거나 같고, 2^N-1보다 작거나 같은 정수이다*

### 출력

*첫째 줄에 문제의 정답을 출력한다.*

***



### 풀이

이번 문제는 봤을 때 상당히 고민을 했던 문제였다.

어느 지점 건지 파악을 하는 문제인데다, 그림부터 점점 커지는 모양이다 보니 처음에는 다이나믹 프로그래밍 문제인줄 알았다. 하지만 하루 종일 다이나믹 프로그래밍으로 풀어보려고 해도 도저히 감이 안 잡혀서 포기하고는 학교 수업 시간에 했던 정리 노트를 보다가 이런 식으로 큰 문제를 작은 문제를 푸는 방법이 하나 더 있다는 것을 알았다.



그렇다. 이건 분할정복 문제였다.

이렇게 감이 잡혔으니 이제 분할 정복으로 풀어보자. 일단  그림에서 보이는 것처럼 문제를 매트릭스틀 4개로 나눠서 각각 어느 분면에 있느냐에 따라 수를 더해주면 된다.

```c++
double xmid = (xs + xe) / 2;
double ymid = (ys + ye) / 2;
int len = xmid - xs + 1;

if (r <= xmid && c <= ymid) {
    //1분면
	return Z(xs, int(xmid), ys, int(ymid));
}
else if (r <= xmid && c > ymid) {
	//2분면
	return pow(len, 2) + Z(xs, int(xmid), int(ymid) + 1, ye);
}
else if (r > xmid && c <= ymid) {
	//3분면
	return 2 * pow(len, 2) + Z(int(xmid) + 1, xe, ys, int(ymid));
}
else {
	//4분면
	return 3 * pow(len, 2) + Z(int(xmid) + 1, xe, int(ymid) + 1, ye);
}
```

일단 현재 구하고 있는 매트릭스틔 길이의 절반을 구해준다.

`int len = xmid - xs + 1;`

즉, len이 현재 분면의 길이의 절반이 된다.

하지만, 그리고 현재 구하고자 하는 위치가 어느 분면에 있는지 확인해서 분할 탐색을 한다.

만약 구하고자 하는 점이 어느 분면에 있으냐 따라 현재 len의 제곱의 수를 더한다.

1분면에 있다면 그냥 아무것도 더하지 않고, 2분면에 있다면 1개를 더하고 3분면에 있다면 2개 4분면에 있다면 3개를 더한다.

`n*pow(len, 2)`



그리고 탐색의 종료 시점은 시작점과 끝의 위치가 동일해 졌을때이다.

 



### 전체 코드

```c++
// https://www.acmicpc.net/problem/1074

#include <iostream>
#include<math.h>

using namespace std;
int r, c;

int Z(int xs, int xe, int ys,int ye) {
	double xmid = (xs + xe) / 2;
	double ymid = (ys + ye) / 2;
	int len = xmid - xs + 1;

	if (xs == xe && ye == ys) {
		return 0;
	}
	if (r <= xmid && c <= ymid) {
		return Z(xs, int(xmid), ys, int(ymid));
	}
	else if (r <= xmid && c > ymid) {
		//1분면 수 더하기
		return pow(len, 2) + Z(xs, int(xmid), int(ymid) + 1, ye);
	}
	else if (r > xmid && c <= ymid) {
		//1분면 수 더하기
		return 2 * pow(len, 2) + Z(int(xmid) + 1, xe, ys, int(ymid));
	}
	else {
		//1분면 수 더하기
		return 3 * pow(len, 2) + Z(int(xmid) + 1, xe, int(ymid) + 1, ye);
	}
}

int main(void)
{
	int n;
	int len;
	cin >> n >> r >> c;

	len = pow(2, n);

	cout << Z(0, len - 1, 0, len - 1) << endl;
}
```

