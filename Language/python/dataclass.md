# python의 DataClass



## 사용 방법

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



## 개요

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



## `field()` 함수의 주요 매개변수

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



### 초기화

- 직접 초기화와 `default` 초기화의 차이

    ```python
    @dataclass
    class Item:
        name :str = ""      # 직접 초기화
        name :str = field(default="")   # default
    ```

    `name: str = ""` 와 `name: str = field(default="")` 의 선언 방식은 사실상 동일하디

    다만 더 복잡한 설정을 사용하고 싶다면 `field()` 초기화를 통해 더 복잡한 설정을 추가할 수 있다.
    
    **즉, 불변 객체의 할당은 사실상 동일하게 작동한다.**

- 직접 초기화와 `default_factory` 초기화의 차이

  ```python
  from optional import List
  
  @dataclass
  class Item:
      storys :List[str] = []      # 직접 초기화
      storys :List[str] = field(default_factory=list)   # default
  ```

  이 설정 방식의 차이는 굉장히 다르다.

  `storys :List[str] = []` 방식은 클래스 정의 시점에 단일 리스트 객체를 생성하여 모든 인스턴스가 이 객체를 공유한다. 만약, 여러 인스턴스가 이 필드를 공유한다면, 한 인스턴스에서 리스트를 변경할 때 다른 인스턴스에서도 그 변경이 반영된다.

  `storys :List[str] = field(default_factory=list)` 방식은 `default_factory`를 사용하여 각 인스턴스가 고유한 리스트 객체를 생성하도록 한다. 객체를 생성할 때마다 새로운 리스트를 생성하므로, 각 인스턴스가 독립적인 리스트를 생성한다.

  **즉, 가변 객체의 `default_factory` 은 각 인스턴스가 고유한 객체를 가지도록 보장할 수 있고, 만약 직접할당을 하게 되면 버그가 발생할 수 있다.**

### Meta Data

`metadata`는 필드 자체의 동작에는 영향을 미치지 않지만, 추가적인 메타정보를 저장하고 접근하는 데 사용된다. 예를 들면 필드의 단위, 설명, 또는 특수 태그를 저장하는데 사용할 수 있다.

```python
from dataclasses import dataclass, field

@dataclass
class Product:
    name: str
    price: float
    weight: float = field(metadata={"unit": "kg"})  # 무게의 단위를 메타데이터로 저장
```

```python
product = Product(name="Laptop", price=1200.0, weight=1.5)

# 메타데이터에 접근
print(product.__dataclass_fields__['weight'].metadata)
```

다만, 새로운 인스턴스를 사용해서 저장하는 것과 메타 데이터는 사용해서 저장하는 것의 장점은 이런 점이 있다.

- 메타 데이터는 부가적인 정보를 제공하지만, 그 자체로는 클래스의 동작에 직접적인 영향을 주지 않는다.
  - **도구 및 라이브러리와의 통합**: 일부 라이브러리나 도구는 데이터 클래스의 필드를 다룰 때 `metadata`를 참조하여 추가 작업을 수행합니다. 예를 들어, ORM(Object-Relational Mapping) 라이브러리에서는 필드의 데이터베이스 칼럼 정보를 `metadata`로 저장할 수 있다.
  - **유효성 검사 및 문서화**: 필드에 대한 설명, 단위, 유효성 검사 규칙 등을 메타데이터로 제공하여 코드를 문서화하거나 유효성 검사 시 활용할 수 있습니다.
- **캡슐화**: `metadata`는 필드의 추가 정보를 저장하면서도, 이 정보를 인스턴스 수준에서 다루지 않도록 캡슐화할 수 있습니다. 즉, 인스턴스 데이터를 오염시키지 않으면서 필드에 대한 메타 정보를 유지할 수 있습니다. 즉, 인스턴스 데이터와 독립적으로 메타 정보를 관리할 수 있어, 클래스의 설계가 더 깔끔해진다.
- 메타 데이터를 사용하는것이 추천되는 경우
  - **사용 권장**: 필드에 대한 설명이나 유효성 검사 규칙, 단위 등과 같은 부가적인 메타 정보를 저장할 때.
  - **사용하지 않는 경우**: 필드의 동작에 직접적으로 관련된 중요한 데이터를 저장할 때는 인스턴스 필드로 저장하는 것이 더 적합합니다.



즉, 메타데이터는 철저하게 부가적인 설명을 저장하는데 사용되는걸 추천하고, 해당 데이터를 사용하는데 필요한 정보라면 새로운 필드를 선언하여 사용하는게 좋다.





## 주의 사항

- 데이터 클래스는 주로 데이터 저장을 위해 사용되므로, 로직을 포함한 복잡한 메서드는 작성하지 않는 것이 좋습니다.
- 가변 타입(예: 리스트, 딕셔너리)을 기본값으로 사용하고자 할 때는 `field(default_factory=...)`를 사용해야 합니다.

