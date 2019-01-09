package com.zjht.adv.entity.base;

import java.util.Date;

import com.zjht.adv.entity.ApiChannel;
import com.zjht.adv.entity.User;

/**
 * ApiChannelService entity. @author MyEclipse Persistence Tools
 */

public class BaseApiChannelService implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2562086829988301539L;
	private Long id;
	private String serviceName;
	private String serviceDescr;
	private Integer status;
	private Date createTime;
	private Date updateTime;
	private String remarks;
	
	private ApiChannel apiChannel;
	private User user;

	// Constructors

	/** default constructor */
	public BaseApiChannelService() {
	}
	public BaseApiChannelService(Long id) {
		this.id=id;
	}
	/** full constructor */
	public BaseApiChannelService(String serviceName,
			String serviceDescr, Integer status, Date createTime,
			Date updateTime, Long userId, String remarks) {
		this.serviceName = serviceName;
		this.serviceDescr = serviceDescr;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.remarks = remarks;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceDescr() {
		return this.serviceDescr;
	}

	public void setServiceDescr(String serviceDescr) {
		this.serviceDescr = serviceDescr;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public ApiChannel getApiChannel() {
		return apiChannel;
	}

	public User getUser() {
		return user;
	}

	public void setApiChannel(ApiChannel apiChannel) {
		this.apiChannel = apiChannel;
	}

	public void setUser(User user) {
		this.user = user;
	}

}