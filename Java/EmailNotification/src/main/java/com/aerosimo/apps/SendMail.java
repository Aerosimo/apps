package com.aerosimo.apps;

import java.io.File;

/**
 * This piece of work is to send email notification.
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

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {

    public String SendEmail(String emailAddress, String emailSubject,String emailMessage, String emailFiles) {
        
    	String emailResponse;
        emailResponse = "Success";
        String[] recipientList;
        recipientList = emailAddress.split(",");
        String[] attachFiles;
        attachFiles = emailFiles.split(","); 
    	String usr = null;
    	String pword = null;
    	String emailHost = null;
    	String emailPort = null;
    	String emailSSL = null;
        
        try {
            File myFile = new File("/opt/oracle/admin/wallet/email.txt");
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
            	usr = myReader.nextLine();
            	pword = myReader.nextLine();
            	emailHost = myReader.nextLine();
            	emailPort = myReader.nextLine();
            	emailSSL = myReader.nextLine();
            }
            myReader.close();
          } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          } 
        final String emailUserName = usr;
        final String emailPassword = pword;

        // Create a Properties object to contain connection configuration information.
        Properties prop;
        prop = new Properties();       
        prop.put("mail.smtp.host", emailHost);
        prop.put("mail.smtp.port", emailPort);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", emailPort);
        prop.put("mail.smtp.socketFactory.class", emailSSL);
        prop.put("mail.smtp.ssl.trust", emailHost);
        prop.put("mail.smtp.starttls.enable", "true");
        if (emailAddress.length() == 0 || emailSubject.length() == 0 || emailMessage.length() == 0) {
            emailResponse = "You can not send email without Address, Subject or Message";
        }
        if (emailAddress.length() != 0 && emailSubject.length() != 0 && emailMessage.length() != 0 &&
            emailFiles.length() == 0) {
            try {               
            	// creates a new session with an authenticator
                Authenticator auth;
                auth = new Authenticator() {
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailUserName, emailPassword);
                    }
                };
                // Create a Session object to represent a mail session with the specified properties.
                Session sess;
                sess = Session.getInstance(prop, auth);
                // Create a message with the specified information.
                Message msg;
                msg = new MimeMessage(sess);
                // creates a new e-mail message
                // Set From: header field of the header.
                msg.setFrom(new InternetAddress(emailUserName));
                // Set To: header field of the header.
                InternetAddress[] toAddresses = new InternetAddress[recipientList.length];
                int counter = 0;
                for (String recipient : recipientList) {
                    toAddresses[counter] = new InternetAddress(recipient.trim());
                    counter++;
                }
                msg.setRecipients(Message.RecipientType.TO, toAddresses);
                // Set Subject: header field
                msg.setSubject(emailSubject);
                // Set Date: Date field
                msg.setSentDate(new Date());
                // Now set the actual message
                msg.setContent(emailMessage, "text/html");
                // sends the e-mail
                Transport.send(msg);
                emailResponse = "Successfully send email";
            } catch (Exception e) {
                emailResponse = "Error sending email:- " + e.getMessage();
            }
        } else {
            try {                
            	// creates a new session with an authenticator
                Authenticator auth;
                auth = new Authenticator() {
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailUserName, emailPassword);
                    }
                };
                // Create a Session object to represent a mail session with the specified properties.
                Session sess;
                sess = Session.getInstance(prop, auth);
                // Create a message with the specified information.
                Message msg;
                msg = new MimeMessage(sess);
                // creates a new e-mail message
                // Set From: header field of the header.
                msg.setFrom(new InternetAddress(emailUserName));
                // Set To: header field of the header.
                InternetAddress[] toAddresses = new InternetAddress[recipientList.length];
                int counter = 0;
                for (String recipient : recipientList) {
                    toAddresses[counter] = new InternetAddress(recipient.trim());
                    counter++;
                }
                msg.setRecipients(Message.RecipientType.TO, toAddresses);
                // Set Subject: header field
                msg.setSubject(emailSubject);
                // Set Date: Date field
                msg.setSentDate(new Date());
                // Now set the actual message
                // creates message part
                MimeBodyPart messageBodyPart;
                messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(emailMessage, "text/html");
                // creates multi-part
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
                // adds attachments
                for (String filePath : attachFiles) {
                    MimeBodyPart attachPart;
                    attachPart = new MimeBodyPart();
                    try {
                        attachPart.attachFile(filePath);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    multipart.addBodyPart(attachPart);
                }
                // sets the multi-part as e-mail's content
                msg.setContent(multipart);
                // sends the e-mail
                Transport.send(msg);
                emailResponse = "Successfully send email";
            } catch (Exception e) {
                emailResponse = "Error sending email:- " + e.getMessage();
            }
        }

        return emailResponse;

    }
}