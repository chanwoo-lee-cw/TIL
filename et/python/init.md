# `__init__.py`

## `__init__.py`의 역할

1. **파이썬 패키지로 표시**:
    - 디렉터리에 `__init__.py` 파일이 있으면 해당 디렉터리가 파이썬 패키지로 취급되어야 한다는 것을 파이썬 측에 알린다. 이렇게 하면 모듈과 같은 방식으로 디렉터리를 가져올 수 있다. `__init*__*.py` 파일이 없으면 해당 디렉터리는 패키지로 인식되지 않고 해당 모듈을 가져올 수 없다.
2. **초기화 코드**:
    - 패키지를 가져올 때마다 `__init__.py` 파일을 실행하므로 패키지를 처음 가져올 때 한 번 실행해야 하는 초기화 코드에 적합한 장소이다. 여기에는 패키지 수준의 데이터를 설정하거나 초기화 상태를 설정할 수 있다.
3. **Imports 컨트롤**:
    - `__init__.py` 파일을 통해 패키지를 가져올 때 외부에 노출되는 내용을 제어할 수 있습니다. `from package import *`를 사용할 때 가져올 모듈 이름 목록이 될 수 있는 `__all__` 변수를 사용합니다.
4. **Import Paths 단순화**:
    - 패키지의 일부를 쉽게 접근할 수 있도록 `__init__.py`를 사용할 수 있다. 예를 들어, 깊게 중첩된 모듈이 있으면 패키지를 `__init__.py`로 가져올 수 있어 패키지 사용자가 전체 경로를 탐색하는 대신 패키지 레벨에서 바로 접근할 수 있다.
5. **Namespace 패키지 지원:**
    - Python 3.3부터 PEP 420은 **`__init__.py`** 파일이 없는 "암시적 네임스페이스 패키지"의 개념을 도입했습니다. 이를 통해 여러 디렉토리 또는 여러 프로젝트에 걸쳐 Python 패키지를 만들 수 있습니다.
    - 그럼에도 불구하고 **`__init__.py`**는 일반적인 (네임스페이스가 아닌) 패키지에 여전히 널리 사용됩니다.

## 작동 방식

패키지 구조

```markdown
myProject
├── module
│		├── __init__.py
│		└── in_module
│				├── __init__.py
│		    └── module_test.py
└── main.py
```

```python
# module/__init__.py
print("this is module_init.py")
```

```python
# module/in_module/__init__.py
print("this is in_module.py")
```

```python
# module/in_module/modue_test.py
from typing import Optional

def import_test(name : Optional[str]):
    print(name)
```

```python
# main.py
from module.in_module.modue_test import import_test

if __name__ == "__main__":
    import_test("call")
```

이렇게 생긴 패키지 구조를 실행했을때, 결과는 아래와 같이 나온다

```markdown
this is module_init.py
this is in_module.py
call
```

즉, `module/in_module/modue_test.py`를 import 했을 때

`module/__init__.py` → `module/in_module/__init__.py`  → `module/in_module/modue_test.py`

순으로 실행된다는 것을 알 수 있다.

## `__init__.py`의 역할 예시

### 1. 파이썬 패키지로 표시

1. 예시 1번

```markdown
myproject/
├── mypackage/
│   ├── __init__.py
│   ├── module1.py
│   └── module2.py
└── main.py
```

```python
# mypackage/module1.py
# 이 파일은 비어있거나 패키지 초기화 코드를 포함 가능
# 일단 선언하는 것만으로도 파이썬 패키지로 취급된다.
```

```python
# mypackage/module1.py
def function1():
    return "Function 1 in module1"
```

```python
# mypackage/module2.py
def function2():
    return "Function 2 in module2"
```

```python
# main.py
from mypackage import module1, module2

print(module1.function1())  # Accessing function1 from module1
print(module2.function2())  # Accessing function2 from module2
```

1. 예시 2번

```python
myproject/
├── mypackage/
│   ├── __init__.py
│   ├── config.py
│   ├── module.py
└── main.py
```

```python
# __init__.py
from .config import package_variable, package_function

print("Initializing mypackage")
```

```python
# mypackage/config.py
package_variable = "This is a package level variable"

def package_function():
    return "This is a package level function"
```

```python
# module.py
from .config import package_variable, package_function

def function():
		message = package_function()
		message2 = package_variable
    return f"Function in module, package_variable = {message2}, package_function = {message}"
```

