# SQL

1. Cmd에서 sqlplus
2. 계정과 암호를 치고 들어간다.
3. select user from dual; -- 유저 명령을 보여줌
4. 학습용 일반 계정 : scott(생성), hr(락해제)
   1. 책의 실습은 스콧으로
   2. 그냥 실습은 hr로
5. 스콧 잠금 해제

```sql
SQL> @C:\oraclexe\app\oracle\product\11.2.0\server\rdbms\admin\scott.sql
SQL> alter user scott identified by tiger;
SQL> select user from dual;	-- 지금 사용자
SQL> select * from tab;		--scott계정이 보유하고 있는 테이블 리스트
SQL> connect system/manager	-- DB접속중에 계정을 바꾸는 명령어 계정/암호
```

6. hr계정 락 풀기

```SQL
SQL> alter user hr account unlock;
SQL> alter user hr identified by hr;
SQL> connect hr/hr;
```

7. Jdbctest 계정 만들기 : JDBC 수업시간에 사용

```SQL
SQL> create user jdbctest identified by jdbctest;
-- jdbctest라는 아이디를 암호 jdbctest로 만든다
SQL> grant connect, resource to jdbctest;
-- jdbctest 라는 아이디에 
```

-  **GRANT** 권한 종류1, 권한 종류2 **TO** 권한을 줄 사용자  

  - CONNECT는 접속 권한
  - RESOURCE는 객체(생성, 수정, 삭제), 데이터(입력, 수정, 조회, 삭제) 권한 
  - RESOURCE로 권한을 주지 않고 테이블 생성 데이터 입력만 가능하도록 일부 권한만 줄 수도 있다.




CRUD

Create : Insert 명령어

Read : Select 명령어(DQL)

Update : Update 명령어

Delete : Delete 명령어



## DQL

### Select 명령어

```SQL
Select 추출하려는칼럼명리스트 (or *)
from 테이블 이름
[Where 행의조건식]
[order by 칼럼명(별칭,식) desc|asc]

select * from emp;
select *;
select ename,sql from emp;
select Sysdate from dual;
select user from dual;
select 100+200 from dual;
--dual이 있는 이유 오라클은 from 을 생략불가능하기 때문에, 뭐라도 리턴 받고 싶으면 넣어줘야됨.
```



- 칼럼 명에 별칭 붙히기

```sql
select sal*12 AS ANNSAL from emp;
select sal*12 AS "ANNSAL" from emp;
select sal*12 ANNSAL from emp;
select sal*12 "ANNSAL" from emp;
```

ANNSAL이라는 별칭을 붙히고 싶을 때 사용,

큰 따옴표는 특수문자, 소문자 등등을 쓰고 싶을때 써야함.

보통 AS는 붙혀주자 구분하기가 편함.



- 정렬 순서 정하기

```sql
Select 추출하려는칼럼명리스트 (or *)
from 테이블 이름
ORDER BY DESC/ASC;

Select 추출하려는칼럼명리스트 (or *)
from 테이블 이름
ORDER BY SAL DESC, ENAME ASC;
```

- ASC 오름차순(기본 값)
- DESC 내림차순
- 항목 별로 정해주는 것도 가능



```sql
select *
from EMP
Where DEPTO = 30;

select *
from EMP
Where DEPTO = 30 
	AND JOB = 'CLERK';

select *
from EMP
Where DEPTO = 30 
	OR JOB = 'CLERK';
```

부서 번호가 30인 사람들만 출력

AND, OR로 조건을 추가 할 수도 있다.

- AND보다 OR이 우선순위가 높다.



#### 연산자

- +(더하기),-(빼기),*(곱하기),/(나누기) 가 있다. mod 연산은 없다.
- <,<=,>,>=,!=,= 같은 연산자도 있다.
- 비등가연산자 !=,<>,^= 전부다 같지 않다 라는 의미
- 날짜도 대소연산자로 비교 가능 -> 타임 스탬프로 저장되기 때문이다.
- NOT : 논리 부정 연산자
- IN연산자

```sql
Select *
From EMP
WhERE JOP = 'MANAGER'
	OR JOB = 'SALESMA';
	
Select *
From EMP
WhERE JOP IN ('MANAGER','SALESMA');
```

둘이 같은 의미다.



- NOT IN 연산자

```sql
Select *
From EMP
WhERE JOP NOT IN ('MANAGER','SALESMA');
```

