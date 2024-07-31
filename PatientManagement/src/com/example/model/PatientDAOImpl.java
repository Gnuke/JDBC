package com.example.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
	public PatientVO readPatient( int number ) throws SQLException {
		String sql = "{ call sp_select_one_patient( ? ) }"; // 불완전한 SQL문
		CallableStatement cstmt = this.conn.prepareCall(sql); // 4.
		cstmt.setInt(1, number); // -> 완전한 SQL문
		
		ResultSet rs = cstmt.executeQuery();
		boolean flag = rs.next(); // 못찾으면 false 찾으면 true
		
		PatientVO p = null;
		if( flag ) { // 찾았으면
			p = new PatientVO( rs.getInt("number"), rs.getString("code"), 
					rs.getInt("days"), rs.getInt("age") );
			p.setDept( rs.getString("dept") ); p.setOperFee( rs.getInt("operfee") );
			p.setHospitalFee( rs.getInt("hospitalfee") );	p.setMoney( rs.getInt("money") );
		}else { // 못찾았으면
		}
		
		return p;
	}
	
	@Override
	public List<PatientVO> readAllPatient() throws SQLException{
//		String sql = "SELECT number, dept, operfee, hospitalfee, money";
//		sql += " FROM Patient ORDER BY number DESC";
		
		// Stored Procedure 사용
		String sql = "{ call sp_select_all_patient() }";
		CallableStatement cstmt = this.conn.prepareCall(sql); // 4.
		
//		Statement stmt = this.conn.createStatement();
		ResultSet rs = cstmt.executeQuery(sql); // 5.
		boolean flag = rs.next();
		List<PatientVO> list = new ArrayList<PatientVO>(); // capacity 10개 생성
		
		if( !flag ) {
			// 한 번도 add()를 하지 않아서 결국 list.size() == 0 이다.
		}else { //while(rs.next()) { // 6.
			do {
				int number = rs.getInt( "number" );
				String dept = rs.getString( "dept" );
				int operfee = rs.getInt( "operfee" );
				int hospitalfee = rs.getInt( "hospitalfee" );
				int money = rs.getInt( "money" );
				
				PatientVO p = new PatientVO();
				p.setNumber(number); 	p.setDept(dept);
				p.setOperFee(operfee); 	p.setHospitalFee(hospitalfee);
				p.setMoney(money);
				list.add(p);
			} while( rs.next() );
		}
		DBClose.dbClose(conn, cstmt, rs);
		return list;
	}

	@Override
	public boolean updatePatient(PatientVO p) throws SQLException {
		String sql = "{ call sp_update_patient( ?, ?, ?, ?, ?, ?, ?, ? ) }"; // -> 불완전 SQL문
		CallableStatement cstmt = this.conn.prepareCall(sql);
		cstmt.setInt( 1, p.getNumber() ); cstmt.setString( 2, p.getCode() ); cstmt.setInt( 3, p.getDays() );
		cstmt.setInt( 4, p.getAge() ); cstmt.setString( 5, p.getDept() );
		cstmt.setInt( 6, p.getOperFee() ); cstmt.setInt( 7, p.getHospitalFee() );
		cstmt.setInt( 8, p.getMoney() ); // 완전한 SQL문
		
		boolean flag = cstmt.execute(); // 5.
		
		DBClose.dbClose( conn, cstmt ); // 7.
		
		return flag;
	}

	@Override
	public boolean deletePatient(int number) throws SQLException {
		//Statement stmt = this.conn.createStatement();
		String sql = "DELETE FROM Patient WHERE number = ?"; // 불완전한 SQL문
		PreparedStatement pstmt = this.conn.prepareStatement( sql ); // 4.
		pstmt.setInt(1, number); // 완전한 SQL 문장
		int row = pstmt.executeUpdate(); // 5.
		DBClose.dbClose( conn, pstmt );
		
		return (row == 1 ) ? true : false;
	}

}
