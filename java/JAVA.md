JAVA



## 설치

1. 크롬 브라우저 설치 :

   https://www.google.com/chrome 

2. Java SE(JDK) 설치 : 

   http://java.sun.com/ -> https://www.oracle.com/technetwork/java/index.html

3. 설치후 환경변수 설정 : JAVA_HOME, PATH
   JAVA_HOME - C:\Program Files\Java\jdk1.8.0_231

   PATH - %JAVA_HOME%\bin

4. Eclipse 설치 : http://www.eclipse.org/

   C:\unico\eclipse-workspace
   프로젝트라는 폴더를 생성해야 한다.

   

***



 ## 자바 구문

 1. 데이터 타입: 2장
 2. 변수 활용:2장
 3. 연산자:3장
 4. 제어문:4장
 5. 배열: 5장 
 -------------- 기본 구문
 6. 클래스 정의와 객체 생성
 7. 상속, 다형성, 추상클래스, 인터페이스
 8. 예외처리

-------------------- OOP 구문 객체지향 구문



***



API - application programming interface
	자주 필요로하는 기능을 미리 만들어 놓은 프로그램
	클래스(Math, Date, Calendar, ...) - 패키지(java.io, java.net,java.sql, java.lang...) 패키지들
	IO 패키지 - 파일 입출력 패키지. 
	패키지 - 클래스 묶음. JAVA.LANG - OBJECT, STRING, STIRNGbUFFER 등등 클래스들.
	패키지화 학습 소스들을 패키지화 : DAY1, DAY2 ..... 클래스 묶어 놓고, 그룹화

LiteralTest

## 데이터 타입

프로그램 - 데이타 처리를 위한 것 빠르고 정확하고
자바로 처리할수 있는 데이타 종류들

	숫자데이터 - 정수/byte,short,int,long , 실수/float/double(소수점 붙어있는 수)
	1 1.0 '1' 완전히 각기 다른 일.
	정수/byte 1byte short 2byte int 4byte long 8byte  || 실수 / float 4 double 8
	왠만해서는 byte나 short 거의 안쓴다고 보면됌.
	
	논리데이터 - true, false
	문자데이터 - 문자의 코드값 처리 가능. 2 byte. '1' = 49(ascii) , '1' - 0031 , 'a' - 0061 '가' - AC00
	문자열데이터 - rorcpfh cnlrmq.

1 정수 1.0 실수 '1' 문자 "1" 문자열

리터럴(literal) : 프로그램 소스코드에서 사용되는 데이터값
	1, 1.0, '1', "1", "가나다", true, false 이 모두다 리터럴 정수, 실수, 문자, 문자열 리터럴, 

변수 : 데이터 값을 저장하는 메모리의 방
	데이터값을 저장하기 위해 메모리의 일정 공간에 붙여진 이름.
 	저장된 데이터 값을 계속해서 변경 가능
	필요시 생성해서 사용한다

변수 선언 int(타입, variable type) num(메모리방 이름, variable name, address)

변수명 명명법 소문자로 시작, 명사, 숫자로 시작 불가, 중간에는 가능, _, $ 는 괜춘.
클래스는 대문자로

자바 주요 연산자
	- 기능
	산술연산자, 비교연산자, 논리연산자, 조건연산자, 대입연산자

	- 사용되는항 (피연산자, 연산에 사용되는 데이터) 의 갯수
	단항 연산자 : ++num(num = num+1) R 지원안함.
	이항연산자 : 항1 연산자 항2
	삼항 연산자: 항1 ? 항2 : 항3
	
	. 
	++, --, +,  -, *, /, !, ~, (타입명)
 	*, /, %
             +, -
	==, !=, >, <, >=, <=, instanceof
	>>,>>>,<<
	&, |, ^
	&& 
             ||
	항1 ? 항2 : 항3 (삼항)
	=, +=, -=, *=, /=
	

 	연산자 피연산자(항)

             ++su, --su, -su, !true
           
             ++su, su++

  l-value : 방 : 변수
  r-value : 값 : 식(변수, 리터럴, 연산식, 리턴값이 있는 메서드의 호출식)

  = 연산을 처리할 때
  l-value의 타입과 r-value의 타입은 동일해야 한다.
  그런데 만일 다른 타입이 사용되면 r-value의 값이 손실되지 않는 범위에서
  l-value의 타입으로 자동 변환한다.

```java
int = char
(4)   (2)
int = double
(4)   (8)

byte < short < int < long < float < double
(1)     (2)    (4)    (8)    (4)     (8)
char < int < long < float < double
(2)    (4)   (8)     (4)     (8)

boolean

short = (short)char
char = (char)short
char = (char)byte
```

  [ 제어문 ]

  정의된 수행 문장들을 한번씩 순차적으로 수행하면서 진행하는 것이 기본이지만
  조건에 따라 수행 문장들을 선택하여 수행거나 반복해서 여러번 수행도록 하고자 할 때
  제어문을 사용한다.

  - 선택(조건)제어문 - if(else), switch
  - 반복제어문 - for, while, do ~ while
  - 분기제어문 - break, continue

  if(조건식)
     수행문장;

  if(조건식) {
     수행문장1;
     수행문장2;
  }

  if(조건식) {
     수행문장1;
     수행문장2;
  } else {
     수행문장3;
     수행문장4;
  }

  if(조건식1)
     수행문장1;
  else if(조건식2)
     수행문장2;
  else if(조건식3)
     수행문장3;
         :
  else
     수행문장n;

   switch(식) {
      case 비교값1 : 수행문장1;
                          수행문장2;
      case 비교값2 : 수행문장3;
                          수행문장4;
      case 비교값3 : 수행문장5;
                          수행문장6;
      case 비교값4 : 수행문장7;
                          수행문장8;
      default : 수행문장9;
   }

```java
switch(식) {
  case 비교값1 :
        수행문장1;
        수행문장2;
        break;
  case 비교값2 : 
        수행문장3;
        수행문장4;
        break;
  case 비교값3 : 
        수행문장5;
        수행문장6;
  case 비교값4 : 
        수행문장7;
        수행문장8;
        break;
    default : 수행문장9;
}
```


   식 : int(byte,short,char), String

​    




   [ 반복구문 ]
   for   	: 미리 반복횟수를 할고 있거나 어떠한 값의 변화를 주면서 반복하려는 경우
   while	: 조건이 만족되는 동안 반복하려는 경우

   for(초기식;조건식;증감식)
       반복문장

   for(;조건식;)
       반복문장;

   while(조건식)
       반복문장


   for(변수의선언 및 초기화;반복횟수를 결정할 조건식;변수의 값을 변화시키는식)

   for(;;)    ---> 무한루프

   for(int i=1; i <= 10; i++)
         반복문장

   for(int i=1; i <= 10; i+=2)
         반복문장


   for(int num=1; num <= 9; ++num)
	System.out.print(5*num + "    ")

   for(int n=5; n > 0; n--)
	System.out.println(n)

   for(int n=5; n > 0; n++)
	System.out.println(n)


   l-value = r-value
   변수        식

   double    int
   long       char
   int         (int)double   
   char       (char)int

   - 자동 형 변환
     (1) 정수에서 실수
     (2) 사이즈 적은타입에서 큰타입

   v1 + v2

   int   int   --> int
   long long --> long
   float float --> float
   int   double --> double
   int   char  --> int
   int   long  --> long
   long  float  --> float

   char  char  --> int

   - 중첩된 for 문 : 구구단

     ​						

   for : 횟수에 반복, 값의 변화에 따른 반복
   while : 조건에 따른 반복	

   for(초기식;조건식;증감식)
         반복문장;	

   초기식;
   while(조건식) {
         반복문장;			      
         증감식;
   }

   JAVA, [SQL], JavaScript, R, Scalar, (PYTHON)

   [ 자바의 산술 이항 연산의 특징 ] - 교재 92 페이지

   (1) int 타입보다 작은 타입들(byte, short, char)은 int 타입으로 변환하여 연산
   (2) 두 항의 타입이 다를 때 하나로 일치해서 연산(표현 범위가 적은 타입에서 큰타입으로)



