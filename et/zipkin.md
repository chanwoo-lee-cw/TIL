# zipkin

## 1. zipkin

### 1.1 zipkin 이란?

MSA 환경에선 하나의 서비스 호출을 위해 여러개의 서비스가 호출 될 수 있다. 그렇기 때문에 특정 구간에서 문제가 발생한 경우에는 전통적인 ARM 방식으로는 추적하기가 힘들기 때문에 발명되었다.

### 1.2 zipkin의 구조

![https://zipkin.io/public/img/architecture-1.png](https://zipkin.io/public/img/architecture-1.png)

추적기는 어플리케이션에서 수행된 작업에 대한 타이밍과 메타데이터를 전송한다. 전체 Trace 고유 번호는 trace ID라고 하고, 각자 MSA에서 돌아가는 ID를 SpanID 라고 한다.

즉, 전체를 추적할 수 있는 traceID 밑에 각 spanID 가 있는 형태로 구성된다.

### 1.3 zipkin의 구성 요소

- collector
    - 추적 데이터가 Zipkin 수집기에 도착하면 검증, 저장 및 검색을 위해 인덱싱됩니다.
- storage
    - 로그 정보를 저장하는 장소
- search
    - 쿼리 데이터를 검색하기위한 간단한 JSON API를 제공합니다.이 API의 주요 소비자는 웹 UI.
- web UI
    - 트레이스를 표시하기 위한 인터페이스

## 2. zipkin Quick Start

- docker
  
    ```bash
    docker run -d -p 9411:9411 openzipkin/zipkin
    ```
    
- java
  
    ```bash
    curl -sSL https://zipkin.io/quickstart.sh | bash -s
    java -jar zipkin.jar
    ```
    
- 소스 실행
  
    ```bash
    # get the latest source
    git clone https://github.com/openzipkin/zipkin
    cd zipkin
    # Build the server and also make its dependencies
    ./mvnw -DskipTests --also-make -pl zipkin-server clean install
    # Run the server
    java -jar ./zipkin-server/target/zipkin-server-*exec.jar
    ```