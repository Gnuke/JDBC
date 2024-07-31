import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class CallableStatementDemo4 {
	public static void main(String[] args) throws SQLException {
		Scanner scan = new Scanner(System.in);
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 1,2,3

		String sql = "{ call sp_selectEmp( ?, ?, ? ) }"; // -> 불완전한 SQL문
		CallableStatement cstmt = null;
		try {
			cstmt = conn.prepareCall(sql); // 4.
			System.out.print( "사원번호 : " ); int empno = scan.nextInt();
			
			cstmt.setInt( 1, empno ); // IN Parameter
			cstmt.registerOutParameter( 2, java.sql.Types.VARCHAR );
			cstmt.registerOutParameter( 3, java.sql.Types.VARCHAR ); // 완전한 SQL문
			cstmt.execute(); // 5.
			
			System.out.printf( "부서명 : %s, 부서위치 : %s%n",
					cstmt.getString(2), cstmt.getString(3) );
		}catch( SQLException e ) {
			System.out.println( e.getMessage() );
		}
		DBClose.dbClose(conn, cstmt); // 7
	}
}
/**
DELIMITER $$
CREATE PROCEDURE sp_selectEmp(
	IN v_empno	SMALLINT,
    OUT v_dname VARCHAR(14),
    OUT v_loc VARCHAR(13)
)
BEGIN
	SELECT dname, loc INTO v_dname, v_loc
    FROM emp JOIN dept ON( emp.deptno = dept.deptno )
    WHERE empno = v_empno;
END
$$
DELIMITER ;
*/
 