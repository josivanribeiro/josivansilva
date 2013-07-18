package com.josivansilva.business.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.josivansilva.business.entities.User;
import com.josivansilva.business.services.UserService;
import com.josivansilva.dao.UserDAO;
import com.josivansilva.dao.impl.UserDAOImpl;
import com.josivansilva.exceptions.BusinessException;
import com.josivansilva.exceptions.DataAccessException;
import com.josivansilva.util.Pagination;

public class UserServiceImpl implements UserService {
	/**
	 * Defines the logger instance property. 
	 */
	private static Logger logger = Logger.getLogger (UserServiceImpl.class);
	/**
	 * Defines the singleton instance property. 
	 */
	private static UserService instance;
	
	
	private static UserDAO userDAO = UserDAOImpl.getInstance();
	
	/**
	 * Default private constructor.
	 */
	private UserServiceImpl () {}
	
	/**
	 * Default singleton.
	 * 
	 * @return the singleton instance.
	 */
	public static UserService getInstance () {
		if (instance == null) {
			instance = new UserServiceImpl ();
		}
		return instance;
	}	
	
	@Override
	public int create (User user) throws BusinessException {
		logger.info ("Start executing the method create.");
		int affectedRows = 0;
		try {
			affectedRows = userDAO.create (user);
			logger.info ("affectedRows [" + affectedRows + "]");
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while creating an user.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method create.");
		return affectedRows;
	}

	@Override
	public int update (User user) throws BusinessException {
		logger.info ("Start executing the method update.");
		int affectedRows = 0;
		try {
			affectedRows = userDAO.update (user);
			logger.info ("affectedRows [" + affectedRows + "]");
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while updating an user.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method update.");
		return affectedRows;
	}
	
	public int removeAll (User[] userArr) throws BusinessException {
		logger.info ("Start executing the method removeAll.");
		int affectedRows = 0;
		try {
			affectedRows = userDAO.removeAll (userArr);
			logger.info ("affectedRows [" + affectedRows + "]");
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while executing the removeAll method.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method removeAll.");
		return affectedRows;
	}

	@Override
	public boolean doLogin (User user) throws BusinessException {
		logger.info ("Start executing the method doLogin.");
		boolean isLogged = false;
		try {
			isLogged = userDAO.doLogin (user);
			logger.info ("isLogged [" + isLogged + "]");
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while executing the doLogin method.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method doLogin.");
		return isLogged;
	}
	
	@Override
	public List<User> findByFilter (User user, Pagination pagination) throws BusinessException {
		logger.info ("Start executing the method findByFilter.");
		List<User> list = new ArrayList<User>();
		try {
			list = userDAO.findByFilter (user, pagination);
			logger.info ("list.size() [" + list.size() + "]");
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while executing the findByFilter method.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findByFilter.");
		return list;
	}
	
	@Override
	public User findById (User user) throws BusinessException {
		logger.info ("Start executing the method findById.");
		User foundUser = null;
		try {
			foundUser = userDAO.findById (user);
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while executing the findById method.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findById.");
		return foundUser;
	}

	@Override
	public User findByUsernameAndPwd (User user) throws BusinessException {
		logger.info ("Start executing the method findByUsernameAndPwd.");
		User foundUser = null;
		try {
			foundUser = userDAO.findByUsernameAndPwd (user);
			if (foundUser.getUSERNAME() != null) {
				logger.info ("foundUser.getUSERNAME() [" + foundUser.getUSERNAME() + "]");
			}
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while executing the findByUsernameAndPwd method.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findByUsernameAndPwd.");
		return foundUser;
	}
	
	public int findRowCount () throws BusinessException {
		logger.info ("Start executing the method findRowCount.");
		int rowCount = 0;
		try {
			rowCount = userDAO.findRowCount();
			logger.info ("rowCount [" + rowCount + "]");
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while executing the findRowCount method.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findRowCount.");
		return rowCount;
	}

}
