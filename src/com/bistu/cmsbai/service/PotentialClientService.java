package com.bistu.cmsbai.service;

import java.util.ArrayList;
import java.util.List;

import com.bistu.cmsbai.dao.ClientDAO;
import com.bistu.cmsbai.dao.FeedbackDAO;
import com.bistu.cmsbai.dao.OrderDAO;
import com.bistu.cmsbai.domain.Client;
import com.bistu.cmsbai.domain.Feedback;
import com.bistu.cmsbai.domain.Order;
import com.bistu.cmsbai.domain.ShowPotentialClient;
import com.bistu.cmsbai.exception.ClientException;
import com.bistu.cmsbai.exception.FeedbackException;
import com.bistu.cmsbai.exception.OrderException;

public class PotentialClientService {
	private ClientDAO clientDAO;
	private OrderDAO orderDAO;
	private FeedbackDAO feedbackDAO;
	
	private List<Client> allClient;
	private List<Order> allOrder;
	private List<Feedback> allFeedback;
	
	public void setClientDAO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}
	
	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}
	
	public void setFeedbackDAO(FeedbackDAO feedbackDAO) {
		this.feedbackDAO = feedbackDAO;
	}
	
	public PotentialClientService() {
		
	}
	
	//根据cid返回累计订单数和累计金额
	private double[] calAccumulateOrderCountAndSumByCid(int cid) {
		double[] result = new double[2];
		double count = 0.0;
		double sum = 0.0;
		for(Order order : allOrder) {
			if(order.getCid() == cid) {
				count++;
				sum += (double)(order.getUnitPrice() * order.getAmount()) * order.getDiscount();
			}
		}
		result[0] = count;
		result[1] = sum;
		return result;
	}
	
	//根据cid返回客户满意度
	private double calClientStatisfactionByCid(int cid) {
		double result = 0.0;
		int count = 0;
		for(Feedback feedback : allFeedback) {
			if(feedback.getCid() == cid) {
				count++;
				double tempSatisfaction = (double)feedback.getQuality() * 0.4 + (double)feedback.getService() * 0.3 +
										(double)feedback.getOntime() * 0.2 + (double)feedback.getPrice() * 0.1;
				result += tempSatisfaction;
			}
		}
		return result / (double)count;
	}
	
	public double getClientStatisfactionByCid(int cid) throws ClientException, FeedbackException, OrderException {
		allClient = clientDAO.getAllClients();
		allOrder = orderDAO.getAllOrders();
		allFeedback = feedbackDAO.getAllFeedbacks();
		return calClientStatisfactionByCid(cid);
	}
	
	//根据算法选择，返回客户推荐星级
	private int calRecommendStarLevelByOptions(int accumulateOrderCount, double accumulateSum, 
			double clientStatisfaction, double clientCredit, String algorithm) {
		if(algorithm.equals("id3")) {
			if(accumulateOrderCount == 0 || accumulateSum == 0 || clientStatisfaction == 0 || clientCredit == 0) {
				return 0;
			}
			
			if(accumulateSum > 150000) {
				if(clientCredit > 80) {
					if(clientStatisfaction > 50) {
						return 5;
					} else {
						return 3;
					}
				} else {
					return 3;
				}
			} else if(accumulateSum > 80000) {
				if(clientStatisfaction > 80) {
					if(accumulateOrderCount >15) {
						return 5;
					} else {
						return 3;
					}
				} else {
					return 1;
				}
			} else {
				if(clientStatisfaction > 80) {
					if(clientCredit > 80) {
						return 3;
					} else {
						return 1;
					}
				} else {
					return 1;
				}
			}
		}else if(algorithm.equals("j48")){
			if(accumulateOrderCount == 0 || accumulateSum == 0 || clientStatisfaction == 0 || clientCredit == 0) {
				return 0;
			}
			
			if(accumulateSum > 150000) {
				if(clientCredit > 80) {
					return 5;
				} else {
					return 3;
				}
			} else if(accumulateSum > 80000) {
				return 3;
			} else {
				if(clientCredit > 80) {
					if(clientStatisfaction > 50) {
						return 3;
					} else {
						return 1;
					}
				} else {
					return 1;
				}
			}
		}
		return 0;
	}
	
	//默认传参id3
	public List<ShowPotentialClient> getShowPotenticalClientList() throws OrderException, FeedbackException, ClientException {
		return getShowPotenticalClientList("id3");
	}
	
	//根据算法选择，获取最优客户管理的列表
	public List<ShowPotentialClient> getShowPotenticalClientList(String algorithm) throws OrderException, FeedbackException, ClientException {
		allClient = clientDAO.getAllClients();
		allOrder = orderDAO.getAllOrders();
		allFeedback = feedbackDAO.getAllFeedbacks();
		List<ShowPotentialClient> showPotentialClients = new ArrayList<ShowPotentialClient>();
		
		for(Client client : allClient) {
			ShowPotentialClient spc = new ShowPotentialClient(client.getCid(), client.getCompanyName(), client.getCredit(), client.getStarLevel());
			
			double[] accumulateOrderCountAndSum = calAccumulateOrderCountAndSumByCid(client.getCid());
			double clientSatisfaction = calClientStatisfactionByCid(client.getCid());
			int recommendStarLevel = calRecommendStarLevelByOptions((int)accumulateOrderCountAndSum[0], 
					accumulateOrderCountAndSum[1], clientSatisfaction, client.getCredit(), algorithm);
			
			spc.setAccumulateOrderCount((int)accumulateOrderCountAndSum[0]);
			spc.setAccumulateSum(accumulateOrderCountAndSum[1]);
			spc.setClientStatisfaction(clientSatisfaction);
			spc.setRecommendStarLevel(recommendStarLevel);
			
			int tempStarLevel = client.getStarLevel();
			if(recommendStarLevel == tempStarLevel) {
				spc.setStarTrend("flat");
			} else if(recommendStarLevel > tempStarLevel) {
				spc.setStarTrend("up");
			} else {
				spc.setStarTrend("down");
			}
			showPotentialClients.add(spc);
		}
		return showPotentialClients;
	}
	
	//根据cid和修改的starLevel进行更新
	public Client modifyStarLevelByCid(int cid, int modifiedStarlevel) throws ClientException {
		return clientDAO.modifyStarLevelByCid(cid, modifiedStarlevel);
	}
}
