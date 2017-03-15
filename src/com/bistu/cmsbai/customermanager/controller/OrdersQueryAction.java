package com.bistu.cmsbai.customermanager.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.bistu.cmsbai.domain.ShowOrderView;
import com.bistu.cmsbai.exception.OrderException;
import com.bistu.cmsbai.service.OrderService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class OrdersQueryAction extends ActionSupport {

	private static final long serialVersionUID = -3437929916643397864L;
	private OrderService orderSvc;
	
	public void setOrderSvc(OrderService orderSvc) {
		this.orderSvc = orderSvc;
	}


	public void getShowOrderViewsByCommitTimeRange() {
		ActionContext ctx = ActionContext.getContext(); 
        HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
        
        String beginDate = request.getParameter("beginDate");
        String endDate = request.getParameter("endDate");
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
			Date beginDateFormat = sdf.parse(beginDate);
			Date endDateFormat = sdf.parse(endDate);
			List<ShowOrderView> showorderviews = orderSvc.getShowOrderViewsByCommitTimeRange(beginDateFormat, endDateFormat);
			
			request.setAttribute("showOrderList", showorderviews);
			request.getRequestDispatcher("/customermanager/GetShowOrderViewSuccess.jsp").forward(request, response);
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (OrderException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getShowOrderViewsByOid() {
		ActionContext ctx = ActionContext.getContext(); 
        HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
        
        String oid = request.getParameter("oid");
        int oidInt = Integer.parseInt(oid);
		
        try {
        	List<ShowOrderView> showorderviews = orderSvc.getShowOrderViewsByOid(oidInt);
        	request.setAttribute("showOrderList", showorderviews);
			request.getRequestDispatcher("/customermanager/GetShowOrderViewSuccess.jsp").forward(request, response);
        } catch (OrderException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getShowOrderViewsByCompanyName() {
		ActionContext ctx = ActionContext.getContext(); 
        HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
        
        String companyName = request.getParameter("companyname");
        try {
        	List<ShowOrderView> showorderviews = orderSvc.getShowOrderViewsByCompanyName(companyName);
        	request.setAttribute("showOrderList", showorderviews);
			request.getRequestDispatcher("/customermanager/GetShowOrderViewSuccess.jsp").forward(request, response);
        } catch (OrderException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
