#include <iostream>

using namespace std;

/*
만약 n의 2의 배수가 아니라면 0,
그리고 2의 배수마다 새로운 모양 생성 가능
*/
int solution(int n)
{
    int dp[31] = {
        0,
    };
    // basecase
    dp[0] = 1;
    dp[1] = 0;
    dp[2] = 3;
    dp[3] = 0;
    // 점화식
    for (int i = 4; i < n + 1; i++)
    {
        if (i % 2 != 0)
        {
            continue;
        }
        dp[i] = dp[i - 2] * 3;
        for (int j = 4; j <= i; j += 2)
        {
            dp[i] += dp[i - j] * 2;
        }
    }
    return dp[n];
}

int main(void)
{
    int n;
    int answer;
    cin >> n;
    answer = solution(n);

    cout << answer << endl;
    return 0;
}