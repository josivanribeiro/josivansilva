package com.josivansilva.business.services;

import java.util.List;

import com.josivansilva.business.entities.Menu;
import com.josivansilva.exceptions.BusinessException;
import com.josivansilva.exceptions.DataAccessException;
import com.josivansilva.util.Pagination;

/**
 * Menu Business Service interface.
 * 
 * @author Josivan Silva
 *
 */
public interface MenuService {

	/**
	 * Creates a new menu.
	 * 
	 * @param menu the menu.
	 * @return
	 * @throws DataAccessException
	 */
	public int create (Menu menu) throws BusinessException;
	
	/**
	 * Updates an existing menu.
	 * 
	 * @param menu the menu.
	 * @return
	 * @throws DataAccessException
	 */
	public int update (Menu menu) throws BusinessException;
	
	/**
	 * Removes all the menus given an array.
	 * 
	 * @param menuArr
	 * @return
	 * @throws DataAccessException
	 */
	public int removeAll (Menu[] menuArr) throws BusinessException;
	
	/**
	 * Finds all the menus.
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public List<Menu> findAll () throws BusinessException;
	
	/**
	 * Finds the menus given a filter.
	 * 
	 * @param menu the menu filter.
	 * @param pagination the pagination.
	 * @return
	 * @throws DataAccessException
	 */
	public List<Menu> findByFilter (Menu menu, Pagination pagination) throws BusinessException;
	
	/**
	 * Finds a menu by its id.
	 * 
	 * @param menu the menu.
	 * @return
	 * @throws DataAccessException
	 */
	public Menu findById (Menu menu) throws BusinessException;
	
	/**
	 * Finds the count rows of a given table.
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public int findRowCount () throws BusinessException;	
		
}
