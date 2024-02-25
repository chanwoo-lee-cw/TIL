# `functools.lru_cache`

## lru_cache란?

> @functools.lru_cache(maxsize=128)은 함수의 반환 결과를 캐시하는 데코레이터이다. 최초 요청 이후에는 캐시한 결과를 반환한다. maxsize는 캐시할 수 있는 최대 개수를 의미하며 이를 초과할 때는 호출 빈도가 가장 작은 것부터 캐시에서 사라진다. 
>
> LRU는 ‘Least Recently Used’의 약자로, 최근에 참조되지 않은 데이터가 교체 시점에 먼저 나가는 방식이다.



## 사용 방법

`lru_cache` 데코레이터의 파라미터로는 대표적으로는 아래와 같은 값이 있다.

- `maxsize`: 캐시에 저장할 최대 결과 수입니다. maxsize는 기본적으로 2의 배수가 되도록 설정하는게 가장 이상적이다.
- `typed`: `True`로 설정하면, 함수에 전달되는 인자의 타입도 캐시의 키에 포함됩니다. 이는 같은 값이지만 다른 타입의 인자에 대해 다른 결과를 캐시하고 싶을 때 유용하다. 기본값은 `False`입니다.



## 예시

```python
from functools import lru_cache

@lru_cache(maxsize=128)
def fibonacci(n):
    if n < 2:
        return n
    return fibonacci(n-1) + fibonacci(n-2)

print(fibonacci(100))
```



## 캐시 정보와 캐시 지우기

- `cache_info()`: 현재 캐시의 상태에 대한 정보를 제공하는 메서드. `hits`, `misses`, `maxsize`, `currsize`를 포함한 명명된 튜플을 반환.
  - `hits`: 캐시 히트의 수. 캐시 히트란 요청된 함수의 인자에 대한 결과가 이미 캐시에 있어서, 다시 처리하지 않고 값을 반환할 수 있는 경우. 캐시 히트가 많을수록 캐시의 성능이 좋다.
  - `misses`: 캐시 미스의 수. 캐시 미스란 요청된 함수의 인자에 대한 결과가 캐시에 없어서, 다시 처리해야하는 경우. 캐시 미스가 발생하면 함수를 처음부터 다시 처리해야 되기 때문에 가능하면 줄이고, 줄이는 것이 캐시의 성능이 좋다
  - `maxsize`: 캐시에 저장할 수 있는 최대 결과의 수. 이 값은 `lru_cache` 데코레이터의 maxsize매개변수의 값이다. `maxsize`가 None이면, 캐시의 크기에 제한이 없다는 것을 의미.
  - `currsize`: 현재 캐시에 저장된 결과의 수. 캐시에 저장된 항목의 수를 나타내며, `maxsize` 이하의 값.
- `cache_clear()`: 캐시를 비우는 메서드.

```python
# 캐시 정보 출력
print(fibonacci.cache_info())

# CacheInfo(hits=98, misses=101, maxsize=128, currsize=101)
# 캐시 지우기
fibonacci.cache_clear()
```



## 주의 사항

- `lru_cache`는 인자가 해시 가능해야 한다. 즉, 인자로 리스트나 딕셔너리와 같은 변경 가능한 타입을 직접 사용할 수 없다.
  - 만약, 해시 불가능한 타입을 사용하는 경우 `TypeError` 가 발생
- 함수 호출 시에 사용되는 모든 인자와 키워드 인자는 캐시 키의 일부가 됩니다.