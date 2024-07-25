package com.example.controller;

import java.sql.SQLException;

import com.example.model.PatientDAO;
import com.example.model.PatientDAOImpl;
import com.example.model.PatientVO;

public class InsertController {
	private PatientDAO pDao;
	
	public InsertController() {
		// TODO Auto-generated constructor stub
		pDao = new PatientDAOImpl();
	}
	
	public boolean insert( PatientVO p ) {
		boolean result = false;
		try {
			CalcController cc = new CalcController(p);
			result = pDao.createPatient(p);
		}catch(SQLException e) {
			System.out.println( e.getMessage() );
		}
		return result;
	}
}
