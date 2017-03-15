package com.bistu.cmsbai.domain;

public class Client {
	private int cid;
	private String companyName;
	private String address;
	private int credit;
	private String linkName;
	private String telePhone;
	private String email;
	private int starLevel;
	
	public Client() {
	
	}

	public Client(int cid, String companyName, String address, int credit,
			String linkName, String telePhone, String email, int starLevel) {
		this.cid = cid;
		this.companyName = companyName;
		this.address = address;
		this.credit = credit;
		this.linkName = linkName;
		this.telePhone = telePhone;
		this.email = email;
		this.starLevel = starLevel;
	}

	public Client(String companyName, String address, int credit,
			String linkName, String telePhone, String email) {
		this.cid = -1;
		this.companyName = companyName;
		this.address = address;
		this.credit = credit;
		this.linkName = linkName;
		this.telePhone = telePhone;
		this.email = email;
		this.starLevel = 0;
	}
	
	public int getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(int starLevel) {
		this.starLevel = starLevel;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "ClientInfo [address=" + address + ", cid=" + cid
				+ ", companyName=" + companyName + ", credit=" + credit
				+ ", email=" + email + ", linkName=" + linkName
				+ ", starLevel=" + starLevel + ", telePhone=" + telePhone + "]";
	}
	
}
