# `__init__.py`

## `__init__.py`의 역할

1. **파이썬 패키지로 표시**:
    - 주된 역할은 디렉토리를 Python 패키지로 표시하는 것입니다. 이를 통해 Python은 이러한 디렉토리에서 모듈을 가져올 수 있습니다. **`__init__.py`** 파일이 없으면 Python은 디렉토리를 패키지 경로의 일부로 인식하지 않으며 가져오기 오류가 발생합니다.
2. **초기화 코드**:
    - 패키지에 필요한 초기화 코드를 포함할 수 있습니다. 이 코드는 패키지가 Python 스크립트에서 가져올 때마다 실행됩니다.
    - 예를 들어, 패키지 수준 데이터를 초기화하거나 필요한 상태를 설정하는 데 사용할 수 있습니다.
3. **Imports 컨트롤**:
    - **`__init__.py`**에서 **`__all__`**을 정의하여 누군가 **`from package import *`**을 사용할 때 가져오는 것을 제어할 수 있습니다. 이는 공개 API를 노출하면서 내부 모듈을 숨기는 데 유용합니다.
4. **Import Paths 단순화**:
    - 이 파일은 패키지 수준에서 더 깊은 모듈 수준의 클래스, 함수 및 변수를 가져오는 데 사용할 수 있습니다. 이렇게 하면 다른 스크립트나 패키지가 사용해야 하는 가져오기 경로를 단순화할 수 있습니다.
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
def connect_to_database(settings):
    # 설정을 사용하여 데이터베이스 연결 시뮬레이션
    print("다음 설정으로 데이터베이스 연결:", settings)
    return "DatabaseConnectionObject"
```

```python
def load_config():
    # 구성 설정을 로드하는 것을 시뮬레이션
    print("구성 설정 로드 중")
    return {'database_settings': 'SomeDatabaseSettings'}
```

```python
def analyze_data(data):
    return f"{data} 분석 중"
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