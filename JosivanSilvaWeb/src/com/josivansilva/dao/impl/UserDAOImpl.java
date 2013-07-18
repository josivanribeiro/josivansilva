package com.josivansilva.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.josivansilva.business.entities.User;
import com.josivansilva.dao.AbstractDAO;
import com.josivansilva.dao.UserDAO;
import com.josivansilva.exceptions.DataAccessException;
import com.josivansilva.util.Pagination;
import com.josivansilva.util.Util;

public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {
	/**
	 * Defines the logger instance property.
	 */
	private static Logger logger = Logger.getLogger (UserDAOImpl.class);
	/**
	 * Defines the singleton instance property.
	 */
	private static UserDAO instance;
	
	/**
	 * Default private constructor.
	 */
	private UserDAOImpl () {}
	
	/**
	 * Default singleton.
	 * 
	 * @return the singleton instance.
	 */
	public static UserDAO getInstance () {
		if (instance == null) {
			instance = new UserDAOImpl ();
		}
		return instance;
	}
	
	@Override
	public int create (User user) throws DataAccessException {
		logger.info ("Start executing the method create.");
		int inserts = 0;
		StringBuilder sbSql = null;
		sbSql = new StringBuilder();
		sbSql.append ("INSERT INTO MYDB.USER (USERNAME, PWD, EMAIL, DT_CREATION, DT_LAST_UPDATE, ROLE_ADMIN) ");
		sbSql.append ("VALUES ('" + user.getUSERNAME() + "',");
		sbSql.append ("MD5('" + user.getPWD() + "'),");
		sbSql.append ("'" + user.getEMAIL() + "', ");
		sbSql.append ("NOW(), ");
		sbSql.append ("NOW(), ");
		sbSql.append (user.isROLE_ADMIN() + ")");
		inserts = updateDb (sbSql.toString());
		logger.info ("inserts [" + inserts + "]");
		logger.info ("Finish executing the method create.");
		return inserts;
	}

	@Override
	public int update (User user) throws DataAccessException {
		logger.info ("Start executing the method update.");
		int updates = 0;
		StringBuilder sbSql = null;
		String whereClause  = null;
		sbSql = new StringBuilder();
		sbSql.append ("UPDATE MYDB.USER SET "); 
		sbSql.append ("USERNAME=");
		sbSql.append ("'" + user.getUSERNAME() + "', ");
		sbSql.append ("PWD=");
		sbSql.append ("MD5('" + user.getPWD() + "'),");
		sbSql.append ("EMAIL=");
		sbSql.append ("'" + user.getEMAIL() + "', ");
		sbSql.append ("DT_LAST_UPDATE=NOW(),");
		sbSql.append ("ROLE_ADMIN=");
		sbSql.append (user.isROLE_ADMIN());
		whereClause = " WHERE ID_USER = " + user.getID_USER();
		sbSql.append (whereClause);
		updates = updateDb (sbSql.toString());
		logger.info ("updates [" + updates + "]");
		logger.info ("Finish executing the method update.");
		return updates;
	}
	
	@Override
	public int removeAll (User[] userArr) throws DataAccessException {
		logger.info ("Start executing the method removeAll.");
		String sql         = null;
		String inClause    = "";
		int affectedRows   = 0;
		boolean isLastItem = false;
		int size = userArr.length - 1;
		for (int i = 0; i < userArr.length; i++) {
			User user = userArr [i];
			isLastItem = (i == size) ? true : false;
			if (!isLastItem) {
				inClause += user.getID_USER() + ", ";
			} else {
				inClause += user.getID_USER();
			}
		}
		sql = "DELETE FROM MYDB.USER WHERE ID_USER IN (" + inClause + ")";
		affectedRows = updateDb (sql);
		logger.info ("affectedRows [" + affectedRows + "]");
		logger.info ("Finish executing the method removeAll.");
		return affectedRows;
	}

	@Override
	public boolean doLogin (User user) throws DataAccessException {
		logger.info ("Start executing the method doLogin.");
		boolean isValid = false;
		String sql      = null;
		int count       = 0;
		sql = "SELECT COUNT(*) FROM USER WHERE USERNAME = '" + user.getUSERNAME() + "' AND PWD = MD5('" + user.getPWD() + "')";
		count = selectRowCount (sql);
		isValid = count > 0 ? true : false;
		logger.info ("isValid [" + isValid + "]");
		logger.info ("Finish executing the method doLogin.");
		return isValid;
	}
	
	@Override
	public User findByUsernameAndPwd (User user) throws DataAccessException {
		logger.info ("Start executing the method findByUsernameAndPwd.");
		User foundUser      = null;
		List<User> userList = new ArrayList<User>();
		String sql     = null;
		sql = "SELECT ID_USER, USERNAME, EMAIL, DT_CREATION, DT_LAST_UPDATE, ROLE_ADMIN " +
			  "FROM MYDB.USER WHERE USERNAME = '" + user.getUSERNAME() + "' AND PWD = MD5('" + user.getPWD() + "')";
		userList = selectDb (sql, new User());
		if (!userList.isEmpty()) {
			foundUser = userList.get (0);
			logger.info ("foundUser.getUSERNAME() [" + foundUser.getUSERNAME() + "]");
		}
		logger.info ("Finish executing the method findByUsernameAndPwd.");
		return foundUser;
	}
	
	@Override
	public List<User> findByFilter (User user, Pagination pagination) throws DataAccessException {
		logger.info ("Start executing the method findByFilter.");
		List<User> list    = new ArrayList<User>();
		String sql         = null;
		String whereClause = "";
		boolean hasFilter  = false;
		if (Util.isNonEmpty (user.getUSERNAME())) {
			whereClause = "WHERE USERNAME LIKE '%" + user.getUSERNAME() + "%' ";
			hasFilter = true;
		}
		if (user.isROLE_ADMIN()) {
			Integer adminRole = user.isROLE_ADMIN() ? 1 : 0;
			if (hasFilter) {
				whereClause += "AND ";
			} else {
				whereClause = "WHERE ";
			}
			whereClause += "ROLE_ADMIN = " + adminRole + " ";
		}
		sql = "SELECT ID_USER, USERNAME, EMAIL, DT_CREATION, DT_LAST_UPDATE, ROLE_ADMIN FROM MYDB.USER " + whereClause + "ORDER BY ID_USER LIMIT " + pagination.getLimit();
		list = selectDb (sql, new User());
		logger.info ("list.size() [" + list.size() + "]");
		logger.info ("Finish executing the method findByFilter.");
		return list;
	}
	
	public int findRowCount () throws DataAccessException {
		logger.info ("Start executing the method findRowCount.");
		int rowCount = 0;
		String sql   = null;
		sql = "SELECT COUNT(*) FROM MYDB.USER";
		rowCount = selectRowCount (sql);
		logger.info ("rowCount [" + rowCount + "]");
		logger.info ("Finish executing the method findRowCount.");
		return rowCount;
	}

	@Override
	public User findById (User user) throws DataAccessException {
		logger.info ("Start executing the method findById.");
		User foundUser  = null;
		String sql      = null;
		sql = "SELECT ID_USER, USERNAME, EMAIL, DT_CREATION, DT_LAST_UPDATE, ROLE_ADMIN FROM MYDB.USER WHERE ID_USER = " + user.getID_USER();
		List<User> list = selectDb (sql, new User());
		if (list != null && !list.isEmpty()) {
			foundUser = list.get (0);
			logger.info ("foundUser [" + foundUser.getUSERNAME() + "]");
		}
		logger.info ("Finish executing the method findById.");
		return foundUser;
	}

}
