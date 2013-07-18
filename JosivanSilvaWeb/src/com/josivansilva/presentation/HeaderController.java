package com.josivansilva.presentation;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;

import com.josivansilva.business.entities.Page;
import com.josivansilva.business.services.PageService;
import com.josivansilva.business.services.impl.PageServiceImpl;
import com.josivansilva.exceptions.BusinessException;


/**
 * Header Controller.
 * 
 * @author Josivan Silva
 *
 */
@ManagedBean
@RequestScoped
public class HeaderController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger (HeaderController.class);
	
	private List<Page> pageList;	
	private PageService pageService = PageServiceImpl.getInstance();	
	
	public List<Page> getPageList() {
		return pageList;
	}

	public void setPageList(List<Page> pageList) {
		this.pageList = pageList;
	}

	@PostConstruct
    public void init() {
		logger.info ("Start executing the method init().");
		populatePage ();
		logger.info ("Finish executing the method init().");
    }
	
	/**
	 * Populates the page list.
	 */
	private void populatePage () {
		logger.info ("Start executing the method populatePage().");
		try {			
			this.pageList = pageService.findAll ();						
		} catch (BusinessException e) {
			String error = "An error occurred while performing the service to find all pages. " + e.getMessage();
			logger.error (error);
		}
		logger.info ("Finish executing the method populatePage().");
	}	

}
