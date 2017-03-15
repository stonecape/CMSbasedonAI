package com.bistu.cmsbai.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bistu.cmsbai.controller.AuthenticationAction;
import com.bistu.cmsbai.domain.CustomerManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthInterceptor extends AbstractInterceptor {
	
	private static final long serialVersionUID = 3783750381874331331L;
	
	public static final String ATTR_CM = "login.customerManager";
    public static final String ATTR_NEXT_PAGE = "login.nextPage";

    public AuthInterceptor() {
    	
    }
    
    @Override
    public String intercept(ActionInvocation ai) throws Exception {
        ActionContext ctx = ActionContext.getContext(); 
        HttpServletRequest request = 
            (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);  
        HttpSession session = request.getSession();
        CustomerManager customerManager = (CustomerManager)session.getAttribute(ATTR_CM);
        if(customerManager == null){
        	AuthenticationAction action = (AuthenticationAction)ai.getAction();
        	String page = action.getPage();
        	session.setAttribute(ATTR_NEXT_PAGE, page);
        	return "login";
        }
        
        String result = ai.invoke();
        return result;
    }
}
