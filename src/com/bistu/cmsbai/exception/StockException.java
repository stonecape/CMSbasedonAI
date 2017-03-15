package com.bistu.cmsbai.exception;

public class StockException extends Exception {
	
	private static final long serialVersionUID = -561236442288009090L;
	
	public StockException(Exception e) {
        super(e);
    }
    public StockException(String msg) {
        super(msg);
    }
}
