package com.bistu.cmsbai.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.bistu.cmsbai.domain.Order;
import com.bistu.cmsbai.domain.ShowOrderView;
import com.bistu.cmsbai.exception.OrderException;

public class OrderDAO {
	private HibernateTemplate hibernateTemplate;

	private static final String GET_ALL_ORDERS = "from Order";
	private static final String GET_ORDER_BY_OID = "select o from Order o where o.oid = ?";
	private static final String GET_ALL_SHOWORDERS = "from ShowOrderView";
	private static final String GET_SHOWORDERS_BY_COMMITTIME_RANGE = "select s from ShowOrderView s " +
			"where s.singleShowOrder.commitDate between ? and ?";
	private static final String GET_SHOWORDERS_BY_OID = "select s from ShowOrderView s where s.singleShowOrder.oid = ?";
	private static final String GET_SHOWORDERS_BY_COMPANYNAME = "select s from ShowOrderView s where s.singleShowOrder.companyName = ?";
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * 根据oid查询并返回Order对象
	 * @param oid
	 * @return
	 * @throws OrderException
	 */
	@SuppressWarnings("unchecked")
	public Order getOrderByOid(int oid) throws OrderException {
		Order order = null;
		List<Order> orderList = new ArrayList<Order>();
		
		try {
			orderList = hibernateTemplate.find(GET_ORDER_BY_OID, new Object[]{oid});
			if(orderList.isEmpty()) {
				throw new OrderException("Order can not found");
			} else if(orderList.size() > 1) {
				throw new OrderException("Order record more than 1");
			}
			order = orderList.get(0);
		} catch(DataAccessException ex) {
			throw new OrderException(ex);
		}
		
		return order;
	}
	/**
	 * 插入新订单
	 * @param order
	 * @return
	 * @throws OrderException
	 */
	public Order insertOrder(Order order) throws OrderException {
		try {
			order.setOid((Integer)hibernateTemplate.save(order));
		} catch(DataAccessException ex) {
			throw new OrderException(ex);
		}
		return order;
	}
	/**
	 * 获取所有订单信息
	 * @return
	 * @throws OrderException
	 */
	@SuppressWarnings("unchecked")
	public List<Order> getAllOrders() throws OrderException {
		List<Order> orders = new ArrayList<Order>();
		try{
			orders = hibernateTemplate.find(GET_ALL_ORDERS);
		} catch(DataAccessException ex) {
			throw new OrderException(ex);
		}
		return orders;
	}
	
	/**
	 * 通过视图结构获得查询结果
	 * @return
	 * @throws OrderException
	 */
	@SuppressWarnings("unchecked")
	public List<ShowOrderView> getAllShowOrders() throws OrderException {
		List<ShowOrderView> showordersView = new ArrayList<ShowOrderView>();
		try{
			showordersView = hibernateTemplate.find(GET_ALL_SHOWORDERS);
		} catch (DataAccessException ex) {
			throw new OrderException(ex);
		}
		return showordersView;
	}
	
	/**
	 * 根据指定日期范围查询ShowOrderView
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @throws OrderException
	 */
	@SuppressWarnings("unchecked")
	public List<ShowOrderView> getShowOrderViewsByCommitTimeRange(Date beginDate, Date endDate) throws OrderException {
		List<ShowOrderView> showordersView = new ArrayList<ShowOrderView>();
		try{
			showordersView = hibernateTemplate.find(GET_SHOWORDERS_BY_COMMITTIME_RANGE, new Object[]{beginDate, endDate});
		} catch (DataAccessException ex) {
			throw new OrderException(ex);
		}
		return showordersView;
	}
	
	/**
	 * 根据订单号查询ShowOrderView
	 * @param oid
	 * @return
	 * @throws OrderException
	 */
	@SuppressWarnings("unchecked")
	public List<ShowOrderView> getShowOrderViewsByOid(int oid) throws OrderException {
		List<ShowOrderView> showordersView = new ArrayList<ShowOrderView>();
		try{
			showordersView = hibernateTemplate.find(GET_SHOWORDERS_BY_OID, new Object[]{oid});
		} catch (DataAccessException ex) {
			throw new OrderException(ex);
		}
		return showordersView;
	}
	
	/**
	 * 根据公司名称查询ShowOrderView
	 * @param companyName
	 * @return
	 * @throws OrderException
	 */
	@SuppressWarnings("unchecked")
	public List<ShowOrderView> getShowOrderViewsByCompanyName(String companyName) throws OrderException {
		List<ShowOrderView> showordersView = new ArrayList<ShowOrderView>();
		try{
			showordersView = hibernateTemplate.find(GET_SHOWORDERS_BY_COMPANYNAME, new Object[]{companyName});
		} catch (DataAccessException ex) {
			throw new OrderException(ex);
		}
		return showordersView;
	}
}
