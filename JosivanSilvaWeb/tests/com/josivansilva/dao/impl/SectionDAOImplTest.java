package com.josivansilva.dao.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.josivansilva.business.entities.Section;
import com.josivansilva.business.entities.User;
import com.josivansilva.dao.SectionDAO;
import com.josivansilva.exceptions.DataAccessException;
import com.josivansilva.util.Pagination;

public class SectionDAOImplTest {

	private SectionDAO sectionDAO = null;
	private static final boolean SUCCESS_OPERATION = true;
	
	@Before 
	public void setUp() {
	    try {
	    	sectionDAO = SectionDAOImpl.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	@Test
	public void testCreate () {
		int inserts = 0;
		boolean isCreated = false;
		Section section = new Section();
		section.setNM_SECTION("Development");
		try {
			inserts = sectionDAO.create (section);
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
		Section section = new Section();
		section.setID_SECTION (1);
		section.setNM_SECTION("Development1");		
		try {
			updates = sectionDAO.update (section);
			isUpdated = updates > 0 ? true : false;
		} catch (DataAccessException e) {
			e.printStackTrace();
		} finally {
			assertEquals ("The result for the operation testUpdate [" + isUpdated + "] " +
    			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isUpdated);
		}
	}*/
	
	/*@Test
	public void testRemoveAll () {
		int affectedRows = 0;
		boolean success  = false;
		Section section1 = new Section();
		Section section2 = new Section();
		
		section1.setID_SECTION(1);
		section2.setID_SECTION(2);
		
		Section[] sectionArr = new Section[2];
		sectionArr[0] = section1;
		sectionArr[1] = section2;
				
		try {
			affectedRows = sectionDAO.removeAll (sectionArr);
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
		List<Section> list = new ArrayList<Section>();
		try {
			list = sectionDAO.findByFilter (new Section(), new Pagination());
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
		List<Section> list = new ArrayList<Section>();
		try {
			list = sectionDAO.findAll ();
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
		boolean exists       = false;
		Section foundSection = null;
		Section section      = new Section();
		section.setID_SECTION (2);
		try {
			foundSection = sectionDAO.findById (section);
			if (foundSection != null) {
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
	public void testFindRowCount () {
		boolean isNonEmpty = false;
		int rowCount       = 0;
		try {
			rowCount = sectionDAO.findRowCount();
			isNonEmpty = (rowCount > 0) ? true : false;
		} catch (DataAccessException e) {
			e.printStackTrace();
		} finally {
			assertEquals ("The result for the operation testFindRowCount [" + isNonEmpty + "] " +
			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isNonEmpty);
		}
	}
	
}
