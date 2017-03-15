package com.bistu.cmsbai.exception;

public class CustomerManagerException extends Exception {
	
	private static final long serialVersionUID = -561236442288009090L;
	
	public CustomerManagerException(Exception e) {
        super(e);
    }
    public CustomerManagerException(String msg) {
        super(msg);
    }
}
