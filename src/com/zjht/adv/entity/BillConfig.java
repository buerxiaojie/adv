package com.zjht.adv.entity;

import java.io.Serializable;

public class BillConfig implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1642195671304154886L;

	private Integer buildSize;//生成记录数
	
	private String minMoney;//最小订单金额
	
	private String maxMoney;//最大订单金额
	
	private String orderPrefix;//订单前缀
	
	private String merchantPrefix;//商户号前缀
	
	private Long time;//随机时间戳访问

	public Integer getBuildSize() {
		return buildSize;
	}

	public void setBuildSize(Integer buildSize) {
		this.buildSize = buildSize;
	}

	public String getMinMoney() {
		return minMoney;
	}

	public void setMinMoney(String minMoney) {
		this.minMoney = minMoney;
	}

	public String getMaxMoney() {
		return maxMoney;
	}

	public void setMaxMoney(String maxMoney) {
		this.maxMoney = maxMoney;
	}

	public String getOrderPrefix() {
		return orderPrefix;
	}

	public void setOrderPrefix(String orderPrefix) {
		this.orderPrefix = orderPrefix;
	}

	public String getMerchantPrefix() {
		return merchantPrefix;
	}

	public void setMerchantPrefix(String merchantPrefix) {
		this.merchantPrefix = merchantPrefix;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}
	
}
