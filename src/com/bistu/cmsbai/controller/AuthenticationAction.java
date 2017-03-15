package com.bistu.cmsbai.controller;

import com.opensymphony.xwork2.ActionSupport;

public class AuthenticationAction  extends ActionSupport {
	private static final long serialVersionUID = 1413233018957716163L;
	private String page;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
    
    @Override
    public String execute() {
        return page;
    }
}
