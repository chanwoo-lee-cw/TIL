# Strategy Pattern

분류: Design Pattern
소분류: Behavioral
작성일시: Dec 10, 2020 10:14 PM
최종 편집일시: Dec 11, 2020 12:47 AM

## 1. 스트래티지 패턴

> 실행 중에 전략(알고리즘)을 선택할 수 있게 하는 행위 소프트웨어 디자인 패턴이다

## 2. 스트래티지 패턴이란?

- 행위를 클래스로 캡슐화해 동적으로 행위를 자유롭게 바꿀 수 있게 해주는 패턴
- 예를 들면, 게임에서 무기마다 공격 모션을 전부 다르게 정해줘야 한다.

## 3. 구조

### 3.1. UML 클래스

![Strategy.png](https://user-images.githubusercontent.com/50443940/101795299-1a9cc600-3b4b-11eb-9c64-440818f7309d.png)

- Strategy
  - 인터페이스나 추상클래스로 외부에서 동일한 방식으로 알고리즘을 호출함을 명시
- Context
  - 스트래티지 패턴을 이용하는 역활을 수행한다.
  - 필요에 따라 동적으로 구체적인 전략을 바꿀 수 있도록 setter 매서드를 제공한다.

## 4. 예시

### 4.1 스트레티지 패턴 미적용

![non_Strategy.png](https://user-images.githubusercontent.com/50443940/101795305-1bcdf300-3b4b-11eb-845c-67c44ed2adec.png)

- 각각 무기 별로 전부 공격과 방어가 다르다.
  - 공격은 한손검과 방패의 경우에는 휘두르기이고, 창의 경우에는 찌르기, 해머의 경우에는 내려치기
  - 방어는 한손검과 방패의 경우에는 막기, 창의 경우에는 회피, 해머의 경우에도 회피이다.
- 그런데 이렇게 했을 때는 각각 무기별로 전략을 각각 클래스에 선언했을 경우에는 어떨까.
  - 만약 회피 모션을 수정해야 되는 경우에는 창과 해머 둘 다 회피 모션을 수정해 줘야 할 것이다.
  - 새로운 무기로 단창과 방패가 추가 된다면, 단창에 공격에 찌르기, 방어에는 막기를 새로 추가 필요.

### 4.2. 스트레티지 패턴 적용

> 무엇이 변화되는지 찾은 후에 그것들을 클래스로 캡슐화

![Strategy.png](https://user-images.githubusercontent.com/50443940/101795309-1c668980-3b4b-11eb-95f3-22e0db480446.png)

- Wepon 클래스가 공격과 방어기능을 이용하는 클라이언트 역활
    - 각각 기능이 AttackStrategy, DefenceStrategy 인터페이스에 의해 캡슐화되어 있다.
    - 이 인터페이스들이 Weapon 클래스의 변경을 차단한다.
- 스트래티지 패턴을 이용하면 새로운 기능의 추가(새로운 공격, 방어 기능)가 기존의 코드에 영향을 미치지 못하게 하므로 OCP를 만족 하는 설계가 된다.
  
- 이렇게 변경된 구조에서는 공격과 방어를 변경해 주기 위해서는 각각의 setter가 필요하다.
  
- Weapon 클래스

    ```java
    public abstract class Weapon {
        public String name;
        private AttackStrategy attackStrategy;
        private DefenceStrategy defenceStrategy;

        Weapon(String name) {
            this.name = name;
        }
        public void attack() {
            attackStrategy.attack();
        }

        public void defence() {
            defenceStrategy.defence();
        }

        public void setAttackStrategy(AttackStrategy attackStrategy) {
            this.attackStrategy = attackStrategy;
        }

        public void setMovingStrategy(DefenceStrategy defenceStrategy) {
            this.defenceStrategy = defenceStrategy;
        }
    }
    ```

- Weapon을 상속 받은 무기들

    ```java
    public class OneSwordSheild extends Weapon {
        public OneSwordSheild(String name) { 
            super(name); 
        }
    }
    ```

    ```java
    public class Spear extends Weapon {
        public Spear(String name) { 
            super(name); 
        }
    }
    ```

- 공격, 방어 전략 클래스
  - 공격

    ```java
    interface AttackStrategy { 
        public void attack(); 
    }
    ```

    ```java
    public class Pierce implements AttackStrategy {
        public void attack() { 
            System.out.println("찌르기!"); 
        }
    }
    ```

    ```java
    public class Wield implements AttackStrategy {
        public void attack() {
            System.out.println("휘두르기");
        }
    }
    ```

    - 방어

    ```java
    interface DefenceStrategy {
        public void defence();
    }
    ```

    ```java
    public class Sheild implements DefenceStrategy {
        public void defence() {
            System.out.println("막기");
        }
    }
    ```

    ```java
    public class Evade implements DefenceStrategy {
        public void defence() {
            System.out.println("구르");
        }
    }
    ```

- 클라이언트에서 사용

    ```java
    public class Main {

        public static int getMinCalculation(int n) {
            Weapon spaer = new Spear("spear");
            spaer.setAttackStrategy(new Pierce());
            spaer.setDefenceStrategy(new Evade());

            Weapon onSwordShild = new Spear("onSwordShild");
            onSwordShild.setAttackStrategy(new Wield());
            onSwordShild.setDefenceStrategy(new Sheild());

            System.out.println("spear Behavior");
            spaer.attack();
            spaer.defence();

            System.out.println("onSwordShild Behavior");
            onSwordShild.attack();
            onSwordShild.defence();
        }
    }
    ```
