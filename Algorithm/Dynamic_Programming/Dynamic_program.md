# 알고리즘

## Dynamic Program

### 정의

복잡한 문제를 하위의 작은 문제로 분할하고, 작은 문제의 답을 모아서 큰 문제를 해결하는 방법을 말한다. 

또한, 이미 한번 구한 결과 값을 저장하여, 중복 계산은 피하고 원래 문제의 해답을 찾는데 이용한다.



### 예시

- Knapsack Problem

가장 유명한 종류의 np문제이자 다이나믹 프로그래밍 문제이다.

가방의 용량이 k이고 n개의 물건이 있을 때, 가장 가치있는 물건을 넣는 방법을 구하는 문제이다.

----

입력 값 :

````
v1 w1
v2 w2
....
vn wn
````

목표 :

````
weigh의 합이 k를 넘지 않는 한에서 선택한 item 의 value의 합을 최대화
````

----

## 예

```yaml
# 물건의 무게와 가치
v1=20		w1=10
v2=19		w2=10
v3=40		w3=15
# 가방의 용량
k = 20
```

라면 v3 한개가 v1 + v2보다 크다.
즉, 주머니를 꽉 채우는 것보다 w3만 채워서 나가는 것이 낫다.

## 풀이 방법

- 물건 하나를 기준으로 선택해서 나머지 물건으로 **부분 문제**를 푸는 것이라고 생각하는 것이 낫다.
  - 용량이 가득 찬 경우에는 물건이 있더라도 가치는 0과 같다.
    -> 물건이 줄었을 때 부분 문제, 용량이 줄었을 때의 부분 문제를 동시에 생각한다.
  - 즉, item1,2 .... i 중에서 선택하여 capicity는 jdls 경우 얻을 수 있는 value의 최대치.
    => 즉, 쉽게 생각하기 위해 i,j가 0인 작은 값부터 생각한다.
  - `A[i][j]` : 얻을 수 있는 이득이 i일 때 무게가 j일 때
    - `i=0` : 고를 수 있는 물건이 없을 때
    - `j=0` : 자루가 꽉 차거나, 물건이 없을 때

## 점화식

#### Basecase

1. 가방의 용량이 남아 있지 않을 때

   ```pseudocode
   for i ... n :
   	dp[i][0] = 0
   ```

2. 더 이상 물건이 없을 때

   ```pseudocode
   for j ... k :
   	dp[0][j] = 0
   ```

#### 점화식

이제 경우의 수는 2가지가 있다.

1. 가방에 해당 물건을 넣을 수 있을 정도의 용량이 남아 있을 때,

   1. 물건을 넣는다.

      ```pseudocode
      dp[i - 1][j - item[i]의 무게] + item[i]의 가치
      ```

   2. 물건을 넣지 않는다.

      ```pseudocode
      dp[i][j] = dp[i - 1][j]
      ```

2. 가방에 해당 물건을 넣을 수 있을 정도의 용량이 없을 때

   ```pseudocode
   dp[i][j] = dp[i - 1][j]
   ```

즉, 점화식은 이렇게 된다.

```pseudocode
if 가방의 용량 > item[i]의 무게 : 
    dp[i][j] = Max(dp[i - 1][j], dp[i - 1][j - item[i]의 무게] + item[i]의 가치
else :
    dp[i][j] = dp[i - 1][j]
```

그래서 `dp[1][0~n]` 을 생각해 보자 첫번째 물건이 입력 받은 후에 w1까지는 0이 그대로 쭉 들어간다. 이유는 용량이 n만큼 남았는데 w1까지는 전부 0으로 초기화 된다. 그 위부터는 다시 v1으로 초기화 되고, 그 다음 `dp[2][0~n]`까지는 역시 똑같이 0으로 초기화 되다 물건 하나만 넣었을때 가치 그리고 쭉 물건 한개만 넣었을 때의 가치로 초기화 되고 w1+w2부터는 물건 둘다 넣었을 때의 가치로만 채워지는 방식이다.



## 코드

```python
import sys
input = sys.stdin.readline

if __name__ == "__main__" :
    (n,k) = map(int,input().strip().split())

    item = [[0,0]]

    for _ in range(n) :
        (m, v) = map(int,input().strip().split())
        item.append([m, v])

    dp = [[0] * (k+1) for _ in range(n+1)]

    for i in range(1,n + 1) :
        for j in range(1,k + 1):
            if j >= item[i][0]:
                dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - item[i][0]] + item[i][1])
            else:
                dp[i][j] = dp[i - 1][j]

    print(dp[n][k])
```


| \    | 0    | 1    | 2    | 3    | 4    | 5    | 6    |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| 13   | 0    | 0    | 0    | 0    | 0    | 0    | 13   |
| 8    | 0    | 0    | 0    | 0    | 8    | 8    | 13   |
| 6    | 0    | 0    | 0    | 6    | 8    | 8    | 13   |
| 12   | 0    | 0    | 0    | 6    | 8    | 12   | 13   |



### 배낭 문제가 NP 인 이유

예를 들어

개인키를 2,3,7,14,30,57,120,251 이라고 했을 때

요청으로 왔으면 하는 값이



공개키는

w' = w*m(mod n)



| SIK  | 2    | 3    | 7    | 14   | 30   | 57   | 120  | 251  |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| GK   | 82   | 123  | 287  | 83   | 248  | 272  | 10   | 471  |



예를 들면 150이라는 숫자를 암호화 시켜서 보내고 싶은 경우에는

2진수로 변환하면 `10010110`라는 숫자가 나온다. 그럼 이 이진수를 각각 자릿수에 매칭하는 수를 더한다

82 + 83 + 373 + 10 = 548 가 되는데 이 수를 보낸다.







# LIS(Longest Increasing Subsequence)

1 2 1 3 1 4 1 5 처럼 부분적으로 증가하는 수열 중 가장 긴 것을 찾아내는 알고리즘이다.

1. lis 배열에 아무 값이 없다면 조사 하는 배열(arr)의 첫번째 값을 넣는다.

2. lis 배열의 가장 큰 값(가장 오른쪽에 있는 값) 보다 현재 보고 있는 arr[i] 값이 크다면 lis배열에 arr[i]값을 추가한다.

3. 그 외에는 lower_bound(주어진 집합의 어떤 원소보다 작거나 같은 원소라는 뜻)을 이용하여 그 위치에 값을 넣어준다.



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

