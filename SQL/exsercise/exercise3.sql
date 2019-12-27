-- 1. 집합연산자 실습 

-- 1.1 course1 과 course2 를 모두 수강하는 학생들의 이름, 전화 번호 그리고 
--    나이를 출력하시오.

select name,phone,age
from course1
union
select name,phone,age
from course2;

-- 1.2 course1 이든 course2 든 수강하는 학생들의 이름, 전화 번호 그리고 
--    나이를 출력하는데 나이가 많은 순으로 출력하시오.
select *
from (select name,phone,age
    from course1
    union
    select name,phone,age
    from course2)
ORDER by age desc;

-- 1.3 course1은 수강하지만 course2 는 수강하지 않는 학생들의 이름을 출력하시오.

select name,phone,age
from course1
Minus
select name,phone,age
from course2;

-- 1.4 course2는 수강하지만 course1 은 수강하지 않는 학생들의 이름과 전화번호를 
--    이름 순으로 출력하시오.

select * 
from (select name,phone,age
    from course2
    Minus
    select name,phone,age
    from course1
    )
ORDER by name;

--2. 모든 직원들 월급의 평균을 구하시오. 
--       (소수점 이하 둘째자리까지만 나타내고 셋째자리 부터는 절삭하시오)

select trunc(avg(sal),2) AS "월급평균"
from emp;


--3. 모든 직원들이 받는 커미션의 합을 구하시오.

select sum(comm) AS "커미셥 합"
from emp;

select job,sum(sal) AS "총월급"
from emp
GROUP by job
ORDER by sum(sal);

select NVL2(deptno,TO_CHAR(deptno)||'번 부서','미정') AS "부서명",count(*)
from emp
GROUP by deptno
ORDER by count(*);

select To_CHAR(hireDATE,'YYYY')||'년도' AS "입사년도",count(*) AS "입사인원"
from emp
group by To_CHAR(hireDATE,'YYYY')
ORDER by count(*) desc;

select To_CHAR(SysDATE,'YYYY"년 "MM"월 "DD"일 "') || '기준으로 '||count(*)||'명이 근무중입니다.' AS "근무 인원"
from emp;

select job,sum(sal)
from emp
where job NOT in('MANAGER')
group by job
having sum(sal)>5000;

select job, To_CHAR(Round(avg(sal+NVL(comm,0)*12),2),'999,999,999.99') AS "평균 월급"
from emp
where deptno = 30
group by job;

select To_CHAR(hireDATE,'MM')||'월' AS "월" ,count(*) AS "입사인원"
from emp
group by To_CHAR(hireDATE,'MM')
having count(*)>=2
order by "월";