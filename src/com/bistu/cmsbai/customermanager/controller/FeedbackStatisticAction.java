package com.bistu.cmsbai.customermanager.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.bistu.cmsbai.exception.FeedbackException;
import com.bistu.cmsbai.service.FeedbackService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FeedbackStatisticAction extends ActionSupport {
	private static final long serialVersionUID = 3249377310426546497L;

	private FeedbackService feedbackSvc;

	public void setFeedbackSvc(FeedbackService feedbackSvc) {
		this.feedbackSvc = feedbackSvc;
	}
	
	public void calculateResultByRangeDate() {
		ActionContext ctx = ActionContext.getContext(); 
        HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
        response.setCharacterEncoding("utf-8");
        
        String beginDate = request.getParameter("beginDate");
        String endDate = request.getParameter("endDate");
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
        	PrintWriter out = response.getWriter();
			Date beginDateFormat = sdf.parse(beginDate);
			Date endDateFormat = sdf.parse(endDate);
			String result = feedbackSvc.calculateFeedbackAvgResultByRangeDate(beginDateFormat, endDateFormat);
			out.write(result);
			out.flush();
        } catch(FeedbackException ex) {
        	ex.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