## 배열 : array

1. 동일한 타입의 데이터들의 집합

2. 배열을 만드는 방법

   1. 배열을 구성하려는 데이터들의 타입

   2. 배열로 구성하려는 데이터들의 최대 갯수 - 한번 정하면 수정 할 수 없다.

      new 데이터타입[크기]

      ``` java
      // 각 타입별로 기본 값
      new int[10];	//0
      new char[26];	//'\u00000'
      new double[5];	// 0.0
      new long[5];	//0L
      new boolean[10]	//False
      ```

   3. 배열을 사용하는 방법

      - 배열을 사용하기 위해서는 배열을 만든 다음 변수에 담는다.

      - 배열 변수가 필요하다.

        - 타입[] 변수명; 타입 변수명[];

        ``` java
        int a1[];
        int[] a2;
        char[] a3;
        boolean a4[];
        
        int a1[] = new int [10];
        int a2[] = {10,20,30};
        
        a[0]	// a1에 담겨진 데이터 중 첫번째 데이터
        a[1]	// a1에 담겨진 데이터 중 두번째 데이터
        // 이런 식으로 간다.
        ```

        베열 변수명[인덱스] // 인덱스는 0부터 지정

        배열을 구성하는 데이터들 : 엘리먼트, 요소, 원소

        배열 변수.length : 배열 변수에 대입된 배열 요소의 갯구

        ```java
        System.out.println(a1[0]);
        System.out.println(a1[1]);
        System.out.println(a1[2]);
        		:
        System.out.println(a1[9]);
        
        // 보통 출력은 for문으로 한다.
        for(int i=0;i<a1.length;i++)
            System.out.println(a1[i]);
        ```







### for : for each 문

- 배열 또는 컬렉션 객체의 데이터들을 꺼내서 반복 처리 하려는 경우 사용하는 반복문이다.
- 앞에서 차례대로 하나하나 꺼내서 처리하려는 경우



``` java
for(변수선언 : 배열 or 컬렉션 객체){
    반복문장;
}

//예시
for (int data : score)
    sum+=data;
//이것과 같다.
for(int i=0;i<score.length;i++)
    sum +=score[i]
```



```java
System.out.println();
System.out.print();
```

문제점

1. 출력 데이터를 한개만
2. 출력데이터 여러 개 일 때는 + 연산자로 결합하여 한 개로 전달해야한다.
3. 출력하려는 데이터의 타입에 따라 출력 형식이 정해진다.
   1. int -> 10진수 숫자
   2. char -> 문자
   3. double -> 소숫점 이하의 자리수를 설정불가



Java 5 ( jdk1.5 )

```java
System.out.printf();	//결함 보완을 위해 추가 됨
System.out.printf("출력하는 포멧 문자열");
System.out.printf("가나다\n");
System.out.printf("가나다%n"); //\n %n둘 다 개행 처리
System.out.printf("가나%d다",sum);
System.out.printf("가나%.2f다",sum);	//소숫점 셋째자리에서 반올림, 둘째자리까지 출력
System.out.printf("가%d나%.2f다",100,99.56789);	//가100나99.568다
```

특징

1. 첫번째 데이터를 문자열로 보내줌
2. 출력하고 싶은 데이터를 두번째 데이터로 보내줌, 첫번째 문자열에 넣고 싶은 위치에 포멧 문자열 넣는다.
3. 무조건 순서대로 넣어야 한다.
4. 포멧 문자와 숫자가 안 맞아도 에러남



```java
System.out.printf("테스트입니당\n");		
// %n는 개행
System.out.printf("%d %x %o %c\n", 100, 100, 100, 100);
System.out.printf("%#x %#X %#o\n", 100, 100, 100);		
//x가 대문자냐 소문자에 따라 0x가 대문자일지 소문자 일지 결정,o는 8진수
System.out.printf("%c %c %c %c\n", '가', 'A', '!', '3' );	
//%c는 char형 출력
System.out.printf("%b\n", true);
//boolean형 출력
System.out.printf("%f %e\n", 100.0, 100.0);		
//%e는 지수형태로 출력
System.out.printf("%.2f\n", 123.5678);
System.out.printf("|%s|\n", "자바");
System.out.printf("|%10s|\n", "자바");		
//15글자를 출력하고 싶을때는 그냥 10 무시되고 출력된다
System.out.printf("|%-10s|\n", "자바");
System.out.printf("%,d원\n", 1000000);		// 큰 숫자는 %d를 하면 알아서 100 단위로 끊는다.			
```



## 매서드

- 수행코드(수행 명령, 수행 문장)를 정의 하는 단위
- main도 매서드이다.
- 클래스 안에 정의 된다.
- 하나의 클래스 안에는 0개 이상의 매서드가 정의 될 수 있다.
- 매서드를 생성하는 방법
- 매서드를 사용하는 방법 - 호출



### 매서드를 생성하는 방법

- 매서드명 :
  1. 소문자로 시작하고 새로운 워드 등장시 첫 글자만 대문자로
  2. 식별자(명명) 규칙 적용
  3. 동사로 시작
- 매서드 호출시 전달 받은 데이터의 유무
  - 있을 경우 : 몇 개, 어떤 타입 -> 매개 변수 선언
- 호출 된 후 호출 된 것으로 값을 줄 것인지의 여부를 결정 -- 리턴값
  - retrun 이라는 구문을 사용한다.



```java
 제어자-리턴 값의 타입-매서드명- 매서드명(매개변수 선언) {
     지역변수 선언;
     수행 문장....;
     return 리턴값;
}
```



### 매서드를 사용하는 방법 - 호출

1. 기능
2. 헤더를 보고
   - 매서드 명
   - 호출시 전달한 데이터(argument)의 유무
   - 리턴 값의 유무에 따라서 리턴 값을 사용
     - 변수=식
     - result = System.out.println();
     - rand =Math.random
   - 배열 타입의 매개변수를 선언하여 매서드 호출시 배열을 아규먼트로 전달받으면 전달 받는 데이터의 갯수를 제한하지 않게 된다.
   - java(JDK 1.5)
     - 가변형 아규먼트 구문이 추가된다.
     - 리턴타입 매서드명(int... p) {}
     - 매서드명(1,2,3), 매서드명(1,2,3,4,5)



Run Configurations

- 실행 정보 수정
- Main class 위치 day6.MethodTest7 - 패키지 명도 같이 인식한다. - 이걸로 들어가서 argument를 수정 blank로 구분
- String[] args 프로그램을 실행 시킬때 전달시키는 데이터.



### 프로그램 아규먼트(명령행 데이터)

프로그램을 수행 시작 시키면서 최초에 한번만 전달되는 데이터들

main(String[] args)

(Integet.parsInt(),Double.parseDouble()를 이용해서 숫자로 변환해서 사용한다.)





## 객체

- 클래스의 정의 - 클래스란 객체를 정의해 놓은 것이다.
- 클래스의 용도 - 클래스는 객체를 생성하는데 사용된다.
- 객체의 정의 - 실제로 존재하는것, 사물 또는 개념
- 객체의 용도 - 속성과 기능에 따라 다름



### 객체와 인스턴스

#### 객체 != 인스턴스

- 객체는 인스턴스를 포함하는 일반 적인 의미
- 인스턴스화
  - 클래스로부터 인스턴스를 생성하는 것
  - 클래스 --- 인스턴스화 ----> 인스턴스(객체)

#### 객체의 구성요소 - 속성과 기능

- 객체는 속성과 기능으로 이루어져 있다.
  - 객체는 속성과 기능의 집합이며, 속성과 기능을 객체의 멤머라고 한다.
- 속성은 변수로, 기능은 매서드로 정의한다.
  - 클래스를 정의할 때 객체의 속성은 변수로, 기능은 매서드로 정의한다.



