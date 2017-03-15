package com.bistu.cmsbai.service;

import java.util.List;

import com.bistu.cmsbai.dao.ClientDAO;
import com.bistu.cmsbai.domain.Client;
import com.bistu.cmsbai.exception.ClientException;

public class ClientService {
	private ClientDAO clientDAO;

	public ClientService() {
		
	}

	public void setClientDAO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}

	//根据条件动态查询客户列表，返回结果List<Client>
	public List<Client> getClientsByOptions(String companyName, int credit, String linkName, String starLevel) throws ClientException {
		String[] starLevelString = starLevel.split(",");
		return clientDAO.getClientsByOptions(companyName, credit, linkName, starLevelString);
	}
	
	//获取所有客户信息
	public List<Client> getAllClients() throws ClientException {
		return clientDAO.getAllClients();
	}
	
	//添加客户
	public Client createClient(Client client) throws ClientException {
		return clientDAO.insertClient(client);
	}
	
	//查询数据库中是否存在传入companyName，返回true表示数据库中不存在传入companyName，可继续添加
	public boolean judgeClientCompanyName(String companyName) throws ClientException {
		return clientDAO.judgeClientCompanyName(companyName);
	}
	
	//修改更新客户信息
	public Client modifyClient(Client client) throws ClientException {
		return clientDAO.updateClient(client);
	}
	
	//根据cid查询并返回Client对象
	public Client getClientByCid(int cid) throws ClientException {
		System.out.println("--------------------------------------");
		System.out.println(clientDAO.getClientByCid(cid));
		return clientDAO.getClientByCid(cid);
	}
}
