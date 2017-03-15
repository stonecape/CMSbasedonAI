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
	
	//��ȡ���п����Ϣ
	public List<Stock> getAllStocks() throws StockException {
		return stockDAO.getAllStocks();
	}
	
	//����sid��ѯ������Stock����
	public Stock getStockBySid(int sid) throws StockException {
		return stockDAO.getStockBySid(sid);
	}

	//��ѯ�����Ƿ����
	public boolean judgeEnoughRemainder(int sid, int amount) throws StockException {
		return stockDAO.judgeEnoughRemainder(sid, amount);
	}
}