#### 인스턴스 생성과 사용

- 인스턴스 생성 방법

  - 클래스명 참조변수명				//객체를 다루기 위한 참조 변수 선언
  - 참조변수명 =  new 클래스명()  // 객체 생성 후, 생선된 객체의 주소를 참조 변수에 저장

  // 배열도 클래스다

  // 클래스는 첫 글자는 대문자



하나의 클래스 안에 같은 이름의 클래스가 존재 할 수 없다.



#### 생성자 매서드 : constructor

- 클래스를 객체 생성할 때 호출 되는 인스턴스 초기화 매서드

  - new 클래스명()

    // 생성자 매서드

- 모든 클래스는 1개 이상의 매서드를 가지고 있어야한다.

- 클래스의 소스에 생성자 매서드가 정의되어 있지 않으면 컴파일러가 생성자를 만들어준다 -> 디폴트 생성자

- 생성자 매서드 정의 방법

  1. 매서드 명은 클래스명과 동일해야 한다.
  2. 매개변수는 선택적이다(오버로딩 가능하다)
  3. 리턴 값의 타입은 생략해야한다.
  4. 객체 생성시 수행햐여 하는 기능 또는 **객체 생성시 데이터를 전달받아서 초기화 하는 기능**
  5. ***모든 클래스에는 반드시 하나 이상의 생성자가 있어야 한다.*** 
  6. 생성자가 하나라도 있으면 기본 생성자를 추가하지 않는다.

- import다른 패키지에 있는 것을 가져오겠다.

  ```java
  import java.util.Date;
  //java.util이라는 패키지에 있는 Date라는 클래스를 가져옴
  ```




##### this

- 자신의 객체의 참조값을 의미하는 리터럴
  - this.xxx
- this() 
  - 생성자 메서드 내에서만 호출 가능
  - **생성자 매서드의 첫 행에서만 호출 가능** -> 상수랑 연관 되기 때문에
  - 동일한 클래스 내에 있는 다른 생성자를 호출

```java
class Book {
	String title;
	String author;
	int price;

	Book() {
		this("자바의 정석","남궁 성",30000);
	}
	Book(String title,String author,int price) {
		this.title = title;
		this.author = author;
		this.price = price;
	}
}
```



##### 클래스 배열

```java
Product[] prod = new Product[5];

for (int i;i<n;i++) {
    prod[i] = new Product(name[i],price[i]);
}
```

- argument가 없는 생성자는 가능하면 만들어 주는게 좋다. -> 상속이 있을때 만들기 편함.



#### 다른 패키지에 있는 매소드 가져오기



day6에 있는 getRandom 매소드 가져오기

방법 1

```java
import day6.Method3;

class DateTest {
    ...main() {
        int month = Method3.getRandom(12);
    }
}
```

방법2

```java
class DateTest {
    ...main() {
        int month = day6.Method3.getRandom(12);
    }
}
```



하지만 getRandom이 public이 안 붙어 있다면 에러가 난다.



### static(정적, 고정)

- 제어자

- 멤버 변수와 메서드 앞에 지정 가능하다. (클래스 앞에는 올 수 없다.)

- static을 설정한 멤버변수와 메서드는 객체 생성을 하지 않아도 자동으로 메모리 영역을 할당하거나 호출 가능한 상태가 된다.

- static 메서드 안에서는 non-static 변수나 매서드를 호출 불가능하다.

- 다른 클래스에서 또 다른 클래스의 static 타입의 멤버를 사용할 때는

  ```java
  class.member;
  ```

- 클래스에 정의 되는 멤머중 어떤 멤버에 static을 부여하는가?

  #### 클래스 변수

  - static이 붙은것. 클래스 로딩시에 **미리** 자동으로 메모리 할당함. 프로그램 끝날 때까지 유지됨
  - 즉, 각자 할당해줘야 하는 것엔 static붙혀주면 안됨. -> 이런건 인스턴스 변수로 ex) student

  #### 인스턴스 변수

- 직접 할당해 주는 것. **객체가 생성 될 때마다 할당됨**

| 변수의  종류 | 선언위치     | 생성시기                     |
| ------------ | ------------ | ---------------------------- |
| 클래스변수   | 클래스  영역 | 클래스가  메모리에 올라갈 때 |
| 인스턴스변수 | 클래스  영역 | 인스턴스  생성시             |
| 지역변수     | 메서드  영역 | 변수  선언문 수행시          |



ex ) 트럼프 카드

```java
class Card {
    String kind;	//무늬
    int number;		//숫자
    
    static int width = 100;		//폭
    static int height = 250;	//높이
}
```



Method Area

- 자바가 로딩될때 할당 되는 영역, static이 할당 되는 영역

Call stack

- 호출되는 매서드에 대한 정보가 기록되는 부분 ex)callback변수

- 지역 변수들이 할당 되는 부분 - 매서드가 할당할때 같이 할당되었다가 매서드가 죽을 때 같이 죽는다.

Heap 



Method Area에 할당된 static은 클래스이름으로 접근하는게 좋다.

static 로 접근하는게 좋다.

static 변수는 클래스 호출시 언제든지 사용할 수 있는 위치가 된다.

메인 메소드는 로딩만 하고 바로 호출하기 때문에 무조건 static



Tread.sleep()도 스태틱



#####  -verbose:class

- run configuration에 VM argument에 -verbose:class를 입력하면 프로그램 실행 정보를 알려준다.

- 로딩하는 것들을 전부 알려준다. - 즉 로딩하는 것들은 JVM에 필요한 클래스들.

- jvm옵션이다- 클래스 로딩 정보를 보여주면서 자바 프로그램을 수행시켜라

자바는 동적 로딩 - 예를 들면 다른 클래스를 호출하기 전에 프린트 하면 프린트가 되는 걸 알 수 있다,

즉, 프린트 한 다음에 card클래스가 필요하니 로딩을 하는것

```java
public static void main(String args[]) throws Exception{	
		System.out.println("CardTest 수행이 시작었습니다.");
		Thread.sleep(10000);
		Card c1 = new Card();
}
```

실행 로딩

```
[Loaded java.security.BasicPermissionCollection from C:\Program Files\Java\jdk1.8.0_231\jre\lib\rt.jar]
[Loaded day8.CardTest from file:/C:/alpha/eclipse-workspace/javaexam/bin/]
[Loaded sun.launcher.LauncherHelper$FXHelper from C:\Program Files\Java\jdk1.8.0_231\jre\lib\rt.jar]
[Loaded java.lang.Class$MethodArray from C:\Program Files\Java\jdk1.8.0_231\jre\lib\rt.jar]
[Loaded java.lang.Void from C:\Program Files\Java\jdk1.8.0_231\jre\lib\rt.jar]
CardTest 수행이 시작었습니다.
[Loaded day8.Card from file:/C:/alpha/eclipse-workspace/javaexam/bin/]
첫 번째 Card 객체가 생성됨
두 번째 Card 객체가 생성됨
```



#### 블록 스코프

```java
Method_header {
    int a;			// 선언된 위치 - 즉 현재 블록이 끝날때까지 유효 -> Method_header끝까지
    if(...) {
        int b;		// 이건 if문이 끝날 때까지 유효
    }
    int c;
    int b;			// if문 밖으로 나왔으니 새로 선언이 가능하다.
}
```



매서드는 객체 생성시 호출 가능한 상태가 됨



### JVM의 메모리 구조

- 메서드영역(Method Area)
  - 클래스 정보와 클래스변수가 저장되는 곳
- 호출스택(Call Stack)
  - 메서드의 작업공간. 메서드가 호출되면 메서드 수행에 필요한 메모리공간(지역변수, 메서드공간)을 할당받고 메서드가 종료되면 사용하던 메모리를 반환한다.
- 힙(Heap)
  - 인스턴스가 생성되는 공간. new연산자에 의해서 생성되는 배열과 객체는 모두 여기에 생성된다.



##### 호출 스택의 특징

