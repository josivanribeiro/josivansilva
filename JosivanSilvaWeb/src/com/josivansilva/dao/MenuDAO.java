package com.josivansilva.dao;

import java.util.List;

import com.josivansilva.business.entities.Menu;
import com.josivansilva.exceptions.DataAccessException;
import com.josivansilva.util.Pagination;

/**
 * Menu DAO interface.
 * 
 * @author Josivan Silva
 *
 */
public interface MenuDAO {
	
	/**
	 * Creates a new menu.
	 * 
	 * @param menu the menu.
	 * @return
	 * @throws DataAccessException
	 */
	public int create (Menu menu) throws DataAccessException;
	
	/**
	 * Updates an existing menu.
	 * 
	 * @param menu the menu.
	 * @return
	 * @throws DataAccessException
	 */
	public int update (Menu menu) throws DataAccessException;
	
	/**
	 * Removes all the menus given an array.
	 * 
	 * @param menuArr
	 * @return
	 * @throws DataAccessException
	 */
	public int removeAll (Menu[] menuArr) throws DataAccessException;
	
	/**
	 * Finds all the menus.
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public List<Menu> findAll () throws DataAccessException;
	
	/**
	 * Finds the menus given a filter.
	 * 
	 * @param menu the menu filter.
	 * @param pagination the pagination.
	 * @return
	 * @throws DataAccessException
	 */
	public List<Menu> findByFilter (Menu menu, Pagination pagination) throws DataAccessException;
	
	/**
	 * Finds a menu by its id.
	 * 
	 * @param menu the menu.
	 * @return
	 * @throws DataAccessException
	 */
	public Menu findById (Menu menu) throws DataAccessException;
	
	/**
	 * Finds the count rows of a given table.
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public int findRowCount () throws DataAccessException;
	
}
