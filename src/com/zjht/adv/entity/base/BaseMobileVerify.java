package com.zjht.adv.entity.base;

// default package

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 */

public abstract class BaseMobileVerify implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3406115843160557800L;

	private Long id;

	private String mobile;

	private String code;

	private String content;

	private Integer status;

	private Date sendTime;

	private String ipAddr;

	// Constructors

	/** default constructor */
	public BaseMobileVerify() {
	}

	/** full constructor */
	public BaseMobileVerify(String mobile, String code, String content, Integer status, Timestamp sendTime) {
		this.mobile = mobile;
		this.code = code;
		this.content = content;
		this.status = status;
		this.sendTime = sendTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

}