- 메서드가 호출되면 수행에 필요한 메모리를 스택에 할당받는다
- 메서드가 수행을 마치면 사용했던 메모리를 반환한다.
- 호출스택의 제일 위에 있는 메서드가 현재 실행중인 메서드다.
- 아래에 있는 메서드가 바로 위의 메서드를 호출한 메서드다.



#### 클래스 매서드와 인스턴스 매서드

- 인스턴스 매서드

  - 인스턴스 생성 후, ‘참조변수.메서드이름()’으로 호출
  - 인스턴스변수나 인스턴스메서드와 관련된 작업을 하는 메서드
  - 메서드 내에서 인스턴스변수 사용가능

- 클래스메서드(static메서드)

  - 객체생성없이 ‘클래스이름.메서드이름()’으로 호출

  - 인스턴스변수나 인스턴스메서드와 관련없는 작업을 하는 메서드

  - 메서드 내에서 인스턴스변수 사용불가

  - 메서드 내에서 인스턴스변수를 사용하지 않는다면 static을 붙이는 것을 고려한다.

    -> 즉, 매서드 내에 하나라도 static 안 붙은거 사용하면 에러난다.

  ```java
  class Book {
  	String title;
  	String author;
  	int price;
  
  	public static String getTitle() {
  		return title;		//error난다. 왜냐하면 함수 객체 생성할때 생성되기 때문에
          					//그때는 인스턴스 매서드가 뭐가 생성될지 모르기 때문이다.
  	}
  }
  ```

메인 클래스에 static 이 붙어 있지 않은 놈을 콜하면 main은 static이 붙어 있기 때문에 main이 콜이 될때 그 함수도 콜 해야 되기 때문에 static을 같이 붙혀줘야 한다. 아니면 객체 생성해 줘야함.

### 표준 입력

- 프로그램이 수행하는 동안 필요로 하는 데이터를 시스템의 표준 입력 장치로부터 받아오는 것

- 표준 입력 장치 - 키보드
- java의 표준 입력 -> System.in
- System.in.read(); ->  한글 깨짐, 무조건 문자열만 받음.
- Java 5에 java.util.Scanner클래스를 제공하여 좀더 편하게 입력 할 수 있게됨

```java
Scanner scna = new Scanner(System.in);

scan.next();
scan.nextInt();
//공백, 탭 엔터를 분리 문자로 쓴다.

scan.nextLine();
//위와 섞어 쓰면 혼동이 생길 가능성이 있다.

// 예로 입력된 문자열을 이걸로 가정하면
//alpha
//beta
scan.next();
//next는 alpha까지만 읽는다. 
//alpha다음에 enter을 눌렀으므로 실제로 입력된 문자는 alpha\n가 입력 되었지만, alpha만 읽는다
scan.nextLine();
//그래서 이게 읽는게 beta가 아닌 \n를 읽는다.
scan.nextLine();
//위의 방식으로 개행 문자를 한번 처리 해 준 다음에 다시 한번 선언을 해서 beta를 읽는다.

//단, 하위 두 항목은 분리 문자가 아닌 다른걸 읽을 때까지 분리 문자를 무시한다
scan.next();
scan.nextInt();
//11                   222
//라고 입력 된다면 nextInt를 두번 선언하면 각각 11 222를 저장한다.

//분리 문자 "\t", "\n", " ",이 있다.
```



***

심각한 에러는 JVM에러 예)스택 오버 플로우

단순하고 해결 가능한 에러는 보통 exception 에러



class를 처음 선언했을 때 딱히 부모를 지정 안해주면

자동으로 java.lang.Object를 물려 받는다. 즉 멤버는 java.lang.Object




```java
class Parent {	//java.lang.Object
	
}

public class ParentChildTEst {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parent p =new Parent();
		System.out.println(p.toString());
		System.out.println(p);
		// 즉 자동적으로 참조된 주소, 를 풀력한다.
		// 객체에 대한 정보를 하나의 문자열로 출력하는 것
		Card c =new Card();
		System.out.println(c.toString());
		java.util.Date d = new java.util.Date();
		System.out.println(d.toString());
        // 이건 날짜가 나옴
        // 부모로 부터 상속 받은 toSting 을 오버라이딩 한 것이다.
	}

}
```

객체를 toSting하면 객체에 대한 정보를 출력한다.

객체는 자동적으로 object가 부모형



### 상속

- 자바의 모든 객체들은 상속이라는 객체 지향 언어의 특징을 지원한다.
- 자바에서 생상되는 모든 객체들은 기본적으로 java.lang.Object이라는 객체를 상속하게 된다.
- 상속의 가장 큰 장점 - ***다형성!!***
- 두 번째 장점 - 코드의 길이가 짧아진다. 공통된 부분은 부모 클래스로 빼버리고 다른 부분은 자식 클래스에 자식에만 설언하면 되기 때문이다.
- 클래스 헤더에 extends 라는 절을 사용하는 부모 클래스를 사용하는 부모 클래스를 설정하는데 **하나의 부모 클래스**에만 설정 가능하다. - 가장 비중이 큰 클래스를 부모로 설정하다.
- 조상으로부터 물려받은 매서들은 필요에 따라 대체할 수 있다 - ***매서드 오버라이딩***
  - 오버로딩 - 같은 함수지만 매개 변수가 다름
  - 오버라이딩 - 상속된 요소들을 상속한 클래스에서 새롭게 정의하는 것
- 어떤 클래스든 객체를 생성하면 해당 클래스만 메모리 할당하는 것이 아니라, 조상 클래스들도 메모리 할당한다. -> 즉, 조상 클래스가 많으면 메모리 많이 잡아 먹는다.
- 자손에서 super, this 등으로 사용가능 하지만, 그 것은 자손의 클래스 안에서만 사용가능하다.
- 자손에서 생성자를 사용하면 조상의 생성자도 자동으로 사용된다. - 이럴 때 부모에 매개변수를 보내고 싶을 때 사용하는게 super() - 즉, super가 없다면 자동으로 넣어서 만들어준다. 단,  super() 나 this() 가 선언 되어 있으면 안 넣어준다. 그리고 자동으로 생성되는 super()은 무조건 argument가 없는 생성자, super를 만들어준다.
- 자신의 매서드가 아닌 조상의 매서드를 사용하고 싶다면 super.매서드명 이런식으로 선언하면 된다. 단순시 매서드명을 선언한다면 재귀로 사용한다.
- this(),super() : 생성자 안에서만 호출 가능 - 둘 중 하나라도 선언 되 있으면 다른 하나 선언 안됨
- this, super :  객체 생성 시점에 초기화 된다. 즉, static 매서드에서는 사용 불가하다. 객체 생성 시점에서 초기화 된다는건 이미 static인 셈이니까. non-static 매서드와 생성자 매서드 내에서만 사용 가능하다. - main에선 사용 불가



### 제어자(modifier) : 수정자, 한정자, 제어자

- 접근 제어자
  - pulbic protected, (default), privat
- 활용 제어자
  - final, static, abstract, trasient, synchronized ...



제어자란 클래스, 매서드, 변수 앞에 설정되어 접근 가능 여부와 사용 방식을 제어하는 구문.

```java
[제어자] class 클래스명 extends 부모 클래스명{
	[제어자] 멤버변수 선언;
    [제어자] 생성자 매서드 정의;
    [제어자] 매서드 정의
}
```



```java
public,final,abstract class 클래스명 extends 부모 클래스명{
	모든접근제어자,final,static 멤버변수 선언;
    모든접근제어자 생성자 매서드 정의;
    모든접근제어자,static,final,abstract 매서드 정의
}
```

접근 제어자는 딱 하나만 사용 가능하지만, 활용제어자는 상반 되는거 아닌 이상 여러개 사용 가능

- 클래스에는 접근 제어자를 두가지만 설정 가능 : public, (default)
  - public 이 설정된 클래스 : 누구나
  - (default) 클래스 : 동일 패키지 내의 클래스만
