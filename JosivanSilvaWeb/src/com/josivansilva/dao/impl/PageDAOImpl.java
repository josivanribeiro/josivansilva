package com.josivansilva.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.josivansilva.business.entities.Page;
import com.josivansilva.dao.AbstractDAO;
import com.josivansilva.dao.PageDAO;
import com.josivansilva.exceptions.DataAccessException;
import com.josivansilva.util.Pagination;
import com.josivansilva.util.Util;

public class PageDAOImpl extends AbstractDAO<Page> implements PageDAO {
	/**
	 * Defines the logger instance property.
	 */
	private static Logger logger = Logger.getLogger (PageDAOImpl.class);
	/**
	 * Defines the singleton instance property.
	 */
	private static PageDAO instance;
	
	/**
	 * Default private constructor.
	 */
	private PageDAOImpl () {}
	
	/**
	 * Default singleton.
	 * 
	 * @return the singleton instance.
	 */
	public static PageDAO getInstance () {
		if (instance == null) {
			instance = new PageDAOImpl ();
		}
		return instance;
	}
	
	@Override
	public int create (Page page) throws DataAccessException {
		logger.info ("Start executing the method create.");
		int inserts = 0;
		StringBuilder sbSql = null;
		sbSql = new StringBuilder();
		sbSql.append ("INSERT INTO MYDB.PAGE (ID_MENU, NM_PAGE, CONTENT, URL, DT_CREATION, DT_LAST_UPDATE) ");
		sbSql.append ("VALUES (" + page.getID_MENU() + ", ");
		sbSql.append ("'" + page.getNM_PAGE() + "',");
		sbSql.append ("'" + page.getCONTENT() + "',");
		sbSql.append ("'" + page.getURL() + "',");
		sbSql.append ("NOW(), ");
		sbSql.append ("NOW())");
		inserts = updateDb (sbSql.toString());
		logger.info ("inserts [" + inserts + "]");
		logger.info ("Finish executing the method create.");
		return inserts;
	}

	@Override
	public int update (Page page) throws DataAccessException {
		logger.info ("Start executing the method update.");
		int updates = 0;
		StringBuilder sbSql = null;
		String whereClause  = null;
		sbSql = new StringBuilder();
		sbSql.append ("UPDATE MYDB.PAGE SET ");
		sbSql.append ("ID_MENU=");
		sbSql.append (page.getID_MENU() + ", ");
		sbSql.append ("NM_PAGE=");
		sbSql.append ("'" + page.getNM_PAGE() + "', ");
		sbSql.append ("CONTENT=");
		sbSql.append ("'" + page.getCONTENT() + "', ");
		sbSql.append ("URL=");
		sbSql.append ("'" + page.getURL() + "', ");
		sbSql.append ("DT_LAST_UPDATE=NOW()");
		whereClause = " WHERE ID_PAGE = " + page.getID_PAGE();
		sbSql.append (whereClause);
		updates = updateDb (sbSql.toString());
		logger.info ("updates [" + updates + "]");
		logger.info ("Finish executing the method update.");
		return updates;
	}

	@Override
	public int removeAll (Page[] pageArr) throws DataAccessException {
		logger.info ("Start executing the method removeAll.");
		String sql         = null;
		String inClause    = "";
		int affectedRows   = 0;
		boolean isLastItem = false;
		int size = pageArr.length - 1;
		for (int i = 0; i < pageArr.length; i++) {
			Page page = pageArr [i];
			isLastItem = (i == size) ? true : false;
			if (!isLastItem) {
				inClause += page.getID_PAGE() + ", ";
			} else {
				inClause += page.getID_PAGE();
			}
		}
		sql = "DELETE FROM MYDB.PAGE WHERE ID_PAGE IN (" + inClause + ")";
		affectedRows = updateDb (sql);
		logger.info ("affectedRows [" + affectedRows + "]");
		logger.info ("Finish executing the method removeAll.");
		return affectedRows;
	}

	@Override
	public List<Page> findAll() throws DataAccessException {
		logger.info ("Start executing the method findAll.");
		List<Page> list = new ArrayList<Page>();
		String sql      = null;
		sql = "SELECT ID_PAGE, ID_MENU, NM_PAGE, URL, DT_CREATION, DT_LAST_UPDATE FROM MYDB.PAGE ORDER BY ID_PAGE";
		list = selectDb (sql, new Page());
		logger.info ("list.size() [" + list.size() + "]");
		logger.info ("Finish executing the method findAll.");
		return list;
	}

	@Override
	public List<Page> findByFilter (Page page, Pagination pagination) throws DataAccessException {
		logger.info ("Start executing the method findByFilter.");
		List<Page> list    = new ArrayList<Page>();
		String sql         = null;
		String whereClause = "";
		if (Util.isNonEmpty (page.getNM_PAGE())) {
			whereClause = "WHERE NM_PAGE LIKE '%" + page.getNM_PAGE() + "%' ";			
		}
		sql = "SELECT ID_PAGE, ID_MENU, NM_PAGE, URL, DT_CREATION, DT_LAST_UPDATE FROM MYDB.PAGE " + whereClause + "ORDER BY ID_PAGE LIMIT " + pagination.getLimit();
		list = selectDb (sql, new Page());
		logger.info ("list.size() [" + list.size() + "]");
		logger.info ("Finish executing the method findByFilter.");
		return list;
	}

	@Override
	public Page findById (Page page) throws DataAccessException {
		logger.info ("Start executing the method findById.");
		Page found = null;
		String sql = null;
		sql = "SELECT ID_PAGE, ID_MENU, NM_PAGE, CONTENT, URL, DT_CREATION, DT_LAST_UPDATE FROM MYDB.PAGE WHERE ID_PAGE = " + page.getID_PAGE();
		List<Page> list = selectDb (sql, new Page());
		if (list != null && !list.isEmpty()) {
			found = list.get (0);
			logger.info ("found [" + found.getNM_PAGE() + "]");
		}
		logger.info ("Finish executing the method findById.");
		return found;
	}

	@Override
	public int findRowCount() throws DataAccessException {
		logger.info ("Start executing the method findRowCount.");
		int rowCount = 0;
		String sql   = null;
		sql = "SELECT COUNT(*) FROM MYDB.PAGE";
		rowCount = selectRowCount (sql);
		logger.info ("rowCount [" + rowCount + "]");
		logger.info ("Finish executing the method findRowCount.");
		return rowCount;
	}

}
