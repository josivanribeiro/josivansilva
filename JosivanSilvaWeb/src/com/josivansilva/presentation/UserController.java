package com.josivansilva.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import com.josivansilva.business.entities.User;
import com.josivansilva.business.services.UserService;
import com.josivansilva.business.services.impl.UserServiceImpl;
import com.josivansilva.constants.Constants;
import com.josivansilva.exceptions.BusinessException;
import com.josivansilva.util.Pagination;
import com.josivansilva.util.Util;

/**
 * User Controller.
 * 
 * @author Josivan Silva
 *
 */
@ManagedBean
@SessionScoped
public class UserController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger (UserController.class);
	/**
	 * user login attributes 
	 */
	private String username = "username";
	private String pwd;
	private boolean adminRole;
	/**
	 * user list filter attributes
	 */
	private String usernameFilter;
	private boolean adminRoleFilter;
	/**
	 * user form attributes
	 */
	private String idUserForm;
	private String usernameForm;
	private String pwdForm;
	private String emailForm;
	private boolean adminRoleForm = false;
	
	private List<User> userList;
	private UserService userService = UserServiceImpl.getInstance();
	private Pagination pagination;
	
	private String removeItems;
	
	private boolean isLogged = false;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public boolean isAdminRole() {
		return adminRole;
	}
	public void setAdminRole(boolean adminRole) {
		this.adminRole = adminRole;
	}
	public String getUsernameFilter() {
		return usernameFilter;
	}
	public void setUsernameFilter(String usernameFilter) {
		this.usernameFilter = usernameFilter;
	}
	public boolean isAdminRoleFilter() {
		return adminRoleFilter;
	}
	public void setAdminRoleFilter (boolean adminRoleFilter) {
		this.adminRoleFilter = adminRoleFilter;
	}
	public String getIdUserForm() {
		return idUserForm;
	}
	public void setIdUserForm(String idUserForm) {
		this.idUserForm = idUserForm;
	}
	public String getUsernameForm() {
		return usernameForm;
	}
	public void setUsernameForm(String usernameForm) {
		this.usernameForm = usernameForm;
	}
	public String getPwdForm() {
		return pwdForm;
	}
	public void setPwdForm(String pwdForm) {
		this.pwdForm = pwdForm;
	}
	public String getEmailForm() {
		return emailForm;
	}
	public void setEmailForm(String emailForm) {
		this.emailForm = emailForm;
	}
	public boolean isAdminRoleForm() {
		return adminRoleForm;
	}
	public void setAdminRoleForm(boolean adminRoleForm) {
		this.adminRoleForm = adminRoleForm;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}	
	public String getRemoveItems() {
		return removeItems;
	}
	public void setRemoveItems(String removeItems) {
		this.removeItems = removeItems;
	}
	public boolean isLogged() {
		return isLogged;
	}
	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}
	
	@PostConstruct
    public void init() {
		logger.info ("Start executing the method init().");
		configPagination ();
		searchByFilter ();
		resetUserForm ();
    }
	
	/**
	 * From the logged user, initializes the username and admin role attributes.
	 */
	private void initUser () {
		logger.info ("Start executing the method initUser().");
		if (isLogged()) {
			User loggedUser = getLoggedUser ();
			this.setUsername (loggedUser.getUSERNAME());
			this.setAdminRole (loggedUser.isROLE_ADMIN());
		}
		logger.info ("Finish executing the method initUser().");
	}
	
	/**
	 * Resets the user form.
	 */
	private void resetUserForm () {
		this.idUserForm   = "";
		this.usernameForm = "username";
		this.emailForm    = "email";		
	}
	
	/**
	 * Gets the logged user from the session.
	 * 
	 * @return an instance of user.
	 */
	public User getLoggedUser () {
		User loggedUser = null;
		loggedUser = (User) FacesContext
    							.getCurrentInstance()
    								.getExternalContext()
    									.getSessionMap()
    										.get (Constants.LOGGED_USER); 
		return loggedUser;
	}
	
	/**
	 * Performs the user login.
	 */
	public String doLogin () {
		logger.info ("Start executing the method doLogin().");
		String toPage = "login?error=loginError&faces-redirect=true";
		if (Util.isNonEmpty (username) && Util.isNonEmpty (pwd)) {
			User user = new User();
			user.setUSERNAME (this.username);
			user.setPWD (this.pwd);
			try {
				isLogged = this.userService.doLogin (user);
				logger.info ("isLogged [" + isLogged +"]");
				if (isLogged) {
					User foundUser = this.userService.findByUsernameAndPwd (user);
					FacesContext
						.getCurrentInstance()
							.getExternalContext()
								.getSessionMap().put (Constants.LOGGED_USER, foundUser);
					initUser ();
					toPage = "userList?faces-redirect=true";
				}
			} catch (BusinessException e) {
				String error = "An error occurred while performing the user login. " + e.getMessage();
				logger.error (error);
			}
		}
		logger.info ("Finish executing the method doLogin().");
		return toPage;
	}
	
	/**
	 * Performs the search according with the specified filter.
	 */
	public void searchByFilter () {
		logger.info ("Start executing the method searchByFilter().");
		User userFilter = new User();
		userList = new ArrayList<User>();
		if (Util.isNonEmpty (usernameFilter)) {
			userFilter.setUSERNAME (usernameFilter);
		}
		userFilter.setROLE_ADMIN (isAdminRoleFilter());
		logger.info ("usernameFilter [" + usernameFilter + "]");
		logger.info ("isAdminRoleFilter [" + isAdminRoleFilter() + "]");		
		try {
			userList = userService.findByFilter (userFilter, this.pagination);
			logger.info ("userList.size() [" + userList.size() + "]");
		} catch (BusinessException e) {
			String error = "An error occurred while searching by filter. " + e.getMessage();
			logger.error (error);
		}
		logger.info ("Finish executing the method searchByFilter().");		
	}
	
	/**
	 * Performs the user save operation.
	 */
	public String save () {
		logger.info ("Start executing the method save().");
		String toPage = "userList?faces-redirect=true";
		User user = new User ();
		user.setUSERNAME (this.usernameForm);
		user.setPWD (this.pwdForm);
		user.setEMAIL(this.emailForm);
		user.setROLE_ADMIN (this.adminRoleForm);
		try {
			if (this.idUserForm != null && !"".equals(this.idUserForm)) {
				user.setID_USER (new Integer (this.idUserForm));
				int affectedRows = userService.update (user);
				if (affectedRows > 0) {
					logger.info ("The user [" + user.getUSERNAME() + "] has been successfully updated.");
					toPage = "userList?status=saveSuccess&faces-redirect=true";
				}				
			} else {
				int affectedRows = userService.create (user);
				if (affectedRows > 0) {
					logger.info ("The user [" + user.getUSERNAME() + "] has been successfully created.");
					toPage = "userList?status=saveSuccess&faces-redirect=true";
				}
			}
		} catch (BusinessException e) {
			String error = "An error occurred while saving an user. " + e.getMessage();
			logger.error (error);
		}
		searchByFilter ();
		resetUserForm ();
		logger.info ("Finish executing the method save().");
		return toPage;		
	}
	
	/**
	 * Performs the user remove operation.
	 */
	public String getRemove () {
		logger.info ("\n\n\nStart executing the method getRemove.\n\n\n");
		String[] removeItemsArr     = null;
		List<User> userToRemoveList = new ArrayList<User>();
		User[] userToRemoveArr      = null;		
		if (removeItems != null && !"".equals(removeItems)) {
			removeItemsArr = removeItems.split(",");				
			if (removeItemsArr != null) {
				for (String idUser : removeItemsArr) {
					User userToRemove = new User();
					userToRemove.setID_USER (new Integer (idUser));
					userToRemoveList.add (userToRemove);
					if (logger.isDebugEnabled()) {
						logger.info ("Adding user to remove with id [" + idUser + "]");
					}
				}
				userToRemoveArr = userToRemoveList.toArray (new User[userToRemoveList.size()]);
				try {
					this.userService.removeAll (userToRemoveArr);				
				} catch (BusinessException e) {
					String error = "An error occurred while removing users. " + e.getMessage();
					logger.error (error);
				}
			}
		}	
		searchByFilter ();
		logger.info ("\n\n\nFinish executing the method getRemove.\n\n\n");
		return null;		
	}
	
	
	/**
	 * Performs the user logout.
	 */
	public String logout() {
		logger.info ("Start executing the method logout().");
		String toPage = "login?faces-redirect=true";
		FacesContext
	    	.getCurrentInstance()
	    		.getExternalContext()
	    			.getSessionMap()
	    				.remove (Constants.LOGGED_USER);
		this.isLogged = false;
		logger.info ("Finish executing the method logout().");
	    return toPage;
	  }
	
	/**
	 * Configures the pagination.
	 */
	public void configPagination () {
		logger.info ("Start executing the method configPagination().");
		try {			
			if (this.pagination == null) {
				this.pagination = new Pagination ();
			}			
			float rowCount  = this.userService.findRowCount();
			this.pagination.setRows (rowCount);
			this.pagination.setPageRows (Constants.PAGINATION_DEFAULT_PAGE_ROWS);			
			if (logger.isDebugEnabled()) {
				logger.info ("this.pagination.getRows() [" + this.pagination.getRows() + "]");
				logger.info ("this.pagination.getPageRows() [" + this.pagination.getPageRows() + "]");
				logger.info ("this.pagination.getPageNumber() [" + this.pagination.getPageNumber() + "]");
				logger.info ("this.pagination.getLimit() [" + this.pagination.getLimit() + "]");
			}
		} catch (BusinessException e) {
			String error = "An error occurred while finding the row count. " + e.getMessage();
			logger.error (error);
		}
		logger.info ("Finish executing the method configPagination().");
	}
	
	/**
	 * Performs the search according with the specified filter and pagination.
	 * 
	 * @param event the action listener event.
	 */
	public void searchByFilterListener (ActionEvent event) {
		logger.info ("\n\n\nStart executing the method searchByFilterListener().\n\n\n");
		String toPage = (String) event.getComponent().getAttributes().get (Constants.PAGINATION_ATTR_TO_PAGE);
		if (toPage.equals (Constants.PAGINATION_FIRST)) {
			this.pagination.setPageNumber (1);
		} else if (toPage.equals (Constants.PAGINATION_PREVIOUS)) {
			int previous = this.pagination.getPrevious();
			this.pagination.setPageNumber (previous);
		} else if (toPage.equals (Constants.PAGINATION_NEXT)) {
			int next = this.pagination.getNext();
			this.pagination.setPageNumber (next);			
		} else if (toPage.equals (Constants.PAGINATION_LAST)) {
			int last = pagination.getLast();
			this.pagination.setPageNumber (last);
		}
		configPagination ();
		searchByFilter ();
		if (logger.isDebugEnabled()) {
			logger.info ("this.pagination.getFirst() [" + this.pagination.getFirst() + "]");
			logger.info ("this.pagination.getPrevious() [" + this.pagination.getPrevious() + "]");
			logger.info ("this.pagination.getNext() [" + this.pagination.getNext() + "]");
			logger.info ("this.pagination.getLast() [" + this.pagination.getLast() + "]");
		}		
		logger.info ("\n\n\nFinish executing the method searchByFilterListener().\n\n\n");
	}
	
	/**
	 * Performs the edit action according with the specified id.
	 * 
	 * @param event the action listener event.
	 */
	public void editListener (ActionEvent event) {
		logger.info ("Start executing the method editListener.");
		User user      = null;
		User foundUser = null;
		String idUser  = null;
		FacesContext ctx = FacesContext.getCurrentInstance();
        idUser = (String) ctx.getExternalContext().getRequestParameterMap().get ("idUser");
        user = new User ();
        user.setID_USER (new Integer (idUser));
        try {
			foundUser = this.userService.findById (user);
			this.idUserForm   = foundUser.getID_USER().toString();
			this.usernameForm = foundUser.getUSERNAME();
			this.emailForm    = foundUser.getEMAIL();
			this.setAdminRoleForm (foundUser.isROLE_ADMIN());
			logger.info ("foundUser.getUSERNAME()" + foundUser.getUSERNAME());
		} catch (BusinessException e) {
			String error = "An error occurred while finding the user by id. " + e.getMessage();
			logger.error (error);
		}
        logger.info ("Finish executing the method editListener.");
    }	
	
	
}