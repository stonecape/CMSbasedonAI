package com.bistu.cmsbai.service;

import com.bistu.cmsbai.dao.CustomerManagerDAO;
import com.bistu.cmsbai.domain.CustomerManager;
import com.bistu.cmsbai.exception.CustomerManagerException;

public class CustomerManagerService {
	private CustomerManagerDAO customermanagerDAO;

	public void setCustomermanagerDAO(CustomerManagerDAO customermanagerDAO) {
		this.customermanagerDAO = customermanagerDAO;
	}
	
	//�����û���������У���û��Ϸ���
	public CustomerManager checkCustomerManagerByUsernameAndPassword(String username, String password) throws CustomerManagerException {
		return customermanagerDAO.checkCustomerManagerByUsernameAndPassword(username, password);
	}
}
