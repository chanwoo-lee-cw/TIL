# Python thread

## thread 란?

- 쓰레드란 프로그램(프로세스) 실행의 단위이며 하나의 프로세스는 **여러개의 쓰레드**로 구성이 가능하다.
- 하나의 프로세스를 구성하는 쓰레드들은 프로세스에 할당된 메모리, 자원 등을 공유한다.
- 프로세스와 같이 실행, 준비, 대기 등의 실행 상태를 가지며 실행 상태가 변할때마다 **쓰레드 문맥교환(context switching)**을 수행한다.

### thread 와 process의 차이

- process : OS로 부터 할당 받은 작업의 단위
- thread : process 를 처리하는 실행의 단위

## thread

### function 형 쓰레드

```python
import threading
import time
import random

def worker(name):
    sleep = random.randrange(0, 10)
    time.sleep(sleep)
    print(f"{name} is end")

for i in range(10):
    thread = threading.Thread(target=worker, args=(f"thread{i}", ))
    thread.start()

time.sleep(15)
```

```python
thread2 is end
thread8 is end
thread5 is end
thread7 is end
thread1 is end
thread9 is end
thread3 is end
thread0 is end
thread6 is end
thread4 is end
```

- 함수를 선언 후에 해당 함수를 쓰레드를 선언해서 호출한다.

### class 형 thread

```python
import threading
import time
import random

class Worker(threading.Thread):
    def __init__(self, name):
        threading.Thread.__init__(self)
        self.name = name

    def run(self):
        sleep = random.randrange(0, 10)
        time.sleep(sleep)
        print(f"{self.name} is end")

for i in range(10):
    thread = Worker(name=f"thread{i}")
    thread.start()
```

```
thread5 is end
thread6 is end
thread9 is end
thread2 is end
thread3 is end
thread8 is end
thread7 is end
thread0 is end
thread4 is end
thread1 is end
```

- thread 클래스를 상속해서 사용한다.

## thread 함수

- `start()`  : 해당 쓰레드를 실행한다.


### join()

> 쓰레드를 실행 후에 쓰레드가 join이 선언된 부분에서 쓰레드가 끝날 때까지 기다린다.
쓰레드를 실행한 다음에 쓰레드의 결과 값이 올때까지 기다려야 할 때 유용하다.
> 

example

```python
import threading
import time
import random

class Worker(threading.Thread):
    def __init__(self, name):
        threading.Thread.__init__(self)
        self.name = name

    def run(self):
        sleep = random.randrange(0, 10)
        time.sleep(sleep)
        print(f"{self.name} is end")

for i in range(10):
    thread = Worker(name=f"thread{i}")
    thread.start()
    thread.join()
```

```python
# 각 쓰레드가 끝날 때까지 기다린다.
thread0 is end
thread1 is end
thread2 is end
thread3 is end
thread4 is end
thread5 is end
thread6 is end
thread7 is end
thread8 is end
thread9 is end
```

원하는 대로 초기안 처럼 각 쓰레드가 따로 돌지만 끝날 때까지 기다려야 한다면 아래와 같이 해야한다.

```python
import threading
import time
import random

class Worker(threading.Thread):
    def __init__(self, name):
        threading.Thread.__init__(self)
        self.name = name

    def run(self):
        sleep = random.randrange(0, 10)
        time.sleep(sleep)
        print(f"{self.name} is end")

tread_list = []

for i in range(10):
    thread = Worker(name=f"thread{i}")
    thread.start()
    tread_list.append(thread)

# 쓰레드를 리스트에 저장한 다음에 다시 join으로 실행한다.
for thread in tread_list:
    thread.join()
```

```python
thread0 is end
thread7 is end
thread8 is end
thread6 is end
thread4 is end
thread2 is end
thread9 is end
thread3 is end
thread5 is end
thread1 is end
```

## Python thread local Storage

thread의 경우의 글로벌 변수의 경우에는 다른 쓰레드에 의해 덮어 씌워지는 경우가 있다. 그래서 이런 경우에 쓰레드별로 작동하는 글로벌 변수를 선언하기 위해서 사용하는 공간

```python
import threading
import time
import random

def print_thread_name():
    currentThread = threading.current_thread()
    dictionary = currentThread.__dict__
    print(dictionary["thread_info"])

class Worker(threading.Thread):
    def __init__(self, name):
        threading.Thread.__init__(self)
        self.name = name

    def run(self):
        sleep = random.randrange(0, 10)
        time.sleep(sleep)
        currentThread = threading.current_thread()
        dictionary = currentThread.__dict__
        dictionary["thread_info"] = f"{self.name} is sleep {sleep}s"
        print_thread_name()

for i in range(10):
    thread = Worker(name=f"thread{i}")
    thread.start()
```

- 출력

```
thread7 is sleep 1s
thread0 is sleep 1s
thread6 is sleep 2s
thread9 is sleep 3s
thread8 is sleep 4s
thread1 is sleep 5s
thread4 is sleep 5s
thread5 is sleep 6s
thread3 is sleep 7s
thread2 is sleep 9s
```

## 출처

- [https://papago.naver.net/website?locale=ko&source=en&target=ko&url=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FThread-local_storage](https://papago.naver.net/website?locale=ko&source=en&target=ko&url=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FThread-local_storage)
- [https://docs.python.org/3/library/threading.html](https://docs.python.org/3/library/threading.html)