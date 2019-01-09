package com.zjht.adv.entity;

// default package

import java.sql.Timestamp;

import com.zjht.adv.entity.base.BaseMobileVerify;


/**
 * 
 */
public class MobileVerify extends BaseMobileVerify implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 发送成功
	 */
	public static final int SENDSTATUS_SUCCESS = 1;
	/**
	 * 发送失败
	 */
	public static final int SENDSTATUS_FAILED = 2;

	// Constructors

	/** default constructor */
	public MobileVerify() {
	}

	/** full constructor */
	public MobileVerify(String mobile, String code, String content, Integer status, Timestamp sendTime) {
		super(mobile, code, content, status, sendTime);
	}
}
