package com.josivansilva.dao;

import java.util.List;

import com.josivansilva.exceptions.DataAccessException;

/**
 * Interface that defines the default operations for the database.
 * 
 * @author Josivan Silva
 * @param <T>
 *
 */
public interface DAOOperations<T> {
	
	
	/**
	 * Executes the SQL insert, update or delete statements and return the
	 * number of affected rows.
	 * @param sql the SQL statement
	 * @return the number of executed inserts/deletes/updates
	 * @throws DataAccessException
	 */
	public int updateDb (final String sql) throws DataAccessException;
	
	/**
	 * Gets the row count number given the sql query.
	 * 
	 * @param sql the sql query.
	 * @return the row count number.
	 * @throws DataAccessException
	 */ 
	public int selectRowCount (final String sql) throws DataAccessException;
	
	/**
	 * Gets a list of beans given a sql query.
	 * 
	 * @param sql the sql query.
	 * @return a list of beans.
	 * @throws DataAccessException
	 */
	@SuppressWarnings("hiding")
	public <T> List<T> selectDb (final String sql, T bean) throws DataAccessException;
	

}
