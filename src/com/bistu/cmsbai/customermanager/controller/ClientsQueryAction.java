package com.bistu.cmsbai.customermanager.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.bistu.cmsbai.domain.Client;
import com.bistu.cmsbai.exception.ClientException;
import com.bistu.cmsbai.service.ClientService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ClientsQueryAction extends ActionSupport{

	private static final long serialVersionUID = 3558800939706750693L;
	private ClientService clientSvc;
	
	public void setClientSvc(ClientService clientSvc) {
		this.clientSvc = clientSvc;
	}
	
	
	public void getClientsByOptions() {
		ActionContext ctx = ActionContext.getContext(); 
        HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
        
        String companyName = request.getParameter("companyname");
        String credit = request.getParameter("credit");
        String linkName = request.getParameter("linkname");
        String starLevel_str = request.getParameter("star_str");
        
        if(starLevel_str.equals("")) {
        	starLevel_str = "0,1,3,5";
        } else {
        	starLevel_str = starLevel_str.substring(1);
        }
        

    	try {
    		int creditInt = -1;
    		if(!credit.equals("") && credit != null) {
    			creditInt = Integer.parseInt(credit);
    		}
			List<Client> clients = clientSvc.getClientsByOptions(companyName, creditInt, linkName, starLevel_str);
			request.setAttribute("clientsByOptions", clients);
			request.getRequestDispatcher("/customermanager/GetClientsByOptionsSuccess.jsp").forward(request, response);
		} catch (ClientException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
