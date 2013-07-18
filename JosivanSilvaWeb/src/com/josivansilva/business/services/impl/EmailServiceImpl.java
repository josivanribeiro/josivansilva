package com.josivansilva.business.services.impl;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.apache.log4j.Logger;

import com.josivansilva.business.entities.Contact;
import com.josivansilva.business.entities.EmailConfiguration;
import com.josivansilva.business.services.EmailService;
import com.josivansilva.constants.Constants;
import com.josivansilva.exceptions.BusinessException;


public class EmailServiceImpl implements EmailService {
	/**
	 * Default logger.
	 */
	private static Logger logger = Logger.getLogger (EmailServiceImpl.class);
	
	/**
	 * Defines the singleton instance property.
	 */
	private static EmailServiceImpl instance;
	
	/**
	 * Default private constructor.
	 */
	private EmailServiceImpl () {}
	
	/**
	 * Default singleton.
	 * 
	 * @return the singleton instance.
	 */
	public static EmailService getInstance () {
		if (instance == null) {
			instance = new EmailServiceImpl ();
		}
		return instance;
	}
	
	/**
	 * Sends a HTML email.
	 * 
	 * @param emailConfig the email configuration bean.
	 * @param htmlContent the HTML content.
	 * @return a boolean indicating the success operation.
	 * @throws MessagingException
	 * @throws IOException
	 */
	private boolean sendHtmlEmail (final EmailConfiguration emailConfig, String htmlContent) throws MessagingException, IOException {
		logger.info ("Starting executing the method sendHtmlEmail.");
		boolean sent = false;
		Properties props = System.getProperties ();
	    // XXX - could use Session.getTransport() and Transport.connect()
	    // XXX - assume we're using SMTP
	    props.put("mail.smtp.host", emailConfig.getHost ());	    
	    // begin - further configuration to send email via TLS (e.g., using Gmail SMTP)
	    props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");		
		props.put("mail.smtp.user", emailConfig.getUser());
		props.put("mail.smtp.host", emailConfig.getHost());
		props.put("mail.smtp.port", emailConfig.getPort());
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", emailConfig.getPort());
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");		
		// end - further configuration to send email via TLS (e.g., using Gmail SMTP)
	    // Get a Session object
	    Session session = Session.getInstance (props);
	    // construct the message
	    Message msg = new MimeMessage (session);
	    msg.setFrom (new InternetAddress (emailConfig.getFrom()));
	    msg.setRecipients (Message.RecipientType.TO, InternetAddress.parse (emailConfig.getTo(), false));
	    if (emailConfig.getCc() != null) {
	    	msg.setRecipients (Message.RecipientType.CC, InternetAddress.parse (emailConfig.getCc(), false));
	    }
	    if (emailConfig.getBcc() != null) {
	    	msg.setRecipients (Message.RecipientType.BCC, InternetAddress.parse (emailConfig.getBcc(), false));
	    }
	    msg.setSubject (emailConfig.getSubject());
	    msg.setHeader ("X-Mailer", emailConfig.getMailer());
	    msg.setSentDate (new Date());
		msg.setDataHandler (new DataHandler (new ByteArrayDataSource (htmlContent, "text/html")));
		Transport transport = session.getTransport ("smtps");
		transport.connect (emailConfig.getHost(), emailConfig.getPort(), emailConfig.getUser(), emailConfig.getPassword());
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close ();
		sent = true;
	    logger.info ("emailConfig.getHost() [" + emailConfig.getHost() + "]");
		logger.info ("emailConfig.getFrom() [" + emailConfig.getFrom() + "]");
		logger.info ("emailConfig.getTo() [" + emailConfig.getTo() + "]");
		logger.info ("emailConfig.getSubject() [" + emailConfig.getSubject() + "]");
		logger.info ("emailConfig.getMailer() [" + emailConfig.getMailer() + "]");
	    logger.info ("sent [" + sent + "]");
	    logger.info ("Finish executing the method sendHtmlEmail.");	    
	    return sent;
	}

	@Override
	public boolean sendContactEmail (Contact contact) throws BusinessException {
		logger.info ("Start executing the method sendResetPasswordEmail.");
		boolean sent = false;
		EmailConfiguration emailConfig = null;
		String htmlContent = null;
		emailConfig = new EmailConfiguration ();
		emailConfig.setHost (Constants.DEFAULT_SMTP_HOST);
		emailConfig.setPort (Constants.DEFAULT_SMTP_PORT);
		emailConfig.setFrom (Constants.DEFAULT_SENDER);
		emailConfig.setMailer (Constants.DEFAULT_HEADER_MAILER);
		emailConfig.setUser (Constants.DEFAULT_SMTP_AUTH_USER);
		emailConfig.setPassword (Constants.DEFAULT_SMTP_AUTH_PWD);
		emailConfig.setTo (contact.getEmail());
		emailConfig.setSubject (Constants.CONTACT_SUBJECT);
		htmlContent = contact.getMessage();
		try {
			sent = sendHtmlEmail (emailConfig, htmlContent);
			logger.info ("sent [" + sent + "]");
		} catch (Exception e) {
			String errorMessage = "A business exception error occurred while sending the Reset password email.";
			logger.error (errorMessage, e);
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method sendResetPasswordEmail.");
		return sent;
	}

	@Override
	public boolean sendContactEmailConfirmation (Contact contact) throws BusinessException {
		logger.info ("Start executing the method sendResetPasswordEmail.");
		boolean sent = false;
		EmailConfiguration emailConfig = null;
		String htmlContent = null;
		emailConfig = new EmailConfiguration ();
		emailConfig.setHost (Constants.DEFAULT_SMTP_HOST);
		emailConfig.setPort (Constants.DEFAULT_SMTP_PORT);
		emailConfig.setFrom (Constants.DEFAULT_SENDER);
		emailConfig.setMailer (Constants.DEFAULT_HEADER_MAILER);
		emailConfig.setUser (Constants.DEFAULT_SMTP_AUTH_USER);
		emailConfig.setPassword (Constants.DEFAULT_SMTP_AUTH_PWD);
		emailConfig.setTo (contact.getEmail());
		emailConfig.setSubject (Constants.CONTACT_SUBJECT);
		//htmlContent = buildResetUserPasswordHtmlContent (user);
		htmlContent = contact.getMessage();
		try {
			sent = sendHtmlEmail (emailConfig, htmlContent);
			logger.info ("sent [" + sent + "]");
		} catch (Exception e) {
			String errorMessage = "A business exception error occurred while sending the Reset password email.";
			logger.error (errorMessage, e);
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method sendResetPasswordEmail.");
		return sent;
	}	

}
