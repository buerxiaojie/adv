package com.zjht.adv.hprose.client;

/**
 * 持卡人类
 * @author ZJHT
 * @createDate Dec 9, 2014 2:22:28 PM 
 * @file ContactorInfo.java 
 * @package com.zjht.hprose.oilNet 
 * @project epayGateway
 * @email chengw@gzjp.com
 * @version 1.0
 */
public class ContactorInfo {
	
	//持卡人姓名
	private String name;
	
	//持卡人身份证号
	private String ID;
	
	//开户行
	private String bankName;
	
	//银行卡号
	private String bankNo;
	
	//数量
	private Integer num;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
}
