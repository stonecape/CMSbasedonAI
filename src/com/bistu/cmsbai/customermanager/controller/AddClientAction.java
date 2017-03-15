package com.bistu.cmsbai.customermanager.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.bistu.cmsbai.domain.Client;
import com.bistu.cmsbai.exception.ClientException;
import com.bistu.cmsbai.service.ClientService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AddClientAction extends ActionSupport {

	private static final long serialVersionUID = -1862813089428752473L;

	private ClientService clientSvc;

	private String companyname;
	private String address;
	private int credit;
	private String linkname;
	private String telephone;
	private String email;
	
	public void setClientSvc(ClientService clientSvc) {
		this.clientSvc = clientSvc;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getLinkname() {
		return linkname;
	}

	public void setLinkname(String linkname) {
		this.linkname = linkname;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String execute() throws Exception {
		try{
			Client client = new Client(companyname, address, credit, linkname, telephone, email);
			clientSvc.createClient(client);
			return SUCCESS;
		} catch(ClientException ex) {
			addActionError(getText("error.client.create", new String[] { ex.getMessage() }));
			return INPUT;
		}
	}
	
	public void judgeClientCompanyName() {
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
		response.setCharacterEncoding("utf-8");
		String companyname = request.getParameter("companyname");
		
		try {
			PrintWriter out = response.getWriter();
			if (!clientSvc.judgeClientCompanyName(companyname)) {
				out.write("" + "客户已存在");
				out.flush();
			} else {
				out.write("");
				out.flush();
			}
		} catch (ClientException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
