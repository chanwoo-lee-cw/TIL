# async

## async 란?

`async`란 Python 표준 라이브러리의 일부인 `asyncio` 라이브러리를 사용하는 것을 말합니다. 이것은 코루틴을 사용하여 싱글 스레드 동시 코드를 작성하고, I/O 접근을 멀티플렉싱하며, 네트워크 클라이언트 및 서버를 실행하기 위한 프레임워크를 제공합니다. Python의 `async`에 대한 몇 가지 주요 포인트는 다음과 같습니다:

1. **비동기 프로그래밍**: 이는 다른 함수가 실행될 수 있도록 함수를 일시 중지할 수 있는 프로그래밍 스타일입니다. 이는 I/O 바운드 및 고수준 구조화된 네트워크 코드에 특히 유용합니다.
2. `async` 및 `await` 구문을 사용한 코루틴**: Python 코루틴은 중단하고 재개할 수 있는 함수입니다. `async def` 구문을 사용하여 정의됩니다. 이러한 함수를 호출하고 `await` 키워드를 사용하여 완료될 때까지 기다릴 수 있습니다.
3. **이벤트 루프**: Python에서 비동기 프로그래밍의 핵심은 이벤트 루프입니다. 이 루프는 다양한 작업의 실행을 관리하고 배분하는 역할을 합니다. 실행 중인 모든 작업을 추적하고 I/O 작업 등으로 유휴 상태일 때 실행됩니다.
4. **동시성**: `asyncio`는 종종 동시성과 관련이 있지만, 실제 병렬성(즉, 여러 스레드나 프로세스의 동시 실행)을 제공하지는 않습니다. 대신, 현재 실행 중인 작업이 이벤트 루프에 제어를 양도해야 하는 협력적 멀티태스킹을 기반으로 합니다.
5. **사용 사례**: `asyncio`는 고수준 네트워크 연결, 웹 소켓, 여러 I/O 바운드 작업을 동시에 수행하는 시나리오 등에서 자주 사용됩니다.
- 예제

```python
import asyncio

async def main():
    print('Hello')
    await asyncio.sleep(1)
    print('World')

asyncio.run(main())
```

# 함수

## run

1. **목적**
    1. `asyncio.run`은 비동기 프로그램의 주 진입점으로 비동기 함수를 실행하는 데 사용되는 함수.
2. **사용 방법**:
    
    `asyncio.run`은 비동기 프로그램을 실행하기 위한 주 진입점으로 사용. run은 한 번만 호출되어야 하며, 여러 번 호출하면 문제가 발생할 수 있다. 그 이유는 매번 새로운 이벤트 루프를 생성하고 닫기 때문.
    
    ```python
    asyncio.run(coro, *, debug=False)
    ```
    
    - `coro`: 실행할 코루틴. 일반적으로 `async def`를 사용하여 정의한 함수입니다.
    - `debug`: 선택 사항. `True`로 설정하면, 이벤트 루프가 디버그 모드에서 실행됩니다.
3. **벤트 루프 관리**: Python 3.7 이전에는 이벤트 루프를 수동으로 관리해야 했고, 이벤트 루프를 생성하고, 루프를 사용하여 코루틴을 실행하고, 마지막으로 루프를 닫는 작업이 포함하고 있었는데, `asyncio.run`은 이러한 모든 단계를 자동으로 처리
4. **예제**
    
    ```python
    import asyncio
    
    async def main():
        # your async code here
        pass
    
    asyncio.run(main())
    ```
    

## await

1. **목적** : await 키워드는 비동기 프로그래밍에서 사용. awaitable 객체(코루틴이나 퓨처 객체와 같은)를 반환하는 함수 앞에 사용하여, 결과가 돌아오기 전까지 해당 부분에서 wait 합니다.
2. **사용법**: await가 함수 호출 앞에 위치하면, 현재의 코루틴의 실행을 일시 중지하여, 대기 중인 작업이 완료될 때까지 백그라운드에서 다른 작업이 실행될 수 있도록 합니다.

### 사용 방법

1. `await asyncio.sleep(n)`:
    - **목적**: `asyncio.sleep(n)`은 코루틴이 `n`초 동안 대기하게 하는 비동기 함수. 파일을 읽거나 네트워크 요청을 하는 것과 같은 IO 바운드 작업을 시뮬레이션 하는데 사용된다.
    - `await`와 함께 사용하기**: `await asyncio.sleep(n)`을 사용하면 호출된 코루틴의 실행이 `n`초 동안 일시 중지됩니다. 이 중지 기간 동안 다른 코루틴이 실행될 수 있어 프로그램이 효율적이고 차단되지 않습니다.
    - **예시**:
        
        ```python
        async def my_coroutine():
            print("시작")
            await asyncio.sleep(2)  # 2초 동안 대기
            print("대기 완료")
        ```
        