- BEETWEEN 연산자

```sql
Select *
From EMP
WhERE SAL >= 2000
	OR SAL <= 3000;
	
Select *
From EMP
WhERE SAL BWTWEEN 2000 AND 3000;
```

둘 다 같은 의미다.

2000과 3000도 포함
꼭 반드시 앞이 최소값이여야 한다.



- LIKE 연산자

```sql
Select *
From EMP
WhERE name like '김%'
	AND name like 'KIM%'
	AND name like 'kim%'
	AND addr LIKE '%강남구%'	-- 양쪽
	AND addr LIKE '%강남구'	-- 강남구로 끝나야함
	AND addr LIKE '강남구%'	-- 강남구로 시작해야함
	AND addr LIKE '김__'		-- 임의로 시작하는 문자 한개
```

%는 0개 이상의 모든 데이터 - meta character

즉 김으로 시작되는 모든 이름

이것도 NOT이 됨

```sql
Select *
From EMP
WHERE name NOT like '%AM%'
```

와일드 카드가 데이터 일부일 경우로 같이 검색하고 싶을때

```sql
Select *
From EMP
WhERE name like '김\%' ESCAPE '\';
--\를 탈출 시킨다. 이것을 와일드 카드를 지우기 위한 기능
```





데이터 베이스에서 NULL은 아직 값이 정해지지 않은 것

0이라도 들어가 있으면 IS NULL로 출력되지 않는다.

NULL과 연산하면 모든 것이 NULL이나 FALSE가 나온다.

```sql
NULL + 100	--NULL
NULL > 100	--FALSE
```



문자열 붙히기

```sql
select concat(last_name,concat(',',job_id)) AS "Employee and Title"
FROM Employees;

select last_name||','||job_id AS "Employee and Title"
FROM Employees;
```



### DATE

date는 날자 포멧

rr/mm/dd	- 기본 설정 날짜 포멧

yy/mm/dd



### 함수

##### 단일행 함수

- 입력 행이 N개이면 출력 행도 N개가 나온다.

1. 문자 함수

   | 함수            | 설명                                                         |
   | --------------- | ------------------------------------------------------------ |
   | UPPER(문자열)   | 문자열 괄호안 데이터를 모두 대문자로 변환                    |
   | LOWER(문자열)   | 문자열 괄호안 데이터를 모두 소문자로 변환                    |
   | INITCAP(문자열) | 괄호 안 문자데이터 중 첫 글자는 대문자로, 나머지 문자를 소문자로 변환 후 반환 |

   ```sql
   --upper함수로 문자열 비교하기(사원 이름에 scott 단어를 포함한 데이터 찾기)
   Select *
   from EMP
   where upper(ENAME) LIKE UPPER('%scott%');
   -- 전부 내문자나 소문자로 변환 시켜서 찾는다.
   ```

2. Length 함수

   - 문자열 길이를 구한다.

3. SBUSTER 함수

   | 함수                                      | 설명                                                         |
   | ----------------------------------------- | ------------------------------------------------------------ |
   | SUBSTR(문자열데이터, 시작위치, 추출 길이) | 문자열 데이터의 시작 위치로부터 추출 길이만큼 추출. 시작 위치가 음수인 경우에는 마지막 위치에서 부터 거슬러간 위치에서 시작 |
   | SUBSTR(문자열데이터, 시작위치)            | 문자열 데이터의 시작 위치로부터 끝까지 추출. 시작 위치가 음수인 경우에는 마지막 위치에서 부터 거슬러간 위치에서 시작 |

4. INSTR 함수

   ```sql
   INSTR([대상 문자열 데이터(필수)],
         [위치를 찾으려는 부분문자(필수)],
         [위치 찾기를 시작할 대상 문자열 데이터 위치(선택, 기본값은 1)],
         [시작위치에서 찾으려는 문자가 몇번째인지 지정(선택, 기본은 1)])
   ```

5. REPLACE 함수

   ```SQL
   REPLACE([문자열 데이터 또는 열이름(필수)], [찾는 문자(필수)],[대체할문자(선택)])
   ```

   선택하지 않는다면 그냥 문자가 삭제

6. LPAD ,RPAD

   ```sql
   LPAD([문자열데이터 또는 열 이름(필수)])
   ```

   예)



1. 다중행 함수
   - 그룹함수라고도 한다
   - 입력행이 N개라도 출력은 1개

