package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo2 {
	public static void main(String[] args) {
		String sql = "SELECT dept.deptno, dname, ROUND( AVG(sal), 2 ) AS avg_sal, COUNT(sal), SUM(sal) ";
		sql += "FROM emp JOIN dept ON( emp.deptno = dept.deptno ) ";
		sql += "GROUP BY deptno ";
		sql += "ORDER BY avg_sal DESC";
		
		Connection conn = new DBConnection().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while( rs.next() ) {
				System.out.printf( "%d\t%s\t%.2f\t%d\t%d%n",
						rs.getInt( "dept.deptno" ), rs.getString( "dname" ), rs.getDouble(3), rs.getInt(4), rs.getInt(5) );
			}
		}catch( SQLException e ) {
			System.out.println( e.getMessage() );
		}finally {
			DBClose.dbClose( conn, stmt, rs );
		}
	}
}
