package com.bistu.cmsbai.domain;

import java.util.Date;

public class Order {
	private int oid;
	private int cid;
	private int sid;
	private int unitPrice;
	private int amount;
	private double discount;
	private Date commitDate;
	
	public Order() {
		
	}

	public Order(int oid, int cid, int sid, int unitPrice, int amount,
			double discount, Date commitDate) {
		this.oid = oid;
		this.cid = cid;
		this.sid = sid;
		this.unitPrice = unitPrice;
		this.amount = amount;
		this.discount = discount;
		this.commitDate = commitDate;
	}
	

	public Order(int cid, int sid, int unitPrice, int amount, double discount,
			Date commitDate) {
		super();
		this.oid = -1;
		this.cid = cid;
		this.sid = sid;
		this.unitPrice = unitPrice;
		this.amount = amount;
		this.discount = discount;
		this.commitDate = commitDate;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
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
		return "Order [amount=" + amount + ", cid=" + cid + ", commitDate="
				+ commitDate + ", discount=" + discount + ", oid=" + oid
				+ ", sid=" + sid + ", unitPrice=" + unitPrice + "]";
	}

}
