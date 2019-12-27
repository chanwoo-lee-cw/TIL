--QUESTION
--1. 사원 테이블에서 사원이름을 첫글자는 대문자로, 나머지는 소문자로 출력하시오.
-- [결과]       사원이름
--           ---------------------
--                  Smith
       
--desc emp;

select INITCAP(ENAME) AS "사원이름"
from EMP;


--2. 사원테이블에서 사원이름과 사원 이름의 두번째 글자부터 네번째 글자까지
-- 출력하시오.
-- [결과]         사원이름    2-4문자
--                  ---------------------
--                  SMITH        MIT
        
select ENAME AS "사원이름" ,
    SUBSTR(ENAME,2,4)  AS "2~4문자"
from EMP;

--3. 사원테이블의 사원 이름의 문자 개수를 출력하시오.
select ENAME,length(ENAME)
from EMP;

--4. 사원테이블에서 사원 이름의 앞 글자 하나와 마지막 글자 하나만 출력하되 
-- 모두 소문자로 각각 출력하시오.
select ENAME,LOWER(SUBSTR(ENAME,1,1) || SUBSTR(ENAME,-1,1))
from EMP;

--5. 3456.78을 반올림하여 소수점 아래 첫번째 자리 까지만 나타내시오.

select round(3456.78,1) from dual;

--6. 월급에 50를 곱하고 십단위는 절삭하여 출력하는데 월급뒤에 '원'을 붙이고 
--    천단위마다 ','를 붙여서 출력한다.
--[결과]	계산 결과
-----------------
--          40,000원
--	80,000원
-- 	62,500원
--	148,700원

select TO_CHAR(trunc(sal*50,-2),'999,999,999')||'원'
from emp;

--7. 직원 이름과 커미션 설정 여부를 출력하는데 설정되었으면 '설정'
--    설정되지 않았으면 '미정'을 출력하시오.
--    	SMITH	미정	
--	ALLEN	설정	300
--	WARD	설정	200
--	JONES	설정	30

select eNAME , NVL2(COMM ,'설정'||TO_CHAR(comm),'미정')
FROM EMP;

--8. 직원 이름과 부서번호 그리고 부서번호에 따른 부서명을 출력하시오.
--    부서가 없는 직원은 '없당' 을 출력하시오.
--    (부서명 : 10 이면 'A 부서', 20 이면 'B 부서', 30 이면 'C 부서')
desc emp
select eNAME ,DEPTNO, NVL2(DEPTNO,TO_CHAR(DEPTNO)||'부서','없당')
FROM EMP;

--9. 직위가 'SALESMAN'이고 월급이 1000이상인 사원의
-- 이름과 월급을 출력하시오.

select eNAME ,SAL
FROM EMP
where job='SALESMAN'
AND SAL>=1000;

--10. 오늘날짜와 오늘날짜에서 10일을 더한 날짜를 출력하시오.

select SYSDATE,SYSDATE+10
FROM dual;

--11. 현재 시간을 "....년 ..월 ..일 ..시 ..분" 으로 출력하시오.

select TO_CHAR(SYSDATE,'YYYY"년"MM"월"DD"일"HH"시"MI"분"')
FROM dual;

--12. 직원의 이름, 월급여, 연봉을 조회하는 질의를 작성하시오.
--(단, 직원의 연봉은 $200의 월 보너스를 포함하여 계산합니다.)

--이름              월급여               연봉
---------------------------------------------------
--SMITH             800                 12000
--ALLEN            1600                 21600
--WARD             1250                 17400

select ename,sal,(sal+200)*12
FROM emp;

--13.  12월27일_추가.docx 로 대체

select ename AS "이름", sal as "월급",
    case 
        when sal<1000 THEN 'A'
        when sal<2000 THEN 'B'
        when sal<3000 THEN 'C'
        when sal<4000 THEN 'D'
        else 'E'
    END AS "코드"
from emp;

--14. 12월27일_추가.docx 로 대체

select ename AS "이름", deptno AS "부서번호",
    DECODE (deptno,
    10,'A',
    20,'B',
    30,'C',
    40,'D'
    )
from emp;

--15. 이름의 두번째 문자가 “A”인 모든 직원의 이름을 조회하는 질의를 작성하시오 

--ENAME
----------
--WARD 
--MARTIN
--JAMES

Select ename
from emp
where ename like '_A%';


--16.   모든 직원의 이름과 현재까지의 입사기간을 월단위(가장 가까운 월로 반올림할 것)로 
--   조회하는 질의를 작성하시오
--   (이때, 입사기간에 해당하는 열별칭은 “MONTHS WORKED”로 하고, 
--    입사기간이 가장 적은 직원순으로 정렬한다.)

--출력예)
--ENAME         MONTHS WORKED
--------------------------------
--ADAMS            284
--SCOTT            286
--MILLER           348

select ename,round(MONTHS_BETWEEN(SYSDATE,HIREDATE))
from EMP;

--17. 사원테이블에서 사원이름과 사원들의 오늘 날짜까지의 근무일수를 구하시오.
--사원이름   근무일수
-- -----------------------
--SMITH    12474일
--ALLEN    12409일
--WARD    12407일
--JONES    12368일
--MARTIN    12189일
--BLAKE    12369일

select ename,TO_CHAR(round(SYSDATE-HIREDATE))||'일' AS 근무일수
from EMP;

--18. 1981년도에 입사한 직원들의 이름, 직위 그리고 입사일을
--         입사한 순으로 출력하시오.

select ename,job,HIREDATE
from EMP
Where TO_CHAR(HIREDATE,'YYYY')= '1981'
order by hiredate asc;

--19. 내생일을 기준으로 요일을 출력하는 SQL  명령을 작성하시오.

select TO_CHAR(TO_DATE('1994/08/27'),'DAY')
from dual;

--20. 현재를 기준으로 내가 태어난지 몇 개월 되었는지 알 수 있는 
--      SQL 명령을 작성하시오.

select MONTHS_BETWEEN(SYSDATE,TO_DATE('1994/08/27','YYYY-MM-DD'))
from dual;

--21. 직원들의 이름과 월급 그리고 커미션 정보를 출력하는데
--     커미션이 정해지지 않은 직원은 "미정"이라고 출력하시오.
select eNAME , NVL(TO_CHAR(COMM) ,'미정')
FROM EMP;

--22. 사원테이블에서 이름의 첫글자가 A이고 마지막 글자가 N이 아닌 사원의
-- 이름을 출력하시오.

Select ename
from emp
where ename like 'A%'
AND ename not like '%N';

--23. 직원의 이름, 입사 날짜 그리고 년도를 기준으로한 반올림 날짜를
--     출력하시오.        

Select ename,ROUND(HIREDATE,'DD'),ROUND(HIREDATE,'YYYY')
from emp