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

# Stream

## stream 이란?

> Stream은 Java 컬렉션과 함께 작동하며, 컬렉션의 요소들을 처리하고, 변환하고, 조작하는데 사용됩니다. Stream은 간결하고 가독성이 좋은 코드를 작성할 수 있도록 도와주며, 병렬처리를 통해 높은 성능을 제공합니다.
> 

## 예제

java steam을 사용하는 가장 대표적인 예제로는 컬렉션의 요소들을 순회하기 위해서 iterator 를 대신하는 용도로 주로 사용된다.

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
numbers.stream()
        .filter(item -> item % 2 == 0)
        .forEach(System.out::println);
```

interger List에서 짝수만 출력하는 방법.

steam을 통한 lamda 표현식을 통해 정수만 남긴 다음에, 메소드 참조 연산자를 통해 자세한 내용을 생락하고 출력했다.

### 1. 리스트에서 필터링하기

```java
// 여러 수로 이루어진 리스트에서 짝수로만 이루어진 새로운 리스트를 만드는 방법
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
List<Integer> evenNumbers = numbers.stream()
                                   .filter(n -> n % 2 == 0)
                                   .collect(Collectors.toList());
```

### 2. 맵에서 키-값 쌍 필터링하기

```java
Map<String, Integer> items = new HashMap<>();
items.put("A", 10);
items.put("B", 20);
items.put("C", 30);
items.put("D", 40);
items.put("E", 50);
 
// Map에서 value이 30 이상으로만 이루어진 새로운 맵을 만드는 방법
Map<String, Integer> filteredItems = items.entrySet()
                                          .stream()
                                          .filter(item -> item.getValue() > 30)
                                          .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
```

### 3. 리스트에서 값 추출하기

```java
List<String> names = Arrays.asList("John", "Jane", "David", "Tom");
String first = names.stream().findFirst().orElse("No names found");
```

문자열 리스트에서 첫 번째 값을 추출하는 코드. 만약 리스트가 비어있는 경우 `No names found`가 반환.

### 4. 리스트에서 집계하기

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
int sum = numbers.stream().mapToInt(Integer::intValue).sum();
```

위 코드는 숫자 리스트의 합계를 구하는 코드입니다. mapToInt() 함수를 사용하여 Integer를 int로 변환한 후 sum() 함수를 사용하여 합계를 구합니다.

## Steam의 하위 함수

### 1. map()

요소를 다른 요소로 변환하는 함수. 스트림의 각 요소에 대해 인자로 주어진 함수를 적용하여 새로운 요소를 반환한다.

```java
List<String> strings = Arrays.asList("Java", "is", "a", "programming", "language");
List<Integer> lengths = strings.stream().map(s -> s.length()).collect(Collectors.toList());
// lengths: [4, 2, 1, 11, 8]
```

위 코드에서 map() 함수는 문자열 리스트를 정수 리스트로 변환합니다. 문자열의 길이를 반환하는 함수를 map() 함수에 전달하여 새로운 정수 리스트를 반환합니다.

### 2. filter()

주어진 조건에 맞는 요소만 선택하는 함수입니다. 스트림의 각 요소에 대해 인자로 주어진 조건을 검사하고, 조건에 맞는 요소만 선택하여 새로운 스트림을 반환합니다.

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
List<Integer> evenNumbers = numbers.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
// evenNumbers: [2, 4, 6, 8, 10]
```

### 3. reduce()

스트림의 모든 요소를 결합하여 하나의 값으로 만드는 함수입니다. 스트림의 요소를 순서대로 연산하여 최종 결과를 반환합니다.

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
int sum = numbers.stream().reduce(0, (a, b) -> a + b);
// sum: 55
```

### 4. forEach()

스트림의 각 요소에 대해 주어진 함수를 실행하는 함수입니다. 스트림의 모든 요소를 반복하며 인자로 주어진 함수를 실행합니다.

```java
List<String> strings = Arrays.asList("Java", "is", "a", "programming", "language");
strings.stream().forEach(s -> System.out.println(s));
// 출력: Java
//       is
//       a
//       programming
//       language
```

### 5. collect()

스트림의 요소들을 컬렉션으로 변환하는 함수입니다. 스트림의 모든 요소를 컬렉션으로 수집하는 작업을 수행할 때 사용됩니다. collect 함수는 Collector 인터페이스를 구현한 객체를 매개변수로 받습니다.

1. toList() : 스트림의 모든 요소를 List로 변환합니다.
    
    ```java
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    List<String> strNumbers = numbers.stream().map(item -> item.toString()).collect(Collectors.toList());
    ```
    
2. toSet(): 스트림의 모든 요소를 Set으로 변환합니다.
3. toMap(): 스트림의 요소를 Key-Value 형태로 변환하여 Map으로 반환합니다.
    
    ```java
    @Data
    class Student {
        private int id;
        private String name;
    }
    
    Map<String, Student> studentMap = students.stream().collect(Collectors.toMap(Student::getName, s -> s));
    ```
    
4. joining(): 스트림의 요소를 지정된 구분자로 구분하여 문자열로 반환합니다.
    
    ```java
    List<String> stringList = Arrays.asList("Java", "is", "a", "programming", "language");
    
    String concatenatedString = stringList.stream().collect(Collectors.joining(" "));
    // Java is a programming language
    // 만약 구분자를 주지 않으면 단순히 이어서 준다.
    ```
    
5. summarizingInt()/summarizingDouble()/summarizingLong(): 스트림의 요소에 대한 요약 통계 정보를 계산하여 반환합니다.
6. groupingBy(): 스트림의 요소를 그룹화하여 Map으로 반환합니다.
    
    ```java
    @Data
    class Student {
        private int age;
        private String name;
    }
    
    Map<String, List<Student>> studentMapByAge = students.stream().collect(Collectors.groupingBy(Student::getAge));
    ```
    
7. partitioningBy(): 스트림의 요소를 조건에 따라 분할하여 Map으로 반환합니다.

### 5. flatMap()

중첩된 스트림을 평면화 시켜서 하나의 스트림으로 만들어주는 함수. 하나의 요소를 여러개의 요소로 매핑하는 map과는 달리, 하나의 요소를 여러개의 스트림으로 매핑한 다음에 하나의 스트림으로 만들어준다.

이 함수는 인자로 받은 요소를 처리하여 평면화된 스트림을 반환하는 로직을 구현해야 합니다.

```java
List<List<Integer>> nestedList = Arrays.asList(
    Arrays.asList(1, 2, 3),
    Arrays.asList(4, 5, 6),
    Arrays.asList(7, 8, 9)
);

List<Integer> flatList = nestedList.stream()
                                   .flatMap(Collection::stream)
                                   .collect(Collectors.toList());
```

```java
Stream<String> s1 = Stream.of("AA", "BB", "CC");
Stream<String> s2 = Stream.of("DD", "EE", "FF");

//Using flatMap() to merge two streams into one stream
Stream<String> s3 = Stream.of(s1, s2).flatMap(s -> s);
```