2. `await asyncio.gather(*tasks)`:
    - **목적**: `asyncio.gather(*tasks)`는 여러 코루틴을 동시에 실행하기 위해 사용됩니다. 이는 코루틴이나 퓨처(futures)의 목록을 받아 동시에 실행하도록 예약합니다.
    - `await`와 함께 사용하기**: `await asyncio.gather(*tasks)`를 사용하면 제공된 모든 코루틴이나 퓨처가 완료될 때까지 기다립니다. 이것은 동시에 여러 작업을 처리하는 효율적인 방법입니다.
    - **예시**:
        
        ```python
        pythonCopy code
        async def task1():
            # 어떤 비동기 작업
            pass
        
        async def task2():
            # 다른 비동기 작업
            pass
        
        async def main():
            await asyncio.gather(task1(), task2())  # task1과 task2를 동시에 실행
        ```
        

## wait

1. **목적**
    
    여러 비동기 작업이 완료될 때까지 기다릴 수 있게 해주며, 그 완료나 타임아웃을 처리하는 방법을 제공합니다. 여러 작업이 동시에 실행되고 있을 때 일부 또는 전부가 완료될 때까지 기다리고 싶을 때 유용
    
2. **사용 방법**:
    
    ```python
    done, pending = await asyncio.wait(aws, *, loop=None, timeout=None, return_when=ALL_COMPLETED)
    ```
    
    - `aws`: 코루틴이나 `Future` 객체와 같은 `awaitable` 객체의 반복 가능한 객체.
    - `loop`: (Python 3.8에서 폐기) awaitable을 실행하기 위해 사용되는 이벤트 루프.
    - `timeout`: (선택 사항) 최대 기다릴 초 수입니다. 지정되지 않은 경우 모든 퓨처가 완료될 때까지 기다립니다.
    - `return_when`: (선택 사항) 함수가 반환되어야 하는 시점을 결정합니다. `ALL_COMPLETED`, `FIRST_COMPLETED`, `FIRST_EXCEPTION`과 같은 값을 가질 수 있습니다.
3. ****반환 값:****
    
    `asyncio.wait`는 두 세트를 반환합니다:
    
    - `done`: 기다리기가 완료될 때까지 완료된 `Future` 인스턴스의 세트입니다(결과를 반환하거나, 예외를 발생시키거나, 취소되었기 때문입니다).
    - `pending`: 기다리기가 완료될 때까지 여전히 보류 중인 `Future` 인스턴스의 세트입니다.
4. **예제**
    
    ```python
    import asyncio
    
    async def my_task(i):
        await asyncio.sleep(i)
        return f"Task {i} 완료됨"
    
    async def main():
        tasks = [my_task(i) for i in range(5)]  # 작업 목록 생성
        done, pending = await asyncio.wait(tasks, return_when=asyncio.FIRST_COMPLETED)
        
        for task in done:
            print(task.result())
        for task in pending:
            print(f"{task} 여전히 보류 중")
    		if pending:
            completed_later = await asyncio.gather(*pending)
            for task in completed_later:
                print(task)
    
    asyncio.run(main())
    ```
    

## gather

1. **목적**
    
    동시에 실행하고자 하는 여러 작업이 있을 때, `asyncio.gather`는 모든 작업을 수집하여 모두 완료될 때까지 기다립니다. 다음은 자세한 설명입니다:
    
2. **사용 방법**:
    
    ```python
    result = await asyncio.gather(*coros_or_futures, return_exceptions=False)
    ```
    
    - `coros_or_futures`: 동시에 실행할 코루틴 또는 퓨처 객체의 목록입니다.
    - `return_exceptions`: `True`인 경우, 예외는 성공적인 결과와 같은 방식으로 처리되며 결과 목록에 포함된다. 그렇지 않으면, 함수는 발생하는 첫 번째 예외를 발생시킵니다.
3. **예제**
    
    ```python
    import asyncio
    
    async def my_task(number):
        await asyncio.sleep(1)
        return f"작업 {number}의 결과"
    
    async def main():
        tasks = [my_task(i) for i in range(3)]
        results = await asyncio.gather(*tasks)
        print(results)
    
    asyncio.run(main())
    ```
    
    ```python
    # result
    ['작업 0의 결과', '작업 1의 결과', '작업 2의 결과']
    ```
    
4. **특징**
    - **결과의 순서**: 작업의 결과는 `asyncio.gather`에 전달된 순서대로 반환.
    - **예외 처리**: 기본적으로, 어떤 작업이 예외를 발생시키면 `gather`는 다른 모든 작업을 취소하고 예외를 발생시킵니다. `return_exceptions`이 `True`로 설정된 경우, 예외는 결과 목록에 반환된다.