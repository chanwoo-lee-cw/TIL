# Kafka

## Kafka란?

> Kafka란? 실시간으로 기록 스트림을 게시, 구독, 저장 및 처리 할 수 있는 **분산형 데이터 스트리밍 플랫폼**.
>
> 대량의 데이터를 안정적이고 실시간으로 처리할 수 있도록 설계되었다. 카프카는 주로 대량의 **이벤트 스트림 데이터**를 처리하고 여러 시스템 간에 **메세지 큐의 Pub-Sub 방식**으로 데이터를 신속하게 전송하는 데 사용됩니다.
>
> 카프카는 기업에서 대규모 데이터 처리 및 이벤트 기반 시스템을 구축하는데 널리 사용되며, 특히 대용량의 로그 데이터를 수집하고 분석하는 데 유용하다.

왜 필요한가? 마이크로서비스는 개발 환경을 공유 데이터베이스 계층과 같은 종속성을 줄여서 개발자들의 생산성을 증가시켰지만, 각 내부 시스템 간 "실시간 데이터 파이프라인"이 필요해졌기 때문이다.



## 기본 데이터 시스템(API) 방식의 문제점

### 1. 시스템 복잡도 증가

- API 방식은 수십개의 MS가 서로의 상태를 공유하다보니, 서비스간의 강결합, 의존성 증가 등의 문제를 발생.
- Kafka를 통해 pub-sub 방식으로 각 서비스간의 데이터 흐름을 분리

### 2. 데이터 파이프 라인의 관리의 어려움

- 각 서비스들끼리 별도의 파이프라인이 존재하고, 각 파이프 라인끼리 데이터 포맷과 처리 방식이 달라서, 한개를 수정하면 요청하는 서비스를 일일히 확인하거나, 일일히 요청하는데 오래 걸린다.
- Kafka를 통해 하나의 플랫픔으로 통일해서 요청하는 서비스 입장에선 한개의 서비스에만 요청하면 된다.

### 3. 내결함성과 재처리 기능 :

- Rest Api는 각각 요청시마다 각자의 로그를 찍어서 에러가 발생할 때마다 에러를 직접 확인하고, 재시도와 실패 대응이 어렵다.
- Kafka를 통해 **오프셋 기반으로 재처리**(메세지를 삭제하지 않기 때문에, 오프셋을 되돌려서 다시 읽을 수 있다.) 가능하고, Sub가 다운되더라도 복구 후에 재시도 가능



## 메세지 큐(MQ)

### MQ 란?

> 메시지 큐(Message Queue)는 **비동기적으로 메시지를 주고받기 위한 통신 방식**으로, **생산자(Producer)**와 **소비자(Consumer)** 사이에서 데이터를 중개하는 **중간 저장소(버퍼 역할)**를 말한다.
>
>  메시지 지향 미들웨어(MOM : Message Oriented Middleware)를 구현한 시스템이다.

### 주요 특징

1. **비동기 처리**: 생산자와 소비자가 동시에 동작하지 않아도 된다.

2. **비결합성**: 생산자와 소비자가 직접 통신하지 않아도 되므로 시스템이 유연해진다.

3. **내구성**: 메시지를 큐에 안전하게 저장했다가, 나중에 소비자가 직접 가져가는 방식.
4. **확장성**: 소비자를 여러 개 두면 병렬 처리가 가능하다.

### 구성 요소

- **생산자(Producer)** : 메세지를 큐에 보내는 개
- **소비자(Consumer)** : 정보를 제공받아서 사용하려는 자
- **큐(Queue)** : producer의 데이터를 임시 저장 및 consumer에 제공하는 곳

