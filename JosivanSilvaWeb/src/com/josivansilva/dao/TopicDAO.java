package com.josivansilva.dao;

import java.util.List;

import com.josivansilva.business.entities.Topic;
import com.josivansilva.exceptions.DataAccessException;
import com.josivansilva.util.Pagination;

/**
 * Topic DAO interface.
 * 
 * @author Josivan Silva
 *
 */
public interface TopicDAO {

	/**
	* Creates a new topic.
	* 
	* @param topic the new topic.
	* @throws DataAccessException 
	*/
	public int create (Topic topic) throws DataAccessException;
	
	/**
	 * Updates the new topic.
	 * 
	 * @param topic the new topic.
	 * @throws DataAccessException
	 */
	public int update (Topic topic) throws DataAccessException;
	
	/**
	 * Removes a topic.
	 * 
	 * @param topic the topic.
	 * @throws DataAccessException
	 */
	public int removeAll (Topic[] topicArr) throws DataAccessException;
		
	/**
	 * Finds all the topics.
	 * 
	 * @return a list of topics.
	 * @throws DataAccessException
	 */
	public List<Topic> findAll () throws DataAccessException;	
	
	/**
	 * Finds a list of topic given a specific filter.
	 * 
	 * @param topic the topic filter.
	 * @return a list of the filtered topic.
	 * @throws DataAccessException
	 */
	public List<Topic> findByFilter (Topic topic, Pagination pagination) throws DataAccessException;
	
	/**
	 * Finds a topic given its id.
	 * 
	 * @param topic the topic filter.
	 * @return the specified topic.
	 * @throws DataAccessException
	 */
	public Topic findById (Topic topic) throws DataAccessException;
	
	/**
	 * Gets the number total of rows from an specific table. 
	 * 
	 * @return the number total of rows.
	 * @throws DataAccessException
	 */
	public int findRowCount () throws DataAccessException;
	
}
