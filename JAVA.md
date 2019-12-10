# JAVA



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

    switch(식) {
      case 비교값1 : 수행문장1;
                          수행문장2;
                          break;
      case 비교값2 : 수행문장3;
                          수행문장4;
                          break;
      case 비교값3 : 수행문장5;
                          수행문장6;
      case 비교값4 : 수행문장7;
                          수행문장8;
    	break;
      default : 수행문장9;                    
   }

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

