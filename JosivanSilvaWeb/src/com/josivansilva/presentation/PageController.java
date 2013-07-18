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
import com.josivansilva.business.entities.Page;
import com.josivansilva.business.services.MenuService;
import com.josivansilva.business.services.PageService;
import com.josivansilva.business.services.impl.MenuServiceImpl;
import com.josivansilva.business.services.impl.PageServiceImpl;
import com.josivansilva.constants.Constants;
import com.josivansilva.exceptions.BusinessException;
import com.josivansilva.util.Pagination;
import com.josivansilva.util.Util;


/**
 * Page Controller.
 * 
 * @author Josivan Silva
 *
 */
@ManagedBean
@RequestScoped
public class PageController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger (PageController.class);
	
	/**
	 * section list filter attributes
	 */
	private String nameFilter;
	/**
	 * name form attributes
	 */
	private String idPageForm;
	private String idMenuForm;
	private String nameForm;
	private String contentForm;
	private String urlForm;
	
	private List<Page> pageList;
	private List<Menu> menuList;
	private PageService pageService = PageServiceImpl.getInstance();
	private MenuService menuService = MenuServiceImpl.getInstance();
	private Pagination pagination;
	private String removeItems;
	private Map<String,Object> menuMap = new LinkedHashMap<String,Object>();
	
	private String remove;
	
	public String getNameFilter() {
		return nameFilter;
	}

	public void setNameFilter(String nameFilter) {
		this.nameFilter = nameFilter;
	}

	public String getIdPageForm() {
		return idPageForm;
	}

	public void setIdPageForm(String idPageForm) {
		this.idPageForm = idPageForm;
	}

	public String getIdMenuForm() {
		return idMenuForm;
	}

	public void setIdMenuForm(String idMenuForm) {
		this.idMenuForm = idMenuForm;
	}

	public String getNameForm() {
		return nameForm;
	}

	public void setNameForm(String nameForm) {
		this.nameForm = nameForm;
	}

	public String getContentForm() {
		return contentForm;
	}

	public void setContentForm(String contentForm) {
		this.contentForm = contentForm;
	}

	public String getUrlForm() {
		return urlForm;
	}

	public void setUrlForm(String urlForm) {
		this.urlForm = urlForm;
	}
	
	public List<Page> getPageList() {
		return pageList;
	}

	public void setPageList(List<Page> pageList) {
		this.pageList = pageList;
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
	
	public Map<String, Object> getMenuMap() {
		return menuMap;
	}

	public void setMenuMap(Map<String, Object> menuMap) {
		this.menuMap = menuMap;
	}
	
	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
	
	public void setRemove(String remove) {
		this.remove = remove;
	}

	@PostConstruct
    public void init() {
		logger.info ("Start executing the method init().");
		populateMenuMap ();
		configPagination ();
		searchByFilter ();
		//resetForm ();
    }
	
	/**
	 * Populates the menu map.
	 */
	private void populateMenuMap () {
		logger.info ("Start executing the method populateMenuMap().");
		try {
			this.menuList = menuService.findAll ();
			for (Menu menu : this.menuList) {
				menuMap.put (menu.getNM_MENU(), menu.getID_MENU()); //label, value
			}							
		} catch (BusinessException e) {
			String error = "An error occurred while performing the service to find all menus. " + e.getMessage();
			logger.error (error);
		}		
		logger.info ("Finish executing the method populateMenuMap().");
	}
	
	/**
	 * Resets the user form.
	 */
	private void resetForm () {
		this.idPageForm  = "";
		this.idMenuForm  = "";
		this.nameForm    = "name";
		this.contentForm = "content";
		this.urlForm     = "url";
	}
	
	/**
	 * Performs the search according with the specified filter.
	 */
	public void searchByFilter () {
		logger.info ("Start executing the method searchByFilter().");
		Page pageFilter = new Page();
		pageList = new ArrayList<Page>();
		if (Util.isNonEmpty (nameFilter)) {
			pageFilter.setNM_PAGE (nameFilter);
		}
		logger.info ("nameFilter [" + nameFilter + "]");
		try {
			pageList = pageService.findByFilter (pageFilter, this.pagination);
			logger.info ("pageList.size() [" + pageList.size() + "]");
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
		logger.info ("\n\n\nStart executing the method save().\n\n\n");
		String toPage = "pageList?faces-redirect=true";
		Page page = new Page ();
		page.setNM_PAGE (this.nameForm);
		page.setID_MENU (new Integer (this.idMenuForm));
		page.setCONTENT(this.contentForm);
		page.setURL(this.urlForm);
		try {
			if (this.idPageForm != null && !"".equals(this.idPageForm)) {
				page.setID_PAGE (new Integer (this.idPageForm));
				int affectedRows = pageService.update (page);
				if (affectedRows > 0) {
					logger.info ("The page [" + page.getNM_PAGE() + "] has been successfully updated.");
					toPage = "pageList?status=saveSuccess&faces-redirect=true";
				}				
			} else {
				int affectedRows = pageService.create (page);
				if (affectedRows > 0) {
					logger.info ("The page [" + page.getNM_PAGE() + "] has been successfully created.");
					toPage = "pageList?status=saveSuccess&faces-redirect=true";
				}
			}
		} catch (BusinessException e) {
			String error = "An error occurred while saving. " + e.getMessage();
			logger.error (error);
		}
		searchByFilter ();
		//resetForm ();
		logger.info ("\n\n\nFinish executing the method save().\n\n\n");
		return toPage;
	}
	
	/**
	 * Performs the user remove operation.
	 */
	public String getRemove () {
		logger.info ("Start executing the method getRemove.");
		String[] removeItemsArr     = null;
		List<Page> pageToRemoveList = new ArrayList<Page>();
		Page[] pageToRemoveArr      = null;		
		if (removeItems != null && !"".equals(removeItems)) {
			removeItemsArr = removeItems.split(",");				
			if (removeItemsArr != null) {
				for (String idPage : removeItemsArr) {
					Page pageToRemove = new Page();
					pageToRemove.setID_PAGE (new Integer (idPage));					
					pageToRemoveList.add (pageToRemove);
					if (logger.isDebugEnabled()) {
						logger.info ("Adding user to remove with id [" + idPage + "]");
					}
				}
				pageToRemoveArr = pageToRemoveList.toArray (new Page[pageToRemoveList.size()]);
				try {
					this.pageService.removeAll (pageToRemoveArr);				
				} catch (BusinessException e) {
					String error = "An error occurred while removing users. " + e.getMessage();
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
				float rowCount  = this.pageService.findRowCount();
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
	public String editAction () {
		logger.info ("Start executing the method editAction.");
		String toPage = "pageUpdate";
		Page page                 = null;
		Page foundPage            = null;
		String idPage             = null;
		Map<String,String> params = null;
		FacesContext ctx = FacesContext.getCurrentInstance();
        params = ctx.getExternalContext().getRequestParameterMap();
		idPage = params.get("idPage");        
        page = new Page ();
        page.setID_PAGE (new Integer (idPage));        
        try {
			foundPage = this.pageService.findById (page);
			this.idPageForm  = foundPage.getID_PAGE().toString();
			this.idMenuForm  = foundPage.getID_MENU().toString();
			this.nameForm    = foundPage.getNM_PAGE();
			this.contentForm = foundPage.getCONTENT();
			this.urlForm     = foundPage.getURL();
			logger.info ("foundPage.getNM_PAGE() " + foundPage.getNM_PAGE());
		} catch (BusinessException e) {
			String error = "An error occurred while finding the page by id. " + e.getMessage();
			logger.error (error);
		}
        logger.info ("Finish executing the method editAction.");
        return toPage;                
    }	

}
