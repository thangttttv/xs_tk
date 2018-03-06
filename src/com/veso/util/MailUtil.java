package com.veso.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 * @author THE THANG
 */
public class MailUtil {

	/** Item store properties * */
	private static Properties prop;

	/**
	 * @param cls
	 * @param propertyName
	 * @return properties
	 * @throws java.io.IOException
	 */
	/*private static java.util.Properties load(Class cls, String propertyName)
			throws java.io.IOException {
		InputStream is = cls.getResourceAsStream(propertyName);
		if (is != null) {
			java.util.Properties propers = new java.util.Properties();
			FileInputStream propsFile = new java.io.FileInputStream("./conf/config.conf"); 
			propers.load(propsFile);
			return propers;
		} else {
			return null;
		}
	}*/
	
	private static java.util.Properties load(Class cls, String propertyName)
	throws java.io.IOException {
	InputStream is = cls.getResourceAsStream(propertyName);
	if (is != null) {
		java.util.Properties propers = new java.util.Properties();
		propers.load(is);
		return propers;
	} else {
		return null;
	}
	}

	/**
	 * @return properties
	 * @throws java.io.IOException
	 */
	private static Properties loadProperties() throws java.io.IOException {
		if (prop == null) {
			prop = load(MailUtil.class, "mail.properties");
		}
		return prop;
	}

