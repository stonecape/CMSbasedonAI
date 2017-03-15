package com.bistu.cmsbai.exception;

public class ClientException extends Exception {
	
	private static final long serialVersionUID = -561236442288009090L;
	
	public ClientException(Exception e) {
        super(e);
    }
    public ClientException(String msg) {
        super(msg);
    }
}
