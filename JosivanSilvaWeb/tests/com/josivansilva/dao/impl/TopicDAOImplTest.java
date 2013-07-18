package com.josivansilva.dao.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.josivansilva.business.entities.Topic;
import com.josivansilva.dao.TopicDAO;
import com.josivansilva.exceptions.DataAccessException;
import com.josivansilva.util.Pagination;

public class TopicDAOImplTest {
	
	private TopicDAO topicDAO = null;
	private static final boolean SUCCESS_OPERATION = true;
	
	@Before 
	public void setUp() {
	    try {
	    	topicDAO = TopicDAOImpl.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	@Test
	public void testCreate () {
		int inserts = 0;
		boolean isCreated = false;
		Topic topic = new Topic();
		topic.setID_SECTION (2);
		topic.setNM_TOPIC("Java");
		try {
			inserts = topicDAO.create (topic);
			isCreated = inserts > 0 ? true : false;
		} catch (DataAccessException e) {
			e.printStackTrace();
		} finally {
			assertEquals ("The result for the operation testCreate [" + isCreated + "] " +
    			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isCreated);
		}
	}
	
	@Test
	public void testUpdate () {
		int updates = 0;
		boolean isUpdated = false;
		Topic topic = new Topic();
		topic.setID_TOPIC (1);
		topic.setID_SECTION(2);
		topic.setNM_TOPIC("C#");
		try {
			updates = topicDAO.update (topic);
			isUpdated = updates > 0 ? true : false;
		} catch (DataAccessException e) {
			e.printStackTrace();
		} finally {
			assertEquals ("The result for the operation testUpdate [" + isUpdated + "] " +
    			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isUpdated);
		}
	}
	
	/*@Test
	public void testRemoveAll () {
		int affectedRows = 0;
		boolean success  = false;
		Topic topic1 = new Topic();
		Topic topic2 = new Topic();
		
		topic1.setID_TOPIC(1);
		topic1.setID_TOPIC(2);
		
		Topic[] topicArr = new Topic[2];
		topicArr[0] = topic1;
		topicArr[1] = topic2;
		
		try {
			affectedRows = topicDAO.removeAll (topicArr);
			success = (affectedRows == 2) ? true : false;
		} catch (DataAccessException e) {
			e.printStackTrace();
		} finally {
			assertEquals ("The result for the operation testRemoveAll [" + success + "] " +
			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, success);
		}
	}*/
	
	@Test
	public void testFindByFilter () {
		boolean isNonEmpty = false;
		List<Topic> list = new ArrayList<Topic>();
		try {
			list = topicDAO.findByFilter (new Topic(), new Pagination());
			isNonEmpty = !list.isEmpty();
		} catch (DataAccessException e) {
			e.printStackTrace();
		} finally {
			assertEquals ("The result for the operation testFindByFilter [" + isNonEmpty + "] " +
			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isNonEmpty);
		}
	}
	
	@Test
	public void testFindById () {
		boolean exists   = false;
		Topic foundTopic = null;
		Topic topic      = new Topic ();
		topic.setID_TOPIC (1);
		try {
			foundTopic = topicDAO.findById (topic);
			if (foundTopic != null) {
				exists = true;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		} finally {
			assertEquals ("The result for the operation testFindById [" + exists + "] " +
			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, exists);
		}
	}

}
