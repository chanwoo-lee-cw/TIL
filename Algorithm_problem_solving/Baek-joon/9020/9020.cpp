// https://www.acmicpc.net/problem/9020

#include <iostream>
#include <vector>
#include <algorithm>
#include <math.h>
#include <cstring>

using namespace std;

#define MAX_N 10001

// 에러토스테네스의 체 - 모든 소수를 구한다.
bool isPrime[MAX_N + 1];

// 에라토스테네스의 체 함수
void eratosthenes(int n)
{
    memset(isPrime, 1, sizeof(isPrime));

    isPrime[0] = isPrime[1] = false;

    int sqrt_n = int(sqrt(n));

    for (int i = 2; i <= sqrt_n; i++)
    {
        if (isPrime[i])
        {
            for (int j = 2; i * j <= n; j++)
            {
                isPrime[i * j] = false;
            }
        }
    }
}

// 골드바흐의 가설의 수를 찾아내기 위한 함수이다.
int goldbach(int n)
{
    // 가장 가까운 수를 찾기 위해 n의 절반 부터 찾는다.
    int goldbachnum = n / 2;

    // 4시 이상으로 입력되고, 골드 바흐의 가설이 참이라는 전제하에서는 안될리가 없지만 혹시 모르니, 양수의 선에서 구한다.
    while (goldbach > 0)
    {
        // goldbachnum와 n을 뺏을 때, 양 측 수 모두 소수하면 그 값을 반환한다.
        int another = n - goldbachnum;
        if (isPrime[goldbachnum] & isPrime[another])
            return goldbachnum;
        goldbachnum--;
    }
    // 혹시 모를 상황을 위해 1 리턴 값
    return 1;
}

int main()
{
    int n = 0;

    vector<int> inputlist;
    int input = NULL;
    int maxnum;

    cin >> n;

    for (int i = 0; i < n; i++)
    {
        cin >> input;
        inputlist.push_back(input);
    }

    // 입력받은 수들 중에서 최대 값까지 에라토스테네스의 체로 소수를 구한다.
    maxnum = *max_element(inputlist.begin(), inputlist.end());
    eratosthenes(maxnum);

    // 각 입력 값들에 대해 골드바흐의 수를 구한다.
    int goldbachnum;
    for (int i = 0; i < n; i++)
    {
        goldbachnum = goldbach(inputlist[i]);
        cout << goldbachnum << " " << (inputlist[i] - goldbachnum) << endl;
    }

    return 0;
}
