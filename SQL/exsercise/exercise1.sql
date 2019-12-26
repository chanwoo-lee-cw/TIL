--1. 모든 테이블의 리스트를 출력하시오.
select * 
from tab;

--2. emp 테이블과 dept 테이블의 구조를 출력하시오.
desc emp;
desc dept;

--3. 사원(emp)테이블에서 모든 데이터를 출력하시오.
select * 
from emp;

--4. 사원테이블에서 사원번호, 사원이름, 월급을 출력하시오.
select empno,ename,sal 
from emp;

--5. 사원테이블에서 월급열만 뽑는데 중복된 데이터가 없게 출력하시오.
select DISTINCT sal
from emp;

--6. 사원테이블에서 사원이름과 월급을 출력하는데 각각의 컬럼명을
-- "이 름","월 급"으로 바꿔서 출력하시오. 단, ALIAS에 공백 추가
select ename AS "이 름",sal AS "월 급"
from emp;

--7. 사원테이블에서 사원이름, 월급을 뽑고, 월급과 커미션을  더한 값을
-- 출력하는데 컬럼명을 '실급여'이라고 해서 출력하시오.
-- 단, NULL값은 나타나지 않게 하시오.(커미션 안받는사람제외)
select sal + comm AS "실급여"
from emp
where sal + comm is NOT null;

--8. 사원테이블에서 'SCOTT'이라는 사원의 사원번호, 이름, 월급을 출력하시오.
select empno,ename,sal
from emp
where ename = 'FORD';

--9. 사원테이블에서 직위가 'SALESMAN'인 사원의 사원번호, 이름, 직위를
-- 출력하시오.

select empno,ename,job
from emp
where job = 'SALESMAN';

--10. 사원테이블에서 사원번호가 7499, 7521, 7654인 사원의 사원번호, 이름
-- 월급을 출력하시오.
select empno,ename,sal
from emp
where empno IN (7499, 7521, 7654);

--11. 사원테이블에서 월급이 1500에서 3000사이인 사원의 사원번호, 이름,
-- 월급을 출력하시오.
select empno,ename,sal
from emp
where sal BETWEEN 1500 AND 3000;

--12. 사원 테이블에서 부서 배치를 받지 못한 직원의 이름을 출력하시오.
select ename
from emp
where MGR is null;


--13. 사원테이블에서 월급이 많은 순으로 모든 정보를 출력하시오.

select *
from emp
ORDER by sal desc;

--14. 사원테이블에서 직원의 이름과 입사일을 출력하는데 최근에 입사한 순으로 출력하시오.
select ename,hiredate
from emp
ORDER by hiredate asc;

--15. 사원 테이블에서 30번 부서에 근무하는 직원들의 이름을 출력하는데
-- 입사한지 오래된 순으로 출력하시오.

select ename
from emp
WHERE deptno = 30
ORDER by hiredate desc;

