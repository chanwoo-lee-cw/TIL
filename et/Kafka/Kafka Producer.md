# Kafka Producer

## Kafka Producer란?

> 사용자가 작성한 이벤트를 **파티션 단위 로그**에 기록하여 브로커에 전달하는 클라이언트 컨포넌트.
>
> 아파치 카프카 생태계 내에서 카프카 Topic에 정보를 전송 또는 생성하는 클라리언트 애플리케이션



## 카프카 퓨로듀서의 주요 업무

- **직렬화(Serialization)**: 메세지 키와 값을 바이트 배열로 변환
- **파티셔닝(Partitioning)**: 각 레코드가 어떤 파티션으로 이동해야하는지 결정
- **배치, 압축(Batching & Buffering)**: 네트워크 사용을 최적화 하기 위해 메세지를 그룹화 한다.
- **재시도 및 오류 처리(Retries & Error Handling)**: 일시적인 실패시 자동으로 재시도
- **확인 (acks)** : Kafka가 메세지를 사실을 확인하는 브로커의 수를 제어한다.
  - `acks=0` : 프로듀서는 카프카에 메세지를 보내고 받았는지 확인하지 않는다.
  - `acks=1` : 리더 브로커가 받고 저장하면 성공으로 친다.
  - `acks=all` : 리더가 받고 모든 카프카 브로커 레플리카가 메세지를 저장해야 성공한걸로 간주한다.



## 주요 구성 및 매개변수

- `bootstap.servers` : 연결한 카프카 브로커의 주소 목록

- `key.serializer` , `value.serializer`: 키와 값 객체를 바이트 배열로 직렬화하는 클래스.
  - 즉, 시리얼라이즈를 커스텀으로 하는 방법

- `batch.size`  : 파티션 메모리별, 최대 메모리 버퍼의 사이즈(즉, 메모리 버퍼가 최대만큼 쌓이면 카프카 브로커로 전송)

- `linger.ms` : 배치를 보내기 전에 최대 메모리 저장 시간(즉, 메모리 버퍼가 시간이 지나면 카프카 브로커로 전송)

- `buffer.memoey` : 프로듀서가 버퍼링 할 수 있는 총 메모리
- `enable.idempotence`: 중복 메세지를 보내는 것을 방지하고, 정확히 한번 보내는 것을 보장하는 설정
- `compression.type`: 메시지 배치를 위한 압축 알고리즘 (`none`, `gzip`, `snappy`, `lz4`, `zstd`).



## 카프카 프로듀서의 처리 단계

### 직렬화

카프카는 메세지를 바이트 배열로 전송하므로, 프로듀서는 키와 값을 바이트 배열로 변환한다. 이를 위해`key.serializer` , `value.serializer` 를 설정한다

- `key.serializer` : 키를 직렬화하는데 사용, 메세지키를 바이트 배열로 전환해서 보낸다. 일반적으로 `StringSerializer`나 `LongSerializer`를 사용한다. 키는 복잡할 필요가 없이 구분을 위한 값이기 때문
- ``value.serializer` : 메세지 값을 바이트 배열로 변환. 보통 복잡하기 때문에. Avro, Json 등등을 직렬화 설정으로 사용한다.



### 파티셔닝

각 토픽을 여러 파티션으로 나눠서 데이터를 분산 저장한다. 프로듀서는 메세지를 어떤 파티션에 보낼지 결정

- **기본 파티셔터** : 메세지에 키가 있는 경우, 키의 해시값을 기반으로 파티션을 결정. 키가 없는 경우 라운드 로빈 방식으로 선택



### 배치 및 압축

- **배치 크기 (`batch.size`)**: 동일한 파티션으로 전송되는 메시지를 모아 하나의 배치로 전송한다. 여러 메세지를 한번에 보내는 방식으로 최적화한다.
- **지연 시간 (`linger.ms`)**: 배치가 가득 차지 않더라도 설정된 시간 동안 메시지를 모아 배치를 생성한다.
- **버퍼 메모리 (`buffer.memory`)**: 프로듀서가 메시지를 버퍼링할 수 있는 총 메모리 크기를 설정합니다. 버퍼가 가득 차면 `send()` 호출이 블록되거나 예외가 발생할 수 있다.
- **압축 유형 (`compression.type`)**: 메시지 배치를 압축하여 네트워크 사용량을 줄일 수 있습니다. 주로 `none`, `gzip`, `snappy`, `lz4`, `zstd` 등의 압축 방식을 사용한다.



### 재시도 및 오류 처리

- **재시도 횟수 (`retries`)**: 메시지 전송 실패 시 재시도할 최대 횟수를 설정한다. 기본값은 `Integer.MAX_VALUE`
- **재시도 간격 (`retry.backoff.ms`)**: 재시도 간의 대기 시간을 설정한다. 기본값은 100ms
- **전송 시간 제한 (`delivery.timeout.ms`)**: 메시지 전송이 성공하거나 실패로 간주되기까지의 최대 시간을 설정합니다. 이 시간 내에 전송이 완료되지 않으면 에러가 발생한다.



### 확인

Kafka는 메시지 전송의 내구성을 보장하기 위해 확인 메커니즘을 제공한다.

- **`acks=0`**: 프로듀서는 브로커의 Acks 신호를 기다리지 않고 기다리지 않고 메시지를 전송. 가장 빠르지만 메시지 손실 가능성이 있다.
- **`acks=1`**: 리더 브로커가 메시지를 수신하면 Acks을 리턴한다. 리더 브로커 장애 시 메시지 손실이 발생할 수 있다.
- **`acks=all`**: 모든 동기화된 복제본이 메시지를 수신해야 Ack을 리턴한다. 가장 높은 내구성을 제공하지만 시간이 오래 걸린다. 즉, 리더 브로커가 메세지를 받으면 다른 모든 브로커 레플리카에 메세지를 전송한다. 그럼 각 레플리카는 성공적으로 응답시 ack을 보내고 리더 브로커는 ack을 다시 프로듀서에게 최종 응답을 보낸다.







## 참고 문헌

- https://gradus.tistory.com/75
- https://always-kimkim.tistory.com/entry/kafka101-producer
- [https://notes.kodekloud.com/docs/Event-Streaming-with-Kafka/Kafka-Producers-Consumers-The-Message-Flow/What-is-a-Kafka-Producer](https://notes.kodekloud.com/docs/Event-Streaming-with-Kafka/Kafka-Producers-Consumers-The-Message-Flow/What-is-a-Kafka-Producer)