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
