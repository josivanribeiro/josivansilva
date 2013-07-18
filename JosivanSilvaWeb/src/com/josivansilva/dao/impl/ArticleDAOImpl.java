package com.josivansilva.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.josivansilva.business.entities.Article;
import com.josivansilva.dao.AbstractDAO;
import com.josivansilva.dao.ArticleDAO;
import com.josivansilva.exceptions.DataAccessException;
import com.josivansilva.util.Pagination;
import com.josivansilva.util.Util;

public class ArticleDAOImpl extends AbstractDAO<Article> implements ArticleDAO {
	/**
	 * Defines the logger instance property.
	 */
	private static Logger logger = Logger.getLogger (ArticleDAOImpl.class);
	/**
	 * Defines the singleton instance property.
	 */
	private static ArticleDAO instance;
	
	/**
	 * Default private constructor.
	 */
	private ArticleDAOImpl () {}
	
	/**
	 * Default singleton.
	 * 
	 * @return the singleton instance.
	 */
	public static ArticleDAO getInstance () {
		if (instance == null) {
			instance = new ArticleDAOImpl ();
		}
		return instance;
	}
	
	@Override
	public int create (Article article) throws DataAccessException {
		logger.info ("Start executing the method create.");
		int inserts = 0;
		StringBuilder sbSql = null;
		sbSql = new StringBuilder();
		sbSql.append ("INSERT INTO MYDB.ARTICLE (ID_SECTION, TITLE, NM_AUTHOR, CONTENT, DT_CREATION, DT_LAST_UPDATE) ");
		sbSql.append ("VALUES (" + article.getID_SECTION() + ", ");
		sbSql.append ("'" + article.getTITLE() + "',");
		sbSql.append ("'" + article.getNM_AUTHOR() + "',");
		sbSql.append ("'" + article.getCONTENT() + "',");
		sbSql.append ("NOW(), ");
		sbSql.append ("NOW())");
		inserts = updateDb (sbSql.toString());
		logger.info ("inserts [" + inserts + "]");
		logger.info ("Finish executing the method create.");
		return inserts;
	}

	@Override
	public int update (Article article) throws DataAccessException {
		logger.info ("Start executing the method update.");
		int updates = 0;
		StringBuilder sbSql = null;
		String whereClause  = null;
		sbSql = new StringBuilder();
		sbSql.append ("UPDATE MYDB.ARTICLE SET ");
		sbSql.append ("ID_SECTION=");
		sbSql.append (article.getID_SECTION() + ", ");
		sbSql.append ("TITLE=");
		sbSql.append ("'" + article.getTITLE() + "', ");
		sbSql.append ("CONTENT=");
		sbSql.append ("'" + article.getCONTENT() + "', ");
		sbSql.append ("NM_AUTHOR=");
		sbSql.append ("'" + article.getNM_AUTHOR() + "', ");
		sbSql.append ("DT_LAST_UPDATE=NOW()");
		whereClause = " WHERE ID_ARTICLE = " + article.getID_ARTICLE();
		sbSql.append (whereClause);
		updates = updateDb (sbSql.toString());
		logger.info ("updates [" + updates + "]");
		logger.info ("Finish executing the method update.");
		return updates;
	}

	@Override
	public int removeAll (Article[] articleArr) throws DataAccessException {
		logger.info ("Start executing the method removeAll.");
		String sql         = null;
		String inClause    = "";
		int affectedRows   = 0;
		boolean isLastItem = false;
		int size = articleArr.length - 1;
		for (int i = 0; i < articleArr.length; i++) {
			Article article = articleArr [i];
			isLastItem = (i == size) ? true : false;
			if (!isLastItem) {
				inClause += article.getID_ARTICLE() + ", ";
			} else {
				inClause += article.getID_ARTICLE();
			}
		}
		sql = "DELETE FROM MYDB.ARTICLE WHERE ID_ARTICLE IN (" + inClause + ")";
		affectedRows = updateDb (sql);
		logger.info ("affectedRows [" + affectedRows + "]");
		logger.info ("Finish executing the method removeAll.");
		return affectedRows;
	}

	@Override
	public List<Article> findByFilter (Article article, Pagination pagination) throws DataAccessException {
		logger.info ("Start executing the method findByFilter.");
		List<Article> list = new ArrayList<Article>();
		String sql         = null;
		String whereClause = "";
		if (Util.isNonEmpty (article.getTITLE())) {
			whereClause = "WHERE TITLE LIKE '%" + article.getTITLE() + "%' ";			
		}
		sql = "SELECT ID_ARTICLE, ID_SECTION, TITLE, CONTENT, NM_AUTHOR, DT_CREATION, DT_LAST_UPDATE FROM MYDB.ARTICLE " + whereClause + "ORDER BY ID_ARTICLE LIMIT " + pagination.getLimit();
		list = selectDb (sql, new Article());
		logger.info ("list.size() [" + list.size() + "]");
		logger.info ("Finish executing the method findByFilter.");
		return list;
	}

	@Override
	public Article findById (Article article) throws DataAccessException {
		logger.info ("Start executing the method findById.");
		Article found = null;
		String sql = null;
		sql = "SELECT ID_ARTICLE, ID_SECTION, TITLE, CONTENT, NM_AUTHOR, DT_CREATION, DT_LAST_UPDATE FROM MYDB.ARTICLE WHERE ID_ARTICLE = " + article.getID_ARTICLE();
		List<Article> list = selectDb (sql, new Article());
		if (list != null && !list.isEmpty()) {
			found = list.get (0);
			logger.info ("found [" + found.getTITLE() + "]");
		}
		logger.info ("Finish executing the method findById.");
		return found;
	}
	
	@Override
	public int findRowCount() throws DataAccessException {
		logger.info ("Start executing the method findRowCount.");
		int rowCount = 0;
		String sql   = null;
		sql = "SELECT COUNT(*) FROM MYDB.ARTICLE";
		rowCount = selectRowCount (sql);
		logger.info ("rowCount [" + rowCount + "]");
		logger.info ("Finish executing the method findRowCount.");
		return rowCount;
	}

	@Override
	public int getLastInsertedId() throws DataAccessException {
		logger.info ("Start executing the method getLastInsertedId.");
		Article found = null;
		String sql = null;
		sql = "SELECT ID_ARTICLE FROM MYDB.ARTICLE ORDER BY ID_ARTICLE DESC LIMIT 1";
		List<Article> list = selectDb (sql, new Article());
		if (list != null && !list.isEmpty()) {
			found = list.get (0);
			logger.info ("found [" + found.getID_ARTICLE() + "]");
		}
		logger.info ("Finish executing the method getLastInsertedId.");
		return found.getID_ARTICLE();
		
		
	}

}
