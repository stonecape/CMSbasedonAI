package com.bistu.cmsbai.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.bistu.cmsbai.domain.Client;
import com.bistu.cmsbai.exception.ClientException;

public class ClientDAO {
	private HibernateTemplate hibernateTemplate;
	
	private static final String GET_ALL_CLIENTS = "from Client";
	private static final String GET_CLIENTS_BY_COMPANYNAME = "select c from Client c where c.companyName = ?";
	private static final String GET_CLIENT_BY_CID = "select c from Client c where c.cid = ?";

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	

	/**
	 * ����ͻ�����˾/�ͻ������Ѿ���ǰ̨AJAXУ��
	 * @param client
	 * @return
	 * @throws ClientException
	 */
	public Client insertClient(Client client) throws ClientException {
		try {
			client.setCid((Integer)hibernateTemplate.save(client));
		} catch(DataAccessException ex) {
			throw new ClientException(ex);
		}
		return client;
	}
	
	/**
	 * ���ݹ�˾/�ͻ����Ʋ�ѯ�Ƿ���ڣ�����true��ʾ���ݿ��в����ڴ���companyName���ɼ������
	 * @param companyName
	 * @return
	 * @throws ClientException
	 */
	@SuppressWarnings("unchecked")
	public boolean judgeClientCompanyName(String companyName) throws ClientException {
		try{
			List<Client> clients = hibernateTemplate.find(GET_CLIENTS_BY_COMPANYNAME, companyName);
			if(clients.size() == 0) {
				return true;
			} else {
				return false;
			}
		} catch(DataAccessException ex) {
			throw new ClientException(ex);
		}
	}
	/**
	 * ��ȡ���пͻ���Ϣ
	 * @return
	 * @throws ClientException
	 */
	@SuppressWarnings("unchecked")
	public List<Client> getAllClients() throws ClientException {
		List<Client> clients = new ArrayList<Client>();
		try{
			clients = hibernateTemplate.find(GET_ALL_CLIENTS);
		} catch(DataAccessException ex) {
			throw new ClientException(ex);
		}
		return clients;
	}
	
	/**
	 * ���һ��HashMap<cid, companyName>
	 * @return
	 * @throws ClientException
	 */
	public Map<Integer, String> getClientCidCompanyName() throws ClientException {
		Map<Integer, String> allClientCidCompanyName = new HashMap<Integer, String>();
		List<Client> allClients = new ArrayList<Client>();
		try{
			allClients = getAllClients();
			for(Client client : allClients) {
				allClientCidCompanyName.put(client.getCid(), client.getCompanyName());
			}
		} catch(DataAccessException ex) {
			throw new ClientException(ex);
		}
		return allClientCidCompanyName;
	}

	/**
	 * ����ѡ���ѯClient����̬��װHQL���
	 * @param companyName
	 * @param credit
	 * @param linkName
	 * @param starLevel
	 * @return
	 * @throws ClientException
	 */
	@SuppressWarnings("unchecked")
	public List<Client> getClientsByOptions(String companyName, int credit, String linkName, String[] starLevelArray) throws ClientException {
		String get_clients_by_options = "select c from Client c where 1=1 ";
		List<Client> clients = new ArrayList<Client>();
		List<Object> tempOptions = new ArrayList<Object>();
		
		if(!companyName.equals("") && companyName != null) {
			get_clients_by_options = get_clients_by_options + "and c.companyName = ? ";
			tempOptions.add(companyName);
		}
		if(credit >= 0) {
			get_clients_by_options = get_clients_by_options + "and c.credit = ? ";
			tempOptions.add(credit);
		}
		if(!linkName.equals("") && linkName != null) {
			get_clients_by_options = get_clients_by_options + "and c.linkName = ? ";
			tempOptions.add(linkName);
		}
		get_clients_by_options = get_clients_by_options + "and ( ";
		for(int i = 0; i < starLevelArray.length; i++) {
			int tempStarLevelInt = Integer.parseInt(starLevelArray[i]);
			tempOptions.add(tempStarLevelInt);
			if(i == starLevelArray.length - 1) {
				get_clients_by_options = get_clients_by_options + "c.starLevel = ? ";
				break;
			}
			get_clients_by_options = get_clients_by_options + "c.starLevel = ? or ";
		}
		get_clients_by_options = get_clients_by_options + ")";
		System.out.println(get_clients_by_options);
		
		try{
			Object[] tempObject = new Object[tempOptions.size()];
			for(int i = 0; i < tempOptions.size(); i++) {
				tempObject[i] = tempOptions.get(i);
			}
			clients = hibernateTemplate.find(get_clients_by_options, tempObject);
		} catch (DataAccessException ex) {
			throw new ClientException(ex);
		}
		
		return clients;
	}
	
	/**
	 * ���¿ͻ���Ϣ
	 * @param client
	 * @return
	 * @throws ClientException
	 */
	public Client updateClient(Client client) throws ClientException {
		try{
			hibernateTemplate.update(client);
		} catch(DataAccessException ex) {
			throw new ClientException(ex);
		}
		return client;
	}
	
	/**
	 * ����cid��ѯ������Client����
	 * @param cid
	 * @return
	 * @throws ClientException
	 */
	@SuppressWarnings("unchecked")
	public Client getClientByCid(int cid) throws ClientException {
		Client client = null;
		List<Client> clientList = null;
		try{
			clientList = hibernateTemplate.find(GET_CLIENT_BY_CID, new Object[]{cid});
			if(clientList.isEmpty()) {
				throw new ClientException("client not found.");
			} else if(clientList.size() > 1) {
				throw new ClientException("client record count more than 1.");
			}
			client = clientList.get(0);
		} catch(DataAccessException ex) {
			throw new ClientException(ex);
		}
		return client;
	}
	
	/**
	 * ����cid���޸ĵ�starLevel���и���
	 * @param cid
	 * @param modifiedStarlevel
	 * @return
	 * @throws ClientException
	 */
	public Client modifyStarLevelByCid(int cid, int modifiedStarlevel) throws ClientException {
		Client client = getClientByCid(cid);
		client.setStarLevel(modifiedStarlevel);
		updateClient(client);
		return client;
	}
}
