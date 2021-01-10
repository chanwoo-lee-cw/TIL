# Command Pattern

분류: Design Pattern
소분류: Behavioral
작성일시: Dec 10, 2020 10:14 PM
최종 편집일시: Dec 12, 2020 9:03 PM

## 1. 커멘드 패턴

> 요구 사항을 객체로 캡슐화하여, 매개 변수를 통해 클라이언트의 서로 다른 요구 사항이나, 요청 내역을 큐에 저장하거나 로그로 기록할 수 있고, 작업 취소도 지원하게 한다.

## 2. 커멘드 패턴이란?

- 실행될 기능을 캡슐화 함으로써 클래스를 변경시키지 않고, 실행될 기능이 다양하고 변경이 필요한 경우에 유용한다.
  - `execute()` 기능 하나를 외부로 공개하고, 이 메소드 호출로 인해 모든 기능이 실행된다. 사용자는 이 것이 리시버 역활을 하는지, 그 리시버에서 어떤 일을 하는지 알 수 없다.
  - `button()` 을 클릭함으로써 불이 켜져 있는 경우엔 불을 켜고, 꺼져 있는 경우엔 불을 끄는 등의 기능을 하게 한다.
  - 즉, 하나의 인터페이스를 선언하고, 각 명령이 들어오면 그에 맞는 서브 클래스를 실행하는 방식이다.

## 3. 커멘드 패턴의 구조

![그림1.png](https://user-images.githubusercontent.com/50443940/101983362-fc0b0c00-3cbd-11eb-8bc4-035c1264bbcf.png)

### 3.1. 커멘드 패턴의 4요소

1. command
    - 커멘드 객체는 별도의 발동자 객체에 전달되어 명령을 발동되게 한다.
    - 가장 핵심이 되는 요소
2. receiver
    - 자신에게 정의된 매서드를 호출한다.
3. invoker
    - 발동자 객체는 필요에 따라 명령 발동에 대한 기록을 남길 수 있다.
    - 한 발동자 객체에 다수의 커멘드 객체가 전달될 수 있다.
4. client
    - 클라이언트 객체는 발동자 객체와 하나 이상의 커멘드 객체를 보유한다.
    - 클라이언트 객체는 어느 시점에서 어떤 명령을 수행할 것인지 결정한다.
    - 명령을 수행하려면, 클라이언트 객체는 발동자 객체로 커맨드 객체를 전달한다.

### 3.2. 커멘드 패턴의 작업 흐름

커멘드 객체는 수신자 객체를 가지고 있으며, 수신자에서 매서드를 호출하고, 이에 수신자는 자신에게 정의된 매서드를 호출한다.

## 4. 예제

### 4.1. Light 기능

- Command

```java
public interface Command {
    void excute();
}
```

- ConcreteCommand

```java
public class TurnOnLightCommand implements Command{
   private Light theLight;

   public TurnOnLightCommand(Light light){
        this.theLight=light;
   }

   public void execute(){
      theLight.turnOn();
   }
}
```

- Invoker

```java
public class Switch {
    private Command buttonClick;

    public Switch(Command buttonClick){
        this.buttonClick=buttonClick;
    }

    public void click(){
         buttonClick.execute();
    }
}
```

- Receiver class

```java
public class Light{
     public Light(){  }

     public void turnOn(){
        System.out.println("The light is on");
     }
}
```

- 클라이언트에서 사용

```java
public class TestCommand {
    public static void main(String[] args) {
        Light light = new Light();
        Command buttonClick = new TurnOnLightCommand(light);

        Switch s = new Switch(buttonClick);

        s.click();  // 스위치에 있는 버튼을 클릭
    }
}
```

### 4.2. Undo 기능추가

- Command

```java
public interface Command {
    void excute();
    void undo();
}
```

- ConcreteCommand

```java
public class TurnOnLightCommand implements Command{
    private Light theLight;

    public TurnOnLightCommand(Light light){
        this.theLight=light;
    }

    public void execute(){
        theLight.turnOn();
    }

    public void undo() {
        theLight.turnOff();
    }
}
```

- 리모콘에서는 명령을 스택에 쌓고, `undo`를 실행할 때마다 `pop`하여 순서대로 실행 취소를 할 수 있다.
- 그 외에도 매크로 기록, 작업 단위 취소, 히스토리 기록 등을 응용해 구현할 수 있다.

### 4.3. Thread

- 가장 대표적인 Command 패턴
- 사용자는 `Thread.run()`에 각각 기능을 만들어 공통된 `run()`을 통해 실행한다. 사용자는 Command가 무엇을 하는지 모른다.

```java
class ThreadEx01 {
    public static void main(String args[]) {
        ThreadEx1_1 t1 = new ThreadEx1_1();

        Runnable r = new ThreadEx1_2();
        Thread t2 = new Thread(r);      // 생성자 Thread(Runnable target)

        t1.start();
        t2.start();
    }
}
```

```java
public class ThreadEx1_1 extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(getName()); // 조상인 Thread의 getName()을 호출
        }
    }
}
```

```java
public class ThreadEx1_2 implements Runnable {
    public void run() {
        for (int i = 0; i < 5; i++) {
            // Thread.currentThread() - 현재 실행중인 Thread를 반환한다.
            System.out.println(Thread.currentThread().getName());
        }
    }
}
```

## 5. 출처

- [https://gmlwjd9405.github.io/2018/07/07/command-pattern.html](https://gmlwjd9405.github.io/2018/07/07/command-pattern.html)
- [https://johngrib.github.io/wiki/command-pattern/](https://johngrib.github.io/wiki/command-pattern/)
- [https://en.wikipedia.org/wiki/Command_pattern](https://en.wikipedia.org/wiki/Command_pattern)
