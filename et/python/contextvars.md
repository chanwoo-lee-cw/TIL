# contextvars



## contextvars란?

각 실행 스레드 또는 비동기 작업에 고유한 "컨텍스트"를 제공합니다. 이 컨텍스트는 전역 변수처럼 작동하지만, 각 스레드 또는 비동기 작업에 독립적으로 유지됩니다.



## 주요 메소드

### ContextVar(name, /, *, default=NOT_SET)**

- 새로운 **`ContextVar`** 객체를 생성합니다.
- **`name`**: 변수의 이름입니다.
- **`default`**: (선택적) 변수의 기본값입니다. 기본값이 제공되지 않으면, **`LookupError`**가 발생할 수 있습니다.

```python
test = contextvars.ContextVar('request_id', default=0)
```



### **get(default=NOT_SET)**:

- 현재 컨텍스트에서 **`ContextVar`**의 값을 반환합니다.
- **`default`**: (선택적) 컨텍스트에 값이 설정되어 있지 않은 경우 반환될 기본값입니다.

```python
print(test.get())
```



### **set(value)**:

- 현재 컨텍스트에 **`ContextVar`**에 새로운 값을 설정합니다.
- **`value`**: 설정할 값입니다.
- 이 메소드는 이전 값의 토큰을 반환합니다. 이 토큰은 나중에 **`reset()`** 메소드를 사용하여 원래 값으로 돌아갈 때 사용됩니다.

```python
test.set(1)
```



### **reset(token)**:

- **`set()`** 메소드에 의해 변경된 **`ContextVar`**의 값을 이전 값으로 되돌립니다.
- **`token`**: **`set()`** 메소드가 반환한 토큰입니다. 이 토큰은 변경된 값을 원래 값으로 되돌리는 데 사용됩니다.

```python
temp = test.set(1)

test.reset(temp)
```





## 예시

```python
import asyncio
import contextvars

# ContextVar 객체 생성
request_id = contextvars.ContextVar('request_id', default=0)
all = contextvars.ContextVar('all', default="")

async def process_request(id):
    # 현재 컨텍스트에 request_id 설정
    request_id.set(id)
    # 다른 비동기 작업을 기다림
    await asyncio.sleep(1)
    # 현재 컨텍스트의 request_id 출력
    print(f"Processing request: {request_id.get()}")
    if id == 2:
        all.set(f"test{id}")
    print(f"Processing all: {all.get()}")

async def main():
    # 동시에 여러 비동기 작업 실행
		all.set("test_all")
    await asyncio.gather(
        process_request(1),
        process_request(2),
        process_request(3)
    )
    print(f"Processing all: {all.get()}")

asyncio.run(main())
```

```
Processing request: 1
Processing all: test_all
Processing request: 2
Processing all: test2
Processing request: 3
Processing all: test_all
Processing all: test_all
```





## Flask인 경우 g와 차이점

Flask에서 **`g`** 변수와 **`contextvars`** 모듈을 사용하는 것 사이에는 몇 가지 주요 차이점이 있습니다. 이 두 방식은 애플리케이션의 컨텍스트에 따라 특정 정보를 저장하고 접근하는 방법입니다.

1. **g 변수**:
    - **`g`**는 Flask에서 제공하는 글로벌 객체로, 한 요청의 생명주기 동안에만 유효합니다.
    - **`g`**는 현재 요청에 대한 정보를 저장하고, 이 정보는 해당 요청이 처리되는 동안에만 접근 가능합니다.
    - 각 요청은 고유한 **`g`** 객체를 가지며, 다른 요청의 **`g`** 객체와 데이터를 공유하지 않습니다.
    - **`g`**는 Flask 애플리케이션에서 전역 변수처럼 사용되지만, 실제로는 각 요청에 대해 로컬로 유지됩니다.
2. **contextvars 모듈**:
    - **`contextvars`**는 Python 3.7 이상에서 사용할 수 있는 표준 라이브러리 모듈입니다.
    - 이 모듈은 요청 또는 스레드 별로 데이터를 분리하여 저장하는 데 사용됩니다. 이는 비동기 프로그래밍과 멀티스레딩 환경에서 특히 유용합니다.
    - **`contextvars`**를 사용하면 각 스레드나 비동기 작업마다 고유한 컨텍스트를 가질 수 있으며, 이 컨텍스트는 다른 스레드나 작업과 격리됩니다.
    - **`contextvars`**는 Flask와 직접적인 관련이 없으며, 보다 일반적인 Python 애플리케이션에서 사용됩니다.

**차이점**:

- **용도**: **`g`**는 Flask 내부에서 요청별 상태를 관리하기 위해 설계되었으며, 주로 Flask의 요청 처리 파이프라인 내에서 사용됩니다. 반면, **`contextvars`**는 Flask에 국한되지 않고, Python 애플리케이션 전반에서 멀티스레딩이나 비동기 프로그래밍을 지원하는 데 사용됩니다.
- **범위와 생명주기**: **`g`**의 생명주기는 하나의 요청에 국한되지만, **`contextvars`**는 요청뿐만 아니라 비동기 작업과 스레드에 대해서도 별도의 컨텍스트를 제공합니다.
- **활용 환경**: **`g`**는 Flask 애플리케이션에서 주로 사용되는 반면, **`contextvars`**는 Flask에 국한되지 않고 다양한 환경에서 사용될 수 있습니다.



## 메소드

- `contextvars.copy_context()`

이 함수는 현재 실행 중인 컨텍스트의 "얕은 복사본(shallow copy)"을 생성합니다. 이 복사본은 현재 컨텍스트의 상태를 그대로 복제하지만, 복제된 컨텍스트는 독립적으로 작동합니다.

### **주요 사용 사례**

**`contextvars.copy_context()`**의 주요 사용 사례는 멀티스레딩 또는 비동기 프로그래밍에서 특정 컨텍스트의 상태를 다른 스레드나 비동기 작업으로 전달할 때입니다. 이를 통해 각 스레드나 작업이 자신만의 컨텍스트를 가지면서도, 특정 시점의 컨텍스트 상태를 공유할 수 있습니다.

### **작동 방식**

- **얕은 복사**: **`copy_context()`**는 현재 컨텍스트의 상태를 복사하지만, 복사된 컨텍스트 내의 **`ContextVar`** 객체들은 원본과 동일한 객체를 참조합니다. 이 말은, 컨텍스트 내부의 변수들이 가리키는 데이터 자체는 복사되지 않는다는 의미입니다.
- **독립적인 컨텍스트**: 복사된 컨텍스트는 원본 컨텍스트와 독립적입니다. 이는 원본 컨텍스트에서 발생하는 변경사항이 복사된 컨텍스트에 영향을 주지 않으며, 반대의 경우도 마찬가지라는 것을 의미합니다.

```python
import contextvars

var = contextvars.ContextVar('var')
var.set('original')

# 현재 컨텍스트의 복사본 생성
ctx_copy = contextvars.copy_context()

# 복사된 컨텍스트에서 'var'의 값을 변경
ctx_copy.run(lambda: var.set('copied'))

# 원본 컨텍스트에서 'var'의 값은 여전히 'original'
print(var.get())  # 출력: original

# 복사된 컨텍스트에서 'var'의 값은 'copied'
print(ctx_copy.run(var.get))  # 출력: copied
```