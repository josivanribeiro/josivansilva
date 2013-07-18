package com.josivansilva.business.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.josivansilva.business.entities.Topic;
import com.josivansilva.business.services.TopicService;
import com.josivansilva.dao.TopicDAO;
import com.josivansilva.dao.impl.TopicDAOImpl;
import com.josivansilva.exceptions.BusinessException;
import com.josivansilva.exceptions.DataAccessException;
import com.josivansilva.util.Pagination;

public class TopicServiceImpl implements TopicService {
	/**
	 * Defines the logger instance property.
	 */
	private static Logger logger = Logger.getLogger (TopicServiceImpl.class);
	/**
	 * Defines the singleton instance property.
	 */
	private static TopicServiceImpl instance;
	
	
	private TopicDAO topicDAO = TopicDAOImpl.getInstance();
	
	/**
	 * Default private constructor.
	 */
	private TopicServiceImpl () {}
	
	/**
	 * Default singleton.
	 * 
	 * @return the singleton instance.
	 */
	public static TopicService getInstance () {
		if (instance == null) {
			instance = new TopicServiceImpl ();
		}
		return instance;
	}

	@Override
	public int create (Topic topic) throws BusinessException {
		logger.info ("Start executing the method create.");
		int affectedRows = 0;
		try {
			affectedRows = topicDAO.create (topic);
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while creating a topic.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method create.");
		return affectedRows;
	}

	@Override
	public int update (Topic topic) throws BusinessException {
		logger.info ("Start executing the method update.");
		int affectedRows = 0;
		try {
			affectedRows = topicDAO.update (topic);
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while updating a page.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method update.");
		return affectedRows;
	}

	@Override
	public int removeAll (Topic[] topicArr) throws BusinessException {
		logger.info ("Start executing the method removeAll.");
		int affectedRows = 0;
		try {
			affectedRows = topicDAO.removeAll (topicArr);
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while removing a page.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method removeAll.");
		return affectedRows;
	}

	@Override
	public List<Topic> findByFilter (Topic topic, Pagination pagination) throws BusinessException {
		logger.info ("Start executing the method findByFilter.");
		List<Topic> list = new ArrayList<Topic>();
		try {
			list = topicDAO.findByFilter (topic, pagination);
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while finding topics by filter.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findByFilter.");
		return list;
	}

	@Override
	public Topic findById (Topic topic) throws BusinessException {
		logger.info ("Start executing the method findById.");
		Topic foundTopic = null;
		try {
			foundTopic = topicDAO.findById (topic);
			logger.info ("foundTopic.getNM_TOPIC() [" + foundTopic.getNM_TOPIC() + "]");
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while finding a topic by id.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findById.");
		return foundTopic;
	}

	@Override
	public int findRowCount() throws BusinessException {
		logger.info ("Start executing the method findRowCount.");
		int rowCount = 0;
		try {
			rowCount = topicDAO.findRowCount();
			logger.info ("rowCount [" + rowCount + "]");
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while executing the findRowCount method.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findRowCount.");
		return rowCount;
	}

	@Override
	public List<Topic> findAll() throws BusinessException {
		logger.info ("Start executing the method findAll.");
		List<Topic> list = new ArrayList<Topic>();
		try {
			list = topicDAO.findAll ();
		} catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while finding topics by filter.";
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findAll.");
		return list;
	}

}
