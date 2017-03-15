package com.bistu.cmsbai.domain;

import java.io.Serializable;
import java.util.Date;

public class ShowOrder implements Serializable{
	private static final long serialVersionUID = -5232878144301742878L;
	
	private int oid;
	private int cid;
	private int sid;
	private String companyName;
	private String profile;
	private int unitPrice;
	private int amount;
	private double discount;
	private Date commitDate;

	public ShowOrder() {

	}
	
	
	public ShowOrder(int oid, int cid, int sid, String companyName,
			String profile, int unitPrice, int amount, double discount,
			Date commitDate) {
		this.oid = oid;
		this.cid = cid;
		this.sid = sid;
		this.companyName = companyName;
		this.profile = profile;
		this.unitPrice = unitPrice;
		this.amount = amount;
		this.discount = discount;
		this.commitDate = commitDate;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Date getCommitDate() {
		return commitDate;
	}

	public void setCommitDate(Date commitDate) {
		this.commitDate = commitDate;
	}


	@Override
	public String toString() {
		return "ShowOrder [amount=" + amount + ", cid=" + cid + ", commitDate="
				+ commitDate + ", companyName=" + companyName + ", discount="
				+ discount + ", oid=" + oid + ", profile=" + profile + ", sid="
				+ sid + ", unitPrice=" + unitPrice + "]";
	}

}
