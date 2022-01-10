package com.vam.persistence;

import static org.junit.Assert.fail;

import java.sql.DriverManager;

import org.junit.Test;


public class JDBCTest {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testConnection() {

		try {
				java.sql.Connection con = 
				DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "DATA", "1234");				
	}catch(Exception e) {
		fail(e.getMessage());
	}

}
}