	/**
	 * @param to
	 * @param subject
	 * @param content
	 * @throws AddressException
	 */
	public boolean sendMail(InternetAddress[] to,InternetAddress[] cc, String subject, String content)
			throws AddressException {
		Properties prop = null;
		Session sendMailSession = null;
		content = content==null?"":content;
		boolean kt = true;
		try {
			prop = loadProperties();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Properties props = new Properties();

			// Put property in to props
			props.put("mail.smtp.auth", prop.getProperty("mail.smtp.auth"));
			props.put("mail.smtp.host", prop.getProperty("mail.smtp.host"));
			props.put("mail.smtp.user", prop.getProperty("mail.smtp.user"));
			props.put("mail.smtp.password", prop
					.getProperty("mail.smtp.password"));

			InternetAddress from = new InternetAddress(prop
					.getProperty("mail.smtp.user"));

			PopupAuthenticator popA = new PopupAuthenticator();
			popA.performCheck(from.getAddress(), prop
					.getProperty("mail.smtp.password"));
			sendMailSession = Session.getInstance(props, popA);

			MimeMessage newMessage = new MimeMessage(sendMailSession);
			newMessage.setFrom(from);			
			newMessage.setRecipients(Message.RecipientType.TO, to);
			if(cc!=null)				
			newMessage.setRecipients(Message.RecipientType.CC, cc);
			
			newMessage.setSentDate(new Date());

			MimeBodyPart textpart = new MimeBodyPart();
			
			String aa[] ={"utf-8","text/html"};
			textpart.setContentLanguage(aa);
			textpart.setText(content, "UTF-8");
			
			Multipart multiPart = new MimeMultipart();
			multiPart.addBodyPart(textpart);

			newMessage.setContent(content,"text/html  ; charset=UTF-8");			
			newMessage.setSubject(subject, "UTF-8");
			sendMailSession.getTransport("smtp");
			Transport.send(newMessage);

		} catch (MessagingException ex) {
			ex.printStackTrace();
			kt = false;
		} catch (Exception e) {
			e.printStackTrace();
			kt = false;
		}
		return kt;
	}

	
	public boolean sendMailAttach(InternetAddress[] to,InternetAddress[] cc, String subject, String content, String fileName, byte[] fileData)
	throws AddressException {
		Properties prop = null;
		Session sendMailSession = null;
		boolean kt = true;
		try {
			prop = loadProperties();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Properties props = new Properties();		
			// Put property in to props
			props.put("mail.smtp.auth", prop.getProperty("mail.smtp.auth"));
			props.put("mail.smtp.host", prop.getProperty("mail.smtp.host"));
			props.put("mail.smtp.user", prop.getProperty("mail.smtp.user"));
			props.put("mail.smtp.password", prop
					.getProperty("mail.smtp.password"));		
			InternetAddress from = new InternetAddress(prop
					.getProperty("mail.smtp.user"));		
			PopupAuthenticator popA = new PopupAuthenticator();
			popA.performCheck(from.getAddress(), prop
					.getProperty("mail.smtp.password"));
			sendMailSession = Session.getInstance(props, popA);
		
			MimeMessage newMessage = new MimeMessage(sendMailSession);

			//Message newMessage = new MimeMessage(sendMailSession);
			newMessage.setFrom(from);			
			newMessage.setRecipients(Message.RecipientType.TO, to);
			if(cc!=null)				
			newMessage.setRecipients(Message.RecipientType.CC, cc);
			
			newMessage.setSentDate(new Date());
			
			// create and fill the first message part
			MimeBodyPart mbp1 = new MimeBodyPart();
			String lang[] ={"utf-8","text/html"};
			/*mbp1.setContentLanguage(lang);				
			mbp1.setText(content,"UTF-8");
			String htmlEncoded = TurkishLocaleHelper
			.convertTrToUTF8(content);*/
			
			mbp1.setText(content,"UTF-8");
			mbp1.setHeader("Content-Type",
					"text/html;charset=UTF-8");
			mbp1.setHeader("Content-Transfer-Encoding",
					"quoted-printable");

	
			// create the second message part
			MimeBodyPart mbp2 = new MimeBodyPart();

			// attach the file to the message
			File file = new File(fileName);
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(fileData);
			FileDataSource fds =new  FileDataSource(file);
			
			mbp2.setDataHandler(new DataHandler(fds));
			mbp2.setFileName(fds.getName());
			mbp2.setContentLanguage(lang);
			// create the Multipart and add its parts to it
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);
			mp.addBodyPart(mbp2);			
			
			// add the Multipart to the message				
			newMessage.setContent(mp,"text/html  ; charset=UTF-8");
			newMessage.setSubject(subject,"UTF-8");
			// set the Date: header
			newMessage.setSentDate(new Date());
			
			Transport.send(newMessage);
		
		} catch (MessagingException ex) {
			ex.printStackTrace();
			kt = false;
		} catch (Exception e) {
			e.printStackTrace();
			kt = false;
		}
		return kt;
	}	

	public boolean sendMailLogin(String userName, String password) {
		Properties prop = null;
		Session sendMailSession = null;
		try {
			prop = loadProperties();
		} catch (IOException e) {
			return false;
		}
		try {
			Properties props = new Properties();

			// Put property in to props
			props.put("mail.smtp.auth", prop.getProperty("mail.smtp.auth"));
			props.put("mail.smtp.host", prop.getProperty("mail.smtp.host"));
			props.put("mail.smtp.user", userName);
			props.put("mail.smtp.password", password);

			InternetAddress from = new InternetAddress(userName);
			InternetAddress[] to = new InternetAddress[1];
			to[0] = new InternetAddress(prop.getProperty("mail.smtp.user"));

			PopupAuthenticator popA = new PopupAuthenticator();
			popA.performCheck(from.getAddress(), password);
			sendMailSession = Session.getInstance(props, popA);

			Message newMessage = new MimeMessage(sendMailSession);

			newMessage.setFrom(from);
			newMessage.setRecipients(Message.RecipientType.TO, to);
			newMessage.setSubject("Login System");
			newMessage.setSentDate(new Date());

			newMessage.setContent("<b>" + userName
					+ "</b> has connect to office.", "text/html");

			sendMailSession.getTransport("smtp");

			Transport.send(newMessage);

			return true;
		}		
		catch (MessagingException ex) {
			return false;
		}
	}
	
	public static void main(String[] args) {
		MailUtil util = new MailUtil();
		InternetAddress[] internetAddresses = new InternetAddress[1];
		InternetAddress[] internetAddressescc = new InternetAddress[1];
		try {
			internetAddresses[0] = new InternetAddress("thangttnd@gmail.com");
			internetAddressescc[0] = new InternetAddress("thangttnd@gmail.vn");
			util.sendMail(internetAddresses,internetAddressescc, "Ha ha",
					"Chao ban");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
}
