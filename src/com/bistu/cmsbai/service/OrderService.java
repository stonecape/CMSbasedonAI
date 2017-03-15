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
	
	/*//返回订单展示列表
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
		
		orderDAO.getAllShowOrders();//测试视图
		return showOrders;
	}*/

	//返回订单展示列表
	public List<ShowOrderView> getAllShowOrders() throws OrderException {
		return orderDAO.getAllShowOrders();
	}
	
	//根据指定日期范围查询ShowOrderView
	public List<ShowOrderView> getShowOrderViewsByCommitTimeRange(Date beginDate, Date endDate) throws OrderException {
		return orderDAO.getShowOrderViewsByCommitTimeRange(beginDate, endDate);
	}
	
	//根据订单号查询
	public List<ShowOrderView> getShowOrderViewsByOid(int oid) throws OrderException {
		return orderDAO.getShowOrderViewsByOid(oid);
	}
	
	//根据公司名称查询
	public List<ShowOrderView> getShowOrderViewsByCompanyName(String companyName) throws OrderException {
		return orderDAO.getShowOrderViewsByCompanyName(companyName);
	}
	
	//添加Order
	public Order createOrder(Order order) throws OrderException, StockException {
		stockDAO.deductRemainder(order.getSid(), order.getAmount());
		return orderDAO.insertOrder(order);
	}
	
	//根据oid查询并返回Order对象
	public Order getOrderByOid(int oid) throws OrderException {
		return orderDAO.getOrderByOid(oid);
	}
}
