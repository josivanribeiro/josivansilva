package com.josivansilva.business.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.josivansilva.business.entities.Page;
import com.josivansilva.business.services.PageService;
import com.josivansilva.dao.PageDAO;
import com.josivansilva.dao.impl.PageDAOImpl;
import com.josivansilva.exceptions.BusinessException;
import com.josivansilva.exceptions.DataAccessException;
import com.josivansilva.util.Pagination;


public class PageServiceImpl implements PageService  {
	/**
	 * Defines the logger instance property. 
	 */
	private static Logger logger = Logger.getLogger (PageServiceImpl.class);
	/**
	 * Defines the singleton instance property.
	 */
	private static PageServiceImpl instance;
	
	
	private PageDAO pageDAO = PageDAOImpl.getInstance();
	
	/**
	 * Default private constructor.
	 */
	private PageServiceImpl () {}
	
	/**
	 * Default singleton.
	 * 
	 * @return the singleton instance.
	 */
	public static PageService getInstance () {
		if (instance == null) {
			instance = new PageServiceImpl ();
		}
		return instance;
	}	
	
	@Override
	public int create (Page page) throws BusinessException {
		logger.info ("Start executing the method create.");
		int affectedRows = 0;
		try {
			affectedRows = pageDAO.create (page);
			logger.info ("affectedRows [" + affectedRows + "]");
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while creating a page.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method create.");
		return affectedRows;
	}

	@Override
	public int update (Page page) throws BusinessException {
		logger.info ("Start executing the method update.");
		int affectedRows = 0;
		try {
			affectedRows = pageDAO.update (page);
			logger.info ("affectedRows [" + affectedRows + "]");
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while updating a page.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method update.");
		return affectedRows;
	}

	@Override
	public int removeAll (Page[] pageArr) throws BusinessException {
		logger.info ("Start executing the method removeAll.");
		int affectedRows = 0;
		try {
			affectedRows = pageDAO.removeAll (pageArr);
			logger.info ("affectedRows [" + affectedRows + "]");
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while removing the pages.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method removeAll.");
		return affectedRows;
	}

	@Override
	public List<Page> findAll() throws BusinessException {
		logger.info ("Start executing the method findAll.");
		List<Page> list = new ArrayList<Page>();
		try {
			list = pageDAO.findAll ();
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while finding all.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findAll.");
		return list;
	}

	@Override
	public List<Page> findByFilter (Page page, Pagination pagination) throws BusinessException {
		logger.info ("Start executing the method findByFilter.");
		List<Page> list = new ArrayList<Page>();
		try {
			list = pageDAO.findByFilter (page, pagination);
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while finding pages by filter.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findByFilter.");
		return list;
	}

	@Override
	public Page findById (Page page) throws BusinessException {
		logger.info ("Start executing the method findById.");
		Page found = null;
		try {
			logger.info ("page.getID_PAGE() " + page.getID_PAGE());
			found = pageDAO.findById (page);
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while finding a page by id.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findById.");
		return found;
	}

	@Override
	public int findRowCount() throws BusinessException {
		logger.info ("Start executing the method findRowCount.");
		int rowCount = 0;
		try {
			rowCount = pageDAO.findRowCount();
			logger.info ("rowCount [" + rowCount + "]");
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while executing the findRowCount method.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findRowCount.");
		return rowCount;
	}

}
