package com.aerosimo.apps;

/**
 * This piece of work is to generate transaction identifier.
 * 
 * @author Aerosimo
 * Organization: Aerosimo Ltd
 * 
 * Last Updated: 202009241300Z
 * 
 * Copyright (c) 2020 Aerosimo
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

import java.util.Random;

public class TransactionGen {

	public static void main(String[] args) {
		StringBuffer randStr;
		randStr = new StringBuffer();
		StringBuffer randStr1;
		randStr1 = new StringBuffer();
		StringBuffer randStr2;
		randStr2 = new StringBuffer();
		String CHAR_LIST;
		CHAR_LIST = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		int RANDOM_STRING_LENGTH;
		RANDOM_STRING_LENGTH = 4;

		/**
		 * This method generates random string with combination of Alphabet and Numbers
		 * 
		 * @return
		 */
		for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
			int randomInt = 0;
			Random randomGenerator = new Random();
			randomInt = randomGenerator.nextInt(CHAR_LIST.length());
			char ch = CHAR_LIST.charAt(randomInt);
			randStr.append(ch);
		}
		for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
			int randomInt = 0;
			Random randomGenerator = new Random();
			randomInt = randomGenerator.nextInt(CHAR_LIST.length());
			char ch = CHAR_LIST.charAt(randomInt);
			randStr1.append(ch);
		}
		for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
			int randomInt = 0;
			Random randomGenerator = new Random();
			randomInt = randomGenerator.nextInt(CHAR_LIST.length());
			char ch = CHAR_LIST.charAt(randomInt);
			randStr2.append(ch);
		}
		String result = randStr.toString() + "-" + randStr1.toString() + "-" + randStr2.toString();
		System.out.println(result);
	}
}