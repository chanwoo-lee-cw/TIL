# Kafka Broker

## 카프카 브로커란?

> 데이터를 저장하고 모든 데이터 스트리밍 요청을 처리하는 서버
> 카프카 클러스터 내에서 실제 메세지를 저장, 검색 및 배포 그리고 클라이언트와의 통신을 담당한다.
> 각 브로커는 퍼블리셔의 데이터를 처리하고 컨슈머에게 데이터를 제공하며 파티션 및 오프셋 상태를 유지하는 서버.
>
> **즉, Kafka Broker는 카프카의 메세지를 저장하고, 프로듀서와 컨슈머로부터 메세지를 주고 받으며, 클러스터 내에서 파티션 리더 역할을 수행하는 단일 서버 프로세스.**



## 카프카 브로커의 핵심 역할

### Message Handling

- **메세지 수신** : 
  - 프로듀서의 지정된 키(파티션을 결정적으로 결정하는 키)를 기반으로, 또는 라운드 로빈 선택을 통해 메세지를 주제 내에 배치할 파티션을 결정한다.
  - 컨슈머 그룹의 각 컨슈머는 해당 토픽의 파티션에서 글을 읽으며, 카프카 브로커는 어떤 컨슈머가 어떤 파티션에서 글을 읽고 있는지 추적한다. 이를 통해 부하와 메세지를 한 소비자에게만 전달되도록 한다.
- **Offsets 할당** : 
  - 각 파티션의 오프셋의 로그를 추적해서 어떤 메세지가 소비되었는지, 어떤 메세지가 소비되지 않았는지 파악한다. 오프셋을 추적하면 그룹내의 컨슈머들 사이에서 실패나 재조정이 발생했을 때, 컨슈머들이 중단했던 곳에서 이어서 할 수 있다.

### Consumer에게 서비스 제공

- **메세지 가져오기 :**
  - 소비자는 브로커에게 메세지를 요청하고, Topic, partition, Offset을 지정한다. 브로커는 요청한 메세지를 검색하여 제공한다.
- **Offsets 관리 :**
  - 브로커는 메세지의 오프셋을 추적하여 소비자가 데이터를 받을 수 있도록 하고, 실패시 다시 마지막 일기 위치에서 다시 시작할 수 있도록 한다.
- **컨슈머 관리 :**
  - 컨슈머 그룹이 해당 토픽을 요청했을때, 토픽을 컨슈머들 수만큼 토픽을 나눠서 분배한다. 각 파티션은 컨슈머 하나가 한 파티션을 담당하고, `heartbeat`를 통해 컨슈머가 죽었는지 확인하고, 죽었다면 남은 컨슈머에게 파티션을 재할당한다.

### 복제 및 내 결함성

- **데이터 복제 :**
  - 브로커는 고가용성과 내결함성을 보장하기 위해 여러 브로커에 걸쳐 파티션 데이터를 복제한다.
- **리더 선출 :**
  - 각 파티션마다 한명의 브로커가 리더 역할을 하며 모든 읽기 및 쓰기 요청을 처리하고 다른 브로커는 팔로워 역할을 한다. 리더가 다운되면 다른 팔로워 중에서 새로운 리더를 선출한다.
- **동기화 :**
  - 팔로워는 리더의 데이터와 주기적으로 동기화 해서, 브로커 장애 시에도 데이터의 손실을 최소화고 서비스를 지속할 수 있다.

### 클러스터 조정

- **메타 데이터 관리 :**
  - 브로커는 토픽, 파티션, 소비자 그룹에 대한 메타 데이터를 관리하며 메세지의 라우팅을 지원한다.
- **컨트롤러 역할 :**
  - 클러스터 내 하나의 브로커가 컨트롤러 역할을 맡아 파티션 리터를 관리하고 관리 작업을 처리한다.



## 카프카 브로커의 아키텍쳐

각 브로커는 고유한 ID로 식별되며 일부 파티션을 담당한다. 이 분포를 통해 카프카는 수평으로 확장할 수 있으며, 더 많은 브로커를 추가하여 부하 증가를 처리할 수 있다.

### Topic

- Kafka에서 데이터를 분류하기 위한 논리적인 단위.
- 하나의 토픽은 하나 이상의 파티션을 가지며, 각 파티션에는 프로듀서가 보낸 메시지들을 저장하는데, 이 데이터를 레코드라고 부른다.
- 레코드들은 **append-only** 방식으로 파티션 로그에 순차적으로 저장된다.

### Partitions

- 병렬 처리를 가능하게 하는 주제의 세분화
- 카프카 병렬 처리의 핵심으로 같은 컨슈머 그룹으로 묶인 레코드를 병렬로 처리하도록 각 컨슈머에 분배한다.
- Kafka는 하나의 파티션은 컨슈머 그룹 내에서 오직 한 컨슈머에만 할당되도록 보장한다.
- 각 컨슈머는 자신에게 할당된 파티션에 대해 메시지를 처리 완료 한 다음에, 처리한 offset 을 Kafka에 `commitOffset()`을 통해 커밋 한다.

### Offsets

- 파티션 내 메시지에 할당된 순차적인 ID

- 컨슈머는 Kafka 브로커에 자신이 처리한 메세지를 offset를 `commitOffset()` 호출을 통해 알려준다.
- 이 오프셋 정보는 내부 토픽인 `__consumer_offsets`에 (컨슈머 그룹 ID, 토픽, 파티션) 기준으로 저장된다.



### 리밸런싱과 오프셋 복구

