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

## 모듈러 역원
- 역원 : N의 값에 곱했을 때 항등원이 나오는 값.