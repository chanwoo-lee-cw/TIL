## 구름 IDE 빙글빙글1

[https://level.goorm.io/exam/60331/%EB%B9%99%EA%B8%80%EB%B9%99%EA%B8%80-1/quiz/1](https://level.goorm.io/exam/60331/빙글빙글-1/quiz/1)



### 풀이

자신의 미숙함을 실감했던 문제이다.

별찍기 문제 같다고 생각해서 푼 문제였지만, 생각보다 굉장히 고생했다...



사실상 경우의 수를 6가지로 나눠 생각해 봤는데

1. 달팽이 윗 부분인 경우
   1. 그 줄이 '#'을 찍는 줄인 경우
      1. 왼쪽에 몇개나 ' '을 두는가
      2. 오른쪽에 몇개나 ' '을 두는가
   2. 그 줄이  ' '을 찍는 줄인 경우
      1. 왼쪽에 몇개나 '#'을 두는가
      2. 오른쪽에 몇개나 '#'을 두는가
2. 달팽이 아랫부분인 경우
   1. 그 줄이 '#'을 찍는 줄인 경우
      1. 왼쪽에 몇개나 ' '을 두는가
      2. 오른쪽에 몇개나 ' '을 두는가
   2. 그 줄이  ' '을 찍는 줄인 경우
      1. 왼쪽에 몇개나 '#'을 두는가
      2. 오른쪽에 몇개나 '#'을 두는가



딱, 봐도 굉장히 비효울적인 코드이다.

그냥 진짜로 일일히 하나하나 전부 다 찍은 셈이다...



각각 왼쪽과 오른쪽에 각각 다른 경우의 수를 세기 위해

```c++
	int right;
    int left;
    int downleft;
    int downright;
```

을 둬서, 각각 서로 다른 경우의 수를 세었다.





그리고 마지막에 와서야 깨달은 거지만, 그냥 아예 배열로 선언한 다음에

```c++
	char** arr = new char* [y]; 
    for (int i = 0; i < y; ++i) {
        arr[i] = new char[x]; 
        memset(arr[i], '#', sizeof(char) * x); // 메모리 공간을 0으로 초기화 
    }
```

이런 식으로 2차월 배열을 동적 할당 해버리거나



아니면 

```c++
#define MAX 100

    //char arr[MAX][MAX] = { 0, };
    char arr[MAX][MAX];

    fill(&arr[0][0], &arr[MAX - 1][MAX], '#');
```

```c++
#define MAX 100

    //char arr[MAX][MAX] = { 0, };
    char arr[MAX][MAX];

    memset(arr, '#', sizeof(arr));
```

fill이나 memset함수를 통해 초기화 시켜주면 훨씬 쉽게 끝낼 수 잇었다...





### 전체 코드

```cpp
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
```



### 다른 사람의 코드

```python
n = int(input())

data = [[' ']*n for i in range(n)]

i,j = 0,-1 # 행과 열의 시작
s = 1 # 행과 열의 증감을 다루기 위한 변수

# 첫번째 줄 완성
for p in range(n):
        j = j + s
        data[i][j] = '#'
n -= 1

while True:
    if n <= 0:
        break

    for p in range(n):
        i = i + s
        data[i][j] = '#'

    s = s * -1

    for p in range(n):
        j += s
        data[i][j] = '#'
    n -= 2

for i in range(len(data)):
    for j in range(len(data[0])):
        print(data[i][j],end=' ')
    print()
    
# 출처 : https://level.goorm.io/exam/60331/%EB%B9%99%EA%B8%80%EB%B9%99%EA%B8%80-1/quiz/1
```

이 코드는 처음부터 2차원 배열을 선언해서 한줄씩 뽑는게 아니라, 아예 1,1 부터 1,n까지 #을 채우고 다시 n,n까지 #을 채우는 등의 아예 루트를 따라가는 방식으로 했다.

그리고 다시 s를 -1을 대입해서 오히려 역순으로 n,n에서 n,1까지따라갔다.