## 백준 1003번 풀이

https://www.acmicpc.net/problem/1003

### 문제

*다음 소스는 N번째 피보나치 수를 구하는 C++ 함수이다.*

```c++
int fibonacci(int n) {
    if (n == 0) {
        printf("0");
        return 0;
    } else if (n == 1) {
        printf("1");
        return 1;
    } else {
        return fibonacci(n‐1) + fibonacci(n‐2);
    }
}
```

*`fibonacci(3)`을 호출하면 다음과 같은 일이 일어난다.*

- *`fibonacci(3)`은 `fibonacci(2)`와 `fibonacci(1)` (첫 번째 호출)을 호출한다.*
- *`fibonacci(2)`는 `fibonacci(1)` (두 번째 호출)과 `fibonacci(0)`을 호출한다.*
- *두 번째 호출한 `fibonacci(1)`은 1을 출력하고 1을 리턴한다.*
- *`fibonacci(0)`은 0을 출력하고, 0을 리턴한다.*
- *`fibonacci(2)`는 `fibonacci(1)`과 `fibonacci(0)`의 결과를 얻고, 1을 리턴한다.*
- *첫 번째 호출한 `fibonacci(1)`은 1을 출력하고, 1을 리턴한다.*
- *`fibonacci(3)`은 `fibonacci(2)`와 `fibonacci(1)`의 결과를 얻고, 2를 리턴한다.*

*1은 2번 출력되고, 0은 1번 출력된다. N이 주어졌을 때, `fibonacci(N)`을 호출했을 때, 0과 1이 각각 몇 번 출력되는지 구하는 프로그램을 작성하시오.*

### 입력

*첫째 줄에 테스트 케이스의 개수 T가 주어진다.*

*각 테스트 케이스는 한 줄로 이루어져 있고, N이 주어진다. N은 40보다 작거나 같은 자연수 또는 0이다.*

### 출력

*각 테스트 케이스마다 0이 출력되는 횟수와 1이 출력되는 횟수를 공백으로 구분해서 출력한다.*

***

### 풀이

이름 그대로 피보나치 문제이다.



코드만 봤을 땐 뭔가 다른 문제인가 싶었지만, 막상 읽어보면 피보나치를 그대로 사용하는 다이나믹 프로그래밍이다.

각각 수 피보나치의 DP의 경우에서 0과 1이 몇번이나 호출이 되느냐.



하지만 피보나치는

`fibonacci(i) = fibonacci(i-1) + fibonacci(i-2)`

로 i 번째 숫자가 결정된다.



즉, i 번째 피보나치에서 0과 1이 몇번이나 실행 되느냐는 i-1과 i-2에서 0과 1이 호출 되는 경우의 수가 같다.



### 전체 코드

```python
import sys
input = sys.stdin.readline

if __name__=='__main__' :
    n = int(input().strip())
    inputlist = []
    for i in range(n) :
        inputlist.append(int(input().strip()))

    dp = {}
    dp[0] = [1,0]
    dp[1] = [0,1]

    maxnum = max(inputlist)

    for i in range(2,maxnum + 1) :
        num1 = dp[i-1]
        num2 = dp[i-2]
        dp[i] = [num1[0]+num2[0],num1[1]+num2[1]]

    for i in inputlist :
        resutlt = dp[i]
        print(str(resutlt[0])+ " " + str(resutlt[1]))
```



