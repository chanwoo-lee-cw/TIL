# CloudWatch

작성일시: 2022년 10월 1일 오후 10:23
최종 편집일시: 2022년 10월 3일 오후 10:31

## 지원되는 로그 및 검색된 필드

CloudWatch Logs Insight는 다양한 로그 유형을 지원합니다. CloudWatch Logs는 Amazon CloudWatch Logs에 전송되는 모든 로그에 대해 다음과 같은 5개의 시스템 필드를 자동으로 생성합니다.

- `@message` 는 파싱되지 않은 raw 로그 이벤트가 포함되어 있습니다. 이것은 입력된 로그 이벤트에 해당합니다.
- `@timestamp` 는 로그 이벤트의 타임 스탬프 필드 안의 이벤트의 타임 스탬프가 포함됩니다. 이는 입력 로그 이벤트의 타임스탬프 필드에 해당합니다.
- `@ingestionTime` 는 CloudWatch Logs가 로그 이벤트를 받은 시간을 포함한다.
- `@logStream` 은 포한함다. **로그 이벤트가 추가된 로그 스트림의 이름이 포함됩니다. 로그 스트림은 로그를 생성한 프로세스와 동일한 프로세스를 통해 로그를 그룹화합니다.**
- `@log` 는 `account-id` : `log-group-name` 형태로 된 로그 식발자 입니다. 멀티 로그 그룹을 querying 할 때, 이것은 특정 사건에 속하는 로그 그룹을 식별하는 데 유용할 수 있습니다.

CloudWatch Logs Insights는 생성하는 필드의 시작 부분에 `@` 기호를 삽입합니다.

많은 로그 타입에 대해서, CloudWatch Logs는 자동적으로 로그에 포함된 로그 필드 또한 자동으로 검색합니다. 이러한 자동 검색 필드는 아래의 표에 나와있습니다.

CloudWatch Logs Insights가 자동으로 검색하지 않는 필드가 있는 다른 유형의 로그의 경우 `parse` 명령을 사용하여 해당 쿼리에 사용할 임시적인 필드를 추출하고 생성할 수 있습니다.

만약 발견된 로그 필드의 이름이 @으로 시작되는 경우, CloudWatch Logs 는 시작부분에 추가 @를 추가하여 표시합니다. 예를 들면 @example.com인 필드의 이름은 @@example.com으로 표시된다.

| Log Type | Discovered log fields |
| --- | --- |
| Lambda logs | @timestamp, @logStream, @message, @requestId,@duration, @billedDuration, @type,@maxMemoryUsed, @memorySize|
| Amazon VPC flow logs | @timestamp, @logStream, @message, accountId, endTime, interfaceId, logStatus, startTime, version, action, bytes, dstAddr, dstPort, packets, protocol, srcAddr, srcPort |

## CloudWatch Logs Insights query syntax

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
    
### Guidelines for working with query commands

