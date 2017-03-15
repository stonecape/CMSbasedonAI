package com.bistu.cmsbai.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.bistu.cmsbai.domain.Feedback;
import com.bistu.cmsbai.exception.FeedbackException;

public class FeedbackDAO {
	private HibernateTemplate hibernateTemplate;
	
	private static final String GET_FEEDBACKS_BY_RANGE_DATE = "select f from Feedback f where f.commitDate between ? and ?";
	private static final String GET_ALL_FEEDBACKS = "from Feedback";
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	/**
	 * 插入反馈信息
	 * @param feedback
	 * @return
	 * @throws FeedbackException
	 */
	public Feedback insertFeedback(Feedback feedback) throws FeedbackException {
		try {
			feedback.setCid((Integer)hibernateTemplate.save(feedback));
		} catch(DataAccessException ex) {
			throw new FeedbackException(ex);
		}
		return feedback;
	}
	
	/**
	 * 根据日期范围查询并返回反馈信息
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @throws FeedbackException
	 */
	@SuppressWarnings("unchecked")
	public List<Feedback> getFeedbacksByRangeDate(Date beginDate, Date endDate) throws FeedbackException {
		List<Feedback> feedbacks = new ArrayList<Feedback>();
		try {
			feedbacks = hibernateTemplate.find(GET_FEEDBACKS_BY_RANGE_DATE, new Object[]{beginDate, endDate});
		} catch(DataAccessException ex) {
			throw new FeedbackException(ex);
		}
		return feedbacks;
	}
	
	/**
	 * 返回所有反馈信息
	 * @return
	 * @throws FeedbackException
	 */
	@SuppressWarnings("unchecked")
	public List<Feedback> getAllFeedbacks() throws FeedbackException {
		List<Feedback> feedbacks = new ArrayList<Feedback>();
		try {
			feedbacks = hibernateTemplate.find(GET_ALL_FEEDBACKS);
		} catch(DataAccessException ex) {
			throw new FeedbackException(ex);
		}
		return feedbacks;
	}
}
