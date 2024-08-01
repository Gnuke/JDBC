package com.example.model;

public class EmpVO {
	private String empno; // 사원번호
	private String ename; // 사원이름
	private int grade; // 기본급
	private int overTime; // 야간수당
	private int family; // 가족수
	
	private String deptCode; // 부서명
	private int gradeSal; // 기본수당
	private int hoSal; // 호급수당
	private int overCom; // 야간수당
	private int com_fam; // 가족수당
	
	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public int getGradeSal() {
		return gradeSal;
	}

	public void setGradeSal(int gradeSal) {
		this.gradeSal = gradeSal;
	}

	public int getHoSal() {
		return hoSal;
	}

	public void setHoSal(int hoSal) {
		this.hoSal = hoSal;
	}

	public int getOverCom() {
		return overCom;
	}

	public void setOverCom(int overCom) {
		this.overCom = overCom;
	}

	public int getCom_fam() {
		return com_fam;
	}

	public void setCom_fam(int com_fam) {
		this.com_fam = com_fam;
	}

	public int getTotSal() {
		return totSal;
	}

	public void setTotSal(int totSal) {
		this.totSal = totSal;
	}

	public double getRealSal() {
		return realSal;
	}

	public void setRealSal(double realSal) {
		this.realSal = realSal;
	}
	private int totSal; // 총금액
	private double realSal; // 실수령액
	
	
	public EmpVO(String empno, String ename, int grade, int overTime, int family) {
		this.empno = empno;
		this.ename = ename;
		this.grade = grade;
		this.overTime = overTime;
		this.family = family;
	}
	
	public String getEmpno() {
		return empno;
	}
	public void setEmpno(String empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getOverTime() {
		return overTime;
	}
	public void setOverTime(int overTime) {
		this.overTime = overTime;
	}
	public int getFamily() {
		return family;
	}
	public void setFamily(int family) {
		this.family = family;
	}
	
}
