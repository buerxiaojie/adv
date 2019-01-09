package com.zjht.adv.entity;


import java.util.Date;

import com.zjht.adv.entity.base.BaseAdvPosition;

/**
 * AdvPosition entity. @author MyEclipse Persistence Tools
 */

public class AdvPosition extends BaseAdvPosition {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2382475043550628806L;

	/**
	 * 
	 */
	public AdvPosition() {
		super();
	}

	/**
	 * @param id
	 */
	public AdvPosition(Long id) {
		super(id);
	}

	/**
	 * @param name
	 * @param createTime
	 * @param updateTime
	 * @param status
	 * @param remarks
	 */
	public AdvPosition(String name, Date createTime, Date updateTime,
			Integer status, String remarks) {
		super(name, createTime, updateTime, status, remarks);
	}
	/**
	 * 在用
	 */
	public static final int STATUS_ABLE=1;
	/**
	 * 禁用
	 */
	public static final int STATUS_DISABLE=0;
	
	public String getStatusStr(){
		if (getStatus()==null) {
			return "未知状态";
		}
		if (getStatus().intValue()==STATUS_ABLE) {
			return "<font color='green'>在用</font>";
		}else{
			return "<font color='red'>禁用</font>";
		}
	}

}