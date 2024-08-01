package com.example.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmpDAOImpl implements EmpDAO{
	
	private Connection conn;

	public EmpDAOImpl() {
		// TODO Auto-generated constructor stub
		DBConnection dbConn = new DBConnection();
		this.conn = dbConn.getConnection();
	}
	
	@Override
	public boolean createEmp(EmpVO e) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<EmpVO> readAllEmp() throws SQLException {
		String sql = "SELECT empno, name, grade, overtime, family ";
		sql += "FROM emp";
		
		Statement stmt = this.conn.createStatement();
		
		ResultSet rs = stmt.executeQuery( sql );
		
		rs.next();
		while( rs.next() ) {
			String empno = rs.getString( "empno" );
			String ename = rs.getString( "name" );
			int grade = rs.getInt( "grade" );
			String empno = rs.getString( "empno" );
			String empno = rs.getString( "empno" );
		}
		
		List<EmpVO> list = new ArrayList<EmpVO>();
		return null;
	}

}
