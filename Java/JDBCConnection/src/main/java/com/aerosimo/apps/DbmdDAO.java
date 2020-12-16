/**
 * 
 */
package com.aerosimo.apps;

/**
 * This piece of work is to try and connect to Oracle Database.
 * 
 * @author Aerosimo
 * @Organization: Aerosimo Ltd
 * 
 * Last Updated: 202009241300Z
 * 
 * Copyright (c) 2020 Aerosimo
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

import java.sql.*;

public class DbmdDAO {

	public static void main(String[] args) {
		// create the database connection object.
		Dbcon con = new Dbcon();	
		if (con.getConnection() != null) {
	
			try {
				DatabaseMetaData dbmd=con.getConnection().getMetaData();			
				System.out.println("Driver Name: "+dbmd.getDriverName());  
				System.out.println("Driver Version: "+dbmd.getDriverVersion());  
				System.out.println("UserName: "+dbmd.getUserName());  
				System.out.println("Database Product Name: "+dbmd.getDatabaseProductName());  
				System.out.println("Database Product Version: "+dbmd.getDatabaseProductVersion());
				con.getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
				
				System.out.println("Could not establish database connection. Error Message: " + e);
			}
		}else {
			System.out.println("Could not establish database connection.");
		}

	}

}
