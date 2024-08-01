/*
 * Author : Gnuke
 * When : Aug, 01, 2024
 * Objective : Emp Sal Mgmt Controller
 * Environment : Windows 11 Enterprise Ed., JDK 17.0.10, Eclipse
 * */

package com.example.controller;

import com.example.model.EmpVO;

public class CalcController {
	
	private EmpVO e;
	
	public CalcController( EmpVO e ) {
		this.e = e;
		this.calc();
	}

	private void calc() {
		int gradeSal = this.getGradeSal( e.getGrade() ); // 기본 수당
		int hoSal = this.getHoSal( e.getEmpno() ); // 호급수당
		int overCom = this.getOverCom( e.getOverTime() ); // 야간수당
		int com_fam = e.getFamily() * 7000; // 가족수당
		
		int totSal = gradeSal + hoSal + overCom + com_fam; 
		// 총금액 = 기본수당 + 호급수당 + 야간수당 + 가족수당 
		double realSal = totSal * 0.9; // 실수령액
		
		String deptCode = this.getDeptCode( e.getEmpno() );
		//this.e.setGradeSal(gradeSal);
		this.e.setHoSal(hoSal);
		this.e.setCom_fam(com_fam);
		this.e.setOverCom(overCom);
		this.e.setTotSal(totSal);
		this.e.setRealSal(realSal);
	}
	
// Method-----------------------------------------------------------------------------
	
	/*
	 * 사원번호에서 부서코드 추출 후 부서이름 return하는 메서드
	 */
	private String getDeptCode( String empno ) { 
		String deptCode = null;
		switch( String.valueOf(empno.charAt(0)) ) {
			case "A": deptCode = "영업부"; break;
			case "B": deptCode = "업무부"; break;
			case "C": deptCode = "홍보부"; break;
			case "D": deptCode = "인사부"; break;
			case "E": deptCode = "경리부"; break;
			case "F": deptCode = "판촉부"; break;
			case "G": deptCode = "총무부"; break;
		}
		return deptCode;
	}
	
	/*
	 * 기본수당 변환 메서드
	 */
	private int getGradeSal( int grade ) {
		int gradeSal = 0;
		switch(grade) {
			case 1: gradeSal = 15000; break;
			case 2: gradeSal = 15000; break;
			case 3: gradeSal = 15000; break;
			case 4: gradeSal = 15000; break;
		}
		return gradeSal;
	}
	
	/*
	 * 사원번호에서 호 추출 후 호급수당 return하는 메서드
	 */
	private int getHoSal( String empno ) {
		int hoSal = 0;
		switch( Integer.parseInt(String.valueOf(empno.charAt(1))) ) {
			case 1: hoSal = 900000; break;
			case 2: hoSal = 400000; break;
			case 3: hoSal = 600000; break;
			case 4: hoSal = 800000; break;
			case 5: hoSal = 300000; break;
			case 6: hoSal = 800000; break;
			case 7: 	hoSal = 800000; break;
		}
		return hoSal;
	}
	
	/*
	 * 야간수당 return 메서드
	 */
	private int getOverCom( int overTime ) {
		int overCom = 0;
		switch( overTime ) {
			case 1: overCom = 1500; break;
			case 2: overCom = 2500; break;
			case 3: overCom = 3500; break;
			case 4: overCom = 4500; break;
		}
		return overCom;
	}
	
}
