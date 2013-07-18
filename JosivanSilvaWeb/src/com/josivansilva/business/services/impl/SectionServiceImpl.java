package com.josivansilva.business.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.josivansilva.business.entities.Section;
import com.josivansilva.business.services.SectionService;
import com.josivansilva.dao.SectionDAO;
import com.josivansilva.dao.impl.SectionDAOImpl;
import com.josivansilva.exceptions.BusinessException;
import com.josivansilva.exceptions.DataAccessException;
import com.josivansilva.util.Pagination;

public class SectionServiceImpl implements SectionService {
	/**
	 * Defines the logger instance property. 
	 */
	private static Logger logger = Logger.getLogger (SectionServiceImpl.class);
	/**
	 * Defines the singleton instance property.
	 */
	private static SectionServiceImpl instance;
	
	
	private SectionDAO sectionDAO = SectionDAOImpl.getInstance();
	
	/**
	 * Default private constructor.
	 */
	private SectionServiceImpl () {}
	
	/**
	 * Default singleton.
	 * 
	 * @return the singleton instance.
	 */
	public static SectionService getInstance () {
		if (instance == null) {
			instance = new SectionServiceImpl ();
		}
		return instance;
	}

	@Override
	public int create (Section section) throws BusinessException {
		logger.info ("Start executing the method create.");
		int affectedRows = 0;
		try {
			affectedRows = sectionDAO.create (section);
			logger.info ("affectedRows [" + affectedRows + "]");
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while creating a section.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method create.");
		return affectedRows;
	}

	@Override
	public int update (Section section) throws BusinessException {
		logger.info ("Start executing the method update.");
		int affectedRows = 0;
		try {
			affectedRows = sectionDAO.update (section);
			logger.info ("affectedRows [" + affectedRows + "]");
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while updating a section.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method update.");
		return affectedRows;
	}

	@Override
	public int removeAll (Section[] sectionArr) throws BusinessException {
		logger.info ("Start executing the method removeAll.");
		int affectedRows = 0;
		try {
			affectedRows = sectionDAO.removeAll (sectionArr);
			logger.info ("affectedRows [" + affectedRows + "]");
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while removing the sections.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method removeAll.");
		return affectedRows;
	}

	@Override
	public List<Section> findByFilter (Section section, Pagination pagination) throws BusinessException {
		logger.info ("Start executing the method findByFilter.");
		List<Section> list = new ArrayList<Section>();
		try {
			list = sectionDAO.findByFilter (section, pagination);
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while finding sections by filter.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findByFilter.");
		return list;
	}

	@Override
	public Section findById (Section section) throws BusinessException {
		logger.info ("Start executing the method findById.");
		Section foundSection = null;
		try {
			logger.info ("section.getID_SECTION() " + section.getID_SECTION());
			foundSection = sectionDAO.findById (section);
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while finding a section by id.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findById.");
		return foundSection;
	}
	
	public int findRowCount () throws BusinessException {
		logger.info ("Start executing the method findRowCount.");
		int rowCount = 0;
		try {
			rowCount = sectionDAO.findRowCount();
			logger.info ("rowCount [" + rowCount + "]");
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while executing the findRowCount method.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findRowCount.");
		return rowCount;
	}

	@Override
	public List<Section> findAll() throws BusinessException {
		logger.info ("Start executing the method findAll.");
		List<Section> list = new ArrayList<Section>();
		try {
			list = sectionDAO.findAll ();
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while finding all sections.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findAll.");
		return list;
	}

}
