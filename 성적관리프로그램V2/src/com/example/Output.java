package com.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Output {
	private List<StudentVO> list;//field
	private Connection conn;
	public Output(List<StudentVO> list) {
		// TODO Auto-generated constructor stub
		this.list = list;
		DBConnection dbconn = new DBConnection();
		this.conn = dbconn.getConnection(); // 1,2,3
	}

	void output() {
//		for( StudentVO std : this.list ) {
		Statement stmt = null;
		for(StudentVO std : this.list ) {
			try {
				stmt = this.conn.createStatement();
				String sql = "INSERT INTO student "; // Linux MySQL로 넣었는데 Linux는 테이블의 대소문자를 구분함.
				sql += "VALUES('" + std.getHakbun() + "', '" + std.getName() + "', '";
				sql += std.getKor() + "', '" + std.getEng() + "', '" + std.getMat();
				sql += "', '" + std.getEdp() + "', '" + std.getTot() + "', '" + std.getAvg();
				sql += "', '" + std.getGrade() + "')";
				//System.out.println( sql );
				
				stmt.executeUpdate(sql);// 4
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println( e.getMessage() );
			}
		}
		DBClose.dbClose(this.conn, stmt); // 6 생략, 7번 까지
		System.out.println( "DB Inserted Successfully");
	}
	
}
