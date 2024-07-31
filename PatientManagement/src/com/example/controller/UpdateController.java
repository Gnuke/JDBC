package com.example.controller;

import java.sql.SQLException;

import com.example.model.PatientDAO;
import com.example.model.PatientDAOImpl;
import com.example.model.PatientVO;

public class UpdateController {
	private PatientDAO pDao;
	
	public UpdateController() {
		this.pDao = new PatientDAOImpl();
	}
	
	public boolean update(PatientVO p) {
		boolean flag = false;
		try {
			CalcController cc = new CalcController(p);
			this.pDao.updatePatient(p);
			flag = true;
		}catch( SQLException e ) {
			System.out.println( e.getMessage() );
			flag = false;
		}
		return flag;
	}

}
