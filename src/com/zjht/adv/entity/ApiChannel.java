package com.zjht.adv.entity;


import java.util.Date;

import com.zjht.adv.entity.base.BaseApiChannel;

/**
 * ApiChannel entity. @author MyEclipse Persistence Tools
 */

public class ApiChannel extends BaseApiChannel {

	/**
	 * 
	 */
	public ApiChannel() {
		super();
	}

	/**
	 * @param appNo
	 * @param appName
	 * @param appDescr
	 * @param appAccount
	 * @param appKey
	 * @param encryptClassName
	 * @param encryptType
	 * @param status
	 * @param activeTime
	 * @param freezeTime
	 * @param createTime
	 * @param updateTime
	 * @param remarks
	 */
	public ApiChannel(String appNo, String appName, String appDescr,
			String appAccount, String appKey, String encryptClassName,
			String encryptType, Integer status, Date activeTime,
			Date freezeTime, Date createTime, Date updateTime, String remarks) {
		super(appNo, appName, appDescr, appAccount, appKey, encryptClassName,
				encryptType, status, activeTime, freezeTime, createTime, updateTime,
				remarks);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2651286511599079913L;

	//状态(0,表示冻结，1表示正常，2表示永久冻结--系统不显示该数据)
	/**
	 * 冻结
	 */
	public static final int STATUS_FREEZE=0;
	/**
	 * 正常
	 */
	public static final int STATUS_NORMAL=1;
	/**
	 * 永久冻结（不再显示）
	 */
	public static final int STATUS_FREEZE_EVER=2;
	
	public String getStatusStr(){
		String reVal="未知状态";
		Integer s=getStatus();
		if (s!=null) {
			switch (s.intValue()) {
			case STATUS_FREEZE:
				reVal="冻结";
				break;
			case STATUS_NORMAL:
				reVal="正常";
				break;
			case STATUS_FREEZE_EVER:
				reVal="永久冻结";
				break;
			default:
				break;
			}
		}
		return reVal;
	}
	
}