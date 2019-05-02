package com.OCS.utilities;  

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.OCS.accelerators.TestEngine;
import com.OCS.support.HtmlReportSupport;


public class SendMail extends HtmlReportSupport {
	public static void attachReportsToEmail() throws Exception {

		String strReportsFolder = "";
		
		strReportsFolder = "Routine_"+TestEngine.propertyManager.get("ReleaseVersion")+"_"+TestEngine.browserName;//+"_"+com.cigniti.support.ReportStampSupport.dateTime();
		String strZipFilePath = "Results"+File.separator
				+ strReportsFolder
				+ ".Zip";
				
		Zip.zipFolder(TestEngine.filePath(), strZipFilePath);

		String to = TestEngine.propertyManager.get("ToAddresses");
		String[] cc = { TestEngine.propertyManager.get("CCAddresses") };
		String[] bcc = {};

		// This is for google
		SendMail.sendMail(
				TestEngine.propertyManager.get("SenderUserName"),
				TestEngine.propertyManager.get("SenderpassWord"),
				"smtp.gmail.com",
				"465",
				"true",
				"true",
				true,
				"javax.net.ssl.SSLSocketFactory",
				"false",
				to,
				cc,
				bcc,
				strReportsFolder,
				" ",
				strZipFilePath,
				strReportsFolder
						+ TestEngine.filePath().split(strReportsFolder)+ ".Zip");

	}

	public static boolean sendMail(String userName, String passWord,
			String host, String port, String starttls, String auth,
			boolean debug, String socketFactoryClass, String fallback,
			String to, String[] cc, String[] bcc, String subject,
			String text, String attachmentPath, String attachmentName) {

//		String strReportsFolder = "Firefox";

		Properties props = new Properties();

		props.put("mail.smtp.user", userName);

		props.put("mail.smtp.host", host);

		if (!"".equals(port))

			props.put("mail.smtp.port", port);

		if (!"".equals(starttls))

			props.put("mail.smtp.starttls.enable", starttls);

		props.put("mail.smtp.auth", auth);

		if (debug) {

			props.put("mail.smtp.debug", "true");

		} else {

			props.put("mail.smtp.debug", "false");

		}

		if (!"".equals(port))

			props.put("mail.smtp.socketFactory.port", port);

		if (!"".equals(socketFactoryClass))

			props.put("mail.smtp.socketFactory.class", socketFactoryClass);

		if (!"".equals(fallback))

			props.put("mail.smtp.socketFactory.fallback", fallback);

		try

		{

			Session session = Session.getDefaultInstance(props, null);

			session.setDebug(debug);

			MimeMessage msg = new MimeMessage(session);

			msg.setSubject(subject);

			Multipart multipart = new MimeMultipart();
			MimeBodyPart messageBodyPart = new MimeBodyPart();

			DataSource source = new FileDataSource(attachmentPath);
			
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(attachmentName);
			multipart.addBodyPart(messageBodyPart);
			msg.setContent(multipart);
			String[] too = to.split(",");
			for (int i = 0; i < too.length; i++) {
				msg.setFrom(new InternetAddress(too[i]));
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
						too[i]));

			}

			for (int i = 0; i < cc.length; i++) {

				msg.addRecipient(Message.RecipientType.CC, new InternetAddress(
						cc[i]));

			}

			for (int i = 0; i < bcc.length; i++) {

				msg.addRecipient(Message.RecipientType.BCC,
						new InternetAddress(bcc[i]));

			}

			msg.saveChanges();
			

			Transport transport = session.getTransport("smtp");

			transport.connect(host, userName, passWord);

			transport.sendMessage(msg, msg.getAllRecipients());

			transport.close();

			return true;

		}

		catch (Exception mex)

		{

			mex.printStackTrace();

			return false;

		}

	}

}















