package com.josivansilva.business.entities;

import java.util.Date;

/**
 * User business entity class.
 * 
 * @author Josivan Silva
 *
 */
public class User {

	private Integer ID_USER;
	private String USERNAME;
	private String PWD;
	private String EMAIL;
	private Date DT_CREATION;
	private Date DT_LAST_UPDATE;
	private boolean ROLE_ADMIN;
	
	public Integer getID_USER() {
		return ID_USER;
	}
	public void setID_USER(Integer ID_USER) {
		this.ID_USER = ID_USER;
	}
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String USERNAME) {
		this.USERNAME = USERNAME;
	}
	public String getPWD() {
		return PWD;
	}
	public void setPWD(String PWD) {
		this.PWD = PWD;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String EMAIL) {
		this.EMAIL = EMAIL;
	}
	public Date getDT_CREATION() {
		return DT_CREATION;
	}
	public void setDT_CREATION(Date DT_CREATION) {
		this.DT_CREATION = DT_CREATION;
	}
	public Date getDT_LAST_UPDATE() {
		return DT_LAST_UPDATE;
	}
	public void setDT_LAST_UPDATE(Date DT_LAST_UPDATE) {
		this.DT_LAST_UPDATE = DT_LAST_UPDATE;
	}
	public boolean isROLE_ADMIN() {
		return ROLE_ADMIN;
	}
	public void setROLE_ADMIN(boolean ROLE_ADMIN) {
		this.ROLE_ADMIN = ROLE_ADMIN;
	}	
	
}
