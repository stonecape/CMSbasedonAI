package com.bistu.cmsbai.customermanager.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.bistu.cmsbai.domain.Client;
import com.bistu.cmsbai.exception.ClientException;
import com.bistu.cmsbai.exception.FeedbackException;
import com.bistu.cmsbai.exception.OrderException;
import com.bistu.cmsbai.service.ClientService;
import com.bistu.cmsbai.service.PotentialClientService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ClientSatisfactionAction extends ActionSupport {

	private static final long serialVersionUID = -6589190602364632365L;
	private ClientService clientSvc;
	private PotentialClientService potentialSvc;
	
	public void setClientSvc(ClientService clientSvc) {
		this.clientSvc = clientSvc;
	}

	public void setPotentialSvc(PotentialClientService potentialSvc) {
		this.potentialSvc = potentialSvc;
	}

	public void checkCidAndReturnClientSatisfaction() {
		ActionContext ctx = ActionContext.getContext(); 
        HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
        response.setCharacterEncoding("utf-8");
        
        String cid = request.getParameter("cid");
        int cidInt = Integer.parseInt(cid);
        
        try{
        	PrintWriter out = response.getWriter();
			Client client = clientSvc.getClientByCid(cidInt);
			String companyName = client.getCompanyName();
			int starLevel = client.getStarLevel();
			
			double satisfaction = potentialSvc.getClientStatisfactionByCid(cidInt);
			BigDecimal bg = new BigDecimal(satisfaction);
	        double satisfactionF = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

	        out.print(companyName + "," + starLevel + "," + satisfactionF);
			out.flush();
        } catch(ClientException e) {
			try {
				PrintWriter out = response.getWriter();
				out.print("null");
				out.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        } catch (FeedbackException e) {
			e.printStackTrace();
		} catch (OrderException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
