package com.josivansilva.dao;

import java.util.List;

import com.josivansilva.business.entities.Section;
import com.josivansilva.exceptions.DataAccessException;
import com.josivansilva.util.Pagination;

/**
 * Section DAO interface.
 * 
 * @author Josivan Silva
 *
 */
public interface SectionDAO {

	/**
	* Creates a new section.
	* 
	* @param section the new section.
	* @throws DataAccessException 
	*/
	public int create (Section section) throws DataAccessException;
	
	/**
	 * Updates the new section.
	 * 
	 * @param section the new section.
	 * @throws DataAccessException
	 */
	public int update (Section section) throws DataAccessException;
	
	/**
	 * Removes all the sections.
	 * 
	 * @param section the section.
	 * @throws DataAccessException
	 */
	public int removeAll (Section[] sectionArr) throws DataAccessException;
	
	/**
	 * Finds a list of section given a specific filter.
	 * 
	 * @param section the section filter.
	 * @return a list of the filtered section.
	 * @throws DataAccessException
	 */
	public List<Section> findByFilter (Section section, Pagination pagination) throws DataAccessException;
	
	
	/**
	 * Finds a list of section.
	 * 
	 * @return a list of section
	 * @throws DataAccessException
	 */
	public List<Section> findAll () throws DataAccessException;
	
	/**
	 * Finds a section given its id.
	 * 
	 * @param section the section filter.
	 * @return the specified section.
	 * @throws DataAccessException
	 */
	public Section findById (Section section) throws DataAccessException;
	
	/**
	 * Gets the number total of rows from an specific table. 
	 * 
	 * @return the number total of rows.
	 * @throws DataAccessException
	 */
	public int findRowCount () throws DataAccessException;
	
}
