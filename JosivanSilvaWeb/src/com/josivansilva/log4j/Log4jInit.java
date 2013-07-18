package com.josivansilva.log4j;

import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Initializes the Log4j log component.
 * 
 * @author Josivan Silva
 *
 */
public class Log4jInit extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger (Log4jInit.class);
	
	public void init () {
		String prefix =  getServletContext().getRealPath("/");
		String file = getInitParameter("log4j-init-file");
		// if the log4j-init-file is not set, then no point in trying
		if(file != null) {
	    	String filename = prefix + file;
	    	try {
	    		DOMConfigurator.configure (filename);
	    		logger.info ("The log4j has been successfully initialized.");
	    	} catch (Exception e) {
	    		throw new RuntimeException ("An error occurred during the log4j initialization. ", e);
	    	}
	    }
	}

}
