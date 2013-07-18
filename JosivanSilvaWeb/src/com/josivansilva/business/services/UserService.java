package com.josivansilva.business.services;

import java.util.List;

import com.josivansilva.business.entities.User;
import com.josivansilva.exceptions.BusinessException;
import com.josivansilva.util.Pagination;

/**
 * User Service interface.
 * 
 * @author Josivan Silva
 *
 */
public interface UserService {

	/**
	* Creates a new user.
	* 
	* @param user the new user.
	* @throws BusinessException 
	*/
	
	
	/**
	 * Creates a new user.
	 * 
	 * @param user the new user.
	 * @return the number of executed inserts/updates.
	 * @throws BusinessException
	 */
	public int create (User user) throws BusinessException;
	
	/**
	 * Updates the user.
	 * 
	 * @param user the user.
	 * @throws BusinessException
	 */
	public int update (User user) throws BusinessException;	
	
	
	/**
	 * Removes all the specified users.
	 * @param userArr the user array.
	 * @return the number of affected rows.
	 * @throws BusinessException
	 */
	public int removeAll (User[] userArr) throws BusinessException;
	
	/**
	 * Finds an user given given an username and password.
	 * 
	 * @param user the user
	 * @return an user new instance
	 * @throws BusinessException
	 */
	public User findByUsernameAndPwd (User user) throws BusinessException;
	
	/**
	 * Finds all the users given the specified filter.
	 * 
	 * @param user the user.
	 * @param pagination the pagination.
	 * @return a list of users.
	 * @throws BusinessException
	 */
	public List<User> findByFilter (User user, Pagination pagination) throws BusinessException;
	
	/**
	 * Finds an user given its id.
	 * 
	 * @param user the user containing the user id.
	 * @return an user object.
	 * @throws BusinessException
	 */
	public User findById (User user) throws BusinessException;
	
	/**
	 * Performs the user login.
	 * 
	 * @param user the user.
	 * @throws BusinessException
	 */
	public boolean doLogin (User user) throws BusinessException;
	
	/**
	 * Gets the number total of rows from an specific table. 
	 * 
	 * @return the number total of rows.
	 * @throws BusinessException
	 */
	public int findRowCount () throws BusinessException;
	
}