```python
# main.py
import mypackage
from mypackage import module

print(mypackage.package_variable)  # Accessing the package-level variable
print(mypackage.package_function())  # Calling the package-level function

print(module.function())
```

`config.py`로 분리한 이유

1. **명확성과 관심사의 분리**:
    - **`__init__.py`**는 주로 패키지를 초기화하는 데 사용됩니다. 이는 필요한 모듈을 가져오기, 제어된 가져오기를 위해 **`__all__`**을 정의하기, 패키지에 필요한 시작 코드를 실행하기 등을 포함할 수 있습니다.
    - 별도의 **`config.py`**를 사용함으로써, 설정 또는 공유 변수를 초기화 논리로부터 분리할 수 있습니다. 이러한 분리는 각 파일이 명확하고 구별된 목적을 가지므로 코드베이스를 이해하고 유지 관리하기 쉽게 만듭니다.
2. **순환 가져오기 방지**:
    - 순환 가져오기는 두 모듈이 서로를 가져올 때 발생합니다. 이는 같은 패키지의 다른 모듈에 **`__init__.py`**에 정의된 패키지 수준 변수나 함수를 가져오려고 할 때 발생할 수 있습니다.
    - 별도의 **`config.py`**를 사용하면 이 문제를 피할 수 있습니다. 패키지 내의 모듈은 **`__init__.py`**의 패키지 초기화와 순환 의존성을 만들 위험 없이 **`config.py`**를 자유롭게 가져올 수 있습니다.
3. **확장성과 확장 가능성**:
    - 패키지가 성장함에 따라, 더 많은 설정이나 공유 리소스가 필요할 수 있습니다. 이러한 리소스를 별도의 **`config.py`** 파일에 유지하면 패키지의 초기화 파일을 복잡하게 만들지 않고도 이러한 리소스를 관리하고 확장하기 쉽습니다.
    - 설정을 확장하거나 수정하는 것이 패키지의 핵심 초기화 논리에 영향을 주지 않고 쉽습니다.
4. **더 큰 프로젝트에 대한 더 나은 조직**:
    - 더 큰 프로젝트의 경우, 전용 설정 파일을 사용하면 다양한 설정과 공유 리소스를 조직하는 데 도움이 됩니다. 이렇게 하면 프로젝트의 다른 개발자들이 패키지 초기화 세부 사항을 파고들지 않고도 설정을 쉽게 찾아 이해할 수 있습니다.
5. **일반적인 관행과 일치**:
    - 많은 Python 프로젝트, 특히 더 큰 프로젝트에서는 별도의 설정 파일을 가지는 것이 일반적입니다. 이 접근 방식은 그러한 관행과 일치하여 프로젝트 구조를 다른 Python 개발자들에게 더 직관적으로 만듭니다.

요약하자면, 공유 변수와 함수를 정의하기 위해 **`__init__.py`**를 사용할 수 있지만, 특히 더 크거나 복잡한 패키지의 경우 이 목적을 위해 전용 **`config.py`**(또는 유사한 것)를 사용하는 것이 종종 더 깨끗하고 유지 관리하기 쉬운 접근 방식입니다.

### 2. **초기화 코드**

패키지를 임포트할 때 파이썬은 패키지 디렉토리에서 **`__init__.py`** 파일을 찾습니다. 이 파일을 찾으면 파이썬은 그 안에 있는 모든 코드를 실행합니다. 이러한 동작은 **`__init__.py`** 파일을 패키지의 초기화자로 사용할 수 있게 합니다. **`__init__.py`**의 초기화 코드에 대한 몇 가지 핵심 포인트는 다음과 같습니다.

1. **일회성 초기화**: 패키지가 처음 임포트될 때 한 번 실행되어야 하는 코드를 위한 장소입니다. 이는 전역 변수 설정, 로깅 구성 또는 데이터베이스 연결과 같은 초기화를 포함할 수 있습니다.
2. **공유 리소스**: 패키지 내의 다양한 모듈에서 공유하는 리소스를 초기화하는 데 사용될 수 있습니다. 예를 들어, 여러 모듈이 공통 데이터베이스 연결을 사용하는 경우, **`__init__.py`**는 이 연결을 설정하고 다른 모듈은 이를 사용할 수 있습니다.
3. **패키지 수준의 행동**: 패키지 수준의 행동이나 속성을 정의할 수도 있습니다. 예를 들어, 패키지 수준의 구성 설정이나 패키지 내의 여러 모듈에 관련된 함수를 가질 수 있습니다.
- 예시

