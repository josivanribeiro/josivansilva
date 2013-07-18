package com.josivansilva.dao;

import java.util.List;

import com.josivansilva.business.entities.User;
import com.josivansilva.exceptions.DataAccessException;
import com.josivansilva.util.Pagination;


/**
 * User DAO interface.
 * 
 * @author Josivan Silva
 *
 */
public interface UserDAO {

	/**
	 * Creates a new user.
	 * 
	 * @param user the new user.
	 * @return the number of executed inserts/updates.
	 * @throws DataAccessException
	 */
	public int create (User user) throws DataAccessException;
	
	/**
	 * Updates the user.
	 * 
	 * @param user the user.
	 * @throws DataAccessException
	 */
	public int update (User user) throws DataAccessException;
	
	/**
	 * Removes all the specified users.
	 * 
	 * @param userArr the array of users.
	 * @return the number of affected rows.
	 */
	public int removeAll (User[] userArr) throws DataAccessException;
	
	/**
	 * Performs the user login.
	 * 
	 * @param user the user.
	 * @throws DataAccessException
	 */
	public boolean doLogin (User user) throws DataAccessException;
	
	/**
	 * Finds an user given given an username and password.
	 * 
	 * @param user the user
	 * @return an user new instance
	 * @throws DataAccessException
	 */
	public User findByUsernameAndPwd (User user) throws DataAccessException;
	
	
	/**
	 * Finds all the users given the specified filter.
	 * 
	 * @param user the user filter
	 * @param pagination the pagination instance.
	 * @return a list of users.
	 * @throws DataAccessException
	 */
	public List<User> findByFilter (User user, Pagination pagination) throws DataAccessException;
	
	/**
	 * Finds an user given its id.
	 * 
	 * @param user the user containing the user id.
	 * @return an user object.
	 * @throws DataAccessException
	 */
	public User findById (User user) throws DataAccessException;
	
	/**
	 * Gets the number total of rows from an specific table. 
	 * 
	 * @return the number total of rows.
	 * @throws DataAccessException
	 */
	public int findRowCount () throws DataAccessException;
	
}
