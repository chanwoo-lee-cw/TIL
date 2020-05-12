-- 계정 생성
create user jdbctest identified by jdbctest;
grant connect, resource to jdbctest;

-- 테이블 생성
start c:/unico/data.sql

select * from emp;

-- REGEXP_REPLACE : REPLACE를 하는데 정규 표현식을 쓸 수 있다.
-- [0-3]들은 *로 바뀐다.
SELECT ename, REGEXP_REPLACE(sal, '[0-3]', '*') as SALARY
FROM emp; 

-- 랭킹 구하는 함수. rownum과 다른 점은 같은 점수가 있으면 같은 등수가 나온다.
-- 1,2,2,4 등 이런식으로 나온다.
SELECT ename, job, sal, RANK() over (ORDER BY sal DESC) 순위
FROM emp;

-- 'ANALYST','MANAGER'의 순위만 메긴다.
SELECT ename, job, sal, RANK() over (ORDER BY sal DESC) 순위
FROM emp 
WHERE job in ('ANALYST','MANAGER');

-- 랭킹을 메기는데 PARTITION BY -> 잡 별로 파티션을 나눈다음에 그 파티션 별로 내림차순 정렬을 한 다음에 처리
SELECT ename, sal, job, RANK() over (PARTITION BY job
                                     ORDER BY sal DESC) as 순위
FROM emp;

-- DENSE_RANK() : 중복 순위만큼 건너 뛰지 않는다.
-- 1,1,2,3 등 이런 식으로 나뉜다.
SELECT job, ename, sal, DENSE_RANK() OVER (PARTITION BY job
                                           ORDER BY sal DESC) 순위
FROM emp
WHERE hiredate BETWEEN to_date('1981/01/01','RRRR/MM/DD') 
                       AND to_date('1981/12/31','RRRR/MM/DD');


SELECT ename, job, sal, RANK() over (ORDER BY sal DESC) AS RANK,
                        DENSE_RANK() over (ORDER BY sal DESC) AS DENSE_RANK
FROM emp 
WHERE job in ('ANALYST','MANAGER');


-- DENSE_RANK(***) 월급을 내림차 순으로 정렬했을때, sal 값이 ***인 대상의 순위를 출력한다.
SELECT DENSE_RANK(2975) within group (ORDER BY sal DESC) 순위
FROM emp;


SELECT DENSE_RANK('81/11/17') within group (ORDER BY hiredate ASC) 순위
FROM emp;


-- 날짜 데이터로 부터 원하는 데이터 뽑아낸다.
SELECT ename as 이름, EXTRACT(year from hiredate) as 년도, 
                              EXTRACT(MONTH from hiredate) as 달,
                              EXTRACT(day from hiredate) as 요일
FROM emp;


-- 랭크를 메기는데 각각 방법 보여주고, CUME_DIST() 누적 분포함수를 보여준다.
SELECT ename, sal, RANK() over (order by sal desc) as RANK ,
                   DENSE_RANK() over (order by sal desc) as DENSE_RANK,
                   CUME_DIST()  over (order by sal desc) as CUM_DIST
FROM emp;

SELECT job, ename, sal, RANK() over ( partition by job
                                      order by sal desc) as RANK ,
                            CUME_DIST() over ( partition by job
                                      order by sal desc) as CUM_DIST
FROM emp;

-- deptno으로 그룹을 나눈 다음에 ename으로 sorting 했다. 그리고 LISTAGG를 통해 부서별로 그룹하여 각각의 직업 이름들을 묶어라
SELECT deptno, LISTAGG(ename,',') within group (order by ename) as EMPLOYEE
FROM emp
GROUP BY deptno;


SELECT job, LISTAGG(ename,',') within group (ORDER BY ename desc) as employee
FROM emp
GROUP BY job;

-- ename을 sal과 함께 결합한다.
-- Jon(1700)
SELECT job, 
       LISTAGG(ename||'('||sal||')',',') within group (ORDER BY ename asc) as employee
FROM emp
GROUP BY job;


-- LAG(sal,1)자신보다 월급순서가 딱 1이 많은 사람의 월급
-- LEAD(sal,1) 자신보다 월급 순서가 딱 1적은 사람의 월급
-- 저 1 값이 offset, offset을 바꾸면 그 값만큼 바뀌게 된다.
-- 아니라면 NULL이 출력된다.
SELECT empno, ename, sal,  
         LAG(sal,1) over (order by sal asc) "전 행의 sal값",
         LEAD(sal,1) over (order by sal asc) "다음 행의 sal값"
FROM emp;