- final
  - 변경할 수 없는, 마지막의
  - final class : 상속 불가
    - ex) Math,System : 변경되어서는 안되는 것들
- abstract
  - 반드시 변경해야만 하는, 마지막 아닌, 미완성의
  - abstract class : 객체 생성 불가, 미완성, 상속만을 위한 클래스

final과 abstract 둘 다 함께 선언 불가



- public
  - 누구나 접근 가능
- protected
  - 동일 패키지이거나 자손이면 접근 가능
- (default)
  - 동일 패키지

- private
  - 자손이든, 객체 생성한 클래스든 접근 불가
  - 멤버가 정의된 클래스 내에서만 사용 가능



non static 변수는 거의 무조건 private로 설정

 	-> ***캡슐화***



다이어그램 그릴 때 마크

\+ : public 

\# : protected

\(\) ,\~ : default

\- : private



- static,final과 함께 지정해서 상수를 만든다.

```java
public class Math {
    public final static double PI=3.141592;
    // 보통 final은 static도 같이 선언한다.
    // 이유는 객체화 하지 않아도 사용 할 수 있게 하기 위해서
}

Math.PI;
Integer.MAX_VALUE;
```



#### 매서드의 제어자

- final
  - 자손에 의해 오버 라이딩이 *불가능*한 매서드를 정의
- abstract
  - 자손에 의해 *반드시* 오버라이딩 해야 하는 메서드를 정의
  - 매서드의 헤더만 정의되고 바디가 없는 메서드



상속할때 조상에서 있었던 메서드를 물려 받을때는 더 좁은 범위로 불가능

ex) public -> private //에러난다

단 더 커지는건 가능

이유 : 다형성 때문에





# 상속

- 기존 클래스를 재사용해서 새로운 클래스를 작성하는 것
- 두 클래스를 조상과 자손으로 관계 맺어주는것
- 자손은 조상의 모든 멤버를 상속받는다.(생성자, 초기화블럭 제외)
- 자손의 멤버개수는 조상보다 적을 수 없다.(같거나 많다.)



### 상속 관계(inheritance)

- 공통부분은 조상에서 관리하고 개별부분은 자손에서 관리한다.
- 조상의 변경은 자손에 영향을 미치지만, 자손의 변경은 조상에 아무런 영향을 미치지 않는다.



### 포함관계(composite)

- 한 클래스의 멤버변수로 다른 클래스를 선언하는 것
- 작은 단위의 클래스를 먼저 만들고, 이 들을 조합해서 하나의 커다란 클래스를 만든다.



### 상속 vs. 포함

- 상속관계
  - ~은 ~이다
  - 원(Circle)은 도형(Shape)이다.(A Circle is a Shape.) : 상속관계
- 포함관계
  - ~은 ~을 가지고 있다.
  - 원(Circle)은 점(Point)를 가지고 있다.(A Circle has a Point.) : 포함관계



### Object클래스 

- **모든 클래스의 최고조상**
- 조상이 없는 클래스는 자동적으로 Object클래스를 상속받게 된다
- 상속계층도의 최상위에는 Object클래스가 위치한다.
- 모든 클래스는 Object클래스에 정의된 11개의 메서드를 상속받는다.



## 오버라이딩

조상클래스로부터 상속받은 메서드의 내용을 상속받는 클래스에 맞게 변경하는 것을 오버라이딩이라고 한다.



#### 오버라이딩의 조건

1. 선언부가 같아야 한다.(이름, 매개변수, 리턴타입)
2. 접근제어자를 좁은 범위로 변경할 수 없다.
   - 조상의 메서드가 protected라면, 범위가 같거나 넓은 protected나 public으로만 변경할 수 있다
3. 조상클래스의 메서드보다 많은 수의 예외를 선언할 수 없다



#### super - 참조 변수

- this
  - 인스턴스 자신을 가리키는 참조변수. 인스턴스의 주소가 저장되어있음
  - 모든 인스턴스 메서드에 지역변수로 숨겨진 채로 존재
- super
  - this와 같음. 조상의 멤버와 자신의 멤버를 구별하는 데 사용. 

***

숫자나 문자는 연산자로 값이 동일한지 비교 가능하지만

문자열은 등가 연산자로 비교할 수 있는 경우도 있지만 일반적으로 API를 사용해야한다.

equal() 매서드를 사용해야 한다.

String 클래스가 제공



자바는 문자열 리터럴은 String 객체로 취급된다.

'y'

- char 타입, 기본형

"y"

- string 타입, 객체형(참조형)



"y".eqauls("...");



***

### 제어자

### abstract

상속해서 부족한 부분을 채워 넣으라고 만든 것

- 클래스
  - 클래스 내에서 추상매서드가 선언되어 있음을 의미한다
- 매서드
  - 선언부만 작성하고 구현부는 작성하지 않은 추상 매서드임을 알린다. - 반드시 상속으로 작성해줘야 한다.
  - 블록으로 만들지 말고 만들고 나서 ;으로 완성해 줘야 한다.
  - 클래스가 abstract라고 각 매서드가 전부 abstract일 필요는 없다.

```java
abstract class abstractTest {
    abstract void move();
}
```

### 접근 제어자

접근 제어자가 사용 될 수 있는 곳 - 클래스, 멤버변수, 매서드, 생성자

#### private

**같은 클래스** 내에서만 접근이 가능하다.

#### default

**같은 패키지** 내에서만 접근이 가능하다.

#### private

같은 패지키 내에서, 다른 패키지의 자손 클래스에서 접근이 가능하다.

#### private

접근 제한이 전혀 없다



### 캡슐화

접근 제어자를 사용하는 이유

- 외부로부터 데이터를 보호하기 위해서
- 외부에는 불필요한, 내부적으로만 사용되는 부분을 감추기 위해서



### 다형성

abstract, interface 등등



부모가 매개변수라면 부모의 자식이라는 변수는 모두 전달 가능

그래서 object는 모두 전달 가능

```java
printObjectInfo(new Object());
printObjectInfo(new String("rksksek"));
printObjectInfo("ABC");
printObjectInfo(new java.util.Date());
printObjectInfo(new java.io.File("c:\\temp"));
printObjectInfo(new int[10]);
printObjectInfo(new double[5]);
printObjectInfo(new day7.Member());
printObjectInfo(new Integer(100));

static void printObjectInfo(Object o) {
		System.out.println("전달된 객체의 클래스 명 :"
                           + o.getClass().getName());
	}
```

참조형 변수(클래스 타입)는 타입에 지정된 클래스 객체 뿐만 아니라 타입에 지정된 클래스의 자손도 참조할 수 있다.

```java
A obj;
obj = new A();
obj = new B();
obj = new C();
```

만약 다형성이 없다면 각각의 매개변수를 받는 매서드를 여러 개 오버로딩 해야 한다.

```java
Object o = new Date();		
//object로부터 오버라이딩 된 것들만 접근 가능, 자손에서 추가된건 사용 불가
Date d = new Date();
//Date에서 새롭게 생성된 변수와 매서드도 접근 가능
Member c = new Date();
//에러가 난다.
```



```java
static void printObjectInfo(Object o) {
	if (o instanceof String) {
	System.out.println("문자열 객체 전달됨" 
                       + o.getClass().getName() 
                       + ((String)o).length());
	}
	System.out.println("전달된 객체의 클래스 명 :" 
                       + o.getClass().getName());
}
```

instanceof : 조상에서 자손형태로 온 매개변수의 타입을 구분한다.



#### 다형성이란?

- 여러 가지 형태를 가질 수 있는 능력

- 하나의 참조변수로 여러 타입의 객체를 참조할 수 있는 것.

  즉, 조상타입의 참조변수로 자손타입의 객체를 다룰 수 있는 것이 다형성.



- 자손타입 -> 조상타입(upcasting)
  - 형변환 생략 가능
- 자손타입 <- 조상 타입(downcasting)
  - 형변환 생략 가능

