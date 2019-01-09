package com.zjht.adv.entity;


import java.util.Date;

import com.zjht.adv.entity.base.BaseApiChannelService;

/**
 * ApiChannelService entity. @author MyEclipse Persistence Tools
 */

public class ApiChannelService extends BaseApiChannelService {

	// Fields

	/**
	 * 
	 */
	public ApiChannelService() {
		super();
	}

	/**
	 * @param id
	 */
	public ApiChannelService(Long id) {
		super(id);
	}

	/**
	 * @param serviceName
	 * @param serviceDescr
	 * @param status
	 * @param createTime
	 * @param updateTime
	 * @param userId
	 * @param remarks
	 */
	public ApiChannelService(String serviceName, String serviceDescr,
			Integer status, Date createTime, Date updateTime, Long userId,
			String remarks) {
		super(serviceName, serviceDescr, status, createTime, updateTime, userId,
				remarks);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7456652909728858129L;

	//状态（0表示停用，1表示在用）
	/**
	 * 禁用
	 */
	public static final int STATUS_ENABLE=0;
	/**
	 * 正常
	 */
	public static final int STATUS_ABLE=1;
	
	public String getStatusStr(){
		String reVal="未知状态";
		Integer s=getStatus();
		if (s!=null) {
			switch (s.intValue()) {
			case STATUS_ENABLE:
				reVal="禁用";
				break;
			case STATUS_ABLE:
				reVal="正常";
				break;
			default:
				break;
			}
		}
		return reVal;
	}
}