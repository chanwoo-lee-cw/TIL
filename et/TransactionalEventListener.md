# TransactionalEventListener

## `@TransactionalEventListener` 어노테이션이란?

> `@TransactionalEventListener` 는 Spring에서 트랜잭션의 상태에 따라 이벤트를 처리할 수 있게 해주는 어노테이션입니다.

`@TransactionalEventListener` 는 데이터베이스 트랜잭션이 완료되는 시점(커밋, 롤백 등)에 따라 이벤트를 처리할 수 있습니다.



## 동작 시점

| phase              | 설명                                                    |
| ------------------ | ------------------------------------------------------- |
| `AFTER_COMMIT`     | **기본값.** 트랜잭션이 정상적으로 커밋된 후 이벤트 실행 |
| `AFTER_ROLLBACK`   | 트랜잭션이 롤백된 후 이벤트 실행                        |
| `AFTER_COMPLETION` | 트랜잭션의 커밋 또는 롤백 후 무조건 실행                |
| `BEFORE_COMMIT`    | 트랜잭션이 커밋되기 직전에 실행                         |



## 활용 예제

### 오더 후 이메일 발송 Slack 발행

```kotlin
data class OrderCreateSlackEventDto(
    val orderId: Long,
  	val userName: String
   	val userEmail: String
  	val orderPrice: BigInteger,
) : ApplicationEvent(orderId)
```

```kotlin
@Service
class OrderService(
  	private val orderRepository: OrderRepository
    private val publisher: ApplicationEventPublisher
) {
    @Transactional
    fun createOrder(orderDto: OrderDto) {
        orderRepository.save(Order.from(orderDto))
        publisher.publishEvent(OrderCreateSlackEventDto(order.id))
    }
}
```

```kotlin
@Component
class OrderEventHandler {
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun sendOrderCreateSlackMessage(eventDto: OrderCreateSlackEventDto) {
      	// TODO 슬랙 발송 이벤트 추가
    }
}
```

`createOrder`가 전부 끝나고, 커밋이 완료된 이후에  `sendOrderCreateSlackMessage` 메소드가 실행이 된다.

-> 즉, 정상적으로 커밋된 경우에만 알림이 전송된다.



### 이슈 발생시 클린 업

```kotlin
@Component
class CleanupHandler {
    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    fun cleanupAfterFailure(event: OrderCreatedEvent) {
        println("주문 생성 실패. 임시 파일 삭제 등 정리 작업 수행")
    }
}
```



## 동작 원리

`@TransactionalEventListener` 는 `TransactionSynchronizationManager` 에 등록되어, 트랜잭션 완료 시점에 맞춰서 실행된다.

그래서, 만약 트랙젝션이 실행되지 않으면 실행되지 않는다. 예를 들면, 아예 트랜젝션으로 감싸지지 않은 메소드나, 트랜젝션으로 감싸져 있더라도 DB 접근을 하는 등의 트렌젝션을 실행하지 않으면 실행되지 않는다.

하지만, 이런 경우에도 실행하는 방법은 있는데, `fallbackExecution = true` 옵션으로 트랜잭션 외부에서 실행 할 수 있다.

```kotlin
@TransactionalEventListener(fallbackExecution = true)
fun handleWithoutTx(event: SomeEvent) {
    println("트랜잭션이 없어도 실행됨")
}
```





## 참고 문헌

- [https://wildeveloperetrain.tistory.com/246](https://wildeveloperetrain.tistory.com/246)