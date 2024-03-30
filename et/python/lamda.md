# Lamda

## Lamda를 사용하는 이유

- 가장 대표적인 이유는 함수 생성을 줄일 수 있기 때문, 딱 한줄짜리 코드를 사용하고자 함수를 만드는 건 비효율적이다.

## Lamada의 기본식

```python
lambda arguments: expression
```

- arguments : 함수로 전달할 인수
- expression : 함수에서 사용할 표현식

## 예제

1. **하나의 인수를 가지는 람다 함수:**
    
    ```python
    square = lambda x: x * x
    ```
    
2. 2개의 인수를 가지는 람다 함수
    
    ```python
    add = lambda x, y: x + y
    ```
    
3. 리스트 정렬
    
    ```python
    # 튜플의 두 번째 요소를 기준으로 리스트 정렬
    pairs = [(1, 'one'), (2, 'two'), (3, 'three'), (4, 'four')]
    pairs.sort(key=lambda pair: pair[1])
    ```
    
4. 조건부 함수 실행
    
    ```python
    # 두 숫자 중 더 큰 숫자 반환
    max_num = lambda x, y: x if x > y else y
    ```
    
5. 리스트 필터링
    
    ```python
    # 짝수만 필터링
    even_numbers = list(filter(lambda x: x % 2 == 0, numbers))
    ```
    
6. 여러 리스트에 대한 연산
    
    ```python
    # 두 리스트의 요소들을 더함
    sum_list = list(map(lambda x, y: x + y, list1, list2))
    ```
    
7. 예외 처리
    
    ```python
    # 람다 내에서 exception을 발생시키는 것은 불가능하지만 0으로 리턴한 다음에 에러 발생시키는건 가능
    safe_division = lambda x, y: x / y if y != 0 else 0
    ```