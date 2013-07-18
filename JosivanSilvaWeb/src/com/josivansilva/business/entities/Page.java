package com.josivansilva.business.entities;

import java.util.Date;

/**
 * Page business entity class.
 * 
 * @author Josivan Silva
 *
 */
public class Page {

	private Integer ID_PAGE;
	private Integer ID_MENU;
	private String NM_PAGE;
	private String CONTENT;
	private String URL;
	private Date DT_CREATION;
	private Date DT_LAST_UPDATE;
	
	public Integer getID_PAGE() {
		return ID_PAGE;
	}
	public void setID_PAGE(Integer iD_PAGE) {
		ID_PAGE = iD_PAGE;
	}
	public Integer getID_MENU() {
		return ID_MENU;
	}
	public void setID_MENU(Integer iD_MENU) {
		ID_MENU = iD_MENU;
	}
	public String getNM_PAGE() {
		return NM_PAGE;
	}
	public void setNM_PAGE(String nM_PAGE) {
		NM_PAGE = nM_PAGE;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
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
