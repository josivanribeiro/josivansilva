package com.josivansilva.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.josivansilva.business.entities.Menu;
import com.josivansilva.dao.AbstractDAO;
import com.josivansilva.dao.MenuDAO;
import com.josivansilva.exceptions.DataAccessException;
import com.josivansilva.util.Pagination;
import com.josivansilva.util.Util;

public class MenuDAOImpl extends AbstractDAO<Menu> implements MenuDAO {
	/**
	 * Defines the logger instance property.
	 */
	private static Logger logger = Logger.getLogger (MenuDAOImpl.class);
	/**
	 * Defines the singleton instance property.
	 */
	private static MenuDAO instance;
	
	/**
	 * Default private constructor.
	 */
	private MenuDAOImpl () {}
	
	/**
	 * Default singleton.
	 * 
	 * @return the singleton instance.
	 */
	public static MenuDAO getInstance () {
		if (instance == null) {
			instance = new MenuDAOImpl ();
		}
		return instance;
	}
	

	@Override
	public int create (Menu menu) throws DataAccessException {
		logger.info ("Start executing the method create.");
		int inserts = 0;
		StringBuilder sbSql = null;
		sbSql = new StringBuilder();
		sbSql.append ("INSERT INTO MYDB.MENU (ID_MENU_FATHER, NM_MENU, DT_CREATION, DT_LAST_UPDATE) ");
		sbSql.append ("VALUES (" + menu.getID_MENU_FATHER() + ",");
		sbSql.append ("'" + menu.getNM_MENU() + "',");
		sbSql.append ("NOW(), ");
		sbSql.append ("NOW())");
		inserts = updateDb (sbSql.toString());
		logger.info ("inserts [" + inserts + "]");
		logger.info ("Finish executing the method create.");
		return inserts;
	}

	@Override
	public int update (Menu menu) throws DataAccessException {
		logger.info ("Start executing the method update.");
		int updates = 0;
		StringBuilder sbSql = null;
		String whereClause  = null;
		sbSql = new StringBuilder();
		sbSql.append ("UPDATE MYDB.MENU SET ");
		sbSql.append ("ID_MENU_FATHER=");
		sbSql.append (menu.getID_MENU_FATHER() + ", ");
		sbSql.append ("NM_MENU=");
		sbSql.append ("'" + menu.getNM_MENU() + "', ");
		sbSql.append ("DT_LAST_UPDATE=NOW()");
		whereClause = " WHERE ID_MENU = " + menu.getID_MENU();
		sbSql.append (whereClause);
		updates = updateDb (sbSql.toString());
		logger.info ("updates [" + updates + "]");
		logger.info ("Finish executing the method update.");
		return updates;
	}

	@Override
	public int removeAll (Menu[] menuArr) throws DataAccessException {
		logger.info ("Start executing the method removeAll.");
		String sql         = null;
		String inClause    = "";
		int affectedRows   = 0;
		boolean isLastItem = false;
		int size = menuArr.length - 1;
		for (int i = 0; i < menuArr.length; i++) {
			Menu menu = menuArr [i];
			isLastItem = (i == size) ? true : false;
			if (!isLastItem) {
				inClause += menu.getID_MENU() + ", ";
			} else {
				inClause += menu.getID_MENU();
			}
		}
		sql = "DELETE FROM MYDB.MENU WHERE ID_MENU IN (" + inClause + ")";
		affectedRows = updateDb (sql);
		logger.info ("affectedRows [" + affectedRows + "]");
		logger.info ("Finish executing the method removeAll.");
		return affectedRows;
	}

	@Override
	public List<Menu> findAll() throws DataAccessException {
		logger.info ("Start executing the method findAll.");
		List<Menu> list = new ArrayList<Menu>();
		String sql         = null;
		sql = "SELECT ID_MENU, ID_MENU_FATHER, NM_MENU, DT_CREATION, DT_LAST_UPDATE FROM MYDB.MENU ORDER BY ID_MENU";
		list = selectDb (sql, new Menu());
		logger.info ("list.size() [" + list.size() + "]");
		logger.info ("Finish executing the method findAll.");
		return list;
	}

	@Override
	public List<Menu> findByFilter (Menu menu, Pagination pagination) throws DataAccessException {
		logger.info ("Start executing the method findByFilter.");
		List<Menu> list    = new ArrayList<Menu>();
		String sql         = null;
		String whereClause = "";
		boolean hasFilter  = false;
		if (Util.isNonEmpty (menu.getNM_MENU())) {
			whereClause = "WHERE NM_MENU LIKE '%" + menu.getNM_MENU() + "%' ";
			hasFilter = true;
		}
		if (menu.getID_MENU_FATHER() != null && menu.getID_MENU_FATHER() > 0) {
			if (hasFilter) {
				whereClause += "AND ";
			} else {
				whereClause = "WHERE ";
			}
			whereClause += "ID_MENU_FATHER = " + menu.getID_MENU_FATHER() + " ";
		}
		sql = "SELECT ID_MENU, ID_MENU_FATHER, NM_MENU, DT_CREATION, DT_LAST_UPDATE FROM MYDB.MENU " + whereClause + "ORDER BY ID_MENU LIMIT " + pagination.getLimit();
		list = selectDb (sql, new Menu());
		logger.info ("list.size() [" + list.size() + "]");
		logger.info ("Finish executing the method findByFilter.");
		return list;
	}

	@Override
	public Menu findById (Menu menu) throws DataAccessException {
		logger.info ("Start executing the method findById.");
		Menu foundMenu = null;
		String sql     = null;
		sql = "SELECT ID_MENU, ID_MENU_FATHER, NM_MENU, DT_CREATION, DT_LAST_UPDATE FROM MYDB.MENU WHERE ID_MENU = " + menu.getID_MENU();
		List<Menu> list = selectDb (sql, new Menu());
		if (list != null && !list.isEmpty()) {
			foundMenu = list.get (0);
			logger.info ("foundMenu [" + foundMenu.getNM_MENU() + "]");
		}
		logger.info ("Finish executing the method findById.");
		return foundMenu;
	}
	
	public int findRowCount () throws DataAccessException {
		logger.info ("Start executing the method findRowCount.");
		int rowCount = 0;
		String sql   = null;
		sql = "SELECT COUNT(*) FROM MYDB.MENU";
		rowCount = selectRowCount (sql);
		logger.info ("rowCount [" + rowCount + "]");
		logger.info ("Finish executing the method findRowCount.");
		return rowCount;
	}

}
