package com.josivansilva.business.services;

import java.util.List;

import com.josivansilva.business.entities.Topic;
import com.josivansilva.exceptions.BusinessException;
import com.josivansilva.exceptions.DataAccessException;
import com.josivansilva.util.Pagination;

/**
 * Topic Business Service interface.
 * 
 * @author Josivan Silva
 *
 */
public interface TopicService {

	/**
	* Creates a new topic.
	* 
	* @param topic the new topic.
	* @throws BusinessException 
	*/
	public int create (Topic topic) throws BusinessException;
	
	/**
	 * Updates the new topic.
	 * 
	 * @param topic the new topic.
	 * @throws BusinessException
	 */
	public int update (Topic topic) throws BusinessException;
	
	/**
	 * Removes a topic.
	 * 
	 * @param topic the topic.
	 * @throws BusinessException
	 */
	public int removeAll (Topic[] topicArr) throws BusinessException;
	
	/**
	 * Finds a list of topic given a specific filter.
	 * 
	 * @param topic the topic filter.
	 * @return a list of the filtered topic.
	 * @throws BusinessException
	 */
	public List<Topic> findByFilter (Topic topic, Pagination pagination) throws BusinessException;
	
	/**
	 * Finds all the topics.
	 * 
	 * @return a list of topics.
	 * @throws BusinessException
	 */
	public List<Topic> findAll () throws BusinessException;
	
	/**
	 * Finds a topic given its id.
	 * 
	 * @param topic the topic filter.
	 * @return the specified topic.
	 * @throws BusinessException
	 */
	public Topic findById (Topic topic) throws BusinessException;
	
	/**
	 * Gets the number total of rows from an specific table. 
	 * 
	 * @return the number total of rows.
	 * @throws BusinessException
	 */
	public int findRowCount () throws BusinessException;
	
}
