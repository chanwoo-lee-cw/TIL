// https://www.acmicpc.net/problem/1712
// 손익 분기점

#include <iostream>
using std::cout;
using std::cin;

// C++을 연습하기 푼 문제이다.

int main()
{
    // A,B,C를 순서대로 입력받는다.
    long A, B, C;
    cin >> A >> B >> C;

    int i = 0;

    // 총 수입 > 고정비용+가변비용이 많아지는 시점을 찾을 때까지 반복
    while (true)
    {
        // 총 수입이 증가하는 부분까지 찾는다
        if (A + i * B < C * i) {
            // 시간을 줄이기 위해 10씩 증가하는 것으로 했으므로
            // 1씩 줄여나가며 역순으로 손익 분기점을 찾는다.
            while (A + i * B < C * i)
                i--;
            i++;
            break;
        }
        // 만약 가변 비용이 고정 비용보다 적다면 절대로 손익 분기점이 안나므로
        // -1 출력하고 리턴
        if (B >= C) {
            cout << "-1";
            return 0;
        }
        i += 10;
    }

    cout << i;

}