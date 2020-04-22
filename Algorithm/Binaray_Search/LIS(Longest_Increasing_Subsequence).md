# LIS(Longest Increasing Subsequence)

1 2 1 3 1 4 1 5 처럼 부분적으로 증가하는 수열 중 가장 긴 것을 찾아내는 알고리즘이다.

1. lis 배열에 아무 값이 없다면 조사 하는 배열(arr)의 첫번째 값을 넣는다.
2. lis 배열의 가장 큰 값(가장 오른쪽에 있는 값) 보다 현재 보고 있는 arr[i] 값이 크다면 lis배열에 arr[i]값을 추가한다.
3. 그 외에는 lower_bound(주어진 집합의 어떤 원소보다 작거나 같은 원소라는 뜻)을 이용하여 그 위치에 값을 넣어준다.



## 참고할 문제

[가장 긴 증가하는 부분 수열](https://github.com/Alphanewbie/TIL/blob/master/Bae-joon/1463/1463.py)



## 코드

**이 알고리즘 설명은 lis의 길이는 알 수 있지만, 최종적인 증가하는 부분수열은 알 수 없습니다.**



```c++
#include <cstdio>
#include <algorithm>
 
using namespace std;
 
 
int _lower_bound(int start, int end, int *arr, int target)
{
    int mid;
    while (end - start > 0)  
        // 주어진 범위[start,end]에서 탐색하도록 한다. start == end이면 반복 종료
    {
        mid = (start + end) / 2;  
        // 주어진 범위의 중간 위치를 계산한다
        if (arr[mid] < target) 
            // 찾고자 하는 값보다 작으면 오른쪽으로 한 칸만 더 시작 구간 갱신
            start = mid + 1;
        else  
            // 찾고자 하는 값보다 크면 거기까지 끝 구간 갱신
            end = mid;
    }
    return end + 1;
    // 찾는 구간에 없는 경우 가장 마지막 +1 위치 전달
}
 
 
int main()
{
    int i, n, j = 0;
    int cnt = 0;
    int lis[1001];
    int arr[1001];
    
    scanf("%d", &n);
 
    for (i = 0; i<n; ++i)
        scanf("%d", &arr[i]);
    
    i = 0;
 
    lis[i] = arr[i];
    i++;
 
    while (i < n)
    {
        // lis의 가장 큰 값보다 더 큰값이 들어오면
        if (lis[j] < arr[i])
        {
            lis[j + 1] = arr[i];
            j++;
        }
 
        else
        {
            int ans = _lower_bound(0, j, lis, arr[i]);
            lis[ans - 1] = arr[i];
        }
 
        i++;
    }
    
    printf("%d", j + 1);
 
    return 0;
}

// 출처: https://www.crocus.co.kr/583 [Crocus]
```



## 원리



1. lis 배열에 아무 값이 없다면 조사 하는 배열(arr)의 첫번째 값을 넣는다.

```c++
i = 0;
lis[i] = arr[i];
```



2. lis 배열의 가장 큰 값(가장 오른쪽에 있는 값) 보다 현재 보고 있는 arr[i] 값이 크다면 lis배열에 arr[i]값을 추가한다.

```c++
// lis의 가장 큰 값보다 더 큰값이 들어오면
if (lis[j] < arr[i])
{
	lis[j + 1] = arr[i];
	j++;
}
```



3. 그 외에는 lower_bound(주어진 집합의 어떤 원소보다 작거나 같은 원소라는 뜻)을 이용하여 그 위치에 값을 넣어준다.

``` c++
else
{
	int ans = _lower_bound(0, j, lis, arr[i]);
	lis[ans - 1] = arr[i];
}
```



여기서 lower_bound란?

이분 탐색을 통해 해당하는 위치를 return 해주는 방식이다.

예를들어

1 5 1 3 4 2 7을 보자



1. lis 배열에 아무것도 없으니 처음에는 1이 들어갈 것이다.

lis[0] = 1



2. lis 배열의 가장 끝 값(1) 보다 5가 더 크니 lis[1]에는 5가 들어갈 것이다.

lis[0] = 1

lis[1] = 5



3. else 구문에 의해 lower_bound 함수에 들어간다.

start = 0, end = 1이고 mid = 0이 된다.

이때 arr[mid] == target이니 end = mid 즉, 0이 되고 return 값으로 end + 1인 1이 return 된다.

그리고 ans 변수에 리턴 값을 받아 lis[ans-1] = arr[i]를 해준다. 즉 lis[0]에 다시 1이 들어가는 상황이다.

lis[0] = 1

lis[1] = 5



4. arr[3] = 3이니 else구문에 의해 lower_bound 함수에 들어간다.

start = 0, end = 1이고 mid = 1이 된다.

그리고 lower_bound 함수 내의 if 구문에 의해 arr[mid] < target ( 1 < 3 )에 의해 start = mid + 1이 되고

start = 1, end = 1이 되어 while문을 탈출한다.

결국 return end + 1이 되어 2가 리턴되고

lis[ans - 1] = lis[1] = 3이 된다.



lis[0] = 1

lis[1] = 3



이런식으로 계속 진행하다 보면 결국 



**lis[0] = 1**

**lis[1] = 2**

**lis[2] = 4**

**lis[3] = 7이 되어 1, 2, 4, 7이라는 lis 배열이 생성되고 길이가 4임을 알 수 있다.(j + 1값)**



**하지만 이 코드는 lis의 길이를 파악 할 수 있는 코드이지만 lis의 정확한 배열은 확인 할 수 없는 코드이다.**



**1, 3, 4, 7이 정확한 배열 값이지만 1, 2, 4, 7으로 도출된다. 즉, lis 배열 길이는 4로 알 수 있지만, lis의 정확한 값은 확인 할 수 없다.**



출처: https://www.crocus.co.kr/583 [Crocus]



### 전체 LIS를 알아내는 코드

```python
import sys
input = sys.stdin.readline

def lower_bound(arr, start, end,  check) :
    while end - start > 0 :
        mid = int((start + end) / 2)
        if arr[mid] < check :
            start = mid + 1
        else :
            end = mid
    return end+1

if __name__ == "__main__":
    n = int(input().strip())
    arr = list(map(int,input().strip().split()))
    lis = [-float('INF')]
    i=0
    lis[i] = arr[i]
    i = i+1

    while i<n :
        if lis[-1]<arr[i] :
            lis.append(arr[i])
        else :
            ans = lower_bound(lis, 0,len(lis),arr[i])
            lis[ans - 1] = arr[i]
        i = i + 1

    print(len(lis))
```

