## 백준 1563번 풀이

https://www.acmicpc.net/problem/1563

### 문제

*백준중학교에서는 학기가 끝날 무렵에 출결사항을 보고 개근상을 줄 것인지 말 것인지 결정한다. 이 학교는 이상해서 학생들이 학교를 너무 자주 빠지기 때문에, 개근상을 주는 조건이 조금 독특하다.*

*출결사항이 기록되는 출결은 출석, 지각, 결석이다.*

*개근상을 받을 수 없는 사람은 지각을 두 번 이상 했거나, 결석을 세 번 연속으로 한 사람이다.*

*한 학기가 4일이고, O를 출석, L을 지각, A를 결석이라고 했을 때, 개근상을 받을 수 있는 출결정보는*

```
OOOO OOOA OOOL OOAO OOAA OOAL OOLO OOLA OAOO OAOA 
OAOL OAAO OAAL OALO OALA OLOO OLOA OLAO OLAA AOOO 
AOOA AOOL AOAO AOAA AOAL AOLO AOLA AAOO AAOA AAOL
AALO AALA ALOO ALOA ALAO ALAA LOOO LOOA LOAO LOAA 
LAOO LAOA LAAO
```

*총 43가지이다.*

*한 학기는 N일이다. N이 주어졌을 때, 개근상을 받을 수 있는 출결정보의 개수를 세는 프로그램을 작성하시오.*

### 입력

*첫째 줄에 N이 주어진다. N은 1,000보다 작거나 같다.*

### 출력

*첫째 줄에 정답을 1,000,000으로 나눈 나머지를 출력한다.*

***



### 풀이

일단 처음에는 뒤에 붙히는 방식으로 규칙을 찾아보려고 했지만, 도저히 규칙을 찾을 수 없었다.

그래서 규칙을 찾아보려고 했지만



그래서 이번에는 조합문제인가 싶어서 n개중에서 지각 2개를 뽑고 결석 이어져 있는 결석 3개를 전부 뽑아서 배치하는 경우를 고려해봤다.

하지만, 이것도 중복이 너무 많이 생기는 문제가 생겼다.



그래서, 이번엔 연속이라는 것에 착안해서 와인잔 문제를 생각했다. 그 이전것과 같이 계산하는 건데 그것을 생각하면 지각을 어떻게 세야할지 감이 잡히지 않아서 이것도 아니였다.



도무지 감이 잡히지 않아서 일단 인터넷을 찾아보았더니, 1차원 배열이 아닌 4차원 배열을 하는 것을 보았다.

```pyhton
dp[o][a][L]
```



하지만, 이제 풀 수 있을거 같았지만 또 막혔다.

예를 들면 n날에 출석을 했다 치면

```pseudocode
dp[n][0][0] = dp[n-1][0][0] + dp[n-2][1][0] 
			+ dp[n-2][0][1] + dp[n-3][1][1] + dp[n-3][0][2] + dp[n-4][1][2]
```

엄청 다양한 경우의 수를 전부 더해야 한다.

거기다,  n날의 하루 지각한 날, 하루 결석한날, 하루 결석하고 하루 지각한날... 등등의 경우의 수를 전부 세려면

코드가 지나치게 길어지는 문제가 있었다.

그리고 푼 코드를 제출해 보니 런타임 에러



일단 다른 사람이 제출한 파이선 코드의 길이를 보니 내가 푼 것에 비해 압도적으로 짧았다.



그래서 다른 사람의 코드를 분석해 보았다.

```python
import sys

input = sys.stdin.readline
sys.setrecursionlimit(1000000000)
memo = {}  # memoization

def dp(total, day, late, abs):
    ...

if __name__ == "__main__":
    total = int(input().strip())
    # 출석 일 수, 지각수, 연속 결석 수
    print(dp(total, 0, 0, 0) % 1000000)
```

일단 입력 시간을 줄이기 위해서 input을 readline으로 오버라이딩 했다.



이걸 몰랐었는데

```python
sys.setrecursionlimit(1000000000)
```

이게 바로 파이썬의 재귀 호출의 한계를 늘려주는 코드였다.

파이썬의 재귀호출의 스택 깊이 한계치는 1000, 하지만 이 코드를 이용하면 재귀호출의 한계를 1000000000 으로 늘려주는 코드였다.



그리고 메모제이션을 해주기 위해서 리스트 대신 딕셔너리를 선언하였다.

나는 3중 배열을 통해 각각 배열의 값을 0으로 초기화 시켜주고 하나하나 채워주는 방식을 생각했는데, 딕셔너리를 이용하면 (O,A,L) 이 3가지 값을 키 삼아 현재 출결 상황에 대한 경우의 수를 값으로 저장할 수 있다.



