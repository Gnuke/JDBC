package com.example.controller;

import java.sql.SQLException;
import java.util.List;

import com.example.model.PatientDAO;
import com.example.model.PatientDAOImpl;
import com.example.model.PatientVO;

public class SelectController {
	private PatientDAO pDao;
	
	public SelectController() {
		this.pDao = new PatientDAOImpl();
	}
	
	public List<PatientVO> selectAllPatient(){
		List<PatientVO> list = null;
		try {
			list = this.pDao.readAllPatient();
		}catch( SQLException e ) {
			System.out.println( e.getMessage() );
		}
		return list;
	}

	public PatientVO selectPatient( int number ) {
		PatientVO p = null;
		try {
			p = this.pDao.readPatient(number);
		}catch(SQLException e) {
			System.out.println( e.getMessage() );
		}
		return p;
	}
	
}
