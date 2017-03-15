package com.bistu.cmsbai.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {
	private static final String ENCODING = "ecoding";
	private static final String DEFAULT_CHARSET = "UTF-8";
	private String charset;
	
	public void init(FilterConfig filterConfig) throws ServletException {
		String charset = filterConfig.getInitParameter(ENCODING);
		if(charset == null){
			charset = DEFAULT_CHARSET;
		}
		this.charset = charset;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(charset);
		chain.doFilter(request, response);
	}

	public void destroy() {

	}

}