`@`나, `.` 가 같은 영문자가 아닌 문자가 포함된 쿼리를 검색할 때는 ``` 키를 사용해 로그 필드를 둘러 싸야 합니다. 예를 들어 로그 필드 `foo-bar`에는 영문자가 아닌 `-`이 포함되어 있으므로 `foo-bar`으로 묶어야 합니다.

`fields` 명령어를 `as` 키워드와 함께 사용하여 로그 이벤트의 필드 및 함수를 사용하는 임시 필드를 만들 수 있습니다. 예를 들어, `fields ispresent as isRes`는 isRes 라는 임시 필드를 생성하고, 그리고 임시 필드는 다른 쿼리에 사용될 수 있습니다.

`isRes`의 값은 `resolverArn`이 검색된 필드인지 여부에 따라 0 또는 1입니다. 쿼리에 여러 필드 명령이 포함되어 있고 표시 명령이 포함되어 있지 않으면 필드 명령에 지정된 모든 필드가 표시됩니다.

## Matches and regular expressions in the filter command

filter 명령은 정규식 사용을 지원합니다. 다음 비교 연산자(=, !=, <, <=, >, >=)와 Boolean 연산자(and, or, not)를 사용할 수 있습니다.

키워드 `in`을 사용하여 설정된 멤버쉽을 테스트하고 배열의 요소를 확인할 수 있습니다. 배열의 요소를 확인하려면 `in` 뒤에 배열을 놓으십시오. 부울 연산자 not은 in과 함께 사용할 수 있습니다. 예를 들어, 하단의 code snippet은 logGroup 필드가 전체 문자열 example_group 인 경우 `in`을 사용하여 로그 이벤트를 반환하는 쿼리입니다.

```
fields @timestamp, @message
| filter logGroup in ["example_group"]
```

키워드 구문 `like`와 `not like`를 사용하여 하위 문자열을 일치시킬 수 있습니다. 정규식 연산자 =~를 사용하여 하위 문자열을 일치시킬 수 있습니다. `like`와 `like`가 있는 부분 문자열을 일치시키려면 일치시킬 부분 문자열을 작은 따옴표 또는 큰 따옴표로 묶으십시오. `like`와 `not like`가 함께 있는 정규 표현 패턴을 사용할 수 있습니다. 부분 문자열을 정규식 연산자와 일치시키려면 일치시킬 부분 문자열을 슬래시로 묶으십시오. 다음 예제에는 `filter` 명령을 사용하여 하위 문자열을 일치시키는 방법을 보여주는 코드 조각이 포함되어 있습니다.

### Examples: Match substrings

다음 예제에서는 `f1`에 ***Exception***라는 단어가 포함된 로그 이벤트를 반환합니다. 세 가지 예 모두 대소문자를 구분합니다.

첫 번째 예제는 하위 문자열과 `like`를 일치시킵니다.

```
fields f1, f2, f3 
| filter f1 like "Exception"
```

두 번째 예는 `like`와 정규 표현 패턴을 가진 하위 문자열과 일치시킨다.

```
fields f1, f2, f3 
| filter f1 like /Exception/
```

세 번째 예제는 부분 문자열을 정규식과 일치시킵니다.

```
fields f1, f2, f3 
| filter f1 =~ /Exception/
```

### Example: Match substrings with wildcards

마침표 기호(.)를 정규식에서 와일드카드로 사용하여 하위 문자열을 일치시킬 수 있습니다. 다음 예제에서 쿼리 반환은 f1의 값이 ServiceLog 문자열로 시작하는 위치와 일치합니다.

```
fields f1, f2, f3
| filter f1 like /ServiceLog./
```

### Example: Exclude substrings from matches

다음 예제에서는 `f1`에 ***Exception***라는 단어가 포함되지 않은 로그 이벤트를 반환하는 쿼리를 보여 줍니다. 예제는 대소문자를 구분합니다.

```
fields f1, f2, f3 
| filter f1 not like "Exception"
```

### Example: Match substrings with case-insensitive patterns

대소문자를 구분하지 않는 substring을 같은 식과 정규식과 일치시킬 수 있습니다. 일치시킬 하위 문자열 앞에 다음 매개 변수**(?i)**를 배치합니다. 다음 예제에서는 `f1`에 ***Exception*** 또는 ***exception***이라는 단어가 포함된 로그 이벤트를 반환하는 쿼리를 보여 줍니다.

```
fields f1, f2, f3 
| filter f1 like /(?i)Exception/
```
## Using aliases in queries

별칭이 포함된 쿼리를 만듭니다. 별칭을 사용하여 로그 필드의 이름을 바꾸거나 사용 후 삭제 필드에 값을 추출할 때 사용합니다. 키워드 `as`를 사용하여 로그 필드를 지정하거나 별칭을 지정합니다. 쿼리에 둘 이상의 별칭을 사용할 수 있습니다. 

별칭을 사용할 수 있는 명령어 :

- `fields`
- `parse`
- `sort`
- `stats`

### Example

```
fields @timestamp, @message, accountId as ID
| sort @timestamp desc
| limit 20
```

쿼리는 `@timestamp`, `@message` 및 `accountId` 필드의 값을 반환합니다. 결과는 내림차순으로 정렬되고 20개로 제한됩니다. 그리고 `accountId`의 값은 별칭 ID 아래에 나열됩니다.

### Eaxmple

```
stats count(*) by duration as time 
| sort time desc
```

쿼리는 로그 그룹에서 `duration` 필드가 발생한 횟수를 카운트하고 결과를 내림차순으로 정렬합니다. `duration`에 대한 값은 `time`이라는 별칭 아래에 나열됩니다.

## Using comments in queries

CloudWatch Logs Insights는 쿼리의 주석을 지원합니다. 해시 문자(#)를 사용하여 주석을 설정합니다. 즉, 주석을 사용하여 쿼리 또는 문서 쿼리의 행을 무시할 수 있습니다.

```
fields @timestamp, @message, accountId
# | filter accountId not like "7983124201998"
| sort @timestamp desc
| limit 20
```

## Supported operations and functions

### Arithmetic operators

산술 연산자는 숫자 데이터 형식을 인수로 받아들이고 숫자 결과를 반환합니다. filter 및 fields 명령에서 산술 연산자를 사용하고 다른 함수에 대한 인수로 사용합니다.

| Operation | Description |
| --- | --- |
| a + b | Addition |
| a - b | Subtraction |
| a * b | Multiplication |
| a / b | Division |
| a ^ b | Exponentiation (2 ^ 3 returns 8) |
| a % b | Remainder or modulus (10 % 3 returns 1) |

### Boolean operators

Boolean 연산자 `and`, `or`, `not`을 사용할 수 있다.

> **Note**
> True, False 값을 반환하는 함수에서만 Boolean 연산자를 사용해야 한다.

### Comparison operators

비교 연산자는 모든 데이터 형식을 인수로 받아들이고 Boolean 결과를 반환합니다. `filter` 명령어 및 기타 함수의 인수로 비교 연산을 사용합니다.

| Operator | Description |
| --- | --- |
| = | Equal |
| != | Not equal |
| < | Less than |
| > | Greater than |
| <= | Less than or equal to |
| >= | Greater than or equal to |

### Numeric operations

숫자 연산은 숫자 데이터 형식을 인수로 받아들이고 숫자 결과를 반환합니다. `filter`와 `fields` 명령에서 숫자 연산을 사용하고 다른 함수의 인수로 사용합니다.

| Operation | Result type | Description |
| --- | --- | --- |
| abs(a: number) | number | 절대값 |
| ceil(a: number) | number | 올림 |
| floor(a: number) | number | 내림 |
| greatest(a: number, ...numbers: number[]) | number | 가장 큰 값 |
| least(a: number, ...numbers: number[]) | number | 가장 작은 값 |
| log(a: number) | number | 자연 로그 |
| sqrt(a: number) | number | 루트 값 |

### Datetime functions

`fields`와 `filter`에서 datetime functions을 사용할 수 있고, 다른 함수의 매개변수로 사용할 수 있다.

| Function | Result type | Description |
| --- | --- | --- |
| bin(period: Period) | Timestamp | @timestamp 값을 지정된 기간으로 반올림하고 자른다. 
예) bin(5m)은 @timestamp의 값을 5분으로 반올림하고 자른 값 |
| datefloor(timestamp: Timestamp, period: Period) | Timestamp | timestamp를 주어진 기간으로 내린다.
예) datefloor(@timestamp, 1h)는 @timestamp 값을 1시간 단위로 내림한다. |
| dateceil(timestamp: Timestamp, period: Period) | Timestamp | timestamp를 주어진 값으로 올림하고 자른다.
예) dateceil(@timestamp, 1h) 모든 @timestamp 값을 1시간 단위로 올림한다.. |
| fromMillis(fieldName: number) | Timestamp | Interprets the input field as the number of milliseconds since the Unix epoch and converts it to a timestamp. |
| toMillis(fieldName: Timestamp) | number | Converts the timestamp found in the named field into a number representing the milliseconds since the Unix epoch. For example, toMillis(@timestamp)converts the timestamp 2022-01-14T13:18:031.000-08:00 to 1642195111000. |


> **Note**
> 현재 CloudWatch Logs Insights는 사람이 읽을 수 있는 타임스탬프가 있는 로그 필터링을 지원하지 않습니다.

### General functions

General functions은 `fields` 와 `filter`명령어에서 사용되고, 다른 함수의 매개변수로 사용된다.

| Function | Result type | Description |
| --- | --- | --- |
| ispresent(fieldName: LogField) | Boolean | Returns true if the field exists |
| coalesce(fieldName: LogField, ...fieldNames: LogField[]) | LogField | Returns the first non-null value from the list |

### IP address string functions

| Function | Result type | Description |
| --- | --- | --- |
| isValidIp(fieldName: string) | boolean | 필드가 유효한 IPv4 또는 IPv6 주소이면 true를 반환합니다. |
| isValidIpV4(fieldName: string) | boolean | 필드가 유효한 IPv4 주소이면 true를 반환합니다. |
| isValidIpV6(fieldName: string) | boolean | 필드가 유효한 IPv6 주소인 경우 true를 반환합니다. |
| isIpInSubnet(fieldName: string, subnet: string) | boolean | 필드가 지정된 v4 또는 v6 서브넷 내의 유효한 IPv4 또는 IPv6 주소인 경우 true를 반환합니다. 서브넷을 지정할 때 192.0.2.0/24 또는 2001:db8:/32와 같은 CIDR 표기법을 사용합니다. |
| isIpv4InSubnet(fieldName: string, subnet: string) | boolean | 필드가 지정된 v4 서브넷 내의 유효한 IPv4 주소인 경우 true를 반환합니다. 서브넷을 지정할 때 192.0.2.0/24와 같은 CIDR 표기법을 사용합니다. |
| isIpv6InSubnet(fieldName: string, subnet: string) | boolean | 필드가 지정된 v6 서브넷 내의 유효한 IPv6 주소인 경우 true를 반환합니다. 서브넷을 지정할 때 2001:db8:/32와 같은 CIDR 표기법을 사용합니다. |

### Stats aggregation functions

| Function | Result type | Description |
| --- | --- | --- |
| avg(fieldName: NumericLogField) | number | 필드에 있는 값의 평균입니다. |
| count() </br> count(fieldName: LogField) | number | 로그 이벤트를 카운트합니다. count()는 쿼리에서 반환되는 모든 이벤트를 카운트하고 count(필드명)는 지정된 필드 이름을 포함하는 모든 레코드를 카운트합니다. |
| max(fieldName: LogField) | LogFieldValue | 쿼리된 로그에서 이 필드에 대한 값의 최대값 |
| min(fieldName: LogField) | LogFieldValue | 쿼리된 로그에서 이 로그 필드의 최소값입니다. |
| stddev(fieldName: NumericLogField) | number | 지정된 필드에 있는 값의 표준 편차입니다. |
| sum(fieldName: NumericLogField) | number | 지정된 필드의 값 합계입니다. |

## 출처

[CloudWatch Logs Insights query syntax](https://docs.aws.amazon.com/AmazonCloudWatch/latest/logs/CWL_QuerySyntax.html)