그리고 main 부분이 눈에 띄었는데

```python
def dp(total, day, late, abs):
```

즉, 내가 한 코드는 출석일을 계산하는 코드였지만, 이 코드는 아예 출석일을 계산하지 않고 현재 날짜만 계산하는 코드를 사용했다. 즉, 출석, 결석, 지각 전부 더한 현재의 날짜가 day라는 날자로 대신했다.



그 다음 dp부분

```python
def dp(total, day, late, abs):
    # 지각이 2번 이상이면 fail
    if 2 <= late:
        return 0

    # 결석 3연속이면 fail
    if 3 <= abs:
        return 0

    # 개근상 조건 충족
    if day == total:
        return 1

    # memoization 기록 가져오기
    # day, late, abs가 다음과 같은 값일 경우 개근상을 받을 수 있는 경우의 수
    if (day, late, abs) in memo:
        return memo[(day, late, abs)]

    # day째 되는 날에, 그 이후 날들에 출석하거나 지각하거나,
    # 결석하였을 때 개근상을 받을 수 있는 경우의 수에 대한 기록
    memo[(day, late, abs)] = dp(total, day + 1, late, 0) + 
    		dp(total, day + 1, late + 1, 0) + dp(total, day + 1, late, abs + 1)
    return memo[(day, late, abs)]
```





```python
	if 2 <= late:
        return 0

    # 결석 3연속이면 fail
    if 3 <= abs:
        return 0
```

일단 초반의 조건은 만약 결석이 되 버린다면 더 이상 재귀 탐색을 멈추고 0을 리턴해 줘서 더이상 이쪽으로 탐색은 의미 없다는 의미이다.



``` python
    # 개근상 조건 충족
    if day == total:
        return 1
```

그리고 만약 전체 날짜가 요청된 날짜와 일치하게 된다면 개근상을 받는다는 것을 알려주고 1을 리턴해서 여 조건에서는 개근상을 받을 수 있다는 것을 알려주고, 이 경우에 받을 수 있다는 갯수를 하나 추가해준다.



그리고 dp부분은 알고 있으니 넘어가고

```python
    # day째 되는 날에, 그 이후 날들에 출석하거나 지각하거나,
    # 결석하였을 때 개근상을 받을 수 있는 경우의 수에 대한 기록
    memo[(day, late, abs)] = dp(total, day + 1, late, 0) + 
    		dp(total, day + 1, late + 1, 0) + dp(total, day + 1, late, abs + 1)
    return memo[(day, late, abs)]
```

이 부분에서 굉장히 이해가 안 됬었는데, 단순히 갯수만 세면 되는 지각과 달리 결석은 연속된 날짜만 세야 되는데, 이런 방법으로 하면 결석된 날자를 세지 못하는 것이 아닌가 라는 생각을 했었다.



그래서 한참을 고민하고 직접 갯수를 세어가며 해본 결과 답이 나왔는데

예를 들면 4일이라고 하고 첫날에 결석을 2번 하고 나머지는 쭉 결석을 했다고 생각해 보자

그럼 LLOO 가 되는데, 이건 memo[(4,0,0)]에 포함이 된다. 이미 이전의 dp에서 결석된 날짜를 이미 더해주었기 때문이다. 

그럼 OOLL의 경우에는 어떻게 되나면 이건 memo[(4,0,2)]에 포함이 되어있기 때문이다.



### 전체 코드

```python
import sys

input = sys.stdin.readline
sys.setrecursionlimit(1000000000)
memo = {}  # memoization


def dp(total, day, late, abs):
    # 지각이 2번 이상이면 fail
    if 2 <= late:
        return 0

    # 결석 3연속이면 fail
    if 3 <= abs:
        return 0

    # 개근상 조건 충족
    if day == total:
        return 1

    # memoization 기록 가져오기
    # day, late, abs가 다음과 같은 값일 경우 개근상을 받을 수 있는 경우의 수
    if (day, late, abs) in memo:
        return memo[(day, late, abs)]

    # day째 되는 날에, 그 이후 날들에 출석하거나 지각하거나,결석하였을 때 개근상을 받을 수 있는 경우의 수에 대한 기록
    memo[(day, late, abs)] = dp(total, day + 1, late, 0) + dp(total, day + 1, late + 1, 0) + dp(total, day + 1, late,
                                                                                                abs + 1)
    return memo[(day, late, abs)]


if __name__ == "__main__":
    total = int(input().strip())
    # 출석 일 수, 지각수, 연속 결석 수
    print(dp(total, 0, 0, 0) % 1000000)

# 출처 : https://daimhada.tistory.com/179
```





### 시사점

- sys.setrecursionlimit(x)
  - x만큼의 재귀 호출의 깊이의 제한을 둔다.