```java
Object alpha = new Integer();
Integer beta = ((Integer)(new Object()));
```



하지만

![그림1](.\그림1.png)

그렇게 매개변수를 보낼 수 있지만

만약 a,b,c 만 보내고 싶을때



<img src=".\그림2.png" alt="그림2" style="zoom:75%;" />

그런 식으로 A,b만 받아줄 a,b만을 매개변수로 받아주는 위 형태를 만드는 것을 

**리팩토링**이라고 한다.



#### 추상 클래스

- 클래스가 설계도라면 추상클래스는 ‘미완성 설계도’

- 선언부만 있고 구현부(몸통, body)가 없는 메서드

만약 자손 클래스의 매서드가 호출 된다면 자식에서 오버라이딩 된 매서드가 호출된다





### interface



- 클래스, final 클래스, absract클래스

- 인터페이스는 모든 매거드가 abstract인 매서드 클래스를 의미한다

- 자바는 단일 상속을 지원하는 OOP언어이다.

  - 모든 매서드가 abstsact인 클래스를 상속한 경우 다른 클래스는 상속 불가

    -> 인터페이스는 추가 상속이 가능한 특별한 클래스이다.

- 인터페이스는 abstact클래스와 비슷한 자바 프로그램의 구조로서 객체 생성은 불가하고 상속으로만 가능하다.

- 인터페이스 생성방법

```java
interface [interfaceName] {
    int num;
    char alpha;
        ...
        ...
    abstract method
}
```

- 인터페이스 사용 방법 : 상속

```java
interface [interfaceName] extends [prointerfaceName] {
    
}

class className extends [proClassName] implements [interfacaeName] {
    
}
```

인터페이스는 상속 제한이 없다. 여러 개를 상속해도 됨.



인터페이스도 변수 선언 가능

위의 리 팩토링에서 만약 B,C,E만을 보내고 싶은게 생겼을때 추가 상속도 불가능하고 S를 수정하자니 코드가 꼬인다. 이럴때 사용하는게 인터페이스



![그림3](.\그림3.png)

인터페이스는 상속 제한이 없어서 여러개 상속이 가능하다

인터페이스 상속은 점선으로 한다.



인터페이스에 선언되는건 abstact매서드

abstract 매서드며 자동으로 public이 붙는다

```java
interface Drawable {
	 void draw();
}
class Rect implements Drawable {
	void draw() {	// 에러가 뜬다. draw는 자동으로 public인데, 
        			// 상속은 부모보다 작은 범위는 불가능
		System.out.println("사각형을 그립니다.");
	}
}
```



자바 패키지의 종류

java.nnn

- 기본
- java.lang,java.util,java.io,java.net,java.sql .....

javax.nnn

- 확장
- javax.sql,java.nio...



위의 2개는 자아 언어에서만 지원하는 API

---

org.nnn

- 자바언어에서만 지원하지 않고, 다른 프로그래밍 언어에서도 지원되는 API로 어떤 표준화 위원회나 조직에서 정산 API를 자바에서도 사용하기 위해서 만든 것





 변경할 수 있다

조상클래스의 메서드보다 많은 수의 예외를 선언할 수 없다

super - 참조 변수
this

인스턴스 자신을 가리키는 참조변수. 인스턴스의 주소가 저장되어있음

모든 인스턴스 메서드에 지역변수로 숨겨진 채로 존재

super

this와 같음. 조상의 멤버와 자신의 멤버를 구별하는 데 사용. 

숫자나 문자는 연산자로 값이 동일한지 비교 가능하지만

문자열은 등가 연산자로 비교할 수 있는 경우도 있지만 일반적으로 API를 사용해야한다.

equal() 매서드를 사용해야 한다.

String 클래스가 제공

자바는 문자열 리터럴은 String 객체로 취급된다.

'y'

char 타입, 기본형

"y"

string 타입, 객체형(참조형)

"y".eqauls("...");

제어자
abstract
상속해서 부족한 부분을 채워 넣으라고 만든 것

클래스

클래스 내에서 추상매서드가 선언되어 있음을 의미한다

매서드

선언부만 작성하고 구현부는 작성하지 않은 추상 매서드임을 알린다. - 반드시 상속으로 작성해줘야 한다.

블록으로 만들지 말고 만들고 나서 ;으로 완성해 줘야 한다.

클래스가 abstract라고 각 매서드가 전부 abstract일 필요는 없다.

￼
abstract class abstractTest {
    abstract void move();
}
접근 제어자
접근 제어자가 사용 될 수 있는 곳 - 클래스, 멤버변수, 매서드, 생성자

private
같은 클래스 내에서만 접근이 가능하다.

default
같은 패키지 내에서만 접근이 가능하다.

private
같은 패지키 내에서, 다른 패키지의 자손 클래스에서 접근이 가능하다.

private
접근 제한이 전혀 없다

캡슐화
접근 제어자를 사용하는 이유

외부로부터 데이터를 보호하기 위해서

외부에는 불필요한, 내부적으로만 사용되는 부분을 감추기 위해서

다형성
abstract, interface 등등

부모가 매개변수라면 부모의 자식이라는 변수는 모두 전달 가능

그래서 object는 모두 전달 가능

￼
printObjectInfo(new Object());
printObjectInfo(new String("rksksek"));
printObjectInfo("ABC");
printObjectInfo(new java.util.Date());
printObjectInfo(new java.io.File("c:\\temp"));
printObjectInfo(new int[10]);
printObjectInfo(new double[5]);
printObjectInfo(new day7.Member());
printObjectInfo(new Integer(100));

static void printObjectInfo(Object o) {
        System.out.println("전달된 객체의 클래스 명 :"
                           + o.getClass().getName());
    }
참조형 변수(클래스 타입)는 타입에 지정된 클래스 객체 뿐만 아니라 타입에 지정된 클래스의 자손도 참조할 수 있다.

￼
A obj;
obj = new A();
obj = new B();
obj = new C();
만약 다형성이 없다면 각각의 매개변수를 받는 매서드를 여러 개 오버로딩 해야 한다.

￼
Object o = new Date();      
//object로부터 오버라이딩 된 것들만 접근 가능, 자손에서 추가된건 사용 불가
Date d = new Date();
//Date에서 새롭게 생성된 변수와 매서드도 접근 가능
Member c = new Date();
//에러가 난다.
￼
static void printObjectInfo(Object o) {
    if (o instanceof String) {
    System.out.println("문자열 객체 전달됨" 
                       + o.getClass().getName() 
                       + ((String)o).length());
    }
    System.out.println("전달된 객체의 클래스 명 :" 
                       + o.getClass().getName());
}
instanceof : 조상에서 자손형태로 온 매개변수의 타입을 구분한다.

다형성이란?
여러 가지 형태를 가질 수 있는 능력

하나의 참조변수로 여러 타입의 객체를 참조할 수 있는 것.

즉, 조상타입의 참조변수로 자손타입의 객체를 다룰 수 있는 것이 다형성.

자손타입 -> 조상타입(upcasting)

형변환 생략 가능

자손타입 <- 조상 타입(downcasting)

형변환 생략 가능

￼
Object alpha = new Integer();
Integer beta = ((Integer)(new Object()));
하지만

￼

그렇게 매개변수를 보낼 수 있지만

만약 a,b,c 만 보내고 싶을때

￼

그런 식으로 A,b만 받아줄 a,b만을 매개변수로 받아주는 위 형태를 만드는 것을 

리팩토링이라고 한다.

추상 클래스
클래스가 설계도라면 추상클래스는 ‘미완성 설계도’

선언부만 있고 구현부(몸통, body)가 없는 메서드

만약 자손 클래스의 매서드가 호출 된다면 자식에서 오버라이딩 된 매서드가 호출된다

interface
클래스, final 클래스, absract클래스

인터페이스는 모든 매거드가 abstract인 매서드 클래스를 의미한다

자바는 단일 상속을 지원하는 OOP언어이다.

