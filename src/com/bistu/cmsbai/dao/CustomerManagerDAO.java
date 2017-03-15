package com.bistu.cmsbai.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.bistu.cmsbai.domain.CustomerManager;
import com.bistu.cmsbai.exception.CustomerManagerException;

public class CustomerManagerDAO {
	private HibernateTemplate hibernateTemplate;
	private static final String GET_CUSTOMERMANAGER_BY_USERNAMEANDPASSWORD = "select cm from CustomerManager cm " +
			"where cm.username = ? and cm.password = ?";

	public CustomerManagerDAO() {
		
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	/*
	 * 根据用户名和密码校验用户合法性
	 */
	@SuppressWarnings("unchecked")
	public CustomerManager checkCustomerManagerByUsernameAndPassword(String username, String password) throws CustomerManagerException {
		CustomerManager customermanager = null;
		try{
			List<CustomerManager> customermanagers = 
				hibernateTemplate.find(GET_CUSTOMERMANAGER_BY_USERNAMEANDPASSWORD, new Object[]{username, password});
			if(customermanagers.isEmpty()) {
				throw new CustomerManagerException("Username or Password didn't right");
			} else if(customermanagers.size() > 1) {
				throw new CustomerManagerException("CustomerManager record count more than 1.");
			}
			customermanager = customermanagers.get(0);
		} catch(DataAccessException ex) {
			throw new CustomerManagerException(ex);
		}
		return customermanager;
	}
}
