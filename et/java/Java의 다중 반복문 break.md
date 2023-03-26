# Java의 다중 반복문 break



코딩을 하다보면 다중 반복문을 작성하게 되는 경우가 자주 있다.

보통 다중 반복문을 한번에 빠져 나오게 하기 위해서는  `flag = false` 를 선언해서 특정 조건을 만족하면 `flag = true`로 바꾸고 각각 반복문을 break 선언해줘야 한다.

하지만, java는 반복문에 이름을 정해줘서 다중 반복문을 한꺼번에 탈출이 가능하다.

```java
loop:
for (int i = 1; i < 10; i += 2) {
    for (int j = 1; j < 10; j += 3) {
        int n = 10 * i + j;
        System.out.println(n);
        if (n > 30) {
            break loop;
        }
    }
}
11
14
17
31
```

가장 바깥의 반복문의 이름을 `loop`로 설정해서 `break loop`를 호출함으로써, break를 한번만 호출하였지만, 다중 반복문을 한꺼번에 탈출하는 것이 가능하다.

```java
int n;
int i = 0, j = 0;

loop:
while (true) {
    i += 2;
    while (true) {
        j += 3;
        n = i * 10 + j;
        System.out.println(n);
        if (n > 30) {
            break loop;
        }
    }
}
23
26
29
32
```

마찬가지로 `while문` 도 똑같이 사용가능하다.







단점은 아마 너무 남발하면 c의 goto를 남발함으로써 가독성이 저하된다는 단점이 있는 것 같다.