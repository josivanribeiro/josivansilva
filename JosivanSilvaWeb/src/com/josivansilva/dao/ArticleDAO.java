package com.josivansilva.dao;

import java.util.List;

import com.josivansilva.business.entities.Article;
import com.josivansilva.exceptions.DataAccessException;
import com.josivansilva.util.Pagination;

/**
 * Article DAO interface.
 * 
 * @author Josivan Silva
 *
 */
public interface ArticleDAO {

	/**
	 * Creates an article.
	 * 
	 * @param article the article.
	 * @return
	 * @throws DataAccessException
	 */
	public int create (Article article) throws DataAccessException;
	
	/**
	 * Updates an article.
	 * 
	 * @param article the article.
	 * @return
	 * @throws DataAccessException
	 */
	public int update (Article article) throws DataAccessException;
	
	/**
	 * Removes all the articles.
	 * 
	 * @param articleArr the article array.
	 * @return
	 * @throws DataAccessException
	 */
	public int removeAll (Article[] articleArr) throws DataAccessException;
	
	/**
	 * Finds the articles given a filter.
	 * 
	 * @param article the article filter.
	 * @param pagination the pagination.
	 * @return
	 * @throws DataAccessException
	 */
	public List<Article> findByFilter (Article article, Pagination pagination) throws DataAccessException;
	
	/**
	 * Finds an article by its id.
	 * 
	 * @param article the article.
	 * @return
	 * @throws DataAccessException
	 */
	public Article findById (Article article) throws DataAccessException;
	
	/**
	 * Gets the row count from a specific table. 
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public int findRowCount() throws DataAccessException;
	
	/**
	 * Gets the last inserted id.
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public int getLastInsertedId () throws DataAccessException;
	
}
