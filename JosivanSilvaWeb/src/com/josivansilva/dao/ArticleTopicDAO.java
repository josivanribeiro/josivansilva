package com.josivansilva.dao;

import java.util.List;

import com.josivansilva.business.entities.ArticleTopic;
import com.josivansilva.exceptions.DataAccessException;

/**
 * Article Topic DAO interface.
 * 
 * @author Josivan Silva
 *
 */
public interface ArticleTopicDAO {

	/**
	* Creates a new article topic.
	* 
	* @param articleTopic the new article topic.
	* @throws DataAccessException 
	*/
	public int create (ArticleTopic articleTopic) throws DataAccessException;
	
	/**
	 * Removes an article topic.
	 * 
	 * @param articleTopic the article topic.
	 * @throws DataAccessException
	 */
	public int remove (ArticleTopic articleTopic) throws DataAccessException;
	
	/**
	 * Finds the article topics given the article.
	 * 
	 * @param articleTopic
	 * @return
	 * @throws DataAccessException
	 */
	public List<ArticleTopic> findByArticle (ArticleTopic articleTopic) throws DataAccessException;
	
}
