# Modular Arithmetic

## 모듈러 연산(모드 연산)

- int 형 정수 x와 n이 있을 때, x mod n 은 x%n의 나머지이다.

#### 예시

- 7 mod 6 =1
- 33 mod 5 =3
- 33 mod 6 = 3
- 51 mod 17 = 0
- 17 mod 6 = 5

### 특징

- 7 mod 6 =1 
- 7 = 13 = 1 mod 6
- ((a mod n) + (b mod n)) mod n = (a+b) mod n
- ((a mod n)(b mod n))mod n = ab mod n

## 모듈러 덧셈
- ((a mod n) + (b mod n)) mod n = (a+b) mod n
#### 예시
- 3 + 5 = 2 mod 6
- 2 + 4 = 0 mod 6
- 3 + 3 = 0 mod 6
- (7 + 12) mod 6 = 19 mod 6 = 1 mod 6
- (7 + 12) mod 6 = (1 + 0) mod 6 = 1 mod 6

## 모듈러 곱셉
- (A * B) mod C = (A mod C * B mod C) mod C
#### 예시
- l 3 × 4 = 0 (mod 6)
- 2 × 4 = 2 (mod 6)
- 5 × 5 = 1 (mod 6)
- (7 × 4) mod 6 = 28 mod 6 = 4 mod 6
- (7 × 4) mod 6 = (1 × 4) mod 6 = 4 mod 6

## 음수의 모듈러 연산

- X mod n = m일때 X가 음수일때
  - -X + m = n이 성립한다 

#### 예시

- -2 mod 4 = 6
- -1 mod 6 = 7



## 모듈러 역원
- 역원 : N의 값에 곱했을 때 항등원[^항등원][^예시]이 나오는 값.
- mod 연산의 항등원 N의 서로소 M
- A (mod C)의 역원은 A<sup>-1</sup> 이다.
  - A * A<sup>-1</sup> = 1 (mod C)이기 때문이다.

- **A mod B에서 A,B가 서로소일 때 Modular Inverse가 존재한다.**



#### 예시

- x mod n의 곱셈 역수 x<sup>-1</sup>로 표시되는 숫자.
  - 3<sup>-1</sup> mod 7 = 5 = 3*5 = 1 mod 7





-----

[^항등원]: 임의의 수 a를 연산했을때 처음의 수 a가 되도록 만들어 주는 수를 말한다.
[^예시]: 덧셈과 뺄셈의 항등원 0, 곱셈과 나눗셈의 항등원 1, 예) A + 0 = A : 항등원 0, A * 1 = A