package com.bistu.cmsbai.exception;

public class FeedbackException extends Exception {
	
	private static final long serialVersionUID = -561236442288009090L;
	
	public FeedbackException(Exception e) {
        super(e);
    }
    public FeedbackException(String msg) {
        super(msg);
    }
}
