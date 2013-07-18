package com.josivansilva.business.services;

import java.util.List;

import com.josivansilva.business.entities.Section;
import com.josivansilva.exceptions.BusinessException;
import com.josivansilva.util.Pagination;

/**
 * Section Business Service interface.
 * 
 * @author Josivan Silva
 *
 */
public interface SectionService {

	/**
	* Creates a new section.
	* 
	* @param section the new section.
	* @throws BusinessException 
	*/
	public int create (Section section) throws BusinessException;
	
	/**
	 * Updates the new section.
	 * 
	 * @param section the new section.
	 * @throws BusinessException
	 */
	public int update (Section section) throws BusinessException;
	
	/**
	 * Removes all the sections.
	 * 
	 * @param section the section.
	 * @throws BusinessException
	 */
	public int removeAll (Section[] sectionArr) throws BusinessException;
	
	/**
	 * Finds a list of section given a specific filter.
	 * 
	 * @param section the section filter.
	 * @return a list of the filtered section.
	 * @throws BusinessException
	 */
	public List<Section> findByFilter (Section section, Pagination pagination) throws BusinessException;
	
	/**
	 * Finds a list of sections.
	 * 
	 * @return a list of sections.
	 * @throws BusinessException
	 */
	public List<Section> findAll () throws BusinessException;
	
	/**
	 * Finds a section given its id.
	 * 
	 * @param section the section filter.
	 * @return the specified section.
	 * @throws BusinessException
	 */
	public Section findById (Section section) throws BusinessException;
	
	/**
	 * Gets the number total of rows from an specific table. 
	 * 
	 * @return the number total of rows.
	 * @throws BusinessException
	 */
	public int findRowCount () throws BusinessException;
	
}