1. 컨슈머가 다운되면 Kafka는 `heartbeat`를 통해 이를 감지하고, 해당 컨슈머에 할당되었던 파티션을 다른 컨슈머에게 자동 재할당한다.
2. 새로운 컨슈머는 `__consumer_offsets`에 저장된 오프셋 정보를 기준으로, 마지막으로 커밋된 지점부터 메시지를 다시 소비한다.
3. 컨슈머 그룹은 서로 독립된 오프셋 공간을 유지하므로, 다른 그룹의 오프셋이나 처리 흐름에는 영향을 주지 않는다.



## 압축 처리

Gzip, Snappy, Lz4, Zstd

이 4가지의 방식으로 압축을 하는데, 카프카는 batch 단위로 압축을 하고, Broker는 압축해제하지 않고 그대로 저장하고, 그대로 컨슈머엑 보낸다.

Consumer가 해당 압축을 직접 해제하여 처리한다.



## Zero-Copy 전송 (`sendfile`)

일반적인 디스크의 데이터를 네트워크를 통해 전달하는 경우에는

1. 운영체제가 디스크상의 파일을 읽어 데이터를 커널 공간(kernel space)의 읽기 버퍼(read buffer)에 저장합니다. 
2. 애플리케이션은 읽기 버퍼에 저장된 데이터를 사용자 공간(use space) 버퍼(application buffer)로 읽어 들입니다. 
3. 애플리케이션은 사용자 공간 버퍼로 읽어 들인 데이터를 소켓 버퍼에 씁니다. 
4. 운영체제는 소켓 버퍼의 데이터를 NIC(Network Interface Card) 버퍼에 복사합니다. 해당 데이터는 네트워크를 통해 전송됩니다. 



이 과정에서 데이터 복사의 횟수는 `디스크 -> 읽기 버퍼 -> 사용자 공간 -> 소켓 버퍼 -> NIC 버퍼`를 거치며 총 4번의 데이터 복사가 수행된다.

이것을 줄이기 위한 것이 `sendfile` 시스템 콜이다.

이 시스템 콜을 사용하면 사용자 공간을 이용할 필요 없이 파일 디스크립터간 데이터를 복사할 수 있다.

즉, `디스크 -> 읽기 버퍼 -> 소켓 버퍼 -> NIC 버퍼`를 거치며 총 3번의 복사만으로 파일을 복제 가능하다

카프카는 해당 기법을 이용해 Consumer의 처리 속도를 향상 시킨다.

consumer가 데이터를 요청할 때마다 데이터를 User Space에 복사하지 않고, `Sendfile` 을 통해 읽기 버퍼에 저장된 데이터를 바로 전달함으로써 효율을 높힌다.

### 주의 사항

- SSL/TLS 적용 시 Zero‑Copy 효과 대부분 사라진다.
- 데이터를 재암호화해야 하므로 `sendfile()`을 사용할 수 없다.



## Zookeeper

> Broker를 관리(Broker들의 목록/설정을 관리)하는 소프트웨어



Kafka Broker의 동기화, 구성관리, 리더 선출, 그룹 서비스 등을 안정적으로 수행할 수 있도록 한다.
내부적으로는 메모리 기반 ZNode를 유지하며, 소량의 데이터를 빠르게 일고 처리하도록 설계

Zookeeper는 변경 사항에 대해 Kafka에 알린다. Topic 생성/제거, Broker 추가/제거 등
Zookeeper는 홀수 서버로 작동되도록 설계



### ZAB 프로토콜 (Atomic Broadcast)

> ZooKeeper 클러스터의 **일관성을 보장하고 장애 복구가 가능한 복제 구조**의 핵심 기술

Leader가 쓰기 요청을 Followers에게 순서대로 브로드캐스트하고, 과반수 Ack를 받으면 커밋되고, 이 과정으로 **일관성과 전체 순서 보장**이 가능하고, 장애 발생시 복구를 보장한다.

- Leader-Follwer 구조:
  - 하나의 Leader가 쓰기 요청을 Follwer들에게 알리고, 과반수의 Ack을 받으면 commit한다. 전체 노드는 동일한 순서대로 JVM에 반영된다.
- Total Order 보장:
  - 모든 노드가 같은 순서대로 트랜젝션을 적용하도록 강제한다. 이는 읽기 전용 follower에서도 일관된 일기 시나리오 제공에 유리하다.
- Quorum 기반:
  - 과반수의 Ack을 받을 시에만 커밋이 이뤄져, 분할 된 상황에서도 일관성 유지한다.
- Leader Election:
  - 장애 또는 재시작이 발생하면 새로운 Leader를 선출하며, 가장 높은 zxid(트랜잭션 ID)를 가진 노드가 Leader로 선출된다.



### ZooKeeper의 기능 요약

- **Configuration Management**: 공통된 구성 데이터를 중앙 저장소에 보관
- **Naming Service**: 서비스 주소 같은 메타데이터 관리
- **Synchronization**: 락, barrier 같은 동기화 원시 기능 제공
- **Group Management**: 노드 그룹 구성 및 구성원 변화 추적
- **Leader Election**: 자동 리더 선출
- **Distributed Locking**: Ephemeral + Sequential ZNode 기반 락 구현



## 참고 문헌

- [https://www.redpanda.com/guides/kafka-architecture-kafka-broker](https://www.redpanda.com/guides/kafka-architecture-kafka-broker)
- [https://rudaks.tistory.com/entry/3장-카프카-기본-개념-설명](https://rudaks.tistory.com/entry/3장-카프카-기본-개념-설명)
- [https://www.instaclustr.com/blog/a-beginners-guide-to-kafka-consumers](https://www.instaclustr.com/blog/a-beginners-guide-to-kafka-consumers)
- https://code-run.tistory.com/80
- [https://velog.io/@hyun6ik/Apache-Kafka-Broker-Zookeeper](https://velog.io/@hyun6ik/Apache-Kafka-Broker-Zookeeper)