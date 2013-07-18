package com.josivansilva.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import com.josivansilva.business.entities.Menu;
import com.josivansilva.business.services.MenuService;
import com.josivansilva.business.services.impl.MenuServiceImpl;
import com.josivansilva.constants.Constants;
import com.josivansilva.exceptions.BusinessException;
import com.josivansilva.util.Pagination;
import com.josivansilva.util.Util;


/**
 * Menu Controller.
 * 
 * @author Josivan Silva
 *
 */
@ManagedBean
@RequestScoped
public class MenuController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger (MenuController.class);
	
	/**
	 * section list filter attributes
	 */
	private String nameFilter;
	private String idMenuFatherFilter = "";
	/**
	 * name form attributes
	 */
	private String idMenuForm;
	private String idMenuFatherForm;
	private String nameForm;
	
	private List<Menu> menuList;
	private List<Menu> menuFatherList;
	private MenuService menuService = MenuServiceImpl.getInstance();
	private Pagination pagination;
	private String removeItems;
	
	private Map<String,Object> menuFatherMap = new LinkedHashMap<String,Object>();
	
	public String getNameFilter() {
		return nameFilter;
	}

	public void setNameFilter(String nameFilter) {
		this.nameFilter = nameFilter;
	}

	public String getIdMenuFatherFilter() {
		return idMenuFatherFilter;
	}

	public void setIdMenuFatherFilter(String idMenuFatherFilter) {
		this.idMenuFatherFilter = idMenuFatherFilter;
	}

	public String getIdMenuForm() {
		return idMenuForm;
	}

	public void setIdMenuForm(String idMenuForm) {
		this.idMenuForm = idMenuForm;
	}

	public String getIdMenuFatherForm() {
		return idMenuFatherForm;
	}

	public void setIdMenuFatherForm(String idMenuFatherForm) {
		this.idMenuFatherForm = idMenuFatherForm;
	}

	public String getNameForm() {
		return nameForm;
	}

	public void setNameForm(String nameForm) {
		this.nameForm = nameForm;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
	
	public List<Menu> getMenuFatherList() {
		return menuFatherList;
	}

	public void setMenuFatherList(List<Menu> menuFatherList) {
		this.menuFatherList = menuFatherList;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public Map<String, Object> getMenuFatherMap() {
		return menuFatherMap;
	}
	public String getRemoveItems() {
		return removeItems;
	}

	public void setRemoveItems(String removeItems) {
		this.removeItems = removeItems;
	}

	@PostConstruct
    public void init() {
		logger.info ("Start executing the method init().");
		populateMenuFatherMap ();
		configPagination ();
		searchByFilter ();
		resetForm ();
		logger.info ("Finish executing the method init().");
    }
	
	/**
	 * Populates the section map.
	 */
	private void populateMenuFatherMap () {
		logger.info ("Start executing the method populateMenuFatherMap().");
		try {			
			this.menuFatherList = menuService.findAll ();
			for (Menu menuFather : menuFatherList) {
				menuFatherMap.put (menuFather.getNM_MENU(), menuFather.getID_MENU()); //label, value
			}			
		} catch (BusinessException e) {
			String error = "An error occurred while performing the service to find all sections. " + e.getMessage();
			logger.error (error);
		}		
		logger.info ("Finish executing the method populateMenuFatherMap().");
	}
	
	/**
	 * Resets the menu form.
	 */
	private void resetForm () {
		this.idMenuForm       = "";
		this.idMenuFatherForm = "";
		this.nameForm         = "name";		
	}
	
	/**
	 * Performs the search according with the specified filter.
	 */
	public void searchByFilter () {
		logger.info ("Start executing the method searchByFilter().");
		Menu menuFilter = new Menu();
		menuList = new ArrayList<Menu>();
		if (Util.isNonEmpty (nameFilter)) {
			menuFilter.setNM_MENU (nameFilter);
		}
		if (Util.isNonEmpty(idMenuFatherFilter)) {
			menuFilter.setID_MENU_FATHER (new Integer (idMenuFatherFilter));
		}
		logger.info ("nameFilter [" + nameFilter + "]");
		logger.info ("idMenuFatherFilter [" + idMenuFatherFilter + "]");
		try {
			menuList = menuService.findByFilter (menuFilter, this.pagination);
			logger.info ("menuList.size() [" + menuList.size() + "]");
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
		String toPage = "menuList?faces-redirect=true";
		Menu menu = new Menu ();
		if (this.idMenuFatherForm == "") {
			menu.setID_MENU_FATHER (null);			
		} else {
			menu.setID_MENU_FATHER (new Integer(this.idMenuFatherForm));
		}		
		menu.setNM_MENU (this.nameForm);		
		try {
			if (this.idMenuForm != null && !"".equals (this.idMenuForm)) {
				menu.setID_MENU (new Integer (this.idMenuForm));
				int affectedRows = menuService.update (menu);
				if (affectedRows > 0) {
					logger.info ("The menu [" + menu.getNM_MENU() + "] has been successfully updated.");
					toPage = "menuList?status=saveSuccess&faces-redirect=true";
				}
			} else {
				int affectedRows = menuService.create (menu);
				if (affectedRows > 0) {
					logger.info ("The menu [" + menu.getNM_MENU() + "] has been successfully created.");
					toPage = "menuList?status=saveSuccess&faces-redirect=true";
				}
			}
		} catch (BusinessException e) {
			String error = "An error occurred while saving. " + e.getMessage();
			logger.error (error);
		}
		searchByFilter ();
		resetForm ();
		logger.info ("Finish executing the method save().");
		return toPage;
	}
	
	/**
	 * Performs the user remove operation.
	 */
	public String getRemove () {
		logger.info ("Start executing the method getRemove.");
		String[] removeItemsArr     = null;
		List<Menu> menuToRemoveList = new ArrayList<Menu>();
		Menu[] menuToRemoveArr      = null;		
		if (removeItems != null && !"".equals(removeItems)) {
			removeItemsArr = removeItems.split(",");				
			if (removeItemsArr != null) {
				for (String idMenu : removeItemsArr) {
					Menu menuToRemove = new Menu();					
					menuToRemove.setID_MENU (new Integer (idMenu));
					menuToRemoveList.add (menuToRemove);
					if (logger.isDebugEnabled()) {
						logger.info ("Adding item to remove with id [" + idMenu + "]");
					}
				}
				menuToRemoveArr = menuToRemoveList.toArray (new Menu[menuToRemoveList.size()]);
				try {
					this.menuService.removeAll (menuToRemoveArr);
				} catch (BusinessException e) {
					String error = "An error occurred while removing. " + e.getMessage();
					logger.error (error);
				}
			}
		}	
		searchByFilter ();
		logger.info ("Finish executing the method getRemove.");
		return null;	
	}
	
	/**
	 * Configures the pagination.
	 */
	public void configPagination () {
		logger.info ("Start executing the method configPagination().");
		try {
			if (this.pagination == null) {
				this.pagination = new Pagination ();
				float rowCount  = this.menuService.findRowCount();
				this.pagination.setRows (rowCount);
				this.pagination.setPageRows (Constants.PAGINATION_DEFAULT_PAGE_ROWS);								
			}
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
		logger.info ("Start executing the method searchByFilterListener().");
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
		logger.info ("Finish executing the method searchByFilterListener().");
	}
	
	/**
	 * Performs the edit action according with the specified id.
	 * 
	 * @param event the action listener event.
	 */
	public void editListener (ActionEvent event) {
		logger.info ("Start executing the method editListener.");
		Menu menu     = null;
		Menu found    = null;
		String idMenu = null;
		FacesContext ctx = FacesContext.getCurrentInstance();
        idMenu = (String) ctx.getExternalContext().getRequestParameterMap().get ("idMenu");
        menu = new Menu ();
        menu.setID_MENU(new Integer (idMenu));
        try {
			found = this.menuService.findById (menu);
			this.idMenuForm = found.getID_MENU().toString();
			this.nameForm   = found.getNM_MENU();
			if (found.getID_MENU_FATHER() != null && found.getID_MENU_FATHER() > 0) {
				this.idMenuFatherForm = found.getID_MENU_FATHER().toString();	
			}									
		} catch (BusinessException e) {
			String error = "An error occurred while finding by id. " + e.getMessage();
			logger.error (error);
		}
        logger.info ("Finish executing the method editListener.");
    }

}
