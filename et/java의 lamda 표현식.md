# lamda 표현식

## 람다 표현식이란?

> Lambda 표현식은 익명 함수를 생성하는데 사용되는 간단한 표기법. 주로, 함수 내부에서 간단한 함수를 정의할 때 사용된다.

## 가장 기본적인 예시

```java
public int sum(int a, int b) {
    return a + b;
}
```

이렇게 겨우 한줄 짜리 코드를 사용하는데, 3줄이나 선언하고 또 따로 선언해야 하는 경우가 종종 생긴다.

이 경우에 람다 표현식으로 사용하면

```java
(a, b) -> a + b
```

같은 방식으로 사용 가능하다.

분명 함수를 선언했지만, 함수명을 선언하지 않았기 때문에 익명함수라고 칭한다. 함수명이 존재하지 않기 때문에 복잡한 기능보단 작은 기능을 다른 함수 내부에서 정의하고 사용할 때 유용하다.

- 전체 사용 예시

```java
import java.util.function.BinaryOperator;

public class Main {
    public static void main(String[] args) {
        int x = 3, y = 2;
        BinaryOperator<Integer> add = (a, b) -> a + b;
        System.out.println(String.format("%02d", add.apply(x, y)));
    }
}
```

위의 예시는 매개변수가 2개라서 괄호를 사용했지만, 만약 매개변수가 1개인 경우네는 괄호를 생략하는 것이 가능하다

```java
BinaryOperator<Integer> square = a -> a * a;
```

## 중괄호가 들어간 lamda 표현식

보통 lamda 표현식의 괄호는 생략 가능하다.

다만 중괄호를 생략할 수 있는 경우가 몇가지가 있다.

### 1. 함수 본문이 여러 줄인 경우

```java
BinaryOperator<Integer> add = (a, b) -> {
    int sum = a + b;
    return sum;
};
```

### 2. return 키워드를 사용하는 경우

```java
BinaryOperator<Integer> add = (a, b) -> a + b; // 중괄호를 생략한 예시
BinaryOperator<Integer> subtract = (a, b) -> {
    return a - b; // 중괄호를 생략할 수 없는 예시
};
```

### 3. 변수를 수정하는 경우

```java
List<Integer> numbers = Arrays.asList(1, 2, 3);
numbers.forEach(n -> { n *= 2; }); // 중괄호를 생략할 수 없는 예시
```

### 4. 함수 인수의 유형을 지정할 때

```java
// 매개변수의 인수 유형 생략 불가능
BinaryOperator<Integer> add = (Integer a, Integer b) -> a + b; 
```

다른 경우와는 다르게 매개변수 부분에 괄호가 들어가는 경우이다. 보통 컴파일러는 인수의 유형을 자동으로 추론하여 자동적으로 매개변수의 자료형을 결정하지만, 종종 추론하지 못해서 직접 자료형을 넣어줘야 하는 경우가 이런 경우에 사용된다.

## 람다를 사용한 다양한 예지

1. 람다를 이용한 쓰레드

   ```java
   public class Main {
       public static void main(String[] args) {
           for (int i = 1; i < 10; i++) {
               int n = i;
               Thread thread = new Thread(() -> {
                   printTreadNumber(n);
               });
               thread.run();
           }
       }
   
       public static void printTreadNumber(int n) {
           System.out.printf("tread number%d\\n", n);
       }
   }
   ```

2. 람다를 이용한 콜렉션 순회

   ```java
   import java.util.ArrayList;
   import java.util.List;
   
   public class Main {
       public static void main(String[] args) {
           List<Integer> intList = new ArrayList<>();
           intList.add(1);
           intList.add(2);
           intList.add(3);
   
           intList.forEach(x -> System.out.printf("number %d\\n", x));
       }
   }
   ```