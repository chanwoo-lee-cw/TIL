# Spring batch

## Spring batch

> 자바 기반의 대용량 데이터 처리를 효율적이고 안정적으로 수행할 수 있도록 도와주는 스프링 기반의 프레임 워크.
>
> 주로 정기적으로 반복되는 작업이나 대량의 데이터를 일괄 처리하는데 사용한다.



## Batch와 Scheduler의 차이

배치는 논리적 물리적으로 관련된 일련의 데이터를 그룹화하여 일괄 처리하는 방법을 의미하지만, 스케쥴러는 주어진 작업을 미리 정의된 시간에 실행할 수 있게 해주는 도구나 소프트웨어를 의미한다.

즉, 배치는 대량의 데이터를 일괄적으로 처리할 뿐, 특정 주기마다 자동으로 돌아가는 스케쥴랑과는 관련이 없다. 요컨데 Batch는 보통 스케쥴러와 함께 사용할 수 있도록 설계디어 있을 뿐이지 스케쥴러 자체를 대체하는 것은 아니다.



## Spring batch의 용어

### Job

- 전체 배치 처리 과정을 캡슐화한 엔티티, 배치 처리 과정을 하나의 단위로 만들어 놓은 객체
- 각 Job은 고유한 이름을 가지며, 이 이름은 실행에 필요한 파라미터와 함께 JobInstance를 구별하는데 사용

### JobInstance

- Job 실행의 단위
- job이 각각 실행할 때마다 추적을 위해 매번 생성되며 특정 job과 식별 가능한 JobParameters에 상응하는 JobInstance는 단 한 개뿐이다.
- 한번 생성된 JobInstance는 실패했더라도 재사용되지 않는다.

### Step

- Job을 구성하는 작업 작업입니다.
- 배치 Job의 배치처리를 정의하고 순차적인 단계를 캡슐화 한 도메인 객체. Job은 하나 이상의 Step을 가져야 한다.
- Step은 Job의 하위 단계로써, 실제 배치 처리 작업이 이루어지는 단위
- 한 개 이상의 Step으로 Job이 구성되며, 각 Step은 순차적으로 처리
- 스텝은  chunk 방식 또는 Tasklet 하나를 가질 수 있다.

### JobLauncher

- Job을 실행시키는 역할
- Job과 JobParameters를 받아서 Job을 실행 시킨다..
- 전반적인 Job의 생명 주기를 관리하며, JobRepository를 통해 실행 상태를 유지한다.

### JobRepository

- Job의 실행 정보, 상태를 저장하고 관리하는 역할
- **ExecutionContext**
  - 프레임워크에서 유지/관리하는 key-value의 컬렉션. StepExecution객체 또는 JobExecution객체에 속하는 상태를 저장
  - Job 실행 도중 각 Step간의 데이터를 공유하는데 사용되는 저장소
  - Job이나 Step이 실패했을때 이를 통해 재시도 하거나 복구한다.
  - `JobExecutionContext`와 `StepExecutionContext` 두 종류가 있다.
    - **JobExecution**
      - JobExecution은 JobInstance의 한 번의 시행 시도를 가진다.
      - 똑같은 요청을 재시도 하더라도, 새로운 JobExecution이 생성될 뿐, 재사용하지 않는다.
      - 실행 상태, 시작시간, 종료 시간, 생성 시간 등의 실행에 대한 세부 정보를 가지고 있다.
    - **StepExecution**
      - Step에 대한 실행 시도 정보를 담은 객체.
      - Job 실행 도중 각 Step간의 데이터를 공유하는데 사용되는 저장소



## 스프링 배치의 처리 방식

### Tasklet 방식

> 한 Step내에서 단일 작업을 수행하는 인터페이스.
>
> 단일 작업을 수행하는데 적합한 방식으로, 한번의 실행으로 완료되는 간단한 작업에 사용된다.

**특징**

- 단순한 작업에 적합하다 :  한번의 실행으로 완료되는 작업에 적합하다.
- 직관적인 구현이 가능하다 : `Tasklet` 인터페이스를 구현하여 `execute()` 메서드에 작업 로직을 작성한다.
- 트랜잭션 처리 :  전체 작업이 하나의 트랜젝션으로 처리된다.
- 재시작 불가 : 작업 도중 실패하면 다시 실행해야한다.

**작업 흐름**

- Tasklet 인터페이스의 execute() 메서드를 구현하여 사용한다. 이 메서드는 하나의 트랜젝션 범위에서 실행된다.
- execute() 매소드는 RepeatStatus를 반환하는데 이것은 Tasklet의 실행상태를 나타낸다.
- RepeatStatus.FINISHED를 반환하면, 해당 Tasklet의 처리가 완료된 것을 의미한다.
- RepeatStatus.CONTINUABLE를 반환하면, Tasklet이 계속 실행되어야 함을 의미한다.



### Chunk 방식

> 대량의 데이터를 작은 데이터 크기(chunk)로 나누어 처리하는 방식
>
> 데이터를 읽고(ItemReader), 처리하고(ItemProcessor), 쓰는(ItemWriter) 작업을 반복적으로 수행한다. 주로 대용량 데이터 처리에 사용된다.

**특징**

- 각 Chunk 처리는 읽고(read), 처리하고(process), 쓰는(write) 작업으로 구성된다.

- ItemReader
  - ItemReader는 배치 작업에서 처리할 아이템을 데이터 소스로부터 읽어오는 역할을 한다.
  - 여러 형식의 데이터 소스(예: 데이터베이스, 파일, 메시지 큐 등)로 부터 데이터를 읽어오는 다양한 ItemReader 구현체가 제공된다.
- ItemProcessor
  - ItemProcessor는 ItemReader로부터 읽어온 아이템을 처리하는 역할을 합니다.
  - 이는 처리는 필요에 따라 사용할 수 있으며, 데이터 검증, 필터링, 변환 등의 작업을 수행할 수 있습니다.
- ItemWriter
  - ItemWriter는 ItemProcessor에서 처리된 데이터를 최종적으로 기록하는 역할을 합니다.
  - ItemWriter 역시 다양한 형태의 구현체를 통해 데이터베이스에 기록하거나, 파일을 생성하거나 메시지를 발행하는 등 다양한 방식으로 데이터를 쓸 수 있습니다.
- Chunk 방식은 데이터를 한 건씩 읽고 처리한 후, 일정한 개수(Chunk Size)만큼 모아서 한 번에 저장한다.
- Chunk마다 트랜잭션이 적용되어, 실패 시 해당 Chunk만 롤백되고 이전에 처리된 데이터는 유지된다.







## 참고 문헌

- https://djlife.tistory.com/31?utm_source=chatgpt.com
- [https://khj93.tistory.com/entry/Spring-Batch란-이해하고-사용하기](https://khj93.tistory.com/entry/Spring-Batch란-이해하고-사용하기)
- https://dkswnkk.tistory.com/707?utm_source=chatgpt.com
- [https://velog.io/@wnguswn7/Tasklet-vs-Chunk-비교와-처리-테스트](https://velog.io/@wnguswn7/Tasklet-vs-Chunk-비교와-처리-테스트)
- [https://jojoldu.tistory.com/331?utm_source=chatgpt.com](https://jojoldu.tistory.com/331?utm_source=chatgpt.com)

