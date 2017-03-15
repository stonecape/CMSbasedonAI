package com.bistu.cmsbai.domain;

import java.util.Date;

public class Feedback {
	private int fid;
	private int cid;
	private Date commitDate;
	private int quality;
	private int service;
	private int price;
	private int ontime;
	
	public Feedback() {
		
	}

	public Feedback(int fid, int cid, Date commitDate, int quality,
			int service, int price, int ontime) {
		this.fid = fid;
		this.cid = cid;
		this.commitDate = commitDate;
		this.quality = quality;
		this.service = service;
		this.price = price;
		this.ontime = ontime;
	}
	
	public Feedback(int cid, Date commitDate, int quality, int service,
			int price, int ontime) {
		this.fid = -1;
		this.cid = cid;
		this.commitDate = commitDate;
		this.quality = quality;
		this.service = service;
		this.price = price;
		this.ontime = ontime;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public Date getCommitDate() {
		return commitDate;
	}

	public void setCommitDate(Date commitDate) {
		this.commitDate = commitDate;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public int getService() {
		return service;
	}

	public void setService(int service) {
		this.service = service;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getOntime() {
		return ontime;
	}

	public void setOntime(int ontime) {
		this.ontime = ontime;
	}

	@Override
	public String toString() {
		return "Feedback [cid=" + cid + ", commitDate=" + commitDate + ", fid="
				+ fid + ", ontime=" + ontime + ", price=" + price
				+ ", quality=" + quality + ", service=" + service + "]";
	}
	
}
