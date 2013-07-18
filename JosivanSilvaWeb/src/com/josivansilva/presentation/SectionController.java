package com.josivansilva.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import com.josivansilva.business.entities.Section;
import com.josivansilva.business.services.SectionService;
import com.josivansilva.business.services.impl.SectionServiceImpl;
import com.josivansilva.constants.Constants;
import com.josivansilva.exceptions.BusinessException;
import com.josivansilva.util.Pagination;
import com.josivansilva.util.Util;


/**
 * Section Controller.
 * 
 * @author Josivan Silva
 *
 */
@ManagedBean
@RequestScoped
public class SectionController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger (SectionController.class);
	
	/**
	 * section list filter attributes
	 */
	private String nameFilter;
	/**
	 * name form attributes
	 */
	private String idSectionForm;
	private String nameForm;
	
	private List<Section> sectionList;
	private SectionService sectionService = SectionServiceImpl.getInstance();
	private Pagination pagination;
	private String removeItems;
	private String remove;
	
	public String getNameFilter() {
		return nameFilter;
	}
	public void setNameFilter(String nameFilter) {
		this.nameFilter = nameFilter;
	}
	public String getIdSectionForm() {
		return idSectionForm;
	}
	public void setIdSectionForm(String idSectionForm) {
		this.idSectionForm = idSectionForm;
	}
	public String getNameForm() {
		return nameForm;
	}
	public void setNameForm(String nameForm) {
		this.nameForm = nameForm;
	}
	public List<Section> getSectionList() {
		return sectionList;
	}
	public void setSectionList(List<Section> sectionList) {
		this.sectionList = sectionList;
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
	public void setRemove(String remove) {
		this.remove = remove;
	}
	@PostConstruct
    public void init() {
		logger.info ("Start executing the method init().");
		configPagination ();
		searchByFilter ();
		resetForm ();
    }
	
	/**
	 * Resets the user form.
	 */
	private void resetForm () {
		this.idSectionForm = "";
		this.nameForm      = "name";		
	}
	
	/**
	 * Performs the search according with the specified filter.
	 */
	public void searchByFilter () {
		logger.info ("Start executing the method searchByFilter().");
		Section sectionFilter = new Section();
		sectionList = new ArrayList<Section>();
		if (Util.isNonEmpty (nameFilter)) {
			sectionFilter.setNM_SECTION(nameFilter);
		}		
		logger.info ("nameFilter [" + nameFilter + "]");				
		try {
			sectionList = sectionService.findByFilter (sectionFilter, this.pagination);
			logger.info ("sectionList.size() [" + sectionList.size() + "]");
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
		String toPage = "sectionList?faces-redirect=true";
		Section section = new Section ();
		section.setNM_SECTION (this.nameForm);
		try {
			if (this.idSectionForm != null && !"".equals(this.idSectionForm)) {
				section.setID_SECTION (new Integer (this.idSectionForm));
				int affectedRows = sectionService.update (section);
				if (affectedRows > 0) {
					logger.info ("The section [" + section.getNM_SECTION() + "] has been successfully updated.");
					toPage = "sectionList?status=saveSuccess&faces-redirect=true";
				}				
			} else {
				int affectedRows = sectionService.create (section);
				if (affectedRows > 0) {
					logger.info ("The section [" + section.getNM_SECTION() + "] has been successfully created.");
					toPage = "sectionList?status=saveSuccess&faces-redirect=true";
				}
			}
		} catch (BusinessException e) {
			String error = "An error occurred while saving an section. " + e.getMessage();
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
		logger.info ("\n\n\nStart executing the method getRemove.\n\n\n");
		String[] removeItemsArr           = null;
		List<Section> sectionToRemoveList = new ArrayList<Section>();
		Section[] sectionToRemoveArr      = null;
		if (removeItems != null && !"".equals(removeItems)) {
			removeItemsArr = removeItems.split(",");				
			if (removeItemsArr != null) {
				for (String idSection : removeItemsArr) {
					Section sectionToRemove = new Section();
					sectionToRemove.setID_SECTION (new Integer (idSection));
					sectionToRemoveList.add (sectionToRemove);
					if (logger.isDebugEnabled()) {
						logger.info ("Adding section to remove with id [" + idSection + "]");
					}
				}
				sectionToRemoveArr = sectionToRemoveList.toArray (new Section[sectionToRemoveList.size()]);
				try {
					this.sectionService.removeAll (sectionToRemoveArr);				
				} catch (BusinessException e) {
					String error = "An error occurred while removing sections. " + e.getMessage();
					logger.error (error);
				}
			}
		}
		searchByFilter ();
		logger.info ("\n\n\nFinish executing the method getRemove.\n\n\n");
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
				float rowCount  = this.sectionService.findRowCount();
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
		//configPagination ();
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
		Section section      = null;
		Section foundSection = null;
		String idSection = null;
		FacesContext ctx = FacesContext.getCurrentInstance();
        idSection = (String) ctx.getExternalContext().getRequestParameterMap().get ("idSection");
        section = new Section ();
        section.setID_SECTION (new Integer (idSection));        
        try {
			foundSection = this.sectionService.findById (section);
			this.idSectionForm = foundSection.getID_SECTION().toString();
			this.nameForm      = foundSection.getNM_SECTION();
			logger.info ("foundSection.getNM_SECTION()" + foundSection.getNM_SECTION());
		} catch (BusinessException e) {
			String error = "An error occurred while finding the section by id. " + e.getMessage();
			logger.error (error);
		}
        logger.info ("Finish executing the method editListener.");
    }	

}
