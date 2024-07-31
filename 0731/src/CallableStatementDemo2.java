import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class CallableStatementDemo2 {
	public static void main(String[] args) throws SQLException {
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 1,2,3
//		Statement stmt = conn.createStatement();
		//String sql = "...";
		String sql = "{ call sp_deleteDept() }"; // 4. -> parameter가 없음
		CallableStatement cstmt = null;
		try {
			cstmt = conn.prepareCall(sql);
			cstmt.execute();
			System.out.println( "삭제 성공" );
		}catch( SQLException e ) {
			System.out.println( e.getMessage() );
		}
		DBClose.dbClose(conn, cstmt); // 7
	}
}
/**
DELIMITER //
CREATE PROCEDURE sp_deleteDept()
BEGIN
	DELETE FROM dept_clone;
END
// DELIMITER ; 
*/
 