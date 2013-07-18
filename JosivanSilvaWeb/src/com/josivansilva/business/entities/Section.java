package com.josivansilva.business.entities;

import java.util.Date;

/**
 * Section business entity class.
 * 
 * @author Josivan Silva
 *
 */
public class Section {
	
	private Integer ID_SECTION;
	private String NM_SECTION;
	private Date DT_CREATION;
	private Date DT_LAST_UPDATE;
	
	public Integer getID_SECTION() {
		return ID_SECTION;
	}
	public void setID_SECTION(Integer iD_SECTION) {
		ID_SECTION = iD_SECTION;
	}
	public String getNM_SECTION() {
		return NM_SECTION;
	}
	public void setNM_SECTION(String nM_SECTION) {
		NM_SECTION = nM_SECTION;
	}
	public Date getDT_CREATION() {
		return DT_CREATION;
	}
	public void setDT_CREATION(Date dT_CREATION) {
		DT_CREATION = dT_CREATION;
	}
	public Date getDT_LAST_UPDATE() {
		return DT_LAST_UPDATE;
	}
	public void setDT_LAST_UPDATE(Date dT_LAST_UPDATE) {
		DT_LAST_UPDATE = dT_LAST_UPDATE;
	}	
	
}