SELECT empno, ename, hiredate,
          LAG(hiredate,1) over (order by hiredate asc) "전 행의 sal값",
          LEAD(hiredate,1) over (order by hiredate asc) "다음 행의 sal값"
FROM emp;


SELECT deptno, empno, ename, hiredate,
          LAG(hiredate,1) over ( partition by deptno
                                       order by hiredate asc) "전 행의 sal값",
          LEAD(hiredate,1) over ( partition by deptno
                                        order by hiredate asc) "다음 행의 sal값"
FROM emp;

SELECT SUM(DECODE(deptno, 10, sal)) as "10",
       SUM(DECODE(deptno, 20, sal)) as "20",
       SUM(DECODE(deptno, 30, sal)) as "30"
FROM emp;


SELECT SUM(DECODE(job,'ANALYST',sal)) as "ANALYST",
          SUM(DECODE(job,'CLERK',sal))  as "CLERK",
          SUM(DECODE(job,'MANAGER',sal)) as "MANAGER",
          SUM(DECODE(job,'SALESMAN',sal)) as "SALESMAN"
FROM emp;



SELECT job, sum(sal)
FROM emp
GROUP BY ROLLUP(job);

SELECT deptno, job, sum(sal)
FROM emp
GROUP BY ROLLUP(deptno, job);

SELECT job, sum(sal)
FROM emp
GROUP BY CUBE(job);

SELECT deptno, job, sum(sal)
FROM emp
GROUP BY CUBE(deptno,job);

SELECT deptno, job, sum(sal)
FROM emp
GROUP BY GROUPING SETS(deptno, job);

SELECT deptno, job, sum(sal)
FROM emp
GROUP BY GROUPING SETS(deptno, job, () );

SELECT deptno, job, sum(sal)
FROM emp
GROUP BY GROUPING SETS(deptno, job, (deptno, job), () );

SELECT empno, ename, sal, RANK() OVER (ORDER BY sal DESC) RANK,
                                     DENSE_RANK() OVER (ORDER BY sal DESC) DENSE_RANK,
                                     ROW_NUMBER() OVER (ORDER BY sal DESC) 번호
FROM emp;


SELECT empno, ename, sal, ROW_NUMBER() OVER (ORDER BY sal DESC) 번호
FROM emp
WHERE deptno = 20;

SELECT deptno, ename, sal, ROW_NUMBER() OVER( PARTITION BY deptno
                                                                   ORDER BY sal DESC) 번호
FROM emp
WHERE deptno in (10,20);

SELECT ROWNUM, empno, ename, job, sal
FROM emp
WHERE ROWNUM <= 5;

SELECT ROWNUM, empno, ename, job, sal
FROM emp
WHERE ROWNUM <= 5
ORDER BY sal;


WITH salemp AS(SELECT *
            FROM emp
           ORDER BY sal)
SELECT empno, ename, job, sal
  FROM salemp
WHERE ROWNUM <= 5;


SELECT ROWNUM, empno, ename, job, sal
FROM emp
WHERE ROWNUM >= 3 AND ROWNUM <= 5
ORDER BY sal;


WITH salemp AS(SELECT rownum sortno, empno, ename, job, mgr, hiredate, sal, comm, deptno
            FROM (SELECT *
                      FROM emp
                      ORDER BY sal))
SELECT empno, ename, job, sal
  FROM salemp
WHERE sortno >= 3 AND sortno <= 5;


SELECT empno, ename, job, sal
  FROM (SELECT rownum sortno, empno, ename, job, mgr, hiredate, sal, comm, deptno
            FROM (SELECT *
                      FROM emp
                      ORDER BY sal))
WHERE sortno >= 3 AND sortno <= 5




SELECT rpad(' ', level*3)  || ename  as  employee , level, sal, job
  FROM emp
  START WITH ename='KING'
  CONNECT BY prior empno = mgr;


SELECT rpad(' ', level*3)  || ename  as  employee , level, sal, job
  FROM emp
  START WITH ename='KING'
  CONNECT BY prior empno = mgr AND ename != 'BLAKE';



SELECT rpad(' ', level*3)  || ename  as  employee , level, sal, job
  FROM emp
  START WITH  ename='JONES'
  CONNECT BY prior empno = mgr ;
  

SELECT ename, SYS_CONNECT_BY_PATH(ename,'/')  as  path 
  FROM emp
  START WITH ename='KING'
  CONNECT BY prior empno = mgr;

SELECT ename, LTRIM(SYS_CONNECT_BY_PATH(ename,'/'), '/')  as  path 
  FROM emp
  START WITH ename='KING'
  CONNECT BY prior empno = mgr;




