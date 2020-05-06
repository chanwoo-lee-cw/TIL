// https://www.acmicpc.net/problem/11653
// 소인수 분해
#include<iostream>
#include<vector>
#include<math.h>

using namespace std;

//소인수 분해된 결과를 저장할 vector
vector<int> measure;

// 소인수 분해를 하기 위핸 vector
void factorization(int n)
{
    // 계산 시간을 줄이기 위하여 n의 루트 까지만 반복한다.
    // 이유는 루트까지만 계산하면 딱 이 지점까지가 n*k의 값으로 나타나는 지점.
    int sqtN = sqrt(n);

    for(int i=2; i<=sqtN ; i++) 
    {
        // 만약 나누어떨어지면 벡터에 넣고 더이상 나누어 떨어지지 않을 때까지 반복
        while (n%i ==0)
        {
            measure.push_back(i);
            n = n/i;
        }
    }
}

int main() 
{
    int n = 0;
    cin >> n;
    
    // 소인수분해 함수를 콜한다.
    factorization(n);

    // 벡터에 저장된 값을 모두 출력한다.
    int doubleSum = 1;
    for(int i=0;i<measure.size();i++) {
        doubleSum = doubleSum * measure[i];
        cout << measure[i] << endl;
    }
    // 하지만 추가로 n,k 딱 하나의 경우로만 나누어 떨어진 경우라면 그 나머지 경우도 출력해야 되므로
    // 벡터에 저장된 모든 값의 곱셉의 결과가 n이 아니라면 그것을 출력한다.
    if(doubleSum != n)
    {
        cout << n/doubleSum;
    }
    return 0;
}