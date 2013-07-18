package com.josivansilva.dao.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.josivansilva.business.entities.ArticleTopic;
import com.josivansilva.dao.ArticleTopicDAO;
import com.josivansilva.exceptions.DataAccessException;

public class ArticleTopicDAOImplTest {

	private ArticleTopicDAO articleTopicDAO = null;
	private static final boolean SUCCESS_OPERATION = true;
	
	@Before 
	public void setUp() {
	    try {
	    	articleTopicDAO = ArticleTopicDAOImpl.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	@Test
	public void testCreate () {
		int inserts = 0;
		boolean isCreated = false;
		ArticleTopic articleTopic = new ArticleTopic();
		articleTopic.setID_ARTICLE(6);
		articleTopic.setID_TOPIC (6);
		try {
			inserts = articleTopicDAO.create (articleTopic);
			isCreated = inserts > 0 ? true : false;
		} catch (DataAccessException e) {
			e.printStackTrace();
		} finally {
			assertEquals ("The result for the operation testCreate [" + isCreated + "] " +
    			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isCreated);
		}
	}
	
	@Test
	public void testRemove () {
		int affectedRows = 0;
		boolean success  = false;
		ArticleTopic articleTopic = new ArticleTopic();
		articleTopic.setID_ARTICLE(6);
		try {
			affectedRows = articleTopicDAO.remove (articleTopic);
			success = (affectedRows == 2) ? true : false;
		} catch (DataAccessException e) {
			e.printStackTrace();
		} finally {
			assertEquals ("The result for the operation testRemoveAll [" + success + "] " +
			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, success);
		}
	}
	
}
