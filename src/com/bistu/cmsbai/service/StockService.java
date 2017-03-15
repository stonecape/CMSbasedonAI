package com.bistu.cmsbai.service;

import java.util.List;

import com.bistu.cmsbai.dao.StockDAO;
import com.bistu.cmsbai.domain.Stock;
import com.bistu.cmsbai.exception.StockException;

public class StockService {
	private StockDAO stockDAO;

	public StockService() {
		
	}

	public void setStockDAO(StockDAO stockDAO) {
		this.stockDAO = stockDAO;
	}
	
	//获取所有库存信息
	public List<Stock> getAllStocks() throws StockException {
		return stockDAO.getAllStocks();
	}
	
	//根据sid查询并返回Stock对象
	public Stock getStockBySid(int sid) throws StockException {
		return stockDAO.getStockBySid(sid);
	}

	//查询余量是否充足
	public boolean judgeEnoughRemainder(int sid, int amount) throws StockException {
		return stockDAO.judgeEnoughRemainder(sid, amount);
	}
}
