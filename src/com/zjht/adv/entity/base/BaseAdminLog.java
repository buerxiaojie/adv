package com.zjht.adv.entity.base;

import java.util.Date;

import com.zjht.adv.entity.User;

/**
 * AdminLog entity. @author MyEclipse Persistence Tools
 */

public class BaseAdminLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -9193552567016576594L;
	private Long id;
	private String details;
	private Integer type;
	private Integer result;
	private Date time;

	private User user;

	// Constructors

	/** default constructor */
	public BaseAdminLog() {
	}

	/** full constructor */
	public BaseAdminLog(String details, Integer type, Integer result, Date time) {
		this.details = details;
		this.type = type;
		this.result = result;
		this.time = time;
	}

	// Property accessors

	public User getUser() {
		return user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void setUser(User user) {
		this.user = user;
	}

}