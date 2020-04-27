// https://www.acmicpc.net/problem/1074

#include <iostream>
#include<math.h>

using namespace std;
int r, c;

// 작은 경우의 수를 푸는 방법을 나누면 똑같은 방법으로 문제를 푸는 방법
// 분할 정복 문제이다.

int Z(int xs, int xe, int ys,int ye) {
	double xmid = (xs + xe) / 2;
	double ymid = (ys + ye) / 2;
	int number = 0;
	int len = xmid - xs + 1;

	if (xs == xe && ye == ys) {
		// 더 이상 나눌 수 없는 없이 한칸만을 가르키게 된다면 그 값을 그대로 반환
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