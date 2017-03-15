package com.bistu.cmsbai.customermanager.controller;

import java.util.Date;

import com.bistu.cmsbai.domain.Feedback;
import com.bistu.cmsbai.exception.FeedbackException;
import com.bistu.cmsbai.service.FeedbackService;
import com.opensymphony.xwork2.ActionSupport;

public class AddNewFeedbackAction extends ActionSupport {

	private static final long serialVersionUID = -7234874844582811601L;
	
	private FeedbackService feedbackSvc;

	private int cid;
	private Date feedbackDate;
	private int quality;
	private int service;
	private int price;
	private int ontime;

	public AddNewFeedbackAction() {

	}

	public void setFeedbackSvc(FeedbackService feedbackSvc) {
		this.feedbackSvc = feedbackSvc;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public Date getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(Date feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public int getService() {
		return service;
	}

	public void setService(int service) {
		this.service = service;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getOntime() {
		return ontime;
	}

	public void setOntime(int ontime) {
		this.ontime = ontime;
	}

	@Override
	public String execute() throws Exception {
		try {
			Feedback feedback = new Feedback(cid, feedbackDate, quality, service, price, ontime);
			feedbackSvc.createFeedbackItem(feedback);
			return SUCCESS;
		} catch(FeedbackException ex) {
			addActionError(getText("error.feedback.create", new String[] { ex.getMessage() }));
			return INPUT;
		}
	}
}
