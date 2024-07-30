package com.example.controller;

import java.sql.SQLException;

import com.example.model.PatientDAO;
import com.example.model.PatientDAOImpl;

public class DeleteController {
	private PatientDAO dao;
	
	public DeleteController() {
		this.dao = new PatientDAOImpl();
	}
	
	public boolean delete( int number ) {
		boolean isSuccess = false;
		try {
			isSuccess = this.dao.deletePatient(number);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return isSuccess;
	}
	
}
