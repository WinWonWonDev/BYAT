package com.greedy.byat.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EncodingFilter implements Filter {

	private String encodingType;
	
	public void init(FilterConfig fConfig) throws ServletException {
		encodingType = fConfig.getInitParameter("encoding-type");
	
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest hrequest = (HttpServletRequest) request;
		
		if("POST".equals(hrequest.getMethod())) {
			request.setCharacterEncoding(encodingType);

		}
		
		chain.doFilter(request, response);
	}

	public void destroy() {
		
	}

}
