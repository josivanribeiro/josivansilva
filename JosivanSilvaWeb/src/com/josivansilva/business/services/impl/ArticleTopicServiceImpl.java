package com.josivansilva.business.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.josivansilva.business.entities.ArticleTopic;
import com.josivansilva.business.services.ArticleTopicService;
import com.josivansilva.dao.ArticleTopicDAO;
import com.josivansilva.dao.impl.ArticleTopicDAOImpl;
import com.josivansilva.exceptions.BusinessException;
import com.josivansilva.exceptions.DataAccessException;

public class ArticleTopicServiceImpl implements ArticleTopicService {
	/**
	 * Defines the logger instance property. 
	 */
	private static Logger logger = Logger.getLogger (ArticleTopicServiceImpl.class);
	/**
	 * Defines the singleton instance property.
	 */
	private static ArticleTopicServiceImpl instance;
	
	
	private ArticleTopicDAO articleTopicDAO = ArticleTopicDAOImpl.getInstance();
	
	/**
	 * Default private constructor.
	 */
	private ArticleTopicServiceImpl() {}
	
	/**
	 * Default singleton.
	 * 
	 * @return the singleton instance.
	 */
	public static ArticleTopicService getInstance () {
		if (instance == null) {
			instance = new ArticleTopicServiceImpl ();
		}
		return instance;
	}
	
	
	@Override
	public void create (ArticleTopic[] articleTopicArr) throws BusinessException {
		logger.info ("Start executing the method create.");
		try {
			for (ArticleTopic articleTopic : articleTopicArr) {
				articleTopicDAO.create (articleTopic);
			}
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while creating an article topic.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method create.");
	}

	@Override
	public void remove (ArticleTopic[] articleTopicArr) throws BusinessException {
		logger.info ("Start executing the method remove.");
		try {
			for (ArticleTopic articleTopic : articleTopicArr) {
				articleTopicDAO.remove (articleTopic);
			}
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while removing an article topic.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method remove.");
	}

	@Override
	public List<ArticleTopic> findByArticle (ArticleTopic articleTopic) throws BusinessException {
		logger.info ("Start executing the method findByArticle.");
		List<ArticleTopic> list = new ArrayList<ArticleTopic>();
		try {
			list = articleTopicDAO.findByArticle (articleTopic);
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while finding articles topic by article.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findByArticle.");
		return list;
	}
	

}
