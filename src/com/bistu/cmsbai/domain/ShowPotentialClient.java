package com.bistu.cmsbai.domain;

public class ShowPotentialClient {
	private int cid;
	private String companyName;
	private int accumulateOrderCount;
	private double accumulateSum;
	private double clientStatisfaction;
	private int clientCredit;
	private int clientStarLevel;
	private int recommendStarLevel;
	private String starTrend;
	
	
	public ShowPotentialClient() {
		
	}

	
	public ShowPotentialClient(int cid, String companyName,
			int accumulateOrderCount, double accumulateSum,
			double clientStatisfaction, int clientCredit, int clientStarLevel,
			int recommendStarLevel, String starTrend) {
		this.cid = cid;
		this.companyName = companyName;
		this.accumulateOrderCount = accumulateOrderCount;
		this.accumulateSum = accumulateSum;
		this.clientStatisfaction = clientStatisfaction;
		this.clientCredit = clientCredit;
		this.clientStarLevel = clientStarLevel;
		this.recommendStarLevel = recommendStarLevel;
		this.starTrend = starTrend;
	}


	public ShowPotentialClient(int cid, String companyName, int clientCredit,
			int clientStarLevel) {
		super();
		this.cid = cid;
		this.companyName = companyName;
		this.clientCredit = clientCredit;
		this.clientStarLevel = clientStarLevel;
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
	public int getAccumulateOrderCount() {
		return accumulateOrderCount;
	}
	public void setAccumulateOrderCount(int accumulateOrderCount) {
		this.accumulateOrderCount = accumulateOrderCount;
	}
	public double getAccumulateSum() {
		return accumulateSum;
	}
	public void setAccumulateSum(double accumulateSum) {
		this.accumulateSum = accumulateSum;
	}
	public double getClientStatisfaction() {
		return clientStatisfaction;
	}
	public void setClientStatisfaction(double clientStatisfaction) {
		this.clientStatisfaction = clientStatisfaction;
	}
	public int getClientCredit() {
		return clientCredit;
	}
	public void setClientCredit(int clientCredit) {
		this.clientCredit = clientCredit;
	}
	public int getClientStarLevel() {
		return clientStarLevel;
	}
	public void setClientStarLevel(int clientStarLevel) {
		this.clientStarLevel = clientStarLevel;
	}
	public int getRecommendStarLevel() {
		return recommendStarLevel;
	}
	public void setRecommendStarLevel(int recommendStarLevel) {
		this.recommendStarLevel = recommendStarLevel;
	}
	public String getStarTrend() {
		return starTrend;
	}
	public void setStarTrend(String starTrend) {
		this.starTrend = starTrend;
	}

	@Override
	public String toString() {
		return "ShowPotentialClient [accumulateOrderCount="
				+ accumulateOrderCount + ", accumulateSum=" + accumulateSum
				+ ", cid=" + cid + ", clientCredit=" + clientCredit
				+ ", clientStarLevel=" + clientStarLevel
				+ ", clientStatisfaction=" + clientStatisfaction
				+ ", companyName=" + companyName + ", recommendStarLevel="
				+ recommendStarLevel + ", starTrend=" + starTrend + "]";
	}

}
