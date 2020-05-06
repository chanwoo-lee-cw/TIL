// https://level.goorm.io/exam/60331/%EB%B9%99%EA%B8%80%EB%B9%99%EA%B8%80-1/quiz/1

#include <iostream>
#include <vector>

using namespace std;

int main()
{
    int n = 0;
    cin >> n;

    int mid = (n - 1) / 2;
    int right = 0;
    int left = 0;
    int downleft = 0;
    int downright = 0;

    for (int i = 0; i < n; i++) {
        // 각각 왼쪽과 오른 쪽에 #이나 ' '을 찍어야 하는지의 갯수 초기화
        right = (i+1) / 2;
        left = right - 1;
        downleft = n/2 - i/2;
        downright = downleft;
        // 달팽이를 찍는 경우의 수 3가지와 그 하위 경우의 수
        for (int j = 0; j < n; j++) {
            // 1. 가운데 보다 위 부분을 찍을때
            if (i <= mid) {
                // 1-1. 찍는 줄이 전부 #을 찍는 경우
                if (i % 2 == 0) {
                    // 1-1-1 오른쪽에 몇개나 ' '을 찍어야 하는가
                    if (left > 0) {
                        if (j % 2 == 1) {
                            cout << "  ";
                            left--;
                        }
                        else
                            cout << "# ";

                    }
                    // 1-1-2 오른쪽에 ' '을 몇개나 찍어야 하는가
                    else {
                        if (n <= right * 2 + j) {
                            int temp = n - j;

                            if (temp % 2 == 0)
                                cout << "  ";
                            else
                                cout << "# ";
                        }
                        else {
                            cout << "# ";
                        }
                    }
                }
                // 1-2 찍는 줄이 전부 ' ' 을 찍는 경우
                else {
                    // 1-2-1 왼쪽에 #을 몇개나 찍어야 하는가
                    if (left > 0) {
                        if (j % 2 == 0) {
                            cout << "# ";
                            left--;
                        }
                        else 
                            cout << "  ";
                        
                    }
                    // 1-2-2 오른쪽에 #을 몇개나 찍어야 하는가
                    else {
                        if (n  <= right*2 + j) {
                            int temp = n - j;

                            if (temp % 2 == 1) 
                                cout << "# ";
                            else
                                cout << "  ";
                        }
                        else {
                            cout << "  ";
                        }
                    }
                }
            }
            // 2. 달팽이 아래, 즉 배쪽을 찍는 경우
            else {
                // 2-1. 전부 #을 찍을때
                if (i%2 == 0)
                {
                    // 배쪽은 좌우 대칭이니 left와 right가 같아도 된다.
                    if (j%2 == 1)
                    {
                        // 2-1-1 왼쪽에 몇개나 ' '을 찍어야 하나
                        if (downleft >0)
                        {
                            cout << "  ";
                            downleft--;
                            continue;
                        }
                        // 2-1-2 오른쪽이 몇개나 #을 찍어야 하나
                        else if(j+2*downright>=n) {
                            cout << "  ";
                            downright--;
                            continue;
                        }

                    }
                    // 나머지는 전부 #으로 통일
                    cout << "# ";
                }
                // 2-2. 달팽이의 배쪽을 전부 ' '으로 찍을 때
                else
                {
                    
                    if (j % 2 == 0)
                    {
                        // 2-2-1. 달팽이의 왼쪽에 몇개나 #을 찍을 것인지
                        if (downleft > 0)
                        {
                            cout << "# ";
                            downleft--;
                            continue;
                        }
                        // 2-2-2. 달팽이의 오른쪽에 몇개나 #을 찍을 것인지
                        else if (j + 2 * downright >= n) {
                            cout << "# ";
                            downright--;
                            continue;
                        }

                    }
                    // 나머지는 전부 ' '으로 찍는다.
                    cout << "  ";
                }
            }
        }
        printf("\n");
    }

    return 0;
}