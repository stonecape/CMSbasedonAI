package com.bistu.cmsbai.service;

import java.util.Date;
import java.util.List;

import com.bistu.cmsbai.dao.FeedbackDAO;
import com.bistu.cmsbai.domain.Feedback;
import com.bistu.cmsbai.exception.FeedbackException;

public class FeedbackService {
	private FeedbackDAO feedbackDAO;

	public void setFeedbackDAO(FeedbackDAO feedbackDAO) {
		this.feedbackDAO = feedbackDAO;
	}
	
	//添加反馈条目
	public Feedback createFeedbackItem(Feedback feedback) throws FeedbackException {
		return feedbackDAO.insertFeedback(feedback);
	}
	
	//根据日期范围查询并返回反馈信息
	public List<Feedback> getFeedbacksByRangeDate(Date beginDate, Date endDate) throws FeedbackException {
		return feedbackDAO.getFeedbacksByRangeDate(beginDate, endDate);
	}
	
	//根据日期范围计算反馈结果平均值
	public String calculateFeedbackAvgResultByRangeDate(Date beginDate, Date endDate) throws FeedbackException {
		List<Feedback> feedbacks = getFeedbacksByRangeDate(beginDate, endDate);
		
		double qualityAvg = 0.0;
		double serviceAvg = 0.0;
		double priceAvg = 0.0;
		double ontimeAvg = 0.0;
		
		int qualitySum = 0;
		int serviceSum = 0;
		int priceSum = 0;
		int ontimeSum = 0;
		String result = "0,0,0,0";
		int sizeCount = feedbacks.size();
		
		if(sizeCount > 0) {
			for(Feedback feedback : feedbacks) {
				qualitySum += feedback.getQuality();
				serviceSum += feedback.getService();
				priceSum += feedback.getPrice();
				ontimeSum += feedback.getOntime();
			}
			
			qualityAvg = (double)qualitySum / sizeCount;
			serviceAvg = (double)serviceSum / sizeCount;
			priceAvg = (double)priceSum / sizeCount;
			ontimeAvg = (double)ontimeSum / sizeCount;
			
			java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");  
			
			result = df.format(qualityAvg) + "," + df.format(serviceAvg) + "," + df.format(priceAvg) + "," + df.format(ontimeAvg);
		}
		
		return result;
	}
}
