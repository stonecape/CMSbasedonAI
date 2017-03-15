package com.bistu.cmsbai.service;

import java.util.Date;
import java.util.List;

import com.bistu.cmsbai.dao.OrderDAO;
import com.bistu.cmsbai.dao.StockDAO;
import com.bistu.cmsbai.domain.Order;
import com.bistu.cmsbai.domain.ShowOrderView;
import com.bistu.cmsbai.exception.OrderException;
import com.bistu.cmsbai.exception.StockException;

public class OrderService {
	private OrderDAO orderDAO;
	private StockDAO stockDAO;
	
	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}
	
	public void setStockDAO(StockDAO stockDAO) {
		this.stockDAO = stockDAO;
	}
	
	/*//���ض���չʾ�б�
	public List<ShowOrder> getAllShowOrders() throws OrderException, ClientException, StockException {
		List<ShowOrder> showOrders = new ArrayList<ShowOrder>();
		List<Order> orders = orderDAO.getAllOrders();
		Map<Integer, String> clientCidCompanyName = clientDAO.getClientCidCompanyName();
		Map<Integer, String> stockSidProfile = stockDAO.getStockSidProfile();
		
		for(Order order : orders) {
			String companyName = clientCidCompanyName.get(order.getCid());
			String profile = stockSidProfile.get(order.getSid());
			double sumPrice = order.getUnitPrice() * order.getAmount() * order.getDiscount();
			ShowOrder tempShowOrder = new ShowOrder(order.getOid(), order.getCid(), order.getSid(), companyName, profile, order.getUnitPrice(), 
					order.getAmount(), order.getDiscount(), sumPrice, order.getCommitDate());
			showOrders.add(tempShowOrder);
		}
		
		orderDAO.getAllShowOrders();//������ͼ
		return showOrders;
	}*/

	//���ض���չʾ�б�
	public List<ShowOrderView> getAllShowOrders() throws OrderException {
		return orderDAO.getAllShowOrders();
	}
	
	//����ָ�����ڷ�Χ��ѯShowOrderView
	public List<ShowOrderView> getShowOrderViewsByCommitTimeRange(Date beginDate, Date endDate) throws OrderException {
		return orderDAO.getShowOrderViewsByCommitTimeRange(beginDate, endDate);
	}
	
	//���ݶ����Ų�ѯ
	public List<ShowOrderView> getShowOrderViewsByOid(int oid) throws OrderException {
		return orderDAO.getShowOrderViewsByOid(oid);
	}
	
	//���ݹ�˾���Ʋ�ѯ
	public List<ShowOrderView> getShowOrderViewsByCompanyName(String companyName) throws OrderException {
		return orderDAO.getShowOrderViewsByCompanyName(companyName);
	}
	
	//���Order
	public Order createOrder(Order order) throws OrderException, StockException {
		stockDAO.deductRemainder(order.getSid(), order.getAmount());
		return orderDAO.insertOrder(order);
	}
	
	//����oid��ѯ������Order����
	public Order getOrderByOid(int oid) throws OrderException {
		return orderDAO.getOrderByOid(oid);
	}
}
