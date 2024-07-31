import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class CallableStatementDemo {
	public static void main(String[] args) throws SQLException {
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 1,2,3
//		Statement stmt = conn.createStatement();
		//String sql = "...";
		String sql = "{ call sp_test( ?, ? ) }"; //미완성 SQL문
		CallableStatement cstmt = conn.prepareCall(sql); // 4.
		// IN Parameter는 setXxx()를 사용하고,
		// OUT Parameter는 registerOutParameter()를 사용한다.
		// INOUT Parameter는 setXxx(), registerOutParameter()를 사용한다. 
		cstmt.registerOutParameter( 1, java.sql.Types.DATE ); // parameter 등록
		cstmt.registerOutParameter( 2, java.sql.Types.VARCHAR );
		// -> 완전한 SQL문
		cstmt.execute();
		
		System.out.println( cstmt.getDate(1) );
		System.out.println( cstmt.getString(2) );
		
		DBClose.dbClose(conn, cstmt); // 7
	}
}
/*
DELIMITER //
CREATE PROCEDURE sp_test()
BEGIN
	SELECT NOW(), VERSION();
END
// 
DELIMITER ; 
 * */
 