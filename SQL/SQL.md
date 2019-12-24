# SQL

1. Cmd에서 sqlplus
2. 계정과 암호를 치고 들어간다.
3. select user from dual; // 유저 명령을 보여줌
4. 학습용 일반 계정 : scott(생성), hr(락해제)
   1. 책의 실습은 스콧으로
   2. 그냥 실습은 hr로
5. 스콧 잠금 해제

```sql
SQL> @C:\oraclexe\app\oracle\product\11.2.0\server\rdbms\admin\scott.sql
SQL> alter user scott identified by tiger;
SQL> select user from dual;	// 지금 사용자
SQL> select * from tab;		//scott계정이 보유하고 있는 테이블 리스트
SQL> connect system/manager// DB접속중에 계정을 바꾸는 명령어 계정/암호
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
// jdbctest라는 아이디를 암호 jdbctest로 만든다
SQL> grant connect, resource to jdbctest;
// jdbctest 라는 아이디에 
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
//dual이 있는 이유 오라클은 from 을 생략불가능하기 때문에, 뭐라도 리턴 받고 싶으면 넣어줘야됨.
```

