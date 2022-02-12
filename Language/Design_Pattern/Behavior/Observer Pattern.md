# Observer Pattern

작성일시: February 9, 2022 10:05 PM
최종 편집일시: February 9, 2022 10:49 PM

## 0. 목차

## 1. 요약

> 객체의 상태 변화를 관찰하는 관찰자들, 즉 옵저버들의 목록을 객체에 등록하여 상태 변화가 있을 때마다 메서드 등을 통해 객체가 직접 목록의 각 옵저버에게 통지하도록 하는 디자인 패턴
> 

## 2. 정리

이벤트를 발생 시키는 주체인 `Subject`에서 이벤트가 발생하면 각 `Observer`는 콜백(callback)을 받는다.

`notify` 함수는 관찰 대상이 발행한 메시지 이외에, 옵서버 자신이 생성한 인자값을 전달할 수도 있다.

각각의 파생 옵서버는 `notify` 함수를 구현함으로써 이벤트가 발생했을 때 처리할 각자의 동작을 정의해야 한다.

주체에는 일반적으로 등록(register), 제거(unregister) 메서드가 있는데, 전자는 새로운 옵저버를 목록에 등록하고 후자는 목록에서 옵저버를 뺀다. 

### 3. 주의

여러 개의 옵저버 패턴을 사용하는 경우에는 순환 구조를 막아야할 필요가 있다.


## 4. 예제

- 인터페이스

```java
interface Observer {
    public void update(String alert);
}
```

```java
interface Subject {
    public void add(Observer observer);
    public void delete(Observer observer);
    public void notifyObserver();
}
```

- Observer

```java
class Safeguard implements Observer {
    private Subject subject;
    private String recieve;

    public Safeguard(Subject subject) {
        this.subject = subject;
        subject.add(this);
    }

    @Override
    public void update(String alert) {
        System.out.printf("recieve %s\n",alert);
        this.recieve = alert;
        rescue();
    }

    public void rescue() {
        System.out.println("safeguard : rescue People");
    }
}
```

```java
class Paramedic implements Observer {
    private Subject subject;
    private String recieve;

    public Paramedic(Subject subject) {
        this.subject = subject;
        subject.add(this);
    }

    @Override
    public void update(String alert) {
        System.out.printf("recieve %s\n",alert);
        this.recieve = alert;
        rescue();
    }

    public void rescue() {
        System.out.println("paramedic : go out to save someone");
    }
}
```

- Subject

```java
class Diver implements Subject {
    ArrayList<Observer> observers;
    String status;

    public Diver() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void add(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void delete(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for(Observer observer : this.observers){
            observer.update(status);
        }
    }

    public void setStatus(String status) {
        this.status = status;
        notifyObserver();
    }
}
```

- Main

```java
public class Main {
    public static void main(String[] args) {
        Diver diver = new Diver();
        Safeguard safeguard = new Safeguard(diver);
        Paramedic paramedic = new Paramedic(diver);

        diver.setStatus("help");
    }
}
```