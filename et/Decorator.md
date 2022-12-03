# Decorator

분류: Python
소분류: Decorator
작성일시: 2021년 12월 6일 오후 11:14
최종 편집일시: 2022년 12월 3일 오후 11:45

# 목차

# 함수형 데코레이터

## 중간에 함수를 실행하는 데코레이터

```python
def wrapping(func):
    def _wrapper(*args, **kwargs):
        print("wrapper_start")
        func(*args, **kwargs)
        print("wrapper_end")

    return _wrapper    # ()을 붙히면 'NoneType' object is not callable 에러 발생

@wrapping
def test(name):
    print(name, "this test")

test("Mr.kim")
```

```python
# 출력
wrapper_start
Mr.kim this test
wrapper_end
```

함수의 시작과 전에 데코레이터를 사용하여 뭔하는 사전 작업을 하기 위한 데코레이터

```python
def wrapping_second(func):
    def _wrapper(*args, **kwargs):
        print("wrapping_second_start")
        func(*args, **kwargs)
        print("wrapping_second_end")

    return _wrapper

def wrapping(func):
    def _wrapper(*args, **kwargs):
        print("wrapper_start")
        func(*args, **kwargs)
        print("wrapper_end")

    return _wrapper

@wrapping_second
@wrapping
def test(name):
    print(name, "this test")

test("Mr.kim")
```

```python
# 출력
wrapping_second_start
wrapper_start
Mr.kim this test
wrapper_end
wrapping_second_end
```

이런 식으로 데코레이터를 여러 개 감쌀 때는 위에 있는 데코레이터부터 순차적으로 실행된다.

## 입력된 문장에 따라 함수의 반환 여부를 데코레이터

```python
def wrapping(func):
    def _wrapper(*args, **kwargs):
        if "Mr.kim" in args:
            return "Error"
        else:
            return func(*args, **kwargs)

    return _wrapper

@wrapping
def test(name):
    return f'{name} this test'

print(test("Mr.kim"))
print(test("Mr.Lee"))
```

```python
# Mr.kim이 포함되어 있으므로 함수를 실행시키지 않고 "Error" 문자열을 실행
Error
# Mr.kim이 포함되어 있지 않았으므로 함수를 실행시킨다.
Mr.Lee this test
```

데코레이터에 의해 사전에 검사해서 잘못된 문장이 있거나 하면, 출력을 하지 않거나 return을 하지 않음으로써 결과값을 돌려주지 않을 수도 있다.

## 매개변수가 있는 함수형 데코레이터

```python
from functools import wraps
from typing import Optional

def decorator(func_name: Optional[str] = None):
    def wrapping(func):
        @wraps(func)
        def __wrapper(*args, **kwargs):
            print(f"===={func_name}===")
            result = func(*args, **kwargs)
            return result
        return __wrapper
    return wrapping

@decorator(func_name="test_fuction")
def test_fuction():
    print("here is in test_fuction")

test_fuction()
```

```python
# 매개 변수로 전달된 함수 이름인 test_fuction를 출력한 다음에 함수가 실행된다.
====test_fuction===
here is in test_fuction
```

원하는 매개변수를 전달해서 함수 선언 이전에 전달하는 것이 가능하다.

# 클래스형 데코레이터

```python
print("all_pre")

class Wrapping:
    def __init__(self, func):
        print("Wrapping__init__")
        self.func = func

    def __call__(self, *args, **kwargs):
        print("Wrapping__call__")
        self.func(*args, **kwargs)
        print("Wrapping__call__end")

print(1)

@Wrapping
def test(name):
    print(f'{name} this test')

print(2)

test("Mr.kim")
test("Mr.lee")
```

```python
all_pre
1
Wrapping__init__
2
Wrapping__call__
Mr.kim this test
Wrapping__call__end
Wrapping__call__
Mr.lee this test
Wrapping__call__end
```

일단 순서대로 출력된 것을 살펴보면

`class Wrapping`이 선언되지만, 초기화는 되지 않았지만, `def test(name)`의 `@Wrapping`을 만나서 그제서야 `Wrapping`클래스가 초기화 된다. 그 이후에 `test`가 선언되면서 `Wrapping` 클래스 안의 `__call__`을 실행하면서 랩핑이 실행되게 된다.

```python
class Wrapping:
    def __init__(self, func):
        self.func = func

    def __call__(self, *args, **kwargs):
        if "Mr.kim" in args:
            return "Error"
        else:
            return self.func(*args, **kwargs)

@Wrapping
def test(name):
    return f'{name} this test'

print(test("Mr.kim"))
print(test("Mr.lee"))
```

```python
# 출력
Error
Mr.lee this test
```

이것도 마찬가지로 return 을 하거나 하지 않음으로써 함수의 결과값을 반환하지 않을 수도 있다.

`(*args, **kwargs)` 라는 매개변수를 저장해 주는 변수를 이용해서 먼저 초기화가 필요한 경우 초기화를 먼저 시켜주츤 방법도 있는데

```python
def wrap(func):
    def _wrapping(*args, **kwargs):
        print(1)
        args[0].set_str_()
        return func(*args, **kwargs)

    return _wrapping

class Test:
    def __init__(self):
        print(0)
        pass

    def set_str_(self):
        print(2)
        self.str = "test"

    @wrap
    def print_test(self):
        print(self.str)

test = Test()
test.print_test()
```

```
# 출력
0
1
2
test
```

1. `Test()`가 초기화 된 다음에 `test.print_test()`가 호출
2.  `@wrap` 데코레이터에 의해 wrap함수를 실핼
3. wrap안의 `args[0].set_str_()`를 호출한다.
    1. 이것이 되는 이유는 파이썬의 클래스 내 매서드의 항상 첫번째 매개변수는 자기 자신의 클래시은 `self` 가 전달되기 때문이다.
    2. `self.str`에 인스턴스를 할당
4. `print_test()` 를 통해 `self.str` 출력