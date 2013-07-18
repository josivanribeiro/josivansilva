package com.josivansilva.dao.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.josivansilva.business.entities.User;
import com.josivansilva.dao.UserDAO;
import com.josivansilva.exceptions.DataAccessException;
import com.josivansilva.util.Pagination;

public class UserDAOImplTest {

	private UserDAO userDAO = null;
	private static final boolean SUCCESS_OPERATION = true;
	
	@Before 
	public void setUp() {
	    try {
	    	userDAO = UserDAOImpl.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	/*@Test
	public void testDoLogin () {
		boolean isLogged = false;
		User user = new User ();
		user.setUSERNAME ("user");
		user.setPWD ("user");
		try {
			isLogged = userDAO.doLogin (user);
		} catch (DataAccessException e) {
			e.printStackTrace ();
		} finally {
			assertEquals ("The result for the operation testDoLogin [" + isLogged + "] " +
    			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isLogged);
		}
	}*/
	
	@SuppressWarnings("unused")
	@Test
	public void testCreate () {
		int inserts = 0;
		boolean isCreated = false;
		User user = new User ();
		user.setUSERNAME ("admin");
		user.setPWD ("admin");
		user.setEMAIL("admin@josivansilva.com");
		user.setROLE_ADMIN (true);
		try {
			inserts = userDAO.create (user);
			isCreated = inserts > 0 ? true : false;
		} catch (DataAccessException e) {
			e.printStackTrace();
		} finally {
			assertEquals ("The result for the operation testCreate [" + isCreated + "] " +
    			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isCreated);
		}
	}
	
	/*@Test
	public void testUpdate () {
		int updates = 0;
		boolean isUpdated = false;
		User user = new User ();
		user.setUSERNAME ("user");
		user.setPWD ("user1");
		user.setEMAIL("josivan@gmail.com");
		user.setROLE_ADMIN (true);
		user.setID_USER (34);
		try {
			updates = userDAO.update (user);
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
		User user1 = new User();
		User user2 = new User();
		User user3 = new User();
		User user4 = new User();
		
		user1.setID_USER (1);
		user2.setID_USER (2);
		user3.setID_USER (3);
		user4.setID_USER (4);
		
		User[] userArr = new User[4];
		userArr[0] = user1; 
		userArr[1] = user2;
		userArr[2] = user3;
		userArr[3] = user4;
		
		try {
			affectedRows = userDAO.removeAll (userArr);
			success = (affectedRows == 4) ? true : false;
		} catch (DataAccessException e) {
			e.printStackTrace();
		} finally {
			assertEquals ("The result for the operation testRemoveAll [" + success + "] " +
			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, success);
		}		
	}
	
	@Test
	public void testFindById () {
		boolean exists = false;
		User foundUser = null;
		User user      = new User();
		user.setID_USER (32);
		try {
			foundUser = userDAO.findById (user);
			if (foundUser != null) {
				exists = true;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		} finally {
			assertEquals ("The result for the operation testFindById [" + exists + "] " +
			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, exists);
		}
	}
	
	@Test
	public void testFindByFilter () {
		boolean isNonEmpty = false;
		List<User> list = new ArrayList<User>();
		try {
			list = userDAO.findByFilter (new User(), new Pagination());
			isNonEmpty = !list.isEmpty();
		} catch (DataAccessException e) {
			e.printStackTrace();
		} finally {
			assertEquals ("The result for the operation testFindByFilter [" + isNonEmpty + "] " +
			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isNonEmpty);
		}
	}
	
	@Test
	public void testFindRowCount () {
		boolean isNonEmpty = false;
		int rowCount       = 0;
		try {
			rowCount = userDAO.findRowCount();
			isNonEmpty = (rowCount > 0) ? true : false;
		} catch (DataAccessException e) {
			e.printStackTrace();
		} finally {
			assertEquals ("The result for the operation testFindRowCount [" + isNonEmpty + "] " +
			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isNonEmpty);
		}
	}*/
	
}
