#include<iostream>
#include<string.h>

using namespace std;

// 함수 선언
int binomial_coefficient(int n, int k);
int dp[11][11];

int main() {
    int n, k;

    cin >> n >> k;
    // 배열 초기화, memset을 사용 하기 위해서 #include<string.h> 선언 필수
    memset(dp, 0, sizeof(dp));

    cout << binomial_coefficient(n, k) << endl;

    return 0;
}

int binomial_coefficient(int n, int k) {
    if (dp[n][k] != 0)
    // 만약 한번 계산 된 것이라면 결과값 리턴
        return dp[n][k];
    if (n == k || k == 0)
    // 만약 nCk에서 경우의 수가 1개만 있는 경우라면 1 리턴
        dp[n][k] = 1;
    else
    // 이항 계수 공식 nCk = n-1Ck-1 + n-1Ck
        dp[n][k] = (binomial_coefficient(n - 1, k - 1) + binomial_coefficient(n - 1, k));

    return dp[n][k];
}