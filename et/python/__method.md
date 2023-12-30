1. **`__init__(self, [...])`**:
    - **목적**: 클래스의 새 인스턴스를 초기화.
    - **사용 사례**: 인스턴스별 속성을 설정하거나 객체가 생성될 때 필요한 작업을 수행하는 데 사용된다. 예를 들면 인스턴스의 초기값을 설정하는데 사용된다.
    
    ```python
    class MyClass:
        def __init__(self, value):
            self.value = value
    
    # MyClass의 인스턴스 생성
    instance = MyClass(10)
    print(instance.value)  # 출력: 10
    ```
    
2. **`__call__(self, [...])`**:
    - **목적**: 클래스의 인스턴스가 함수처럼 동작하도록 합니다.
    - **사용 사례**: 수학적 함수를 나타내기 위해 설계된 클래스가 있다면 `__call__`을 사용하여 이 클래스의 인스턴스를 함수처럼 호출하는데 사용하거나, 클래스가 호출될 때 추가 기능을 추가하는데 사용된다.
    
    ```python
    class Adder:
        def __init__(self, initial):
            self.initial = initial
    
        def __call__(self, x):
            return self.initial + x
    
    # 인스턴스 생성
    adder = Adder(5)
    # 인스턴스를 함수처럼 사용
    print(adder(10))  # 출력: 15
    ```
    
    ```python
    class Adder:
        def __init__(self):
            self.history = []
    
        def __call__(self, x, y):
            result = x + y
            # Logging the operation as an add-on
            self.history.append(f"Added {x} and {y} to get {result}")
            return result
    
        def show_history(self):
            return self.history
    
    # Creating an instance of Adder
    adder = Adder()
    
    # Using the instance as a callable with add-ons
    print(adder(3, 4))  # Output: 7
    print(adder(10, 20)) # Output: 30
    
    # Displaying the history of operations (the add-on effect)
    print(adder.show_history())
    # Output: ['Added 3 and 4 to get 7', 'Added 10 and 20 to get 30']
    ```
    
3. **`__str__(self)`**:
    - **목적**: 객체의 인간이 읽을 수 있는 문자열 표현을 반환합니다.
    - **사용 사례**: 로깅이나 디버깅에 자주 사용되며, 객체에 대한 인간 친화적인 설명이 도움이 됩니다.
    
    ```python
    class Number:
        def __init__(self, value):
            self.value = value
    
        def __str__(self):
            return f"this is {self.value}"
    
    num = Number(3)
    
    print(num)
    ```
    
    ```
    # output
    this is 3
    ```
    
4. **`__repr__(self)`**:
    - **목적**: 객체의 모호하지 않은 문자열 표현(종종 객체를 재생성하는 데 사용될 수 있는 표현)을 반환합니다.
    - **사용 사례**: 디버거와 개발자에게 유용하며, 객체 상태를 이해하고 재생성하는 데 도움이 됩니다.
    
    ```python
    class Number:
        def __init__(self, value):
            self.value = value
    
        def __repr__(self):
            return f"this is {self.value}"
    
    num = Number(3)
    
    print(num)
    ```
    
5. **`__len__(self)`**:
    - **목적**: 컨테이너의 길이를 반환합니다.
    - **사용 사례**: 사용자 정의 컨테이너 또는 컬렉션 클래스를 생성하는 경우 `__len__`을 구현하면 파이썬의 내장 함수 `len()`이 컨테이너에 있는 항목의 수를 반환할 수 있습니다.
6. **`__del__(self)`**:
    - **목적**: 인스턴스가 파괴될 때 호출됩니다.
    - **사용 사례**: 파일이나 네트워크 연결과 같은 외부 리소스를 해제하는 데 사용될 수 있습니다.
7. **반복 메소드 (`__iter__(self)` 및 `__next__(self)`)**:
    - **목적**: `__iter__`는 반복자 객체를 반환하고 `__next__`는 반복의 다음 항목을 반환합니다.
    - **사용 사례**: 이러한 메소드를 구현하면 클래스를 for 루프와 같이 반복할 수 있습니다.
8. **항목 접근 메소드 (`__getitem__(self, key)`, `__setitem__(self, key, value)`, `__delitem__(self, key)`)**:
    - **목적**: 항목에 접근, 설정 또는 삭제하는 방법을 정의합니다.
    - **사용 사례**: 이 메소드들을 클래스에 구현하면 컬렉션 또는 매핑 유형(리스트나 딕셔너리와 같은)처럼 동작합니다.
9. **컨텍스트 관리자 메소드 (`__enter__(self)` 및 `__exit__(self, exc_type, exc_value, traceback)`)**:
    - **목적**: `with` 문의 시작과 끝에서 발생하는 작업을 정의합니다.
    - **사용 사례**: 파일 열기 및 닫기와 같은 리소스 관리에 유용합니다.
10. *비교 메소드 (`__eq__(self, other)`, `**ne**(self