import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class CallableStatementDemo3 {
	public static void main(String[] args) throws SQLException {
		Scanner scan = new Scanner(System.in);
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 1,2,3

		String sql = "{ call sp_insertDept( ?, ?, ? ) }"; // -> 불완전한 SQL문
		CallableStatement cstmt = null;
		try {
			cstmt = conn.prepareCall(sql); // 4.
			System.out.print( "부서번호 : " ); int deptno = scan.nextInt();
			System.out.print( "부서이름 : " ); String dname = scan.next();
			System.out.print( "부서위치 : " ); String loc = scan.next();
			
			cstmt.setInt( 1, deptno );
			cstmt.setString( 2, dname );
			cstmt.setString( 3, loc ); // -> 완전한 SQL문
			cstmt.execute(); // 5.
			
			System.out.println( "새 레코드 삽입 성공" );
		}catch( SQLException e ) {
			System.out.println( e.getMessage() );
		}
		DBClose.dbClose(conn, cstmt); // 7
	}
}
/**
DELIMITER $$
CREATE PROCEDURE sp_insertDept
(
	IN v_deptno	TINYINT,
    IN v_dname	VARCHAR(14),
    IN v_loc	VARCHAR(13)
)
BEGIN
	INSERT INTO dept_clone( deptno, dname, loc )
    VALUES( v_deptno, v_dname, v_loc );
    COMMIT;
END
$$ DELIMITER ; 
*/
 