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

	//����������̬��ѯ�ͻ��б����ؽ��List<Client>
	public List<Client> getClientsByOptions(String companyName, int credit, String linkName, String starLevel) throws ClientException {
		String[] starLevelString = starLevel.split(",");
		return clientDAO.getClientsByOptions(companyName, credit, linkName, starLevelString);
	}
	
	//��ȡ���пͻ���Ϣ
	public List<Client> getAllClients() throws ClientException {
		return clientDAO.getAllClients();
	}
	
	//��ӿͻ�
	public Client createClient(Client client) throws ClientException {
		return clientDAO.insertClient(client);
	}
	
	//��ѯ���ݿ����Ƿ���ڴ���companyName������true��ʾ���ݿ��в����ڴ���companyName���ɼ������
	public boolean judgeClientCompanyName(String companyName) throws ClientException {
		return clientDAO.judgeClientCompanyName(companyName);
	}
	
	//�޸ĸ��¿ͻ���Ϣ
	public Client modifyClient(Client client) throws ClientException {
		return clientDAO.updateClient(client);
	}
	
	//����cid��ѯ������Client����
	public Client getClientByCid(int cid) throws ClientException {
		System.out.println("--------------------------------------");
		System.out.println(clientDAO.getClientByCid(cid));
		return clientDAO.getClientByCid(cid);
	}
}
