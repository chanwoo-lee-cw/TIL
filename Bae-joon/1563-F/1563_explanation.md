## 백준 2169번 풀이

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



그래서, 이번엔 연속이라는 것에 착안해서 와인잔 문제를 생각했다. 그 이전것과 같이 계산하는 건데 그것을 생각하면 지각을 어떻게 세야할지 감이 잡히지 않아서 이것도 컷이였다.



하지만, 여기서 감을 좀 잡았는데 지각이 문제라면 지각을 다른 변수로 즉 2차원 배열로 빼는 것으로 고려해봤다.

```pyhton
dp[o][a]
```

이런 방식으로 하지만, 이것도 또 막혔는데 지각 처리가 좀 곤란하다는 문제였다.

그래서 지각도 밖으로 뺐다.



하지만, 이제 풀 수 있을거 같은 점에서 막혔다.

예를 들면 n날에 출석을 했다 치면

```pseudocode

```







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

```

