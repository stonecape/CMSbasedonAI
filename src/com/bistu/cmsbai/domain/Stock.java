package com.bistu.cmsbai.domain;

public class Stock {
	private int sid;
	private String profile;
	private int remainder;
	private String location;
	private String director;
	private int status;
	
	public Stock() {
		
	}

	public Stock(int sid, String profile, int remainder, String location,
			String director, int status) {
		super();
		this.sid = sid;
		this.profile = profile;
		this.remainder = remainder;
		this.location = location;
		this.director = director;
		this.status = status;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public int getRemainder() {
		return remainder;
	}

	public void setRemainder(int remainder) {
		this.remainder = remainder;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Stock [director=" + director + ", location=" + location
				+ ", profile=" + profile + ", remainder=" + remainder
				+ ", sid=" + sid + ", status=" + status + "]";
	}
	
}
