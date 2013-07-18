package com.josivansilva.business.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.josivansilva.business.entities.Menu;
import com.josivansilva.business.services.MenuService;
import com.josivansilva.dao.MenuDAO;
import com.josivansilva.dao.impl.MenuDAOImpl;
import com.josivansilva.exceptions.BusinessException;
import com.josivansilva.exceptions.DataAccessException;
import com.josivansilva.util.Pagination;


public class MenuServiceImpl implements MenuService  {
	/**
	 * Defines the logger instance property. 
	 */
	private static Logger logger = Logger.getLogger (MenuServiceImpl.class);
	/**
	 * Defines the singleton instance property.
	 */
	private static MenuServiceImpl instance;
	
	
	private MenuDAO menuDAO = MenuDAOImpl.getInstance();
	
	/**
	 * Default private constructor.
	 */
	private MenuServiceImpl () {}
	
	/**
	 * Default singleton.
	 * 
	 * @return the singleton instance.
	 */
	public static MenuService getInstance () {
		if (instance == null) {
			instance = new MenuServiceImpl ();
		}
		return instance;
	}

	@Override
	public int create (Menu menu) throws BusinessException {
		logger.info ("Start executing the method create.");
		int affectedRows = 0;
		try {
			affectedRows = menuDAO.create (menu);
			logger.info ("affectedRows [" + affectedRows + "]");
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while creating a menu.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method create.");
		return affectedRows;
	}

	@Override
	public int update (Menu menu) throws BusinessException {
		logger.info ("Start executing the method update.");
		int affectedRows = 0;
		try {
			affectedRows = menuDAO.update (menu);
			logger.info ("affectedRows [" + affectedRows + "]");
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while updating a menu.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method update.");
		return affectedRows;
	}

	@Override
	public int removeAll (Menu[] menuArr) throws BusinessException {
		logger.info ("Start executing the method removeAll.");
		int affectedRows = 0;
		try {
			affectedRows = menuDAO.removeAll (menuArr);
			logger.info ("affectedRows [" + affectedRows + "]");
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while removing the menus.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method removeAll.");
		return affectedRows;
	}

	@Override
	public List<Menu> findAll() throws BusinessException {
		logger.info ("Start executing the method findAll.");
		List<Menu> list = new ArrayList<Menu>();
		try {
			list = menuDAO.findAll ();
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while finding all.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findAll.");
		return list;
	}

	@Override
	public List<Menu> findByFilter (Menu menu, Pagination pagination) throws BusinessException {
		logger.info ("Start executing the method findByFilter.");
		List<Menu> list = new ArrayList<Menu>();
		try {
			list = menuDAO.findByFilter (menu, pagination);
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while finding menus by filter.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findByFilter.");
		return list;
	}

	@Override
	public Menu findById (Menu menu) throws BusinessException {
		logger.info ("Start executing the method findById.");
		Menu found = null;
		try {
			logger.info ("menu.getID_MENU() " + menu.getID_MENU());
			found = menuDAO.findById (menu);
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while finding a menu by id.";
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
			rowCount = menuDAO.findRowCount();
			logger.info ("rowCount [" + rowCount + "]");
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while executing the findRowCount method.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findRowCount.");
		return rowCount;
	}
	
}
