package com.zjht.adv.entity.base;

import java.util.Date;

import com.zjht.adv.entity.User;

/**
 * AdvPosition entity. @author MyEclipse Persistence Tools
 */

public class BaseAdvPosition implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7632512023339033871L;
	private Long id;
	private String name;
	private Date createTime;
	private Date updateTime;
	private Integer status;
	private String remarks;
	
	private User user;

	// Constructors

	/** default constructor */
	public BaseAdvPosition() {
	}

	public BaseAdvPosition(Long id) {
		this.id=id;
	}
	
	/** full constructor */
	public BaseAdvPosition(String name, Date createTime, Date updateTime,
			Integer status, String remarks) {
		this.name = name;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.status = status;
		this.remarks = remarks;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}