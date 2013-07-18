package com.josivansilva.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.josivansilva.business.entities.Topic;
import com.josivansilva.dao.AbstractDAO;
import com.josivansilva.dao.TopicDAO;
import com.josivansilva.exceptions.DataAccessException;
import com.josivansilva.util.Pagination;
import com.josivansilva.util.Util;

public class TopicDAOImpl extends AbstractDAO<Topic> implements TopicDAO {
	/**
	 * Defines the logger instance property.
	 */
	private static Logger logger = Logger.getLogger (TopicDAOImpl.class);
	/**
	 * Defines the singleton instance property.
	 */
	private static TopicDAO instance;
	
	/**
	 * Default private constructor.
	 */
	private TopicDAOImpl () {}
	
	/**
	 * Default singleton.
	 * 
	 * @return the singleton instance.
	 */
	public static TopicDAO getInstance () {
		if (instance == null) {
			instance = new TopicDAOImpl ();
		}
		return instance;
	}

	@Override
	public int create (Topic topic) throws DataAccessException {
		logger.info ("Start executing the method create.");
		int inserts = 0;
		StringBuilder sbSql = null;
		sbSql = new StringBuilder();
		sbSql.append ("INSERT INTO MYDB.TOPIC (ID_SECTION, NM_TOPIC, DT_CREATION, DT_LAST_UPDATE) ");
		sbSql.append ("VALUES (" + topic.getID_SECTION() + ",");
		sbSql.append ("'" + topic.getNM_TOPIC() + "',");
		sbSql.append ("NOW(), ");
		sbSql.append ("NOW())");
		inserts = updateDb (sbSql.toString());
		logger.info ("inserts [" + inserts + "]");
		logger.info ("Finish executing the method create.");
		return inserts;
	}

	@Override
	public int update (Topic topic) throws DataAccessException {
		logger.info ("Start executing the method update.");
		int updates = 0;
		StringBuilder sbSql = null;
		String whereClause  = null;
		sbSql = new StringBuilder();
		sbSql.append ("UPDATE MYDB.TOPIC SET ");
		sbSql.append ("ID_SECTION=");
		sbSql.append (topic.getID_SECTION() + ", ");
		sbSql.append ("NM_TOPIC=");
		sbSql.append ("'" + topic.getNM_TOPIC() + "', ");
		sbSql.append ("DT_LAST_UPDATE=NOW()");
		whereClause = " WHERE ID_TOPIC = " + topic.getID_TOPIC();
		sbSql.append (whereClause);
		
		logger.info("\n\n\nsbSql.toString()" + sbSql.toString() + "\n\n\n");
		
		updates = updateDb (sbSql.toString());
		logger.info ("updates [" + updates + "]");
		logger.info ("Finish executing the method update.");
		return updates;
	}

	@Override
	public int removeAll (Topic[] topicArr) throws DataAccessException {
		logger.info ("Start executing the method removeAll.");
		String sql         = null;
		String inClause    = "";
		int affectedRows   = 0;
		boolean isLastItem = false;
		int size = topicArr.length - 1;
		for (int i = 0; i < topicArr.length; i++) {
			Topic topic = topicArr [i];
			isLastItem = (i == size) ? true : false;
			if (!isLastItem) {
				inClause += topic.getID_TOPIC() + ", ";
			} else {
				inClause += topic.getID_TOPIC();
			}
		}
		sql = "DELETE FROM MYDB.TOPIC WHERE ID_TOPIC IN (" + inClause + ")";
		affectedRows = updateDb (sql);
		logger.info ("affectedRows [" + affectedRows + "]");
		logger.info ("Finish executing the method removeAll.");
		return affectedRows;
	}

	@Override
	public List<Topic> findByFilter (Topic topic, Pagination pagination) throws DataAccessException {
		logger.info ("Start executing the method findByFilter.");
		List<Topic> list   = new ArrayList<Topic>();
		String sql         = null;
		String whereClause = "";
		boolean hasFilter  = false;
		if (Util.isNonEmpty (topic.getNM_TOPIC())) {
			whereClause = "WHERE NM_TOPIC LIKE '%" + topic.getNM_TOPIC() + "%' ";
			hasFilter = true;
		}
		if (topic.getID_SECTION() != null && topic.getID_SECTION() > 0) {
			if (hasFilter) {
				whereClause += "AND ";
			} else {
				whereClause = "WHERE ";
			}
			whereClause += "ID_SECTION = " + topic.getID_SECTION() + " ";
		}
		sql = "SELECT ID_TOPIC, ID_SECTION, NM_TOPIC, DT_CREATION, DT_LAST_UPDATE FROM MYDB.TOPIC " + whereClause + "ORDER BY ID_TOPIC LIMIT " + pagination.getLimit();
		list = selectDb (sql, new Topic());
		logger.info ("list.size() [" + list.size() + "]");
		logger.info ("Finish executing the method findByFilter.");
		return list;
	}

	@Override
	public Topic findById (Topic topic) throws DataAccessException {
		logger.info ("Start executing the method findById.");
		Topic foundTopic = null;
		String sql       = null;
		sql = "SELECT ID_TOPIC, ID_SECTION, NM_TOPIC, DT_CREATION, DT_LAST_UPDATE FROM MYDB.TOPIC WHERE ID_TOPIC = " + topic.getID_TOPIC();
		List<Topic> list = selectDb (sql, new Topic());
		if (list != null && !list.isEmpty()) {
			foundTopic = list.get (0);
			logger.info ("foundSection [" + foundTopic.getNM_TOPIC() + "]");
		}
		logger.info ("Finish executing the method findById.");
		return foundTopic;
	}
	
	public List<Topic> findAll () throws DataAccessException {
		logger.info ("Start executing the method findAll.");
		List<Topic> list   = new ArrayList<Topic>();
		String sql         = null;
		sql = "SELECT ID_TOPIC, ID_SECTION, NM_TOPIC, DT_CREATION, DT_LAST_UPDATE FROM MYDB.TOPIC ORDER BY ID_TOPIC";
		list = selectDb (sql, new Topic());
		logger.info ("list.size() [" + list.size() + "]");
		logger.info ("Finish executing the method findAll.");
		return list;
	}
	
	public int findRowCount () throws DataAccessException {
		logger.info ("Start executing the method findRowCount.");
		int rowCount = 0;
		String sql   = null;
		sql = "SELECT COUNT(*) FROM MYDB.TOPIC";
		rowCount = selectRowCount (sql);
		logger.info ("rowCount [" + rowCount + "]");
		logger.info ("Finish executing the method findRowCount.");
		return rowCount;
	}

}
