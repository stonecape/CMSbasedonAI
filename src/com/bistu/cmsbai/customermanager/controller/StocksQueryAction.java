package com.bistu.cmsbai.customermanager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.bistu.cmsbai.domain.Stock;
import com.bistu.cmsbai.exception.StockException;
import com.bistu.cmsbai.service.StockService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class StocksQueryAction extends ActionSupport {

	private static final long serialVersionUID = 1156995815220193053L;
	
	private StockService stockSvc;

	public void setStockSvc(StockService stockSvc) {
		this.stockSvc = stockSvc;
	}
	
	public void getStockBySid() {
		ActionContext ctx = ActionContext.getContext(); 
        HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
        
        String sid = request.getParameter("sid");
        int sidInt = Integer.parseInt(sid);
        
        try{
        	Stock stock = stockSvc.getStockBySid(sidInt);
        	request.setAttribute("stockBySid", stock);
        	request.getRequestDispatcher("/customermanager/GetStockBySidSuccess.jsp").forward(request, response);
        } catch(StockException e) {
        	try {
				request.getRequestDispatcher("/customermanager/GetStockBySidError.jsp").forward(request, response);
			} catch (ServletException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        } catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
