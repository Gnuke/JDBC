package com.example.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PatientDAOImpl implements PatientDAO {
	private Connection conn;
	
	public PatientDAOImpl() {
		// TODO Auto-generated constructor stub
		DBConnection dbConn = new DBConnection();
		this.conn = dbConn.getConnection(); // 1,2,3
	}
	
	@Override
	public boolean createPatient(PatientVO p) throws SQLException { // Override의 조건 : 부모가 정의하지 않은 Exception을 자식이 정의할 수 없다. -> 자식의 Exception은 부모보다 같거나 작아야 한다.
		Statement stmt = this.conn.createStatement();
		StringBuffer sb = new StringBuffer();
		sb.append( "INSERT INTO Patient " );
		sb.append( "VALUES(" + p.getNumber() + ", '" + p.getCode() + "', ");
		sb.append( p.getDays() + ", " + p.getAge() + ", '" + p.getDept() + "', " );
		sb.append( p.getOperFee() + ", " + p.getHospitalFee() + ", " + p.getMoney() + ")" );
		
		System.out.println( sb.toString() ); // 검사
		
		int su = stmt.executeUpdate( sb.toString() );
		
		DBClose.dbClose(this.conn, stmt);
		
		return (su == 1) ? true : false; // su 가 1 => 하나가 정상 입력 되면 return true 
	}

	@Override
	public PatientVO readPatient(int number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updatePatient(PatientVO p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletePatient(int number) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<PatientVO> readAllPatient() {
		// TODO Auto-generated method stub
		return null;
	}

}
