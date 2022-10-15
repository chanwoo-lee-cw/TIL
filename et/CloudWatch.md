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


### CloudWatch Logs Insights 쿼리 명령

- `display`
    
    display 기능을 사용하여 쿼리 결과에 특정 필드를 표시합니다.
    
    예시) 한 필드의 display
    
    @message 필드에서 데이터를 추출하여, loggingType, loggingMessage 를 사용한 임시 쿼리를 만드는 예시. 디스플레이에는 loggingMessage에 대한 값만 표시된다.
    
    ```
    fields @message
    | parse @message "[*] *" as loggingType, loggingMessage
    | filter loggingType = "ERROR"
    | display loggingMessage
    ```
    
    - display는 쿼리에서 한번만 사용한다. 두번 이상 사용되는 경우 마지막 항목에서 지정된 필드가 표시된다.
- `fields`
    
    field 명령어를 사용해서 쿼리 결과에 특정 필드를 표시합니다.
    
    예시) 특정 feild를 display
    
    ```
    fields @timestamp, @message
    | sort @timestamp desc
    | limit 20
    ```
    
- `filter`
    
    filter를 사용하여 하나 이상의 조건과 일치하는 로그 이벤트를 가져옵니다.
    
    예시 ) 하나의 조건을 사용한 로그의 필터
    
    range값이 3000이 넘는 이벤트 로그를 쿼링한다.
    
    ```
    fields @timestamp, @message
    | filter (range>3000)
    | sort @timestamp desc
    | limit 20
    ```
    
    예시) 이상의 조건을 사용한 로그 필터
    
    ```
    fields @timestamp, @message
    | filter (range>3000 and accountId=9876543210)
    | sort @timestamp desc
    | limit 20
    ```
    
- `stats`
    
    `stats`를 사용하여 로그 필드 값을 사용하여 집계 통계를 계산합니다.
    
- `sort`
    
    `sort`을 사용하여 로그 이벤트를 오름차순(asc) 또는 내림차순(desc)으로 표시합니다.
    
- `limit`
    
    `limit`를 사용하여 로그 이벤트의 쿼리에서 다시 돌아오도록 원하는 값을 지정합니다.
    
### **Guidelines for working with query commands**

`@`나, `.` 가 같은 영문자가 아닌 문자가 포함된 쿼리를 검색할 때는 ``` 키를 사용해 로그 필드를 둘러 싸야 합니다. 예를 들어 로그 필드 `foo-bar`에는 영문자가 아닌 `-`이 포함되어 있으므로 `foo-bar`으로 묶어야 합니다.

`fields` 명령어를 `as` 키워드와 함께 사용하여 로그 이벤트의 필드 및 함수를 사용하는 임시 필드를 만들 수 있습니다. 예를 들어, `fields ispresent as isRes`는 isRes 라는 임시 필드를 생성하고, 그리고 임시 필드는 다른 쿼리에 사용될 수 있습니다.

`isRes`의 값은 `resolverArn`이 검색된 필드인지 여부에 따라 0 또는 1입니다. 쿼리에 여러 필드 명령이 포함되어 있고 표시 명령이 포함되어 있지 않으면 필드 명령에 지정된 모든 필드가 표시됩니다.

## ****Matches and regular expressions in the filter command****

filter 명령은 정규식 사용을 지원합니다. 다음 비교 연산자(=, !=, <, <=, >, >=)와 Boolean 연산자(and, or, not)를 사용할 수 있습니다.

키워드 `in`을 사용하여 설정된 멤버쉽을 테스트하고 배열의 요소를 확인할 수 있습니다. 배열의 요소를 확인하려면 `in` 뒤에 배열을 놓으십시오. 부울 연산자 not은 in과 함께 사용할 수 있습니다. 예를 들어, 하단의 code snippet은 logGroup 필드가 전체 문자열 example_group 인 경우 `in`을 사용하여 로그 이벤트를 반환하는 쿼리입니다.

```
fields @timestamp, @message
| filter logGroup in ["example_group"]
```

키워드 구문 `like`와 `not like`를 사용하여 하위 문자열을 일치시킬 수 있습니다. 정규식 연산자 =~를 사용하여 하위 문자열을 일치시킬 수 있습니다. `like`와 `like`가 있는 부분 문자열을 일치시키려면 일치시킬 부분 문자열을 작은 따옴표 또는 큰 따옴표로 묶으십시오. `like`와 `not like`가 함께 있는 정규 표현 패턴을 사용할 수 있습니다. 부분 문자열을 정규식 연산자와 일치시키려면 일치시킬 부분 문자열을 슬래시로 묶으십시오. 다음 예제에는 `filter` 명령을 사용하여 하위 문자열을 일치시키는 방법을 보여주는 코드 조각이 포함되어 있습니다.

## 출처

[CloudWatch Logs Insights query syntax](https://docs.aws.amazon.com/AmazonCloudWatch/latest/logs/CWL_QuerySyntax.html)