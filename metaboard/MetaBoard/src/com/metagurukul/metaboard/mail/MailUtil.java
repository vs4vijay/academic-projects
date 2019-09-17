package com.metagurukul.metaboard.mail;


import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {
	
    Properties mailServerConf=new Properties();
    
		
	public  void sendMail(String to,String title,String description)
	{
	   	
		mailServerConf.put("mail.smtp.host", "smtp.gmail.com");
		mailServerConf.put("mail.smtp.socketFactory.port", "465");
		mailServerConf.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		mailServerConf.put("mail.smtp.auth", "true");
		mailServerConf.put("mail.smtp.port", "465");
		
		Session session = Session.getDefaultInstance(mailServerConf,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("MetaBoardNotice@gmail.com","cubemeta123");
					}
				});
	 
			try {
	 
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("MetaBoardNotice@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(to));
				message.setSubject(title);
				message.setText(description);
	 
				Transport.send(message);
	 
				System.out.println("Mail Done");
	 
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
	}
	

}
