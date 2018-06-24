package com.example.demo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CORSFIlter implements Filter {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(CORSFIlter.class);
	
	
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		LOGGER.info("Initialising CORS");
		
	}

	

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest requestUse=(HttpServletRequest) request;
		HttpServletResponse responseUse=(HttpServletResponse) response;
		
		responseUse.setHeader("Access-Control-Allow-Origin", "*");
	    responseUse.setHeader("Access-Control-Allow-Methods", "POST, GET");
	    responseUse.setHeader("Access-Control-Max-Age", "3600");
	    responseUse.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		responseUse.setHeader("Access-Control-Allow-Origin", requestUse.getHeader("Origin"));
		chain.doFilter(requestUse, responseUse);
		
		
	}
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	

}
