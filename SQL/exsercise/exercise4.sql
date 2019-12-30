select *
from emp e,dept d
where d.deptno=e.deptno;

select e.ename,e.job,d.dname
from emp e,dept d
where d.deptno=e.deptno
    AND d.LOC = 'DALLAS';
    
select e.ename,d.dname
from emp e,dept d
where d.deptno=e.deptno
    AND e.ename LIKE '%A%';
    
select e.ename,d.dname,e.sal
from emp e,dept d
where d.deptno=e.deptno
    AND e.sal>=3000;
    
select e.empno AS "직원번호" , 
    e.ename AS "직원 이름",
    e.sal*12 AS "연봉", 
    e.sal*12 + e.comm AS "실급여",
    s.grade AS "급여등급"
from emp e, salgrade s
where e.sal BETWEEN s.losal AND s.hisal
    AND comm is not null;
    
select e.empno AS "직원번호" , 
    e.ename AS "직원 이름",
    e.sal AS "월급", 
    s.grade AS "급여등급"
from emp e, salgrade s
where e.sal BETWEEN s.losal AND s.hisal
    AND e.deptno = 10;
    
select e.empno AS "직원번호" , 
    e.ename AS "직원 이름",
    e.sal AS "월급", 
    s.grade AS "급여등급"
from emp e, salgrade s
where e.sal BETWEEN s.losal AND s.hisal
    AND e.deptno IN (10,20)
ORDER by e.deptno desc,e.sal asc;
    
    
select e.ename,e.deptno,NVL(d.dname,'미정')
from emp e,dept d
where d.deptno(+)=e.deptno;

select e.ename,NVL(e.deptno,0) AS 부서번호,d.dname
from emp e,dept d
where d.deptno=e.deptno(+);

select e.ename,e.comm,d.dname,d.loc
from emp e,dept d
where d.deptno=e.deptno(+)
    AND comm is not NULL;
    
select e.ename,e.sal,s.grade
from emp e,dept d,salgrade s
where d.deptno=e.deptno(+)
    AND e.sal BETWEEN s.losal AND s.hisal
    AND d.loc = 'DALLAS';
    
select NVL(e.ename,'누구?')AS "이름",d.deptno,d.dname
from emp e,dept d
where d.deptno=e.deptno(+)
UNION
select e.ename,d.deptno,NVL(d.dname,'어디?') AS "부서명"
from emp e,dept d
where d.deptno(+)=e.deptno;

select e.empno AS "사원번호",e.ename AS "사원이름", NVL(TO_CHAR(d.empno),'없음') AS "관리자번호", NVL(d.ename, '없음') AS "관리자이름"
from emp e,emp d
where e.mgr = d.empno;