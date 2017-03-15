package com.bistu.cmsbai.exception;

public class OrderException extends Exception {
	
	private static final long serialVersionUID = -561236442288009090L;
	
	public OrderException(Exception e) {
        super(e);
    }
    public OrderException(String msg) {
        super(msg);
    }
}
