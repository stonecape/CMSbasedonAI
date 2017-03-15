package com.bistu.cmsbai.customermanager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bistu.cmsbai.domain.CustomerManager;
import com.bistu.cmsbai.exception.CustomerManagerException;
import com.bistu.cmsbai.service.CustomerManagerService;
import com.bistu.cmsbai.web.AuthInterceptor;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CustomerManagerLoginAction extends ActionSupport{

	private static final long serialVersionUID = -2541965205609229158L;
	private String username;
	private String password;
	private String rand;
	private String page;
	private CustomerManagerService customermanagerSvc;
	
	
	public CustomerManagerLoginAction() {
		
	}
	
	public void setCustomermanagerSvc(CustomerManagerService customermanagerSvc) {
		this.customermanagerSvc = customermanagerSvc;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRand() {
		return rand;
	}
	public void setRand(String rand) {
		this.rand = rand;
	}
	public String getPage() {
        return page;
    }

	@Override
	public String execute() throws Exception {
		String arandom=(String)(ActionContext.getContext().getSession().get("random"));
		if(!arandom.equals(this.getRand())) {   
			addActionError(getText("error.verifyRand.invalid"));
			return INPUT;
		}
		
		try {
			CustomerManager customermanager = 
				customermanagerSvc.checkCustomerManagerByUsernameAndPassword(username, password);
			
			ActionContext ctx = ActionContext.getContext(); 
            HttpServletRequest request = 
                    (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);  
            HttpSession session = request.getSession();
            
            session.setAttribute(AuthInterceptor.ATTR_CM, customermanager);
            page = (String)session.getAttribute(AuthInterceptor.ATTR_NEXT_PAGE);
            if(page == null){
            	return "timeout";
            }
            return SUCCESS;
		} catch(CustomerManagerException ex) {
			addActionError(getText("error.verifyPassword.invalid", 
                    new String[]{ex.getMessage()}));
        	return INPUT;
		}
	}
	
	public String removeLoginCustomerManager() {
		ActionContext ctx = ActionContext.getContext(); 
        HttpServletRequest request = 
                (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);  
        HttpSession session = request.getSession();
        
        CustomerManager customerManager =(CustomerManager)session.getAttribute(AuthInterceptor.ATTR_CM);
        if(customerManager == null) {
        	return INPUT;
        } else {
        	session.removeAttribute(AuthInterceptor.ATTR_CM);
        	return SUCCESS;
        }
	}
}
