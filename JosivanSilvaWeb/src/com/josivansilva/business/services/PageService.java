package com.josivansilva.business.services;

import java.util.List;

import com.josivansilva.business.entities.Page;
import com.josivansilva.exceptions.BusinessException;
import com.josivansilva.util.Pagination;

/**
 * Page Business Service interface.
 * 
 * @author Josivan Silva
 *
 */
public interface PageService {

	/**
	 * Creates a new page.
	 * 
	 * @param page the page.
	 * @return
	 * @throws BusinessException
	 */
	public int create (Page page) throws BusinessException;
	
	/**
	 * Updates a page.
	 * 
	 * @param page the page.
	 * @return
	 * @throws BusinessException
	 */
	public int update (Page page) throws BusinessException;
	
	/**
	 * Removes all the pages given an array.
	 * 
	 * @param pageArr
	 * @return
	 * @throws BusinessException
	 */
	public int removeAll (Page[] pageArr) throws BusinessException;
	
	/**
	 * Finds all the pages.
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<Page> findAll () throws BusinessException;
	
	/**
	 * Finds pages by filter.
	 * 
	 * @param page the page filter.
	 * @param pagination the pagination.
	 * @return
	 * @throws BusinessException
	 */
	public List<Page> findByFilter (Page page, Pagination pagination) throws BusinessException;
	
	/**
	 * Finds a page by id.
	 * 
	 * @param page the page.
	 * @return
	 * @throws BusinessException
	 */
	public Page findById (Page page) throws BusinessException;
	
	/**
	 * Finds the count rows of a given table.
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public int findRowCount () throws BusinessException;
	
}
