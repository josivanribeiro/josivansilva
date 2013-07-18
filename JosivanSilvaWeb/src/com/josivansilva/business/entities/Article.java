package com.josivansilva.business.entities;

import java.util.Date;

/**
 * Article business entity class.
 * 
 * @author Josivan Silva
 *
 */
public class Article {

	private Integer ID_ARTICLE;
	private Integer ID_SECTION;
	private String TITLE;
	private String NM_AUTHOR;
	private String CONTENT;
	private Date DT_CREATION;
	private Date DT_LAST_UPDATE;
	
	public Integer getID_ARTICLE() {
		return ID_ARTICLE;
	}
	public void setID_ARTICLE(Integer iD_ARTICLE) {
		ID_ARTICLE = iD_ARTICLE;
	}
	public Integer getID_SECTION() {
		return ID_SECTION;
	}
	public void setID_SECTION(Integer iD_SECTION) {
		ID_SECTION = iD_SECTION;
	}
	public String getTITLE() {
		return TITLE;
	}
	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}
	public String getNM_AUTHOR() {
		return NM_AUTHOR;
	}
	public void setNM_AUTHOR(String nM_AUTHOR) {
		NM_AUTHOR = nM_AUTHOR;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
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
