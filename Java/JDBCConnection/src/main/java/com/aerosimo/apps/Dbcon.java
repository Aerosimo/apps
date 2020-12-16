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

import java.io.File;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Dbcon {

    public Connection getConnection() {
        String url = null;
        String dbpass = null;
        String dbuser = null;
        String driver = null;
        File dbsecret;
        Scanner secretreader;
        Connection con;
        con = null;
        try {
            // We need to provide file path as the parameter where to read the password from:
            // Use of double backquote is to avoid compiler interpret words
            // Example \test as \t (ie. as a escape sequence)
            dbsecret = new File("/opt/oracle/admin/wallet/hats.txt");                        
            secretreader = new Scanner(dbsecret);
            // while loop to read all lines
            while(secretreader.hasNextLine()) {
            	driver = secretreader.nextLine();
            	dbuser = secretreader.nextLine();
            	dbpass = secretreader.nextLine();
            	url = secretreader.nextLine();  
            }
            // Load the class driver class
            Class.forName(driver);
            // Create the driver manager connection object.
            con = DriverManager.getConnection(url, dbuser, dbpass);
            // Close the file reader
            secretreader.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
}