package com.josivansilva.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.josivansilva.presentation.UserController;

/**
 * Authorization login filter.
 * 
 * @author Josivan Silva
 *
 */
public class AuthorizationFilter implements Filter {

	private static Logger logger = Logger.getLogger (AuthorizationFilter.class);
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub		
	}

	@Override
	public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		logger.info ("Start executing the method doFilter().");
		UserController userController = (UserController)((HttpServletRequest)request).getSession().getAttribute("userController");
		if (userController == null || !userController.isLogged()) {
			HttpServletResponse res = (HttpServletResponse) response;
			HttpServletRequest req  = (HttpServletRequest) request;
			res.sendRedirect (req.getContextPath() + "/website_admin/");			
		}		
		chain.doFilter (request, response);
		logger.info ("Finish executing the method doFilter().");
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
