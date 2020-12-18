package com.aerosimo.apps;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

/**
 * This piece of work is to retrieve daily horoscopes based on database record.
 * 
 * @author Aerosimo Organization: Aerosimo Ltd
 * 
 *         Last Updated: 202009241300Z
 * 
 *         Copyright (c) 2020 Aerosimo Permission is hereby granted, free of
 *         charge, to any person obtaining a copy of this software and
 *         associated documentation files (the "Software"), to deal in the
 *         Software without restriction, including without limitation the rights
 *         to use, copy, modify, merge, publish, distribute, sublicense, and/or
 *         sell copies of the Software, and to permit persons to whom the
 *         Software is furnished to do so, subject to the following conditions:
 * 
 *         The above copyright notice and this permission notice shall be
 *         included in all copies or substantial portions of the Software.
 * 
 *         THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *         EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 *         MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *         NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 *         BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 *         ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 *         CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *         SOFTWARE.
 *
 */

public class Astrology {
	
	public static void main(String[] args) {

	// To get all zodiac signs for the day use array as below, 
			// if the intention is to use one sign at a time then use normal string
			// and no need for the loop
			
			String[] signs = { "Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius",
					"Capricorn", "Aquarius", "Pisces" };
					// call in for-loop to loop through all the zodiac signs
			for (int i = 0; i < signs.length; i++) {
				String resource = "https://aztro.sameerkumar.website/?sign=" + signs[i].toLowerCase() + "&day=today";
				try {
					URL url = new URL(resource); 
					HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // Open url connection
					// Set connection properties
					conn.setConnectTimeout(5000);
					conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
					conn.setDoOutput(true);
					conn.setDoInput(true);
					conn.setRequestMethod("POST");
					OutputStream os = conn.getOutputStream();
					os.write(resource.getBytes("UTF-8"));
					os.close();
					// read the response
					InputStream in = new BufferedInputStream(conn.getInputStream());
					String result = IOUtils.toString(in, "UTF-8");
					JSONObject myResponse = new JSONObject(result);
					
					// Print out the values of the object.
					System.out.println(result);
					System.out.println("----------------------------------");
					System.out.println("result after Reading JSON Response");
					System.out.println("----------------------------------");
					System.out.println("date_range: "+myResponse.getString("date_range"));
					System.out.println("current_date: "+myResponse.getString("current_date"));
					System.out.println("description: "+myResponse.getString("description"));
					System.out.println("compatibility: "+myResponse.getString("compatibility"));
					System.out.println("mood: "+myResponse.getString("mood"));
					System.out.println("color: "+myResponse.getString("color"));   
					System.out.println("lucky_number: "+myResponse.getString("lucky_number"));
					System.out.println("lucky_time: "+myResponse.getString("lucky_time"));
					in.close();
					conn.disconnect();
				} catch (Exception e) {
					System.out.println(e);
				}
				
			}
		}
	}