모든 매서드가 abstsact인 클래스를 상속한 경우 다른 클래스는 상속 불가

-> 인터페이스는 추가 상속이 가능한 특별한 클래스이다.

인터페이스는 abstact클래스와 비슷한 자바 프로그램의 구조로서 객체 생성은 불가하고 상속으로만 가능하다.

인터페이스 생성방법

￼
interface [interfaceName] {
    int num;
    char alpha;
        ...
        ...
    abstract method
}
인터페이스 사용 방법 : 상속

￼
interface [interfaceName] extends [prointerfaceName] {
    
}

class className extends [proClassName] implements [interfacaeName] {
    
}
인터페이스는 상속 제한이 없다. 여러 개를 상속해도 됨.

인터페이스도 변수 선언 가능

위의 리 팩토링에서 만약 B,C,E만을 보내고 싶은게 생겼을때 추가 상속도 불가능하고 S를 수정하자니 코드가 꼬인다. 이럴때 사용하는게 인터페이스

￼

인터페이스는 상속 제한이 없어서 여러개 상속이 가능하다

인터페이스 상속은 점선으로 한다.

인터페이스에 선언되는건 abstact매서드

abstract 매서드며 자동으로 public이 붙는다

￼
interface Drawable {
     void draw();
}
class Rect implements Drawable {
    void draw() {   // 에러가 뜬다. draw는 자동으로 public인데, 
                    // 상속은 부모보다 작은 범위는 불가능
        System.out.println("사각형을 그립니다.");
    }
}
자바 패키지의 종류

java.nnn

기본

java.lang,java.util,java.io,java.net,java.sql .....

javax.nnn

확장

javax.sql,java.nio...

위의 2개는 자아 언어에서만 지원하는 API

org.nnn

자바언어에서만 지원하지 않고, 다른 프로그래밍 언어에서도 지원되는 API로 어떤 표준화 위원회나 조직에서 정산 API를 자바에서도 사용하기 위해서 만든 것





### 인터페이스

- class’대신 ‘interface’를 사용한다는 것 외에는 클래스 작성과 동일하다.
- 하지만, 구성요소(멤버)는 추상메서드와 상수만 가능하다.

``` java
interface anming{
    public static final 타입 상수이름 = 값;	
    // 무조건 public static final이 붙고 값도 초기화 해줘야 한다
    public abstract 매서드이름(매개변수);
    // 무조건 public abstract이여야 한다
}
```



### 오류처리

- 컴파일 오류 : 구문 오류,API 사용 오류
- 실행 오류 : 
  - 에러
    - JVM영역에서 발생하는 오류로서 치명적이라 JVM이 프로그래밍 실행을 중단시키고 callstack 정보를 화면에 출력한다.
    - 미리 예측하고 대비하는 코드 작성 불가
  - 예외
    - 자바 프로그래밍 영역에서 발생하는 실행 오류로 다소 가벼운 잘못된 상황
    - 런타임 예외
      - 발생 원인이 프로그램 코드 - 버그- 예외처리 선택
    - 일반 예외(Not-RuntimeException)
      - 발생 원인이 외부적인 요인이다. - 예외 처리 필수

- 예외 처리 방법

  1. 적극적인 예외처리

     ```java
     try {
         예외가 발생할 수도 있는 코드
     }catch (처리해야 하는 예외 클래스의 변수 선언) {
     
     }finally {
         예외 발생 여부와 관계없이 마지막에 수행을 보장하는 코드
     }
     ```

  2. 소극적인 예외 처리

     - 매서드 헤더에 **throws 처리 해야 하는 예외 클래스**절을 추가
     - 예외가 발생하면 호출하여 대신 처리하게 함

- 예외 발생

  - throw를 호출하여 발생시키고자 하는 예외 클래스의 객체

    ```java
    throw new IOException(["예외메시지"])
    ```

  - 예외를 발생시킬때는 throw, 예외가 발생했을때는 throws
  - 예외를 발생시키는 코드를 가지고 있는 매서는 헤더 throws 절을 사용해서 이 매서드는 호출시 예외가 발생 할 수도 있다는 것을 알려야 한다.



- final은 설령 return 하더라도 실행한다.

  ```java
  try {
  	}catch(ArithmeticException e) {
      	e.printStackTrace();
  		System.out.println("프로그램 아규먼트는 0 이 아닌 값을 전달하세요");
  		return;
  	}finally {
  		System.out.println("항상수행");
  }
  ```

  리턴 되더라도 final은 실행이된다.

  그리고 e.printStackTrace();는 비동시 실행이라서 e.printStackTrace();가 끝나기 전에 다른 코드가 실행 된다. 

  ```
  수행시작
  프로그램 아규먼트는 0 이 아닌 값을 전달하세요
  항상수행
  java.lang.ArithmeticException: / by zero
  	at day11.ExceptionTest1.main(ExceptionTest1.java:11)
  ```

  이런 형태가 된다.

Exception은 가장 부모 예외 클래스이다.



```java
package day11;
import java.util.Random;
class TestException extends Exception {
	TestException(String message){
		super(message);
	}
}
public class ExceptionTest3 {
	public static void main(String[] args)  {
		System.out.println("main()수행시작");
		a();
		System.out.println("main()수행종료");
	}
	static void a()  {
		System.out.println("a()수행시작");
		try {
			b();
		} catch (TestException e){	
			System.out.println("오류 발생 : "+e.getMessage());
		}
		System.out.println("a()수행종료");
	}
	static void b() throws TestException {
		System.out.println("b()수행시작");
		c();
		System.out.println("b()수행종료");
	}
	static void c() throws TestException {
		System.out.println("c()수행시작");
		boolean flag = new Random().nextBoolean();
		if(flag){
			throw new TestException("<<:::::테스트로 예외발생시킴:::::>>");
		}else {
			System.out.println("ㅋㅋㅋㅋ");
		}	
		System.out.println("c()수행종료");
	}	
}
```

c에서 예외가 발생, throws로 b에게 던지고 b는 자기를 호출한 a를 찾아가 catch 블록을 실행함

만약 a도 thorw같은 식으로 catch가 없으면 main 의 JVM한테 넘긴다



### API

java.lang - object,Math,Integer,String,StingBuffer,Character...

- String :
  - 문자열 처리와 관련된 매서드들을 가지고 있다.
  - Stirng 객체가 생성된 이후 초기화 된 문자열 내용은 변경 불가하다.
  - 읽기 용도로만 사용 가능하다.
  - ex) "abc" + "가나다" -> "abc가나다" 합쳐서 새로운 문자를 아예 새로 만든 다음에 저장하는것
  - "a"+"b"+"c"+"d" = 총 문자열 4개를 만든다음에 마지막만 저장하는것, 나머지는 grabege가됨
- StringBuffer : 
  - 문자열을 편집하는 용도 -> 훨씬 메모리를 덜쓴다. 쓸데없는 가비지를 만들지 않고 하나만 수정

  ```java
  StringBuffer sb= new StingBuffer();
sb.append("a");
sb.append("b");
sb.append("c");
sb.append("d");
  ```
  
  - equals() : object 클래스 : == 연산과 동일
  
    ```java
    Book b1 = new Book("짱구","xxx",10000);
    b1 == b2;	//? false
    b3==b2;		//? true
    b1.equals(b2); 	//?false
    b3.equals(b2);	//?true;
    ```



### API

- Application Programming Interface
- 자주 사용되는 기능을 미리 만들어 놓은 프로그램
- 자바 : 클래스, abstarct 클래스, 인터페이스..
- c : 함수





# Collection API

- 데이터들을 저장하여 사용하는 방의 역활을 하는 API
- 공통점
  - 저장할 수 있는 데이터 타입의 제한이 없다.
  - 저장할 수 있는 데이터의 갯수에 제한이 없다.



### List

- 저장되는 데이터의 순서를 유지한다.
- 저장되는 데이터의 중복을 허용한다.
- Ex) ArrayList, LinkedList, Vector

