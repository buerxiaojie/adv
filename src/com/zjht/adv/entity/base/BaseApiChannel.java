package com.zjht.adv.entity.base;

import java.util.Date;

import com.zjht.adv.entity.User;

/**
 * ApiChannel entity. @author MyEclipse Persistence Tools
 */

public class BaseApiChannel implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3552478849125387037L;
	private Long id;
	private String appNo;
	private String appName;
	private String appDescr;
	private String appAccount;
	private String appKey;
	private String encryptClassName;
	private String encryptType;
	private Integer status;
	private Date activeTime;
	private Date freezeTime;
	private Date createTime;
	private Date updateTime;
	private String remarks;
	private User user;
	
	private String appPassport;

	// Constructors

	/** default constructor */
	public BaseApiChannel() {
	}

	/** full constructor */
	/**
	 * 
	 * @param appNo 渠道编号
	 * @param appName 渠道名称
	 * @param appDescr 渠道描述
	 * @param appAccount 账号
	 * @param appKey 终端密钥
	 * @param encryptClassName 加密处理类(注：需要带包名)
	 * @param encryptType 签名加密类型(目前仅限MD5和SHA-1)
	 * @param status 状态(0,表示冻结，1表示正常，2表示永久冻结--系统不显示该数据)
	 * @param activeTime 生效时间
	 * @param freezeTime 冻结时间
	 * @param createTime 创建时间
	 * @param updateTime 更新时间
	 * @param remarks 备注
	 */
	public BaseApiChannel(String appNo, String appName, String appDescr,
			String appAccount, String appKey, String encryptClassName,
			String encryptType, Integer status, Date activeTime,
			Date freezeTime, Date createTime, Date updateTime,
			String remarks) {
		this.appNo = appNo;
		this.appName = appName;
		this.appDescr = appDescr;
		this.appAccount = appAccount;
		this.appKey = appKey;
		this.encryptClassName = encryptClassName;
		this.encryptType = encryptType;
		this.status = status;
		this.activeTime = activeTime;
		this.freezeTime = freezeTime;
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

	public String getAppNo() {
		return this.appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getAppName() {
		return this.appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppDescr() {
		return this.appDescr;
	}

	public void setAppDescr(String appDescr) {
		this.appDescr = appDescr;
	}

	public String getAppAccount() {
		return this.appAccount;
	}

	public void setAppAccount(String appAccount) {
		this.appAccount = appAccount;
	}

	public String getAppKey() {
		return this.appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getEncryptClassName() {
		return this.encryptClassName;
	}

	public void setEncryptClassName(String encryptClassName) {
		this.encryptClassName = encryptClassName;
	}

	public String getEncryptType() {
		return encryptType;
	}

	public void setEncryptType(String encryptType) {
		this.encryptType = encryptType;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getActiveTime() {
		return this.activeTime;
	}

	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}

	public Date getFreezeTime() {
		return this.freezeTime;
	}

	public void setFreezeTime(Date freezeTime) {
		this.freezeTime = freezeTime;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAppPassport() {
		return appPassport;
	}

	public void setAppPassport(String appPassport) {
		this.appPassport = appPassport;
	}

}