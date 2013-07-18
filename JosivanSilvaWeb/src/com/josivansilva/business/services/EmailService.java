package com.josivansilva.business.services;

import com.josivansilva.business.entities.Contact;
import com.josivansilva.exceptions.BusinessException;

/**
 * Email business service interface.
 * 
 * @author Josivan Silva
 *
 */
public interface EmailService {

	/**
	 * Sends a contact email.
	 * 
	 * @param contact
	 * @return
	 * @throws BusinessException
	 */
	public boolean sendContactEmail (Contact contact) throws BusinessException;
	
	public boolean sendContactEmailConfirmation (Contact contact) throws BusinessException;
	
}
