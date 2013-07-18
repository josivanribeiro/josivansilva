package com.josivansilva.business.services;

import java.util.List;

import com.josivansilva.business.entities.ArticleTopic;
import com.josivansilva.exceptions.BusinessException;
import com.josivansilva.exceptions.DataAccessException;

public interface ArticleTopicService {
	
	/**
	 * Creates a new article topic.
	 * 
	 * @param articleTopicArr
	 * @return
	 * @throws BusinessException
	 */
	public void create (ArticleTopic[] articleTopicArr) throws BusinessException;
	
	
	/**
	 * Removes an article topic given an array.
	 * 
	 * @param articleTopicArr
	 * @return
	 * @throws BusinessException
	 */
	public void remove (ArticleTopic[] articleTopicArr) throws BusinessException;
	
	/**
	 * Finds the article topics given the article.
	 * 
	 * @param articleTopic
	 * @return
	 * @throws DataAccessException
	 */
	public List<ArticleTopic> findByArticle (ArticleTopic articleTopic) throws BusinessException;
	
}
