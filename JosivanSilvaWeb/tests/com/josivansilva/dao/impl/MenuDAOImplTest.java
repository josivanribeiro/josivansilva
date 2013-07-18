package com.josivansilva.dao.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.josivansilva.business.entities.Menu;
import com.josivansilva.business.entities.Topic;
import com.josivansilva.dao.MenuDAO;
import com.josivansilva.exceptions.DataAccessException;
import com.josivansilva.util.Pagination;

public class MenuDAOImplTest {

	private MenuDAO menuDAO = null;
	private static final boolean SUCCESS_OPERATION = true;
	
	@Before 
	public void setUp() {
	    try {
	    	menuDAO = MenuDAOImpl.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	@Test
	public void testCreate () {
		int inserts = 0;
		boolean isCreated = false;
		Menu menu = new Menu();
		menu.setID_MENU_FATHER(null);
		menu.setNM_MENU("Home");
		try {
			inserts = menuDAO.create (menu);
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
		Menu menu = new Menu();
		menu.setID_MENU (1);
		menu.setNM_MENU("About1");
		try {
			updates = menuDAO.update (menu);
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
		Menu menu1 = new Menu();
		Menu menu2 = new Menu();
		
		menu1.setID_MENU (1);
		menu2.setID_MENU (2);
				
		Menu[] menuArr = new Menu[2];
		menuArr[0] = menu1;
		menuArr[1] = menu2;
				
		try {
			affectedRows = menuDAO.removeAll (menuArr);
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
		List<Menu> list = new ArrayList<Menu>();
		try {
			list = menuDAO.findByFilter (new Menu(), new Pagination());
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
		List<Menu> list = new ArrayList<Menu>();
		try {
			list = menuDAO.findAll ();
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
		Menu found = null;
		Menu menu  = new Menu ();
		menu.setID_MENU (3);
		try {
			found = menuDAO.findById (menu);
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
