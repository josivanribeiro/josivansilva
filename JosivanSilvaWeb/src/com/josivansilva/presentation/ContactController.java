package com.josivansilva.presentation;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;

import com.josivansilva.business.entities.Contact;
import com.josivansilva.business.services.EmailService;
import com.josivansilva.business.services.impl.EmailServiceImpl;
import com.josivansilva.exceptions.BusinessException;


/**
 * Contact Controller.
 * 
 * @author Josivan Silva
 *
 */
@ManagedBean
@RequestScoped
public class ContactController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger (ContactController.class);
	
	/**
	 * name form attributes
	 */
	private String firstNameForm;
	private String lastNameForm;
	private String emailForm;
	private String messageForm;
	
	private EmailService emailService = EmailServiceImpl.getInstance();
			
	public String getFirstNameForm() {
		return firstNameForm;
	}

	public void setFirstNameForm(String firstNameForm) {
		this.firstNameForm = firstNameForm;
	}

	public String getLastNameForm() {
		return lastNameForm;
	}

	public void setLastNameForm(String lastNameForm) {
		this.lastNameForm = lastNameForm;
	}

	public String getEmailForm() {
		return emailForm;
	}

	public void setEmailForm(String emailForm) {
		this.emailForm = emailForm;
	}

	public String getMessageForm() {
		return messageForm;
	}

	public void setMessageForm(String messageForm) {
		this.messageForm = messageForm;
	}

	@PostConstruct
    public void init() {
		resetForm ();
    }
	
	/**
	 * Resets the form.
	 */
	private void resetForm () {
		this.firstNameForm = "first name";
		this.lastNameForm  = "last name";
		this.emailForm     = "email";
		this.messageForm   = "message";
	}
	
	/**
	 * Processes the contact email form, sending a confirmation email as well.
	 * 
	 * @return
	 */
	public String processForm() {
		String toPage = "contact?faces-redirect=true";
		boolean successContact = false;
		boolean successContactConfirmation = false;
		Contact contact = new Contact();
		contact.setFirstName (this.firstNameForm);
		contact.setLastName (this.lastNameForm);
		contact.setEmail (this.emailForm);
		contact.setMessage (this.messageForm);
		try {
			successContact = emailService.sendContactEmail (contact);
		} catch (BusinessException e) {
			String error = "An error occurred while sending the contact email. " + e.getMessage();
			logger.error (error);
		}
		try {
			successContactConfirmation = emailService.sendContactEmailConfirmation (contact);
		} catch (BusinessException e) {
			String error = "An error occurred while sending the contact email confirmation. " + e.getMessage();
			logger.error (error);
		}
		if (successContact && successContactConfirmation) {
			toPage = "contact?status=success&faces-redirect=true";
		}		
		return toPage;
	}
	
}
