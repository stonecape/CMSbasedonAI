package com.bistu.cmsbai.customermanager.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.bistu.cmsbai.domain.Client;
import com.bistu.cmsbai.domain.Order;
import com.bistu.cmsbai.domain.Stock;
import com.bistu.cmsbai.exception.ClientException;
import com.bistu.cmsbai.exception.OrderException;
import com.bistu.cmsbai.exception.StockException;
import com.bistu.cmsbai.service.ClientService;
import com.bistu.cmsbai.service.OrderService;
import com.bistu.cmsbai.service.StockService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AddOrderAction extends ActionSupport{

	private static final long serialVersionUID = 1406540452611577270L;
	
	private ClientService clientSvc;
	private StockService stockSvc;
	private OrderService orderSvc;
	
	private int cid;
	private int sid;
	private int unitprice;
	private int amount;
	private double discount;
	
	public void setOrderSvc(OrderService orderSvc) {
		this.orderSvc = orderSvc;
	}

	public void setClientSvc(ClientService clientSvc) {
		this.clientSvc = clientSvc;
	}

	public void setStockSvc(StockService stockSvc) {
		this.stockSvc = stockSvc;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(int unitprice) {
		this.unitprice = unitprice;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	
	@Override
	public String execute() throws Exception {
		try{
			Order order = new Order(cid, sid, unitprice, amount, discount, new Date());
			orderSvc.createOrder(order);
			return SUCCESS;
		} catch(OrderException ex) {
			addActionError(getText("error.order.create", new String[] { ex.getMessage() }));
			return INPUT;
		}
	}

	public void checkCidAndReturnClientInfo() {
		ActionContext ctx = ActionContext.getContext(); 
        HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
        response.setCharacterEncoding("utf-8");
        
        String cid = request.getParameter("cid");
        int cidInt = Integer.parseInt(cid);
        
        try {
        	PrintWriter out = response.getWriter();
			Client client = clientSvc.getClientByCid(cidInt);
			String companyName = client.getCompanyName();
			int starLevel = client.getStarLevel();
			
			out.print(companyName + "," + starLevel);
			out.flush();
		} catch (ClientException e) {
			try {
				PrintWriter out = response.getWriter();
				out.print("null");
				out.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void checkSidAndReturnStockInfo() {
		ActionContext ctx = ActionContext.getContext(); 
        HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
        response.setCharacterEncoding("utf-8");
        
        String sid = request.getParameter("sid");
        int sidInt = Integer.parseInt(sid);
		
        try {
        	PrintWriter out = response.getWriter();
        	Stock stock = stockSvc.getStockBySid(sidInt);
        	int status = stock.getStatus();
        	if(status == 0) {
        		out.print("suspend");
				out.flush();
        	} else {
        		out.print(stock.getProfile());
				out.flush();
        	}
        } catch(StockException ex) {
        	try {
				PrintWriter out = response.getWriter();
				out.print("null");
				out.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        } catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void checkStockRemainder() {
		ActionContext ctx = ActionContext.getContext(); 
        HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
        response.setCharacterEncoding("utf-8");
        
        String sid = request.getParameter("sid");
        String amount = request.getParameter("amount");
        
        int sidInt = Integer.parseInt(sid);
        int amountInt = Integer.parseInt(amount);
        
        try {
        	PrintWriter out = response.getWriter();
        	System.out.println("==============>" + stockSvc.judgeEnoughRemainder(sidInt, amountInt));
        	if(stockSvc.judgeEnoughRemainder(sidInt, amountInt)) {
        		out.print("ok");
				out.flush();
        	} else {
        		out.print("insufficient");
				out.flush();
        	}
        } catch(StockException ex) {
        	ex.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
		}
	}
}
