package com.josivansilva.business.entities;

import java.util.Date;


/**
 * Menu business entity class.
 * 
 * @author Josivan Silva.
 *
 */
public class Menu {

	private Integer ID_MENU;
	private Integer ID_MENU_FATHER;
	private String NM_MENU;
	private Date DT_CREATION;
	private Date DT_LAST_UPDATE;
	
	public Integer getID_MENU() {
		return ID_MENU;
	}
	public void setID_MENU(Integer iD_MENU) {
		ID_MENU = iD_MENU;
	}
	public Integer getID_MENU_FATHER() {
		return ID_MENU_FATHER;
	}
	public void setID_MENU_FATHER(Integer iD_MENU_FATHER) {
		ID_MENU_FATHER = iD_MENU_FATHER;
	}
	public String getNM_MENU() {
		return NM_MENU;
	}
	public void setNM_MENU(String nM_MENU) {
		NM_MENU = nM_MENU;
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