### Set

- 저장되는 데이터의 순서를 유지하지 않는다.
- 저장되는 데이터의 중복을 허용하지 않는다. -> 중복데이터를 자동적으로 체크한다.
- 해쉬코드도 설정해서 그 객체만의 코드를 설정한다.
  - -> 해쉬코드를 오버라이딩 해줘야 한다. 오버라이딩 안해주면 object의 해쉬코드 사용(주소값)
- Ex) HashSet,LinkedHashSet

### Map

- 데이터 이름과 데이터 값을 쌍으로 저장한다.
- 데이터 이름은 중복 저장이 불가능하다.
  - 데이터 이름(Key)라고 부름
  - Key-value 쌍으로 데이터 저장(put)
- Ex) HashMap, HashTable

### Queue

- FIFO(Firtst in First out)



### Statck

- FILO(First in Last out)

### Hash

- 검색이 빠르다. 찾기가 쉽다.
- 데이터 분류의 방법



### Generics

- 객체 생성시 이 객체가 처리할 데이터 타입 정보를 알려주는 용도

  - ArrayList\<String> list = new ArrayLsit\<String>();
  - 제너릭스를 명시해 주지 않으면 자동으로 Object가 된다.

- 장점 

  - 견고한 프로그램을 만들 수 있다. 더 에러가 안나는 프로그램을 만들 수 있다.
  - 타입 파라미터를 정할 수 있다
  - 타입 파라미터를 정해 놓으면 넣을때도 자동 현변환이 되지만 꺼낼때도 자동적으로 형변환이 된다. -> 형변환에서 자유롭다

  ```java
  LinkedList<String> list = new LinkedList<String>();
  // <String> 이 타입 파라미터
  LinkedList<String> list = new LinkedList<>();
  //생성자 부분은 생략 가능
  ```
  
  ```java
  class Value2 {
  	Object obj;
  	void put(Object obj){
  		this.obj = obj;
  	}
  	Object get() {
  		return obj;
  	}
  }
  // Object 이므로 다양한 객체(String, Date 등등) 다 들어 갈 수 있지만, 꺼낼때는 형변환 해줘야함. 돌아갈때도 원래 들어갔단 데이터긴 하지만, 그래도 형변환시켜줘야 한다.
  
  class Value3<TT> {
  	TT obj;
  	void put(TT obj){
  		this.obj = obj;
  	}
  	TT get() {
  		return obj;
  	}
  }
  // <> 안에 뭐가 들어가던 상관은 없다. <TT>건 <E> 건 <A>건 상관이 없다. 보통 E인 이유인 Element라고 명시해 주기 위해
  ```



### ArrayList

```java
ArrayList<String> list = new ArrayList<String>();
//처음에 10개 정도 만들고 데이터 넣을 수록 추가 된다.
list.add("apple");
list.add("banana");
//앞에서부터 비어있는 곳에 차례대로 순서대로 넣는다.
list.get(1);
//위치 1에 있는 것에 삽입
list.size();
//list의 데이터의 수를 반환
list.add(1,"kiwi");
//1의 위치에 kiwi를 삽입한다. 원래 있던 것들은 뒤로 밀린다. 
list.set(0,"orange");
//위치 0에 있는 것을 orange로 바뀐다.
list.remove(1);
//인덱스 1에 있는 데이터를 삭제. 그 뒤에 있는 데이터를 당긴다.
list.remove("banana");
//리스트에서 가장 처음에 만난 banana를 삭제한다.
list.indexOf("kiwi");
//처음으로 만난 데이터의 위치를 반환한다. 없다면 -1이 리턴
//반복 한다면 그 다음?
list.LastIndexOf("kiwi");
//데이터를 뒤에서부터 검색하는 방법
```

### LinkedList

```java
LinkedList<String> list = new LinkedList<String>();

list.add("grape");
list.add("strawberry");
list.add("pitch");

list.get(2);
//인덱스 2에 있는 pitch를 리턴
//처음부터 접근하는거라 시간이 오래 걸린다.

list.remove(1);
//인덱스 1위치에 있는 데이터 삭제

//데이터 순차 접근을 효율적으로 하는 방법
//iterator 메소드를 호출합니다

Iterator<String> iterator = list.iterator();
String str = Iterator.next();
//만약 더이상 데이터가 없으면 NoSuchElementException 발생
```

### Stack

```java
LinkedLsit<Integer> stack = new LinkedLsit<Integer>();
stack.addLast(new Integer(12));
stack.addLast(new Integer(59));
stack.addLast(new Integer(7));

while(!stack.isEmpty()) {
    Integer num = stack.getLast();
    //데이터를 제거 하지 않고 꺼냄
    Integer num = stack.removeLsat();
    //데이터를 제거하고 꺼냄(pop)
    System.out.println(num);
}
```



### Queue

```java
LinkedLsit<Integer> queue = new LinkedLsit<Integer>();

queue.offer("rabbit");
queue.offer("deer");
queue.offer("tiger");
// 데이터를 추가 push와 같다.

queue.poll();
//데이터를 제거하고 꺼냄 - 토끼 나옴

queue.peek();
//데이터를 제거하지 않고 나옴
```



### HashSet

- 자료의 넣은 순서를 보존하지 않는다.
- 단, 데이터 중복이 안되는 것을 보장해 준다.
  - 만약, 들어간다면 true, 중복이라서 못 들어 간다면 false를 리턴해 준다.
- 데이터를 읽어오는 것을 구현 안되있어서 처음부터 읽어야 된다.

```java
System.out.println(set.add("자바"));			//true
System.out.println(set.add("카푸치노"));		//true
System.out.println(set.add("자바"));			//false
System.out.println("저장된 데이터의 수 = " + set.size());
Iterator<String> iterator = set.iterator();
while (iterator.hasNext()) {
	String str = iterator.next();
	System.out.println(str);
}
```

- 순서도 보존하고 싶으면 LinkedHashSet을 사용하자.



### HashTable

- 여러 개의 통(bucket)을 만들어두고 키 값을 이용하여 데이터를 넣을 통 번호를 계산하는 자료구조
  - 몇개의 버킷으로 나눌지가 중요. 세세하게?
- 정해진 룰로 나누는 것
  - 이 룰을 해쉬 알고리즘이라고 한다.

```java
HashMap<String,Integer> hashtable = new HashMap<String,Integer>();
//<키의 타입, 데이터 타입>
//기본은 16개의 버켓을 만든다.
HashMap<String,Integer> hashtable = new HashMap<String,Integer>(100);
//100개의 버킷을 만든다.
hashtable.put("Harry",new Integer(95));
hashtable.get("Harry");
hashtable.remove("Harry");
```

- 키 값이 중복 되어서는 안된다. 중복 되면 새로운 얘로 replace해버린다.



#### iterator

- 콜렉션 셋이 뭐가 됬던 하나하나 꺼내는 것을 규격화 시킨 매서드
- Hash건 LinkedList건 잘 꺼내온다.

```java
Iterator iter = list.iterator();
	while(iter.hasNext()){			//hasnext() 다음 데이터가 있는지 확인하는 매서드
		Object value = iter.next();
		String s = (String)value;		
		System.out.println(s);
}
```





### HashMap

- 똑같은 해쉬 키를 사용해 만든 것은 캐쉬 코드가 똑같다.

```java
String s1 = new String("듀크");
String s2 = new String("듀크");
String s5 = "듀크";
System.out.println(s1.hashCode());
System.out.println(s2.hashCode());
System.out.println(s5.hashCode());
//전부 똑같은 값이 출력된다.
```

- 단 오브젝트는 내용이 같더라도, 참조값을 기준으로 판단하기때문에 다른 값 리턴

```java
Nameobj1=newName("헤르미온느","그레인져");
Nameobj2=newName("헤르미온느","그레인져");
inthash1=obj1.hashCode();
inthash2=obj2.hashCode();
// 둘이 다른 값 리턴
```

