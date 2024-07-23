package com.example.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.DBConnection;

class ConnectionTest {
	private Connection conn;
	
	@BeforeEach
	void init() {
		DBConnection dbconn = new DBConnection();
		this.conn = dbconn.getConnection();
		//System.out.println( this.conn );
	}
	
	@Test
	void test() {
		assertNotNull( this.conn );
	}
	
	@AfterEach
	void close() {
		try {
			this.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
