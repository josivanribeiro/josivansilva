package com.josivansilva.business.services;

import java.util.List;

import com.josivansilva.business.entities.Article;
import com.josivansilva.exceptions.BusinessException;
import com.josivansilva.exceptions.DataAccessException;
import com.josivansilva.util.Pagination;

/**
 * Article Business Service interface.
 * 
 * @author Josivan Silva
 *
 */
public interface ArticleService {

	/**
	 * Creates an article.
	 * 
	 * @param article the article.
	 * @return
	 * @throws BusinessException
	 */
	public int create (Article article) throws BusinessException;
	
	/**
	 * Updates an article.
	 * 
	 * @param article the article.
	 * @return
	 * @throws BusinessException
	 */
	public int update (Article article) throws BusinessException;
	
	/**
	 * Removes all the articles.
	 * 
	 * @param articleArr the article array.
	 * @return
	 * @throws BusinessException
	 */
	public int removeAll (Article[] articleArr) throws BusinessException;
	
	/**
	 * Finds the articles given a filter.
	 * 
	 * @param article the article filter.
	 * @param pagination the pagination.
	 * @return
	 * @throws BusinessException
	 */
	public List<Article> findByFilter (Article article, Pagination pagination) throws BusinessException;
	
	/**
	 * Finds an article by its id.
	 * 
	 * @param article the article.
	 * @return
	 * @throws BusinessException
	 */
	public Article findById (Article article) throws BusinessException;
	
	/**
	 * Gets the row count from a specific table. 
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public int findRowCount() throws BusinessException;
	
	/**
	 * Gets the last inserted id.
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public int getLastInsertedId () throws BusinessException;
	
}
