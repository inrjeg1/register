package com.helloalmere.register.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.text.StringSubstitutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.ResourceUtils;

import com.helloalmere.register.model.Category;
import com.helloalmere.register.model.Profile;

public class MailService {

	private static String USER_NAME = "helloalmere"; // GMail user name (just the part before "@gmail.com")
	private static String PASSWORD = "Almere@2019"; // GMail password
	private static String RECIPIENT = "vanakkamalmere@gmail.com";

	// Sending Mail Test
	public static void main(String[] args) {
		String from = USER_NAME;
		String pass = PASSWORD;
		String[] to = { RECIPIENT }; // list of recipient email addresses
		String subject = "Java send mail example";
		String body = "Welcome to JavaMail!";

		sendFromGMail(from, pass, to, subject, body);
	}

	@Async
	public static void sendMail(Profile profile) {
		String subject = "BADMINTON REGISTRATION CONFIRMATION";
		String body = getEmailBody(profile);
		String[] to = { profile.getEmail() };
		sendFromGMail(MailService.USER_NAME, MailService.PASSWORD, to, subject, body);
	}

	private static String getEmailBody(Profile profile) {
		StringBuilder body = new StringBuilder();
		
		try {
			File template = ResourceUtils.getFile("classpath:templates/emailbody.txt");
			body.append(new String(Files.readAllBytes(template.toPath())));
			
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put("firstName", profile.getFirstName());
		valuesMap.put("lastName", profile.getLastName());
		valuesMap.put("email",profile.getEmail());
		valuesMap.put("phoneNumber", profile.getPhoneNumber());
		valuesMap.put("category", getCategory(profile.getCategory()));
		valuesMap.put("proficiency", profile.getProficiency());
		StringSubstitutor  ssb = new StringSubstitutor(valuesMap);
		ssb.replace(body.toString());

		return ssb.replace(body.toString());
	}
	
	private static String getCategory(Category category) {
		StringBuilder categoryText = new StringBuilder();
		if (category.getSingle()) {
			categoryText.append("Singles , ");
		}
		
		if (category.getDoubles()) {
			categoryText.append("Doubles, ");
		}
		
		if (category.getMixedDoubles()) {
			categoryText.append("Mixed-Doubles,");
		}
		
		return categoryText.toString();
	}

	private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			// set message headers
			message.addHeader("Content-type", "text/HTML; charset=UTF-8");
			message.addHeader("format", "flowed");
			message.addHeader("Content-Transfer-Encoding", "8bit");
			message.setFrom(new InternetAddress(from));
			InternetAddress[] toAddress = new InternetAddress[to.length];

			// To get the array of addresses
			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}

			for (int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			message.setSubject(subject);
			message.setSentDate(new Date());
			message.setContent(body, "text/html");
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}
}