import java.sql.*;

public class JDBCDemo1 {
	private static final String url = "jdbc:mysql://127.0.0.1:3306/mycompany";
	private static final String id = "root";
	private static final String pw = "mymysql";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName( "com.mysql.cj.jdbc.Driver" );
		} catch (ClassNotFoundException e) {
			System.out.println( e.getMessage() );
		}
		try {
			conn = DriverManager.getConnection(url, id, pw);
			stmt = conn.createStatement();
			
			String sql = "SELECT empno, ename, job, sal   ";
			sql += " FROM emp   ";
			sql += " WHERE sal >= 1500";
			
//			rs = stmt.executeQuery( "SELECT empno, ename, job, sal FROM emp" );
			rs = stmt.executeQuery( sql );
			while( rs.next() ) {
				int empno = rs.getInt(1); // empno
				String ename = rs.getString( "ename" ); // 가독성을 위해 컬럼 명을 써주는 것이 좋다
				String job = rs.getString( "job" );
				double sal = rs.getDouble( "sal" );
				
				System.out.printf( "%d\t%s\t%s\t%.2f\n", empno, ename, job, sal );
			}
		}catch( SQLException e ) {
			System.out.println( e.getMessage() );
		}finally {
			try {
				
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if( conn != null) conn.close();
				
			}catch( SQLException e ) {
				System.out.println( e.getMessage() );
			}
		}
	}

}
