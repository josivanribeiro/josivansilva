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

import com.josivansilva.business.entities.Section;
import com.josivansilva.business.entities.Topic;
import com.josivansilva.business.services.SectionService;
import com.josivansilva.business.services.TopicService;
import com.josivansilva.business.services.impl.SectionServiceImpl;
import com.josivansilva.business.services.impl.TopicServiceImpl;
import com.josivansilva.constants.Constants;
import com.josivansilva.exceptions.BusinessException;
import com.josivansilva.util.Pagination;
import com.josivansilva.util.Util;


/**
 * Topic Controller.
 * 
 * @author Josivan Silva
 *
 */
@ManagedBean
@RequestScoped
public class TopicController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger (TopicController.class);
	
	/**
	 * section list filter attributes
	 */
	private String nameFilter;
	private String idSectionFilter = "0";
	/**
	 * name form attributes
	 */
	private String idTopicForm;
	private String idSectionForm;
	private String nameForm;
	
	private List<Topic> topicList;
	private List<Section> sectionList;
	private TopicService topicService     = TopicServiceImpl.getInstance();
	private SectionService sectionService = SectionServiceImpl.getInstance();
	private Pagination pagination;
	
	private Map<String,Object> sectionMap = new LinkedHashMap<String,Object>();
	private String removeItems;
	private String remove;
	
	public String getNameFilter() {
		return nameFilter;
	}

	public void setNameFilter(String nameFilter) {
		this.nameFilter = nameFilter;
	}

	public String getIdSectionFilter() {
		return idSectionFilter;
	}

	public void setIdSectionFilter(String idSectionFilter) {
		this.idSectionFilter = idSectionFilter;
	}

	public String getIdTopicForm() {
		return idTopicForm;
	}

	public void setIdTopicForm(String idTopicForm) {
		this.idTopicForm = idTopicForm;
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

	public List<Topic> getTopicList() {
		return topicList;
	}

	public void setTopicList(List<Topic> topicList) {
		this.topicList = topicList;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	public Map<String, Object> getSectionMap() {
		return sectionMap;
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
		logger.info ("\n\n\nStart executing the method init().\n\n\n");
		populateSectionMap ();
		configPagination ();
		searchByFilter ();
		resetForm ();
		
		logger.info ("\n\n\nFinish executing the method init().\n\n\n");
    }
	
	/**
	 * Populates the section map.
	 */
	private void populateSectionMap () {
		logger.info ("Start executing the method populateSectionMap().");
		try {
			this.sectionList = sectionService.findAll ();
			for (Section section : this.sectionList) {
				sectionMap.put (section.getNM_SECTION(), section.getID_SECTION()); //label, value
			}
			logger.info ("\n\n\n sectionList.size()" + sectionList.size() + "\n\n\n");				
		} catch (BusinessException e) {
			String error = "An error occurred while performing the service to find all sections. " + e.getMessage();
			logger.error (error);
		}		
		logger.info ("Finish executing the method populateSectionMap().");
	}
	
	/**
	 * Resets the topic form.
	 */
	private void resetForm () {
		this.idTopicForm   = "";
		this.idSectionForm = "";
		this.nameForm      = "name";		
	}
	
	/**
	 * Performs the search according with the specified filter.
	 */
	public void searchByFilter () {
		logger.info ("Start executing the method searchByFilter().");
		Topic topicFilter = new Topic();
		topicList = new ArrayList<Topic>();
		if (Util.isNonEmpty (nameFilter)) {
			topicFilter.setNM_TOPIC (nameFilter);
		}
		if (Util.isNonEmpty(idSectionFilter)) {
			topicFilter.setID_SECTION( new Integer (idSectionFilter));
		}
		logger.info ("nameFilter [" + nameFilter + "]");
		logger.info ("idSectionFilter [" + idSectionFilter + "]");
		try {
			topicList = topicService.findByFilter (topicFilter, this.pagination);
			logger.info ("topicList.size() [" + topicList.size() + "]");
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
		String toPage = "topicList?faces-redirect=true";
		Topic topic = new Topic ();
		if (this.idSectionForm != null && !"".equals(this.idSectionForm)) {
			topic.setID_SECTION (new Integer(this.idSectionForm));
		}		
		topic.setNM_TOPIC (this.nameForm);
		try {
			if (this.idTopicForm != null && !"".equals (this.idTopicForm)) {
				topic.setID_TOPIC (new Integer (this.idTopicForm));
				int affectedRows = topicService.update (topic);
				if (affectedRows > 0) {
					logger.info ("The topic [" + topic.getNM_TOPIC() + "] has been successfully updated.");
					toPage = "topicList?status=saveSuccess&faces-redirect=true";
				}				
			} else {
				int affectedRows = topicService.create (topic);
				if (affectedRows > 0) {
					logger.info ("The topic [" + topic.getNM_TOPIC() + "] has been successfully created.");
					toPage = "topicList?status=saveSuccess&faces-redirect=true";
				}
			}
		} catch (BusinessException e) {
			String error = "An error occurred while saving an user. " + e.getMessage();
			logger.error (error);
		}
		searchByFilter ();
		resetForm ();
		logger.info ("\n\n\nFinish executing the method save().\n\n\n");
		return toPage;
	}
	
	/**
	 * Performs the user remove operation.
	 */
	public String getRemove () {
		logger.info ("Start executing the method getRemove.");
		String[] removeItemsArr           = null;
		List<Topic> topicToRemoveList = new ArrayList<Topic>();
		Topic[] topicToRemoveArr      = null;
		if (removeItems != null && !"".equals(removeItems)) {
			removeItemsArr = removeItems.split(",");				
			if (removeItemsArr != null) {
				for (String idTopic : removeItemsArr) {
					Topic topicToRemove = new Topic();
					topicToRemove.setID_TOPIC(new Integer (idTopic));
					topicToRemoveList.add (topicToRemove);
					if (logger.isDebugEnabled()) {
						logger.info ("Adding topic to remove with id [" + idTopic + "]");
					}
				}
				topicToRemoveArr = topicToRemoveList.toArray (new Topic[topicToRemoveList.size()]);
				try {
					this.topicService.removeAll (topicToRemoveArr);
				} catch (BusinessException e) {
					String error = "An error occurred while removing topics. " + e.getMessage();
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
				float rowCount  = this.topicService.findRowCount();
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
		Topic topic      = null;
		Topic foundTopic = null;
		String idTopic   = null;
		FacesContext ctx = FacesContext.getCurrentInstance();
        idTopic = (String) ctx.getExternalContext().getRequestParameterMap().get ("idTopic");
        topic = new Topic ();
        topic.setID_TOPIC (new Integer (idTopic));
        try {
			foundTopic = this.topicService.findById (topic);
			this.idTopicForm   = foundTopic.getID_TOPIC().toString();
			this.idSectionForm = foundTopic.getID_SECTION().toString();
			this.nameForm      = foundTopic.getNM_TOPIC();			
		} catch (BusinessException e) {
			String error = "An error occurred while finding the section by id. " + e.getMessage();
			logger.error (error);
		}
        logger.info ("Finish executing the method editListener.");
    }

}
