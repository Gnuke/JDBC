/*
 * 7단계
 * 1. import java.sql.*
 * */
import java.sql.*;

public class JDBCDemo {
	public static void main(String[] args) {
		// 2. MySQL Driver Loading
		try {
			Class.forName( "com.mysql.cj.jdbc.Driver" );
			System.out.println( "Class founded" );
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println( "Class not found" );
		}
		// 3. Memory에 loading 된 MySQL Driver를 이용하여 Connection하자
		// DriverManager.registerDriver(null); // forName을 사용하거나 DriverManager.registerDriver 사용
		String url = "jdbc:mysql://127.0.0.1:3306/mycompany";		// url 주소
		Connection conn = null;
		try {
			conn = DriverManager.getConnection( url, "root", "mymysql" );	// DriverManager.getConnection( String url, "username" , "pw" );
			System.out.println( "Connection Success" );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println( "Connection Failed" );
		}
		// 4. Statement 객체 생성
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 5. Statement 객체를 이용하여 SQL 실행
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery( "SELECT now(), version()" ); // SELECT 용
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println( "Query Syntax Error" );
		}
		// 6. ResultSet의 결과 Parsing
		try {
			rs.next(); // 한 줄 내려야한다.
			String now = rs.getString( 1 ); // JDBC는 index가 1 부터 시작한다. (1) -> now()
			String version = rs.getString( 2 );
			System.out.println( now );
			System.out.println( version );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println( e.getMessage() );
		}
		// 7. 반드시 Close 해야 한다.
		try {
			if( rs != null ) rs.close();
			if( conn != null ) conn.close();
			if( stmt != null ) stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println( e.getMessage() );
		}
	}
}
