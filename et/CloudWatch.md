# CloudWatch

작성일시: 2022년 10월 1일 오후 10:23
최종 편집일시: 2022년 10월 3일 오후 10:31

## 목차

## 지원되는 로그 및 검색된 필드

CloudWatch Logs Insight는 다양한 로그 유형을 지원합니다. CloudWatch Logs는 Amazon CloudWatch Logs에 전송되는 모든 로그에 대해 다음과 같은 5개의 시스템 필드를 자동으로 생성합니다.

- `@message` 는 파싱되지 않은 raw 로그 이벤트가 포함되어 있습니다. 이것은 입력된 로그 이벤트에 해당합니다.
- `@timestamp` 는 로그 이벤트의 타임 스탬프 필드 안의 이벤트의 타임 스탬프가 포함됩니다. 이는 입력 로그 이벤트의 타임스탬프 필드에 해당합니다.
- `@ingestionTime` 는 CloudWatch Logs가 로그 이벤트를 받은 시간을 포함한다.
- `@logStream` 은 포한함다. **로그 이벤트가 추가된 로그 스트림의 이름이 포함됩니다. 로그 스트림은 로그를 생성한 프로세스와 동일한 프로세스를 통해 로그를 그룹화합니다.**
- `@log` 는 `*`account-id`*:*`log-group-name`* 형태로 된 로그 식발자 입니다. 멀티 로그 그룹을 querying 할 때, 이것은 특정 사건에 속하는 로그 그룹을 식별하는 데 유용할 수 있습니다.

CloudWatch Logs Insights는 생성하는 필드의 시작 부분에 `@` 기호를 삽입합니다.

많은 로그 타입에 대해서, CloudWatch Logs는 자동적으로 로그에 포함된 로그 필드 또한 자동으로 검색합니다. 이러한 자동 검색 필드는 아래의 표에 나와있습니다.

CloudWatch Logs Insights가 자동으로 검색하지 않는 필드가 있는 다른 유형의 로그의 경우 `parse` 명령을 사용하여 해당 쿼리에 사용할 임시적인 필드를 추출하고 생성할 수 있습니다.

만약 발견된 로그 필드의 이름이 @으로 시작되는 경우, CloudWatch Logs 는 시작부분에 추가 @를 추가하여 표시합니다. 예를 들면 @example.com인 필드의 이름은 @@example.com으로 표시된다.

| Log Type | Discovered log fields |
| --- | --- |
| Lambda logs | @timestamp, @logStream, @message, @requestId,@duration, @billedDuration, @type,@maxMemoryUsed, @memorySize|
| Amazon VPC flow logs | @timestamp, @logStream, @message, accountId, endTime, interfaceId, logStatus, startTime, version, action, bytes, dstAddr, dstPort, packets, protocol, srcAddr, srcPort |

## **CloudWatch Logs Insights query syntax**

CloudWatch Logs Insights를 사용하면 쿼리 언어를 사용하여 로그 그룹을 쿼리할 수 있습니다. 쿼리 구문은 일반 함수, 산술 및 비교 연산, 정규식을 포함하지만 이에 국한되지 않는 다양한 함수와 연산을 지원합니다. 
여러 명령이 포함된 쿼리를 만드십시오. 명령어는 파이프 문자(|)로 구분합니다. 
주석이 포함된 쿼리를 만드십시오. 해시 문자(#)로 주석을 설정합니다.