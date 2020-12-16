package com.aerosimo.apps;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DbconTest {
	
	@Test
	void testGetConnection() {
		// create the database connection object.
		Dbcon con;
		con = new Dbcon();
		// check for equality
		Assertions.assertEquals(con.getConnection() != null,true,"Checking the Database is connected");

	}
		
	@Test
	void testGetUser() {
		// create the database connection object.
		Dbcon con;
		con = new Dbcon();
		String sql;
		sql = "select user from dual";
		PreparedStatement preparedStatement;		
		try {
			preparedStatement = con.getConnection().prepareStatement(sql);
			ResultSet result = preparedStatement.executeQuery();
			String actual = null;
			while (result.next()) {
				actual = result.getString(1);
			}
			String expected = "HATS"; //Change the value of this to whatever your database username
			// check for equality
			Assertions.assertEquals(expected, actual,"This should get database username and compare it");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
