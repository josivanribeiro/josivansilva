package com.josivansilva.dao.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.josivansilva.business.entities.Page;
import com.josivansilva.dao.PageDAO;
import com.josivansilva.exceptions.DataAccessException;
import com.josivansilva.util.Pagination;

public class PageDAOImplTest {

	private PageDAO pageDAO = null;
	private static final boolean SUCCESS_OPERATION = true;
	
	@Before 
	public void setUp() {
	    try {
	    	pageDAO = PageDAOImpl.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	@Test
	public void testCreate () {
		int inserts = 0;
		boolean isCreated = false;
		Page page = new Page();
		page.setID_MENU (26);
		page.setNM_PAGE("Contact");
		page.setCONTENT("content contact");
		page.setURL("contact.page");
		try {
			inserts = pageDAO.create (page);
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
		Page page = new Page();
		page.setID_MENU (26);
		page.setNM_PAGE ("Contact1");
		page.setCONTENT("content contact 1");
		page.setURL("contact1.page");
		page.setID_PAGE (1);
		try {
			updates = pageDAO.update (page);
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
		Page page1 = new Page();
		Page page2 = new Page();
		
		page1.setID_PAGE (1);
		page2.setID_PAGE (2);
				
		Page[] pageArr = new Page[2];
		pageArr[0] = page1;
		pageArr[1] = page2;
				
		try {
			affectedRows = pageDAO.removeAll (pageArr);
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
		List<Page> list = new ArrayList<Page>();
		try {
			list = pageDAO.findByFilter (new Page(), new Pagination());
			isNonEmpty = !list.isEmpty();
		} catch (DataAccessException e) {
			e.printStackTrace();
		} finally {
			assertEquals ("The result for the operation testFindByFilter [" + isNonEmpty + "] " +
			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isNonEmpty);
		}
	}
	
	@Test
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
	}
	
}
