package com.vs4vijay.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Mail Utility for Sending Mails
 * 
 * @author Vijay
 * 
 */
public class MailUtil {

	public static void send(String email, String body) {
		Properties properties = new Properties();
		Session session = Session.getDefaultInstance(properties);
		Message message = new MimeMessage(session);
		try {
			InternetAddress sender = new InternetAddress("vs4vijay@gmail.com",
					"Vijay Soni");
			InternetAddress receiver = new InternetAddress(
					"vs4vijay@gmail.com", "Vizay Soni");
			message.setFrom(sender);
			message.addRecipient(RecipientType.TO, receiver);
			message.setSubject("Sent from AppEngine");
			if (email != null && !email.isEmpty()) {
				body = "Sent from : " + email + " ::: ";
			}
			message.setText(body + " >>>> Sent from AppEngine");
			Transport.send(message);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public static void send(String body) {
		send("", body);
	}

	public static void send() {
		send("Test Mail");
	}

}
