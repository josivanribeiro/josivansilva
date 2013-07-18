package com.josivansilva.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.josivansilva.business.entities.Section;
import com.josivansilva.dao.AbstractDAO;
import com.josivansilva.dao.SectionDAO;
import com.josivansilva.exceptions.DataAccessException;
import com.josivansilva.util.Pagination;
import com.josivansilva.util.Util;

public class SectionDAOImpl extends AbstractDAO<Section> implements SectionDAO {
	/**
	 * Defines the logger instance property.
	 */
	private static Logger logger = Logger.getLogger (SectionDAOImpl.class);
	/**
	 * Defines the singleton instance property.
	 */
	private static SectionDAO instance;
	
	/**
	 * Default private constructor.
	 */
	private SectionDAOImpl () {}
	
	/**
	 * Default singleton.
	 * 
	 * @return the singleton instance.
	 */
	public static SectionDAO getInstance () {
		if (instance == null) {
			instance = new SectionDAOImpl ();
		}
		return instance;
	}

	@Override
	public int create (Section section) throws DataAccessException {
		logger.info ("Start executing the method create.");
		int inserts = 0;
		StringBuilder sbSql = null;
		sbSql = new StringBuilder();
		sbSql.append ("INSERT INTO MYDB.SECTION (NM_SECTION, DT_CREATION, DT_LAST_UPDATE) ");
		sbSql.append ("VALUES ('" + section.getNM_SECTION() + "',");
		sbSql.append ("NOW(), ");
		sbSql.append ("NOW())");
		inserts = updateDb (sbSql.toString());
		logger.info ("inserts [" + inserts + "]");
		logger.info ("Finish executing the method create.");
		return inserts;
	}

	@Override
	public int update (Section section) throws DataAccessException {
		logger.info ("Start executing the method update.");
		int updates = 0;
		StringBuilder sbSql = null;
		String whereClause  = null;
		sbSql = new StringBuilder();
		sbSql.append ("UPDATE MYDB.SECTION SET ");
		sbSql.append ("NM_SECTION=");
		sbSql.append ("'" + section.getNM_SECTION() + "', ");
		sbSql.append ("DT_LAST_UPDATE=NOW()");
		whereClause = " WHERE ID_SECTION = " + section.getID_SECTION();
		sbSql.append (whereClause);
		updates = updateDb (sbSql.toString());
		logger.info ("updates [" + updates + "]");
		logger.info ("Finish executing the method update.");
		return updates;
	}

	@Override
	public int removeAll (Section[] sectionArr) throws DataAccessException {
		logger.info ("Start executing the method removeAll.");
		String sql         = null;
		String inClause    = "";
		int affectedRows   = 0;
		boolean isLastItem = false;
		int size = sectionArr.length - 1;
		for (int i = 0; i < sectionArr.length; i++) {
			Section section = sectionArr [i];
			isLastItem = (i == size) ? true : false;
			if (!isLastItem) {
				inClause += section.getID_SECTION() + ", ";
			} else {
				inClause += section.getID_SECTION();
			}
		}
		sql = "DELETE FROM MYDB.SECTION WHERE ID_SECTION IN (" + inClause + ")";
		affectedRows = updateDb (sql);
		logger.info ("affectedRows [" + affectedRows + "]");
		logger.info ("Finish executing the method removeAll.");
		return affectedRows;
	}

	@Override
	public List<Section> findByFilter (Section section, Pagination pagination) throws DataAccessException {
		logger.info ("Start executing the method findByFilter.");
		List<Section> list    = new ArrayList<Section>();
		String sql         = null;
		String whereClause = "";
		if (Util.isNonEmpty (section.getNM_SECTION())) {
			whereClause = "WHERE NM_SECTION LIKE '%" + section.getNM_SECTION() + "%' ";
		}
		sql = "SELECT ID_SECTION, NM_SECTION, DT_CREATION, DT_LAST_UPDATE FROM MYDB.SECTION " + whereClause + "ORDER BY ID_SECTION LIMIT " + pagination.getLimit();
		list = selectDb (sql, new Section());
		logger.info ("list.size() [" + list.size() + "]");
		logger.info ("Finish executing the method findByFilter.");
		return list;
	}
	
	public int findRowCount () throws DataAccessException {
		logger.info ("Start executing the method findRowCount.");
		int rowCount = 0;
		String sql   = null;
		sql = "SELECT COUNT(*) FROM MYDB.SECTION";
		rowCount = selectRowCount (sql);
		logger.info ("rowCount [" + rowCount + "]");
		logger.info ("Finish executing the method findRowCount.");
		return rowCount;
	}

	@Override
	public Section findById (Section section) throws DataAccessException {
		logger.info ("Start executing the method findById.");
		Section foundSection = null;
		String sql           = null;
		sql = "SELECT ID_SECTION, NM_SECTION, DT_CREATION, DT_LAST_UPDATE FROM MYDB.SECTION WHERE ID_SECTION = " + section.getID_SECTION();
		List<Section> list = selectDb (sql, new Section());
		if (list != null && !list.isEmpty()) {
			foundSection = list.get (0);
			logger.info ("foundSection [" + foundSection.getNM_SECTION() + "]");
		}
		logger.info ("Finish executing the method findById.");
		return foundSection;
	}

	@Override
	public List<Section> findAll() throws DataAccessException {
		logger.info ("Start executing the method findAll.");
		List<Section> list = new ArrayList<Section>();
		String sql         = null;
		sql = "SELECT ID_SECTION, NM_SECTION, DT_CREATION, DT_LAST_UPDATE FROM MYDB.SECTION ORDER BY ID_SECTION";
		list = selectDb (sql, new Section());
		logger.info ("list.size() [" + list.size() + "]");
		logger.info ("Finish executing the method findAll.");
		return list;
	}

}
