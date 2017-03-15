package com.bistu.cmsbai.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.bistu.cmsbai.domain.Stock;
import com.bistu.cmsbai.exception.StockException;

public class StockDAO {
	private HibernateTemplate hibernateTemplate;
	
	private static final String GET_ALL_STOCKS = "from Stock";
	private static final String GET_STOCK_BY_SID = "select s from Stock s where s.sid = ?";
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	/**
	 * 获取所有库存信息
	 * @return
	 * @throws StockException
	 */
	@SuppressWarnings("unchecked")
	public List<Stock> getAllStocks() throws StockException {
		List<Stock> stocks = new ArrayList<Stock>();
		try{
			stocks = hibernateTemplate.find(GET_ALL_STOCKS);
		} catch(DataAccessException ex) {
			throw new StockException(ex);
		}
		return stocks;
	}
	
	/**
	 * 根据sid查询并返回Stock对象
	 * @param sid
	 * @return
	 * @throws StockException
	 */
	@SuppressWarnings("unchecked")
	public Stock getStockBySid(int sid) throws StockException {
		Stock stock = null;
		List<Stock> stockList = null;
		try{
			stockList = hibernateTemplate.find(GET_STOCK_BY_SID, new Object[]{sid});
			if(stockList.isEmpty()) {
				throw new StockException("stock can not found.");
			} else if(stockList.size() > 1) {
				throw new StockException("stock record count more than 1.");
			}
			stock = stockList.get(0);
		} catch(DataAccessException ex) {
			throw new StockException(ex);
		}
		return stock;
	}
	
	/**
	 * 获得一个HashMap<sid, profile>
	 * @return
	 * @throws StockException
	 */
	public Map<Integer, String>	getStockSidProfile() throws StockException {
		Map<Integer, String> stockSidProfile = new HashMap<Integer, String>();
		List<Stock> stocks = new ArrayList<Stock>();
		try{
			stocks = getAllStocks();
			for(Stock stock : stocks) {
				stockSidProfile.put(stock.getSid(), stock.getProfile());
			}
		} catch(DataAccessException ex) {
			throw new StockException(ex);
		}
		return stockSidProfile;
	}
	
	/**
	 * 更新stock
	 * @param stock
	 * @throws StockException
	 */
	public void updateStock(Stock stock) throws StockException {
		try{
			hibernateTemplate.update(stock); 
		} catch(DataAccessException ex) {
			throw new StockException(ex);
		}
	}
	
	/**
	 * 判断余量是否充足
	 * @param sid
	 * @param amount
	 * @return
	 * @throws StockException
	 */
	public boolean judgeEnoughRemainder(int sid, int amount) throws StockException {
		Stock stock = getStockBySid(sid);
		if(stock.getRemainder() - amount >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 扣除余量
	 * @param sid
	 * @param amount
	 * @throws StockException
	 */
	public void deductRemainder(int sid, int amount) throws StockException {
		if(judgeEnoughRemainder(sid, amount)) {
			Stock stock = getStockBySid(sid);
			int currentRemainder = stock.getRemainder();
			stock.setRemainder(currentRemainder - amount);
			updateStock(stock);
		} else {
			throw new StockException("Remainder Insufficent");
		}
	}
}
