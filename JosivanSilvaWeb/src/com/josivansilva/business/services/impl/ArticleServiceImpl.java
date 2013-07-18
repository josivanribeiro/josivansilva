package com.josivansilva.business.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.josivansilva.business.entities.Article;
import com.josivansilva.business.services.ArticleService;
import com.josivansilva.dao.ArticleDAO;
import com.josivansilva.dao.impl.ArticleDAOImpl;
import com.josivansilva.exceptions.BusinessException;
import com.josivansilva.exceptions.DataAccessException;
import com.josivansilva.util.Pagination;


public class ArticleServiceImpl implements ArticleService {
	/**
	 * Defines the logger instance property. 
	 */
	private static Logger logger = Logger.getLogger (ArticleServiceImpl.class);
	/**
	 * Defines the singleton instance property.
	 */
	private static ArticleServiceImpl instance;
	
	
	private ArticleDAO articleDAO = ArticleDAOImpl.getInstance();
	
	/**
	 * Default private constructor.
	 */
	private ArticleServiceImpl () {}
	
	/**
	 * Default singleton.
	 * 
	 * @return the singleton instance.
	 */
	public static ArticleService getInstance () {
		if (instance == null) {
			instance = new ArticleServiceImpl ();
		}
		return instance;
	}
	
	@Override
	public int create (Article article) throws BusinessException {
		logger.info ("Start executing the method create.");
		int affectedRows = 0;
		try {
			affectedRows = articleDAO.create (article);
			logger.info ("affectedRows [" + affectedRows + "]");
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while creating an article.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method create.");
		return affectedRows;
	}

	@Override
	public int update(Article article) throws BusinessException {
		logger.info ("Start executing the method update.");
		int affectedRows = 0;
		try {
			affectedRows = articleDAO.update (article);
			logger.info ("affectedRows [" + affectedRows + "]");
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while updating an article.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method update.");
		return affectedRows;
	}

	@Override
	public int removeAll (Article[] articleArr) throws BusinessException {
		logger.info ("Start executing the method removeAll.");
		int affectedRows = 0;
		try {
			affectedRows = articleDAO.removeAll (articleArr);
			logger.info ("affectedRows [" + affectedRows + "]");
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while removing the articles.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method removeAll.");
		return affectedRows;
	}

	@Override
	public List<Article> findByFilter (Article article, Pagination pagination) throws BusinessException {
		logger.info ("Start executing the method findByFilter.");
		List<Article> list = new ArrayList<Article>();
		try {
			list = articleDAO.findByFilter (article, pagination);
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while finding articles by filter.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findByFilter.");
		return list;
	}

	@Override
	public Article findById (Article article) throws BusinessException {
		logger.info ("Start executing the method findById.");
		Article found = null;
		try {
			logger.info ("article.getID_ARTICLE() " + article.getID_ARTICLE());
			found = articleDAO.findById (article);
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while finding a article by id.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findById.");
		return found;
	}

	@Override
	public int findRowCount() throws BusinessException {
		logger.info ("Start executing the method findRowCount.");
		int rowCount = 0;
		try {
			rowCount = articleDAO.findRowCount();
			logger.info ("rowCount [" + rowCount + "]");
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while executing the findRowCount method.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findRowCount.");
		return rowCount;
	}

	@Override
	public int getLastInsertedId() throws BusinessException {
		logger.info ("Start executing the method getLastInsertedId.");
		int idArticle;
		try {
			idArticle = articleDAO.getLastInsertedId();
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while getting the last inserted id.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method getLastInsertedId.");
		return idArticle;
	}	

}
