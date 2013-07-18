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

import com.josivansilva.business.entities.Article;
import com.josivansilva.business.entities.ArticleTopic;
import com.josivansilva.business.entities.Section;
import com.josivansilva.business.entities.Topic;
import com.josivansilva.business.services.ArticleService;
import com.josivansilva.business.services.ArticleTopicService;
import com.josivansilva.business.services.SectionService;
import com.josivansilva.business.services.TopicService;
import com.josivansilva.business.services.impl.ArticleServiceImpl;
import com.josivansilva.business.services.impl.ArticleTopicServiceImpl;
import com.josivansilva.business.services.impl.SectionServiceImpl;
import com.josivansilva.business.services.impl.TopicServiceImpl;
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
public class ArticleController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger (ArticleController.class);
	
	/**
	 * section list filter attributes
	 */
	private String titleFilter;
	/**
	 * name form attributes
	 */
	private String idArticleForm;
	private String idSectionForm;
	private String titleForm;
	private String nameAuthorForm;
	private String contentForm;
		
	private List<Article> articleList;
	private List<Section> sectionList;
	private List<Topic> topicList;	
	
	private ArticleService articleService = ArticleServiceImpl.getInstance();
	private SectionService sectionService = SectionServiceImpl.getInstance();
	private TopicService topicService = TopicServiceImpl.getInstance();
	private ArticleTopicService articleTopicService = ArticleTopicServiceImpl.getInstance(); 
	
	private Pagination pagination;
	private String removeItems;
	private Map<String,Object> sectionMap = new LinkedHashMap<String,Object>();
	private Map<String,Object> topicMap = new LinkedHashMap<String,Object>();
	private String remove;
	private String[] selectedTopics;
	
	public String getTitleFilter() {
		return titleFilter;
	}

	public void setTitleFilter(String titleFilter) {
		this.titleFilter = titleFilter;
	}

	public String getIdArticleForm() {
		return idArticleForm;
	}

	public void setIdArticleForm(String idArticleForm) {
		this.idArticleForm = idArticleForm;
	}

	public String getIdSectionForm() {
		return idSectionForm;
	}

	public void setIdSectionForm(String idSectionForm) {
		this.idSectionForm = idSectionForm;
	}

	public String getTitleForm() {
		return titleForm;
	}

	public void setTitleForm(String titleForm) {
		this.titleForm = titleForm;
	}
	
	public String getNameAuthorForm() {
		return nameAuthorForm;
	}

	public void setNameAuthorForm(String nameAuthorForm) {
		this.nameAuthorForm = nameAuthorForm;
	}

	public String getContentForm() {
		return contentForm;
	}

	public void setContentForm(String contentForm) {
		this.contentForm = contentForm;
	}

	public List<Article> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
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

	public Map<String, Object> getSectionMap() {
		return sectionMap;
	}

	public void setSectionMap(Map<String, Object> sectionMap) {
		this.sectionMap = sectionMap;
	}
	
	public Map<String, Object> getTopicMap() {
		return topicMap;
	}

	public void setTopicMap(Map<String, Object> topicMap) {
		this.topicMap = topicMap;
	}

	public void setRemove(String remove) {
		this.remove = remove;
	}
	
	public String[] getSelectedTopics() {
		return selectedTopics;
	}

	public void setSelectedTopics(String[] selectedTopics) {
		this.selectedTopics = selectedTopics;
	}

	@PostConstruct
    public void init() {
		logger.info ("Start executing the method init().");
		populateSectionMap ();
		populateTopicMap ();
		configPagination ();
		searchByFilter ();
		resetForm ();
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
		} catch (BusinessException e) {
			String error = "An error occurred while performing the service to find all sections. " + e.getMessage();
			logger.error (error);
		}
		logger.info ("Finish executing the method populateSectionMap().");
	}
	
	/**
	 * Populates the topic map.
	 */
	private void populateTopicMap () {
		logger.info ("Start executing the method populateTopicMap().");
		try {
			this.topicList = topicService.findAll ();
			for (Topic topic : this.topicList) {
				topicMap.put (topic.getNM_TOPIC(), topic.getID_TOPIC());				 
			}
		} catch (BusinessException e) {
			String error = "An error occurred while performing the service to find all topics. " + e.getMessage();
			logger.error (error);
		}
		logger.info ("Finish executing the method populateTopicMap().");
	}
	
	/**
	 * Resets the user form.
	 */
	private void resetForm () {
		this.idArticleForm  = "";
		this.idSectionForm  = "";
		this.titleForm      = "title";
		this.nameAuthorForm = "name author";
		this.contentForm    = "content";		
	}
	
	/**
	 * Performs the search according with the specified filter.
	 */
	public void searchByFilter () {
		logger.info ("Start executing the method searchByFilter().");
		Article articleFilter = new Article();
		articleList = new ArrayList<Article>();
		if (Util.isNonEmpty (titleFilter)) {
			articleFilter.setTITLE (titleFilter);
		}
		logger.info ("titleFilter [" + titleFilter + "]");
		try {
			articleList = articleService.findByFilter (articleFilter, this.pagination);
			logger.info ("articleList.size() [" + articleList.size() + "]");
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
		String toPage = "articleList?faces-redirect=true";
		Article article = new Article ();
		article.setTITLE(this.titleForm);
		article.setID_SECTION(new Integer(this.idSectionForm));
		article.setNM_AUTHOR(this.nameAuthorForm);
		article.setCONTENT(this.contentForm);
		try {
			if (this.idArticleForm != null && !"".equals(this.idArticleForm)) {
				article.setID_ARTICLE(new Integer (idArticleForm));
				int affectedRows = articleService.update (article);
				removeArticleTopics (new Integer(idArticleForm));
				ArticleTopic[] articleTopicArr = getArticleTopicToUpdate (new Integer(idArticleForm));
				articleTopicService.create (articleTopicArr);				
				if (affectedRows > 0) {
					logger.info ("The article [" + article.getTITLE() + "] has been successfully updated.");
					toPage = "articleList?status=saveSuccess&faces-redirect=true";
				}
			} else {
				int affectedRows = articleService.create (article);
				int lastInsertedId = articleService.getLastInsertedId();
				logger.info ("lastInsertedId [" + lastInsertedId + "]");				
				ArticleTopic[] articleTopicArr = getArticleTopicToUpdate (lastInsertedId);
				articleTopicService.create (articleTopicArr);
				if (affectedRows > 0) {
					logger.info ("The article [" + article.getTITLE() + "] has been successfully created.");
					toPage = "articleList?status=saveSuccess&faces-redirect=true";
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
	 * Gets the article topics in order to update.
	 * 
	 * @param idArticle the id article.
	 * @return
	 */
	private ArticleTopic[] getArticleTopicToUpdate (Integer idArticle) {
		logger.info ("Start executing the method getArticleTopicToUpdate().");
		List<ArticleTopic> list = new ArrayList<ArticleTopic>();
		ArticleTopic[] articleTopicArr = null;
		for (String idTopic : selectedTopics) {
			ArticleTopic articleTopic = new ArticleTopic ();
			articleTopic.setID_ARTICLE(idArticle);
			articleTopic.setID_TOPIC(new Integer(idTopic));
			list.add (articleTopic);
		}
		articleTopicArr = list.toArray (new ArticleTopic[list.size()]);
		logger.info ("Finish executing the method getArticleTopicToUpdate().");
		return articleTopicArr;
	}
	
	/**
	 * Performs the removal of article topic given an article.
	 * 
	 * @param idArticle the id article.
	 */
	private void removeArticleTopics (Integer idArticle) {
		ArticleTopic[] articleTopicArr = new ArticleTopic[1];
		ArticleTopic articleTopic = new ArticleTopic();
		articleTopic.setID_ARTICLE (idArticle);
		articleTopicArr [0] = articleTopic;
		try {
			this.articleTopicService.remove (articleTopicArr);
		} catch (BusinessException e) {
			String error = "An error occurred while removing article topics. " + e.getMessage();
			logger.error (error);
		}
		
	}
	
	/**
	 * Performs the user remove operation.
	 */
	public String getRemove () {
		logger.info ("Start executing the method getRemove.");
		String[] removeItemsArr           = null;
		List<Article> articleToRemoveList = new ArrayList<Article>();
		Article[] articleToRemoveArr      = null;
		if (removeItems != null && !"".equals(removeItems)) {
			removeItemsArr = removeItems.split(",");				
			if (removeItemsArr != null) {
				for (String idArticle : removeItemsArr) {
					Article articleToRemove = new Article();
					articleToRemove.setID_ARTICLE(new Integer (idArticle));
					articleToRemoveList.add (articleToRemove);
					if (logger.isDebugEnabled()) {
						logger.info ("Adding article to remove with id [" + idArticle + "]");
					}
				}
				articleToRemoveArr = articleToRemoveList.toArray (new Article[articleToRemoveList.size()]);
				try {
					this.articleService.removeAll (articleToRemoveArr);				
				} catch (BusinessException e) {
					String error = "An error occurred while removing articles. " + e.getMessage();
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
				float rowCount  = this.articleService.findRowCount();
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
	 */
	public String editAction () {
		logger.info ("Start executing the method editAction.");
		String toPage = "articleUpdate";
		Article article           = null;
		Article foundArticle      = null;
		String idArticle          = null;
		Map<String,String> params = null;
		FacesContext ctx = FacesContext.getCurrentInstance();
        params = ctx.getExternalContext().getRequestParameterMap();
        idArticle = params.get("idArticle");        
        article = new Article ();
        article.setID_ARTICLE(new Integer (idArticle));        
        try {
			foundArticle = this.articleService.findById (article);
			this.idArticleForm  = foundArticle.getID_ARTICLE().toString();
			this.idSectionForm  = foundArticle.getID_SECTION().toString();
			this.titleForm      = foundArticle.getTITLE();
			this.nameAuthorForm = foundArticle.getNM_AUTHOR();
			this.contentForm    = foundArticle.getCONTENT();
			
			setArticleTopics (new Integer (this.idArticleForm));
			
			logger.info ("foundArticle.getTITLE() " + foundArticle.getTITLE());
		} catch (BusinessException e) {
			String error = "An error occurred while finding the article by id. " + e.getMessage();
			logger.error (error);
		}
        logger.info ("Finish executing the method editAction.");
        return toPage;
    }
	
	/**
	 * Sets the selected article topics.
	 * 
	 * @param idArticle
	 */
	private void setArticleTopics (Integer idArticle) {
		logger.info ("Start executing the method setArticleTopics.");
		List<ArticleTopic> list = new ArrayList<ArticleTopic>();
		ArticleTopic articleTopic = new ArticleTopic();
		articleTopic.setID_ARTICLE (idArticle);
		String[] selectedTopics = null;
		try {
			list = this.articleTopicService.findByArticle (articleTopic);
			selectedTopics = new String[list.size()];
			int count = 0;
			for (ArticleTopic topic : list) {
				selectedTopics[count] = topic.getID_TOPIC().toString();
				count++;
			}
			this.setSelectedTopics (selectedTopics);
		} catch (BusinessException e) {
			String error = "An error occurred while finding the article topic by article. " + e.getMessage();
			logger.error (error);
		}
		logger.info ("Finish executing the method setArticleTopics.");
	}

}
