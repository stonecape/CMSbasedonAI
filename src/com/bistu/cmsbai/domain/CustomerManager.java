package com.bistu.cmsbai.domain;

public class CustomerManager {
	private int cmid;
	private String username;
	private String password;
	
	public CustomerManager() {
		
	}

	public CustomerManager(int cmid, String username, String password) {
		this.cmid = cmid;
		this.username = username;
		this.password = password;
	}

	public int getCmid() {
		return cmid;
	}

	public void setCmid(int cmid) {
		this.cmid = cmid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "CustomerManager [cmid=" + cmid + ", password=" + password
				+ ", username=" + username + "]";
	}
}
