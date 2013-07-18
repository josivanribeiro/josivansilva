package com.josivansilva.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.josivansilva.business.entities.ArticleTopic;
import com.josivansilva.dao.AbstractDAO;
import com.josivansilva.dao.ArticleTopicDAO;
import com.josivansilva.exceptions.DataAccessException;

public class ArticleTopicDAOImpl extends AbstractDAO<ArticleTopic> implements ArticleTopicDAO {
	/**
	 * Defines the logger instance property.
	 */
	private static Logger logger = Logger.getLogger (ArticleTopicDAOImpl.class);
	/**
	 * Defines the singleton instance property.
	 */
	private static ArticleTopicDAO instance;
	
	/**
	 * Default private constructor.
	 */
	private ArticleTopicDAOImpl () {}
	
	/**
	 * Default singleton.
	 * 
	 * @return the singleton instance.
	 */
	public static ArticleTopicDAO getInstance () {
		if (instance == null) {
			instance = new ArticleTopicDAOImpl ();
		}
		return instance;
	}	
	
	@Override
	public int create (ArticleTopic articleTopic) throws DataAccessException {
		logger.info ("Start executing the method create.");
		int inserts = 0;
		StringBuilder sbSql = null;
		sbSql = new StringBuilder();
		sbSql.append ("INSERT INTO MYDB.ARTICLE_TOPIC (ID_ARTICLE, ID_TOPIC) ");
		sbSql.append ("VALUES (" + articleTopic.getID_ARTICLE() + ", ");
		sbSql.append (articleTopic.getID_TOPIC() + ")");
		inserts = updateDb (sbSql.toString());
		logger.info ("inserts [" + inserts + "]");
		logger.info ("Finish executing the method create.");
		return inserts;
		
	}

	@Override
	public int remove (ArticleTopic articleTopic) throws DataAccessException {
		logger.info ("Start executing the method removeAll.");
		String sql         = null;
		int affectedRows   = 0;
		sql = "DELETE FROM MYDB.ARTICLE_TOPIC WHERE ID_ARTICLE = " + articleTopic.getID_ARTICLE() + "";
		affectedRows = updateDb (sql);
		logger.info ("affectedRows [" + affectedRows + "]");
		logger.info ("Finish executing the method removeAll.");
		return affectedRows;
		
	}

	@Override
	public List<ArticleTopic> findByArticle (ArticleTopic articleTopic) throws DataAccessException {
		logger.info ("Start executing the method findByArticle.");
		List<ArticleTopic> list = new ArrayList<ArticleTopic>();
		String sql         = null;
		sql = "SELECT ID_ARTICLE, ID_TOPIC FROM MYDB.ARTICLE_TOPIC WHERE ID_ARTICLE = " + articleTopic.getID_ARTICLE() + " ORDER BY ID_TOPIC";
		list = selectDb (sql, new ArticleTopic());
		logger.info ("list.size() [" + list.size() + "]");
		logger.info ("Finish executing the method findByArticle.");
		return list;
	}	

}
