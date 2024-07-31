import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CallableStatementDemo6 {
	public static void main(String[] args) throws SQLException {
		Scanner scan = new Scanner(System.in);
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 1,2,3
		
		String sql = "{ call sp_select_emp_dept( ? ) }"; // -> 불완전한 SQL문
		CallableStatement cstmt = null;
		ResultSet rs = null;
		try {
			cstmt = conn.prepareCall(sql); // 4.
			System.out.print( "부서번호 : " ); int deptno = scan.nextInt();
			cstmt.setInt( 1, deptno ); // -> 완전한 SQL문
			rs = cstmt.executeQuery(); 
			// 5. => executeQuery 사용 이유 ... record가 반복되기 때문
			boolean flag = rs.next();
			if( !flag ) { // 레코드가 한 개도 없다면
				System.out.println( "조건에 맞는 결과값이 없습니다." );
			}else { // 한 개라도 있다면
				do { // next() 한 것을 처리하고 다음 반복을 할 필요가 있으므로 do를 사용
					System.out.printf( "%d\t%s\t%s\t%s%n",
							rs.getInt("empno"), rs.getString("ename"),
							rs.getString("dname"), rs.getString("loc") );
				}while( rs.next() );
			}
		}catch( SQLException e ) {
			System.out.println( e.getMessage() );
		}
		DBClose.dbClose(conn, cstmt); // 7
	}
}
/**
 * 부서번호로 소속 사원의 사원번호, 사원명, 부서명, 부서위치 출력
 * 여러 개 Select Stored Procedure
Delimiter $$
CREATE PROCEDURE sp_select_emp_dept
(
	IN	v_deptno	TINYINT
)
BEGIN
	SELECT empno, ename, dname, loc, dept.deptno
    FROM emp NATURAL JOIN dept
    WHERE deptno = v_deptno;
END
$$
Delimiter ;
*/
 