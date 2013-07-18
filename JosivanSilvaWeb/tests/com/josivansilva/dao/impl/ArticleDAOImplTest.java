package com.josivansilva.dao.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.josivansilva.business.entities.Article;
import com.josivansilva.business.entities.Page;
import com.josivansilva.dao.ArticleDAO;
import com.josivansilva.exceptions.DataAccessException;
import com.josivansilva.util.Pagination;

public class ArticleDAOImplTest {

	private ArticleDAO articleDAO = null;
	private static final boolean SUCCESS_OPERATION = true;
	
	@Before 
	public void setUp() {
	    try {
	    	articleDAO = ArticleDAOImpl.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	@Test
	public void testCreate () {
		int inserts = 0;
		boolean isCreated = false;
		Article article = new Article ();
		article.setID_SECTION (1);
		article.setTITLE ("What is your next JVM Language?");
		article.setCONTENT ("content");
		article.setNM_AUTHOR("Josivan Silva");
		try {
			inserts = articleDAO.create (article);
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
		Article article = new Article ();
		article.setID_SECTION (1);
		article.setTITLE ("What is your next JVM111 Language?");
		article.setCONTENT ("content");
		article.setNM_AUTHOR("Josivan Ribeiro da Silva");
		article.setID_ARTICLE (1);
		try {
			updates = articleDAO.update (article);
			isUpdated = updates > 0 ? true : false;
		} catch (DataAccessException e) {
			e.printStackTrace();
		} finally {
			assertEquals ("The result for the operation testUpdate [" + isUpdated + "] " +
    			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isUpdated);
		}
	}
	
	@Test
	public void testRemoveAll () {
		int affectedRows = 0;
		boolean success  = false;
		Article article1 = new Article();
		Article article2 = new Article();
		
		article1.setID_ARTICLE (1);
		article2.setID_ARTICLE (2);
		
		Article[] articleArr = new Article[2];
		articleArr[0] = article1;
		articleArr[1] = article2;
				
		try {
			affectedRows = articleDAO.removeAll (articleArr);
			success = (affectedRows == 2) ? true : false;
		} catch (DataAccessException e) {
			e.printStackTrace();
		} finally {
			assertEquals ("The result for the operation testRemoveAll [" + success + "] " +
			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, success);
		}
	}
	
	@Test
	public void testFindByFilter () {
		boolean isNonEmpty = false;
		List<Article> list = new ArrayList<Article>();
		try {
			list = articleDAO.findByFilter (new Article(), new Pagination());
			isNonEmpty = !list.isEmpty();
		} catch (DataAccessException e) {
			e.printStackTrace();
		} finally {
			assertEquals ("The result for the operation testFindByFilter [" + isNonEmpty + "] " +
			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isNonEmpty);
		}
	}
	
	/*@Test
	public void testFindAll () {
		boolean isNonEmpty = false;
		List<Page> list = new ArrayList<Page>();
		try {
			list = pageDAO.findAll ();
			isNonEmpty = !list.isEmpty();
		} catch (DataAccessException e) {
			e.printStackTrace();
		} finally {
			assertEquals ("The result for the operation testFindAll [" + isNonEmpty + "] " +
			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isNonEmpty);
		}
	}
	
	@Test
	public void testFindById () {
		boolean exists   = false;
		Page found = null;
		Page page  = new Page ();
		page.setID_PAGE (1);
		try {
			found = pageDAO.findById (page);
			if (found != null) {
				exists = true;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		} finally {
			assertEquals ("The result for the operation testFindById [" + exists + "] " +
			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, exists);
		}
	}*/
	
	@Test
	public void testGetLastInsertedId () {
		int lastInsertedId;
		boolean success = false;
		try {
			lastInsertedId = articleDAO.getLastInsertedId();
			if (lastInsertedId > 0) {
				success = true;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		} finally {
			assertEquals ("The result for the operation testGetLastInsertedId [" + success + "] " +
			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, success);
		}
	}
	
}
