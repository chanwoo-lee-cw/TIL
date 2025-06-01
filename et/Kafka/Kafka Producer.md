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

- `batch.size`  : 파티션 메모리별, 최대 메모리 버퍼의 사이즈(즉, 메모리 버퍼가 최대만큼 쌓이면 카프카 브로커로 전송)

- `linger.ms` : 배치를 보내기 전에 최대 메모리 저장 시간(즉, 메모리 버퍼가 시간이 지나면 카프카 브로커로 전송)

- `buffer.memoey` : 프로듀서가 버퍼링 할 수 있는 총 메모리
- `enable.idempotence`: 중복 메세지를 보내는 것을 방지하고, 정확히 한번 보내는 것을 보장하는 설정
- `compression.type`: 메시지 배치를 위한 압축 알고리즘 (`none`, `gzip`, `snappy`, `lz4`, `zstd`).











## 참고 문헌

- https://gradus.tistory.com/75
- https://always-kimkim.tistory.com/entry/kafka101-producer
- [https://notes.kodekloud.com/docs/Event-Streaming-with-Kafka/Kafka-Producers-Consumers-The-Message-Flow/What-is-a-Kafka-Producer](https://notes.kodekloud.com/docs/Event-Streaming-with-Kafka/Kafka-Producers-Consumers-The-Message-Flow/What-is-a-Kafka-Producer)