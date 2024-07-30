CREATE VIEW mycompany.empview10
AS
SELECT empno, ename, job
FROM emp
WHERE deptno = 10;