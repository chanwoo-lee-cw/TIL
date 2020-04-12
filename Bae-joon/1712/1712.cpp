#include <iostream>
using std::cout;
using std::cin;

int main()
{
    // https://www.acmicpc.net/problem/1712
    long A, B, C;
    cin >> A >> B >> C;

    int i = 0;

    while (true)
    {
        if (A + i * B < C * i) {
            while (A + i * B < C * i)
                i--;
            i++;
            break;
        }
        if (B >= C) {
            cout << "-1";
            return 0;
        }
        i += 10;
    }

    cout << i;

}