![](https://velog.velcdn.com/images/alphanewbie/post/a44fbd9f-dbbf-4d6a-b2bd-a0c1a08f0a17/image.png)

## Kafka의 역할

### 메세지 브로커

- 정의
  - **생산자(Producer)**가 보낸 메시지를 **소비자(Consumer)**에게 **중개·전달해주는 시스템**. 즉, 메시지를 안전하게 받고, 큐나 토픽을 통해 적절한 소비자에게 전달하는 역할.

- 특징

  - 메시지 중심 (Command 기반: “이 작업을 해라”)

  - 비동기 처리 / 큐 기반

  - 주로 **작업 지시(command)**를 전달

### 이벤트 브로커

- 정의
  - 시스템 내에서 발생한 **이벤트(event)**를 여러 구독자(Subscriber)에게 **발행-구독(Pub/Sub)** 모델로 전달하는 역할

- 특징

  - 이벤트 중심 (“발생한 사실” 전달)

  - **Pub/Sub 구조** (하나의 이벤트를 여러 소비자가 구독 가능)

  - **데이터 스트림 처리에 강함**

### Pub/Sub

- 구성 요소

  - **Publisher (발행자)**: 이벤트나 메시지를 특정 **주제(Topic)**에 발행

  - **Subscriber (구독자)**: 특정 Topic을 구독하고, 관련 메시지를 수신

```
Publisher → [ Topic ] → Subscriber
```

- 메시지 흐름 예시
  1. **Publisher**가 `"user.created"`라는 이벤트를 Topic에 발행
  2. 이 Topic을 구독 중인 **A 시스템**, **B 시스템**이 자동으로 알림을 받음

### 요약

카프카는 이벤트 브로커로로써 이벤트 발생 사실을 순서대로 저장하고, 여러 소비자가 독립적으로 구독한다.



## 카프카의 구성요소

### Producer

> Kafka에 데이터를 보내는 클라이언트
>
> 메세지를 특정 Topic에 발행하고, 키를 지정하여 특정 파티션에 메세지를 보낼 수 있습니다.

프로듀서는 새 메세지를 특정 토픽에 생성하지만, 메세지가 어떤 파티션에 기록되는지 관여하지 않는다.

### Consumer

> Kafa에서 데이터를 읽어오는 클라이언트
>
> 특정 Topic을 구독하여, Consumer Group을 통해 병렬 처리가 가능하다.

컨슈머는 하나 이상의 토픽을 구독하면서 메세지가 생성된 순서로 읽는다. 컨슈머는 메세지를 읽을 때마다 파티션 단위로 오프셋을 유지하며 메시지 위치를 알 수 있다.

### Broker

> Kafka 서버 인스턴스, Producer로써 메세지를 수신하고 디스크에 저장한다.
>
> Consumer의 요청에 따라 메세지를 전달하여, 여러개의 브로커가 모여 Kafka Cluster를 구성한다.

하나의 카프카 서버를 브로커라고 말한다. 이 각각의 브로커는 클러스터의 구성원으로 동작하며, 그중 한개의 브로커는 컨트롤러의 역할을 수행한다. 컨트롤러는 각각 브로커에게 담당 파티션을 팔당하고 각각 브로커들이 정상적으로 동작하는지 모니터링한다.

### Topic

> 메세지를 분류하는 논리적 단위로, 특정 주제나 목적에 따라 메세지를 구분한다.
>
> 하나의 Topic은 여러개의 Partition으로 나누어 병렬 처리를 지원

카프카의 메세지는 토픽으로 분류된다. 토픽은 DB의 테이블과 유사하다.

즉, 하나의 주제를 정해놓고 해당 메세지를 저장하는 방식이다.

### Partition

> Topic을 구성하는 단위로, 메세지는 파티션에 순차적으로 저장된다.
>
> 파티션을 통해 데이터의 병렬 처리와 확장이 가능하며, 각 메세지는 Offset을 가진다.

메세지는 파티션에 추가되는 형태로만 기록되며, 맨 앞에서부터 제일 끝까지의 순서로 읽힌다.

대개 하나의 토픽은 여러개의 파티션을 갖지만, 메세지 처리 순서는 토픽이 아닌 파티션별로 관리된다.

즉, 같은 파티션에 저장된 메세지는 순차적으로 처리된지만, 다른 파티션간에는 메세지 순서가 보장되지 않는다. 즉, A,B,C의 메세지를 넣는다고 해서 

Partition 0 - A, B, C

Partition 1 - B, C

이런식으로 넣으면 전체 파티션의 처리 순서가 A,B,C이라는 보장은 없다.

### Consumer Group

> 여러 Consumer가 하나의 그룹을 이루어 Topic을 구독한다.
>
> 각 파티션은 하나의 Consumer에게만 할당되어 병렬처리가 가능하며, 장애 발생 시 다른 Consumer가 처리를 이어받게 처리할 수 있다.

Kafka에서 컨슈머 그룹은 컨슈머로 구성되는데, 이 그룹은 Kafka 토픽의 메시지를 협력하여 처리한다. 각 컨슈머 그룹은 토픽의 전체 메시지를 처리하며, 그룹 내의 각 컨슈머는 일부 파티션을 담당하게 된다.

즉, 요약하자면 컨슈머 그룹 안에 있는 컨슈머들은 토픽의 전체 메세지를 분담해서 처리하게 된다.

### ZooKeeper

> Kafka 클러스터의 메타 데이터를 관리하고 브로커의 상태를 모니터링

Kafka는 메시지를 생산하고 처리하기만 하고, 자체적으로 클러스터 상태를 저장하지 않기 때문에, 클러스터의 메타데이터와 상태 정보를 관리하기 위해 ZooKeeper에 메타데이터 관리를 위임한다.





## 참고

- https://www.redhat.com/ko/topics/integration/what-is-apache-kafka
- [https://velog.io/@holicme7/Apache-Kafka-카프카란-무엇인가](https://velog.io/@holicme7/Apache-Kafka-카프카란-무엇인가)
- [https://medium.com/@0joon/10분안에-알아보는-kafka-bed877e7a3bc](https://medium.com/@0joon/10분안에-알아보는-kafka-bed877e7a3bc)
- [https://velog.io/@qlgks1/카프카Kafka란](https://velog.io/@qlgks1/카프카Kafka란)
- [https://medium.com/%40greg.shiny82/apache-kafka-간략하게-살펴보기-343ad84a959b](https://medium.com/%40greg.shiny82/apache-kafka-간략하게-살펴보기-343ad84a959b)