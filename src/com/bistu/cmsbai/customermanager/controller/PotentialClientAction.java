package com.bistu.cmsbai.customermanager.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.bistu.cmsbai.domain.Client;
import com.bistu.cmsbai.domain.ShowPotentialClient;
import com.bistu.cmsbai.exception.ClientException;
import com.bistu.cmsbai.exception.FeedbackException;
import com.bistu.cmsbai.exception.OrderException;
import com.bistu.cmsbai.service.PotentialClientService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PotentialClientAction extends ActionSupport {

	private static final long serialVersionUID = 6772901895171067293L;
	private PotentialClientService potentialClientSvc;
	
	public void setPotentialClientSvc(PotentialClientService potentialClientSvc) {
		this.potentialClientSvc = potentialClientSvc;
	}
	
	public void getFilterResultByAlgorithm() {
		ActionContext ctx = ActionContext.getContext(); 
        HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
        
        String algorithm = request.getParameter("algorithm");
        
        try {
			List<ShowPotentialClient> showPotentialClients = potentialClientSvc.getShowPotenticalClientList(algorithm);
			request.setAttribute("showpotentialclients", showPotentialClients);
			request.getRequestDispatcher("/customermanager/GetShowPotentialClientSuccess.jsp").forward(request, response);
		} catch (OrderException e) {
			e.printStackTrace();
		} catch (FeedbackException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void modifyStarLevel() {
		ActionContext ctx = ActionContext.getContext(); 
        HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
        response.setCharacterEncoding("utf-8");
        
        String cid = request.getParameter("cid");
        String modifiedStarLevel = request.getParameter("modifiedstarlevel");
        
        int cidInt = Integer.parseInt(cid);
        int modifiedStarLevelInt = Integer.parseInt(modifiedStarLevel);
        
        try {
			Client client = potentialClientSvc.modifyStarLevelByCid(cidInt, modifiedStarLevelInt);
			PrintWriter out = response.getWriter();
			System.out.println("=======>" + client.getStarLevel());
			String starLevel = client.getStarLevel() + "";
			out.print(starLevel);
			out.flush();
		} catch (ClientException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
