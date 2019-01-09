package com.zjht.adv.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.jasson.im.api.APIClient;

/**
 * @author lijunjie
 * 
 */
public class SmsSenderUtil implements InitializingBean, DisposableBean {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private APIClient apiClient;
	private String dbIP = PropertyUtil.getPropertyValue("sms", "sms.api.dbIP");
	private String dbUser = PropertyUtil.getPropertyValue("sms", "sms.api.dbUser");
	private String dbPwd = PropertyUtil.getPropertyValue("sms", "sms.api.dbPwd");
	private String apiCode = PropertyUtil.getPropertyValue("sms", "sms.api.apiCode");
	private String dbName = PropertyUtil.getPropertyValue("sms", "sms.api.dbName");

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		initApiClient();
	}

	private void initApiClient() {
		try {
			if (apiClient != null)
				apiClient.release();
		} catch (Exception e) {
			// ignore error here
		}
		apiClient = new APIClient();
		if (apiClient.init(dbIP, dbUser, dbPwd, apiCode, dbName) == 0) {
			logger.info("init sms client successfully");
		} else {
			logger.warn("initialize sms client failed");
		}
	}

	public int send(String mobile, String content, long smID) {
		int i = -1;
		try {
			i = apiClient.sendSM(mobile, content, smID);
			if (DateTimeUtils.getCurrentDate().getTime()<DateTimeUtils.formatDate("2014-11-01 0:00:00", "yyyy-MM-dd HH:mm:ss").getTime()) {
				logger.info("调用发送短信接口，手机号码："+mobile+"，短信内容"+content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (i != 0) {
			initApiClient();
			i = apiClient.sendSM(mobile, content, smID);
		}
		return i;
	}

	public String getDbIP() {
		return dbIP;
	}

	public void setDbIP(String dbIP) {
		this.dbIP = dbIP;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPwd() {
		return dbPwd;
	}

	public void setDbPwd(String dbPwd) {
		this.dbPwd = dbPwd;
	}

	public String getApiCode() {
		return apiCode;
	}

	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	@Override
	public void destroy() throws Exception {
		try {
			if (apiClient != null)
				apiClient.release();
		} catch (Exception e) {
			// ignore error here
		}
	}
}
