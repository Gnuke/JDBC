package com.example.model;

import java.sql.SQLException;
import java.util.List;

public interface EmpDAO {
	/**
	 * 새로운 사원 등록.
	 * @param Emp
	 * @throws SQLException 
	 */
	boolean createEmp( EmpVO e ) throws SQLException;
	/**
	 * 모든 사원 정보 리턴
	 * @return 사원 리스트
	 */
	List<EmpVO>readAllEmp() throws SQLException;
}
