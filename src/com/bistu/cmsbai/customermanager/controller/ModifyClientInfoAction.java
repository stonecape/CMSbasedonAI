package com.bistu.cmsbai.customermanager.controller;

import com.bistu.cmsbai.domain.Client;
import com.bistu.cmsbai.exception.ClientException;
import com.bistu.cmsbai.service.ClientService;
import com.opensymphony.xwork2.ActionSupport;

public class ModifyClientInfoAction extends ActionSupport {

	private static final long serialVersionUID = 2787957158466091652L;
	
	private ClientService clientSvc;
	
	private int cid;
	private String companyname;
	private String address;
	private int credit;
	private String linkname;
	private String telephone;
	private String email;
	private int starlevel;
	
	
	public void setClientSvc(ClientService clientSvc) {
		this.clientSvc = clientSvc;
	}


	public int getCid() {
		return cid;
	}


	public void setCid(int cid) {
		this.cid = cid;
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


	public int getStarlevel() {
		return starlevel;
	}


	public void setStarlevel(int starlevel) {
		this.starlevel = starlevel;
	}


	@Override
	public String execute() throws Exception {
		try{
			Client client = new Client(cid, companyname, address, credit, linkname, telephone, email, starlevel);
			clientSvc.modifyClient(client);
			return SUCCESS;
		} catch(ClientException ex) {
			addActionError(getText("error.client.modify", new String[] { ex.getMessage() }));
			return INPUT;
		}
	}
}
