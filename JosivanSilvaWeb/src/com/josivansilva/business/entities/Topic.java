package com.josivansilva.business.entities;

import java.util.Date;

/**
 * Topic business entity class.
 * 
 * @author Josivan Silva
 *
 */
public class Topic {

	private Integer ID_TOPIC;
	private Integer ID_SECTION;
	private String NM_TOPIC;
	private Date DT_CREATION;
	private Date DT_LAST_UPDATE;
	
	public Integer getID_TOPIC() {
		return ID_TOPIC;
	}
	public void setID_TOPIC(Integer iD_TOPIC) {
		ID_TOPIC = iD_TOPIC;
	}
	public Integer getID_SECTION() {
		return ID_SECTION;
	}
	public void setID_SECTION(Integer iD_SECTION) {
		ID_SECTION = iD_SECTION;
	}
	public String getNM_TOPIC() {
		return NM_TOPIC;
	}
	public void setNM_TOPIC(String nM_TOPIC) {
		NM_TOPIC = nM_TOPIC;
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
