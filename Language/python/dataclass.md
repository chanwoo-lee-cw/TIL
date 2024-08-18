# python의 DataClass



### 사용 방법

`@dataclass` 데코레이터를 사용하여 생성이 가능하다

```python
from dataclasses import dataclass

@dataclass
class Person:
    name: str
    age: int = 0			# Default 값
```

Person이라는 이라는 데이터 클래스 객체를 생성한다



```python
person1 = Person(name="Peter")
person2 = Person(name="Peter", age = 30)

print(person1)
print(person2)

print(person1 == person2)
```

```
// 출력값
Person(name='Peter', age=0)
Person(name='Peter', age=30)

False
```



### 개요

1. **자동 생성 메서드**: 선언하지 않아도 DataClass 자체적으로 생성하는 클래스
   1. `__init__` 메서드: 필드를 매개변수로 받아 객체를 초기화.
   2. `__repr__` 메서드: 객체를 사람이 읽기 쉬운 문자열로 반환. 위의 print 값이 예시
   3. `__eq__` 메서드: 두 객체가 동일한지 비교.
2. **`field` 옵션**:
   - 필드에 대해 추가적인 옵션을 설정할 수 있습니다. 
   - 예를 들어, 특정 필드를 `init=False`로 설정하여 `__init__` 메서드에서 제외할 수 있습니다.

```python
from dataclasses import dataclass, field

@dataclass
class Product:
    name: str
    price: float
    discount: float = field(default=0.0, init=False)

product = Product(name="Laptop", price=1000.0)
print(product)
```



### `field()` 함수의 주요 매개변수

1. **`default`**:

   - 필드의 기본값을 설정합니다.
   - 이 값을 설정하면 데이터 클래스 생성 시 해당 필드에 값을 제공하지 않아도 됩니다.
   - 예시: `field(default=0)`

   ```python
   python
   코드 복사
   @dataclass
   class Item:
       name: str
       price: float = field(default=10.0)
   ```

2. **`default_factory`**:

   - 가변 타입(예: 리스트, 딕셔너리, 집합 등)의 필드에 기본값을 설정할 때 사용합니다.
   - `default_factory`는 함수나 호출 가능한 객체를 받아서, 필드의 기본값을 동적으로 생성합니다.
   - 예시: `field(default_factory=list)`

   ```python
   @dataclass
   class Item:
       name: str
       tags: list = field(default_factory=list)
   ```

3. **`init`**:

   - 이 필드를 `__init__` 메서드의 인자로 받을지 여부를 결정합니다.
   - `init=False`로 설정하면 객체 생성 시 해당 필드를 초기화하지 않으며, 생성 후에 값을 할당해야 합니다.
   - 예시: `field(init=False)`

   ```python
   @dataclass
   class Item:
       name: str
       identifier: int = field(init=False)
   
       def __post_init__(self):
           self.identifier = hash(self.name)
   ```

4. **`repr`**:

   - 이 필드를 `__repr__` 메서드에서 포함할지 여부를 결정합니다.
   - `repr=False`로 설정하면 `__repr__` 출력에 해당 필드가 포함되지 않습니다.
   - 예시: `field(repr=False)`

   ```python
   @dataclass
   class Item:
       name: str
       secret_code: str = field(repr=False)
   ```

5. **`compare`**:

   - 이 필드를 객체 비교(`__eq__`, `__lt__`, `__le__`, `__gt__`, `__ge__`)에 사용할지 여부를 결정합니다.
   - `compare=False`로 설정하면 해당 필드는 비교에 사용되지 않습니다.
   - 예시: `field(compare=False)`

   ```python
   @dataclass
   class Item:
       name: str
       serial_number: int = field(compare=False)
   ```

6. **`metadata`**:

   - 사용자 정의 메타데이터를 필드에 추가할 수 있습니다.
   - 이는 주로 고급 사용 사례에서 필드와 관련된 추가 정보를 저장하는 데 사용됩니다.
   - 예시: `field(metadata={"unit": "kg"})`

   ```python
   @dataclass
   class Item:
       name: str
       weight: float = field(metadata={"unit": "kg"})
   ```



### 주의 사항

- 데이터 클래스는 주로 데이터 저장을 위해 사용되므로, 로직을 포함한 복잡한 메서드는 작성하지 않는 것이 좋습니다.
- 가변 타입(예: 리스트, 딕셔너리)을 기본값으로 사용하고자 할 때는 `field(default_factory=...)`를 사용해야 합니다.



