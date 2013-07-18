package com.josivansilva.dao;

import java.util.List;

import com.josivansilva.business.entities.Page;
import com.josivansilva.exceptions.DataAccessException;
import com.josivansilva.util.Pagination;

/**
 * Page DAO interface.
 * 
 * @author Josivan Silva
 *
 */
public interface PageDAO {

	/**
	 * Creates a new page.
	 * 
	 * @param page the page.
	 * @return
	 * @throws DataAccessException
	 */
	public int create (Page page) throws DataAccessException;
	
	/**
	 * Updates a page.
	 * 
	 * @param page the page.
	 * @return
	 * @throws DataAccessException
	 */
	public int update (Page page) throws DataAccessException;
	
	/**
	 * Removes all the pages given an array.
	 * 
	 * @param pageArr
	 * @return
	 * @throws DataAccessException
	 */
	public int removeAll (Page[] pageArr) throws DataAccessException;
	
	/**
	 * Finds all the pages.
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public List<Page> findAll () throws DataAccessException;
	
	/**
	 * Finds pages by filter.
	 * 
	 * @param page the page filter.
	 * @param pagination the pagination.
	 * @return
	 * @throws DataAccessException
	 */
	public List<Page> findByFilter (Page page, Pagination pagination) throws DataAccessException;
	
	/**
	 * Finds a page by id.
	 * 
	 * @param page the page.
	 * @return
	 * @throws DataAccessException
	 */
	public Page findById (Page page) throws DataAccessException;
	
	/**
	 * Finds the count rows of a given table.
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public int findRowCount () throws DataAccessException;
	
}