```python
db_operations/
├── __init__.py
├── connection.py
└── query.py
```

```python
# __init__.py
from .connection import create_connection

# 패키지가 임포트될 때 데이터베이스 연결 초기화
db_connection = create_connection()

print("데이터베이스 연결 초기화됨.")
```

```python
# connection.py
def create_connection():
    # 데이터베이스 연결 생성 로직을 위한 자리 표시자
    return "DatabaseConnectionObject"
```

```python
# query.py
from . import db_connection

def run_query(sql):
    # __init__.py에서 초기화된 db_connection 사용
    print(f"{db_connection}에서 실행하는 쿼리: {sql}")
```

### 3. import 제어

**`__init__.py`** 파일은 와일드카드 임포트(예: **`from package import *`**)를 사용할 때 패키지에서 어떤 모듈이나 심볼(클래스, 함수, 변수 등)이 외부에 노출되는지 제어하는 데 사용될 수 있습니다. 이는 다음과 같이 작동한다.

1. **`__init__.py`** 파일에서 **`__all__`**이라는 리스트를 정의할 수 있습니다. 이 리스트에는 와일드카드 임포트를 사용할 때 공개적으로 사용 가능하게 하려는 모듈과 심볼의 이름을 포함시키면 됩니다.
2. **내부 모듈 캡슐화**:
    - 임포트를 제어함으로써 패키지의 내부 구조를 캡슐화할 수 있습니다. 이는 공개 인터페이스가 일관되게 유지되는 한, 패키지의 내부 조직을 변경해도 사용자에게 영향을 미치지 않음을 의미한다.
3. **공개 인터페이스 간소화**:
    - 이 방법은 패키지의 공개 인터페이스를 간소화하는 데 도움이 됩니다. 사용자는 내부 모듈 구조를 몰라도 미리 정의된 모듈과 심볼을 임포트할 수 있습니다.
- 예시

```python
data_analysis/
├── __init__.py
├── query/
│   ├── __init__.py
│   └── query.py
└── connection.py
```

```python
# query/query.py
def insert(data):
		# insert query
    ...
```

```python
# connection.py
def create_connection():
    # 데이터베이스 연결 생성 로직을 위한 자리 표시자
    return "DatabaseConnectionObject"
```

```python
# data_analysis/__init__.py
from .query.query import insert
from .connection import create_connection

__all__ = ["insert", "create_connection"]
```

## 만들수 있는 예시

### DB 패키지

```python
analytics_project/
├── analytics_package/
│   ├── __init__.py
│   ├── database.py
│   ├── config_loader.py
│   └── analytics_module.py
└── main.py
```

```python
print("analytics 패키지 초기화 중")

# 예시 시작 작업
from .config_loader import load_config
from .database import connect_to_database

# 구성 설정 로드
config = load_config()

# 데이터베이스에 연결
database_connection = connect_to_database(config['database_settings'])

# 데이터베이스 연결을 패키지 수준에서 사용할 수 있도록 함
__all__ = ['database_connection', 'analytics_module']
```

```python
# analytics_package/__init__.py
def connect_to_database(settings):
    # 설정을 사용하여 데이터베이스 연결 시뮬레이션
    print("다음 설정으로 데이터베이스 연결:", settings)
    return "DatabaseConnectionObject"
```

```python
# analytics_package/config_loader.py
def load_config():
    # 구성 설정을 로드하는 것을 시뮬레이션
    print("구성 설정 로드 중")
    return {'database_settings': 'SomeDatabaseSettings'}
```

```python
# analytics_package/analytics_module.py
from . import database_connection

def analyze_data(data):
    # 데이터 베이스 연결 사용
    print(f"Using database connection: {database_connection} to analyze {data}")
    # 데이터 베이스 상호 작용 로직
    return f"Analysis result for {data}"
```

```python
import analytics_package

# 데이터베이스 연결 사용
db_connection = analytics_package.database_connection
print(f"db 연결 사용 중: {db_connection}")

# 패키지의 모듈에서 함수 사용
from analytics_package.analytics_module import analyze_data
print(analyze_data("샘플 데이터"))
```