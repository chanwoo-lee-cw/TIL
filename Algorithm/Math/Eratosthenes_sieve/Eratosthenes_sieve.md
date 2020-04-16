# 에라토스테네스의 체

에라토스테네스의 체란 1~N까지의 범위 안의 모든 소수를 구하기 위한 방법이다.



## 알고리즘

1. 2부터 소수를 구하고자 하는 구간의 모든 수를 나열한다. 그림에서 회색 사각형으로 두른 수들이 여기에 해당한다.
2. 2는 소수이므로 오른쪽에 2를 쓴다. (빨간색)
3. 자기 자신을 제외한 2의 배수를 모두 지운다.
4. 남아있는 수 가운데 3은 소수이므로 오른쪽에 3을 쓴다. (초록색)
5. 자기 자신을 제외한 3의 배수를 모두 지운다.
6. 남아있는 수 가운데 5는 소수이므로 오른쪽에 5를 쓴다. (파란색)
7. 자기 자신을 제외한 5의 배수를 모두 지운다.
8. 남아있는 수 가운데 7은 소수이므로 오른쪽에 7을 쓴다. (노란색)
9. 자기 자신을 제외한 7의 배수를 모두 지운다.
10. 위의 과정을 반복하면 구하는 구간의 모든 소수가 남는다.

<img src="./Eratosthenes.gif" alt="에라토스테네스의 체"/>

[^출처]: [위키피디아- 에라토스테네스의 체](https://ko.wikipedia.org/wiki/에라토스테네스의_체)

### 코드

```python
def prime_list(n):
    sieve = [True] * n

    m = int(n ** 0.5)
    for i in range(2, m + 1):
        if sieve[i] == True:           
            for j in range(i+i, n, i): 
                sieve[j] = False

    return sieve
```





## 최적화된 에라토스테네스의 체

최적화는 N<sup>0.5</sup>를 이용하는데

합성수 N이 p*q로 표현이 된다면 p,q 둘 하나는  N<sup>0.5</sup>의 이하,  나머지 하나는  N<sup>0.5</sup>의 이상이기 때문에

1<= N<sup>0.5</sup>까지만 조사해도 된다.

### 코드

```python
def eratosthenes(n) :
    sieve = [True] * (n + 1)
    sieve[0] = False
    sieve[1] = False

    sqrtn = int(n**0.5)

    for i in range(2,sqrtn) :
        if(sieve[i]) :
            for j in range(i**2,n+1,i) :
                sieve[j] = False

    return sieve
```







## 출처

[위키피디아- 에라토스테네스의 체]([https://ko.wikipedia.org/wiki/%EC%97%90%EB%9D%BC%ED%86%A0%EC%8A%A4%ED%85%8C%EB%84%A4%EC%8A%A4%EC%9D%98_%EC%B2%B4](https://ko.wikipedia.org/wiki/에라토스테네스의_체))

[crosus- 최적화된 에라토스 테네스의 체](https://www.crocus.co.kr/576)