package com.bistu.cmsbai.controller;

import java.io.ByteArrayInputStream;

import com.bistu.cmsbai.util.RandomNumUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class RandomAction extends ActionSupport{    
	private static final long serialVersionUID = -3873688345385938767L;
	private ByteArrayInputStream inputStream;    
	
	public String execute() throws Exception{    
		RandomNumUtil rdnu=RandomNumUtil.Instance();    
		this.setInputStream(rdnu.getImage());//取得带有随机字符串的图片    
		ActionContext.getContext().getSession().put("random", rdnu.getString());//取得随机字符串放入HttpSession    
		return SUCCESS;    
	}    
	
	public void setInputStream(ByteArrayInputStream inputStream) {    
		this.inputStream = inputStream;    
	}    
	
	public ByteArrayInputStream getInputStream() {    
		return inputStream;    
	}   
}